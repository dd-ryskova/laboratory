package ru.ssau.tk.DontCry.laboratory.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.pow;
import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{1, 4, 9, 16, 25};
    private final Object object = new Object();

    private final double DELTA = 0.0001;

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), object);
    }

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getCount(), 5, DELTA);
        assertEquals(synchronizedArray.getCount(), 5, DELTA);
    }

    @Test
    public void testGetX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        for (int i = 0; i < synchronizedTabulatedFunction.getCount(); ++i) {
            assertEquals(synchronizedTabulatedFunction.getX(i), i + 1, DELTA);
        }

        for (int i = 0; i < synchronizedTabulatedFunction.getCount(); ++i) {
            assertEquals(synchronizedArray.getX(i), i + 1, DELTA);
        }
    }

    @Test
    public void testGetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        for (int i = 0; i < synchronizedTabulatedFunction.getCount(); ++i) {
            assertEquals(synchronizedTabulatedFunction.getY(i), pow((i + 1), 2), DELTA);
        }

        for (int i = 0; i < synchronizedArray.getCount(); ++i) {
            assertEquals(synchronizedArray.getY(i), pow((i + 1), 2), DELTA);
        }
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        synchronizedTabulatedFunction.setY(0, 36);
        assertEquals(synchronizedTabulatedFunction.getY(0), 36, DELTA);
        synchronizedTabulatedFunction.setY(1, 49);
        assertEquals(synchronizedTabulatedFunction.getY(1), 49, DELTA);


        synchronizedArray.setY(2, 64);
        assertEquals(synchronizedArray.getY(2), 64, DELTA);
        synchronizedArray.setY(3, 81);
        assertEquals(synchronizedArray.getY(3), 81, DELTA);
    }

    @Test
    public void testIndexOfX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        for (int i = 0; i < synchronizedTabulatedFunction.getCount(); ++i) {
            assertEquals(synchronizedTabulatedFunction.indexOfX(i + 1), i, DELTA);
        }

        for (int i = 0; i < synchronizedArray.getCount(); ++i) {
            assertEquals(synchronizedArray.indexOfX(i + 1), i, DELTA);
        }
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        for (int i = 0; i < synchronizedTabulatedFunction.getCount(); ++i) {
            assertEquals(synchronizedTabulatedFunction.indexOfY(pow((i + 1), 2)), i, DELTA);
        }

        for (int i = 0; i < synchronizedArray.getCount(); ++i) {
            assertEquals(synchronizedArray.indexOfY(pow((i + 1), 2)), i, DELTA);
        }
    }

    @Test
    public void testLeftBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.leftBound(), 1, DELTA);
        assertEquals(synchronizedArray.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.rightBound(), 5, DELTA);
        assertEquals(synchronizedArray.rightBound(), 5, DELTA);
    }

    @Test
    public void testApply() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.apply(3), 9, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(7), 43, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(0), -2, DELTA);
    }

    @Test
    public void testIteratorWhile() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        Iterator<Point> ListIterator = synchronizedTabulatedFunction.iterator();

        int i = 0;
        while (ListIterator.hasNext()) {
            Point myPoint = ListIterator.next();
            assertEquals(synchronizedTabulatedFunction.getX(i), myPoint.x);
            assertEquals(synchronizedTabulatedFunction.getY(i++), myPoint.y);
        }
        assertEquals(synchronizedTabulatedFunction.getCount(), i);

        assertThrows(NoSuchElementException.class, ListIterator::next);
    }

    @Test
    public void testIteratorForEach() {
        SynchronizedTabulatedFunction synchronizedArray = getSynchronizedArray();

        int i = 0;
        for (Point point : synchronizedArray) {
            assertEquals(point.x, synchronizedArray.getX(i), DELTA);
            assertEquals(point.y, synchronizedArray.getY(i++), DELTA);
        }
        assertEquals(synchronizedArray.getCount(), i);
    }

    @Test
    public void testDoSynchronously() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals((int) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount), 5);
        assertEquals(synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::rightBound), 5.);
    }
}