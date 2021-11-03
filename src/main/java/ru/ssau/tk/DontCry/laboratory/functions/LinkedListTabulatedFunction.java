package ru.ssau.tk.DontCry.laboratory.functions;

import ru.ssau.tk.DontCry.laboratory.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {

    private static final long serialVersionUID = 3178930916011714034L;

    private Node head;
    private int count = 0;

    protected static class Node implements Serializable {
        private static final long serialVersionUID = 4266961572292281669L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Длина массива меньше минимальной!");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Неправильные значения входных параметров!");
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            last.next = newNode;
        }
        head.prev = newNode;
        count++;
    }

    private Node getNode(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        Node indexNode;
        if (index < count / 2.) {
            indexNode = head;
            for (int i = 0; i <= count / 2.; i++) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i >= count / 2.; i--) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.prev;
                }
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        return Objects.requireNonNull(getNode(index)).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("Некорректный индекс!");
        }
        return Objects.requireNonNull(getNode(index)).y;
    }

    @Override
    public void setY(int index, double value) {
        Objects.requireNonNull(getNode(index)).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("Х меньше левой границы!");
        }
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    private Node floorNodeOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X меньше левой границы в списке!");
        }
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x <= x) {
                indexNode = indexNode.next;
            } else {
                return indexNode.prev;
            }
        }
        return head.prev;
    }

    @Override
    public double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    public double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        assert leftNode != null;
        Node rightNode = leftNode.next;
        if (x < leftNode.x || x > rightNode.x) {
            throw new InterpolationException("X за пределами интерполяции!");
        }
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        }
        Node node = floorNodeOfX(x);
        return interpolate(x, node.x, node.next.x, node.y, node.next.y);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }
}
