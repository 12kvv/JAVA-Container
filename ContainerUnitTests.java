package com.korneeva.lab1;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing the Container implementation
 * @author vilak
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ContainerUnitTests {
	/**
	 *  Default constructor
	 */
	public ContainerUnitTests() {}
	/**
	 * Instance of a container
	 */
    private Container < String > container;

    /**
     * Creating a new empty container for each test
     */
    @BeforeEach
    public void setUp() {
        container = new Container < String > ();
    }

    /**
     * Checking if a new container's elements count equals to zero
     */
    @Test
    public void testContainerInit() {
        assertTrue(container.getElementsCount() == 0);
    }

    /**
     * Checking if a constructor throws an exception when negative size is given 
     */
    @Test
    public void testInvalidCapacity() {
        assertThrows(NegativeArraySizeException.class, () -> {
            container = new Container < String > (-1);
        });
    }

    /**
     * Checking if insertElement() method inserts elements correctly
     */
    @Test
    public void testAddElements() {
        container.insertElement("zero");
        container.insertElement("one");
        container.insertElement("two");

        assertEquals("zero", container.getElementAt(0));
        assertEquals("one", container.getElementAt(1));
        assertEquals("two", container.getElementAt(2));

        container.insertElement("three");

        assertEquals("zero", container.getElementAt(0));
        assertEquals("one", container.getElementAt(1));
        assertEquals("two", container.getElementAt(2));
        assertEquals("three", container.getElementAt(3));

        assertTrue(container.getElementsCount() == 4);
    }

    /**
     * Checking if insertElement() method throws an exception when a null element is given
     */
    @Test
    public void testAddElementNull() {
        assertThrows(NullPointerException.class, () -> {
            container.insertElement(null);
        });

    }

    /**
     * Checking if setElementAt() method throws an exception when a null element is given
     */
    @Test
    public void testSetElementNull() {
        assertThrows(NullPointerException.class, () -> {
            container.insertElement("notNull");
            container.setElementAt(0, null);
        });
    }

    /**
     * Checking if setElement() method sets the element correctly
     */
    @Test
    public void testSetElement() {
        container.insertElement("zero");
        container.insertElement("one");
        container.insertElement("two");

        container.setElementAt(1, "newOne");

        assertEquals("zero", container.getElementAt(0));
        assertEquals("newOne", container.getElementAt(1));
        assertEquals("two", container.getElementAt(2));
    }

    /**
     * Checking if removeElementAt() method removes the element correctly
     */
    @Test
    public void testRemoveElementAt() {
        container.insertElement("zero");
        container.insertElement("one");
        container.insertElement("two");

        assertEquals("one", container.removeElementAt(1));
        assertTrue(container.getElementsCount() == 2);
        assertEquals("zero", container.getElementAt(0));
        assertEquals("two", container.getElementAt(1));
    }

    /**
     * Checking if removeElementAt() method throws an exception when given a negative index
     */
    @Test
    public void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.removeElementAt(-1);
        });
    }

    /**
     * Checking if ensureCapacity() enlarges the container correctly and does not remove any elements
     */
    @Test
    public void testResizingContainer() {
        for (int i = 0; i <= Container.DEFAULT_SIZE; i++) {
            container.insertElement(String.valueOf(i));
        }
        for (int i = 0; i <= Container.DEFAULT_SIZE; i++) {
            assertEquals(String.valueOf(i), container.getElementAt(i));
        }
    }
}