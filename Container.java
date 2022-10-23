package com.korneeva.lab1;

import java.util.Arrays;

/**
 * Container implementation
 * @author vilak
 * @param <T> This is the type parameter
 */
 
public class Container < T > {
	/**
	 * Default size
	 */
	public static final int DEFAULT_SIZE = 10;
	/**
	 * Container size
	 */
    private int size;
    
    /**
     * Container elements count
     */
    private int elementsCount;
    
    /**
     * Array of container elements
     */
    private T[] elements;

    /**
     * Clears the container and sets elements count to 0, container size does not change
     */
    public void clear() {
        for (T element: this.elements) {
            element = null;
        }
        this.elementsCount = 0;
    }

    /**
     * Returns the size of a container
     * @return the size of a container
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the count of elements in a container
     * @return the count of elements in a container
     */
    public int getElementsCount() {
        return this.elementsCount;
    }

    /**
     * Returns the element at given index
     * @param index index of an element to get value from
     * @return the element at given index
     * @throws IndexOutOfBoundsException If the given index is not in a current container's bounds
     */
    public T getElementAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.getElementsCount() - 1)
            throw new IndexOutOfBoundsException();
        else
            return elements[index];
    }

    /**
     * Sets the size of a container
     * @param newContainerSize the size of a container to be set
     */
    private void setSize(int newContainerSize) {
        this.size = newContainerSize;
    }

    /**
     * Sets the count of elements of a container
     * @param newContainerElementsCount the count of elements of a container to be set
     */
    private void setElementsCount(int newContainerElementsCount) {
        this.elementsCount = newContainerElementsCount;
    }

    /**
     * Sets the count of elements of a container
     * @param index the index of an element to be changed
     * @param newValue the new value of an element to be changed
     * @throws NullPointerException If a new value is null
     */
    public void setElementAt(int index, T newValue) throws NullPointerException {
        if (newValue == null)
            throw new NullPointerException();
        else
            this.elements[index] = newValue;
    }

    /**
     * Ensures if the container can hold one more element
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (this.elementsCount == this.size) {
            this.setSize(this.size * 2);
            Container < T > newContainer = new Container < T > (this.size);
            System.arraycopy(this.elements, 0, newContainer.elements, 0, this.size / 2);
            this.elements = (T[]) new Object[this.size];
            System.arraycopy(newContainer.elements, 0, this.elements, 0, this.size / 2);
        }
    }

    /**
     * Inserts a new element to the end of the container
     * @param newValue the value of an element to be added to the end of the container
     * @throws NullPointerException If a new value in null
     */
    public void insertElement(T newValue) throws NullPointerException {
        if (newValue == null)
            throw new NullPointerException();
        else {
            ensureCapacity();
            setElementAt(this.getElementsCount(), newValue);
            setElementsCount(this.getElementsCount() + 1);
        }
    }

    /**
     * Removes the element at given index, rebuilds the container without this element
     * @param index the index of an element to remove
     * @return the value of the removed element
     * @throws IndexOutOfBoundsException If the given index is not in a current container's bounds
     */
    @SuppressWarnings("unchecked")
    public T removeElementAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.getElementsCount() - 1)
            throw new IndexOutOfBoundsException();
        else {
            T returnValue = this.getElementAt(index);
            Container < T > newContainer = new Container < T > (this.size);
            for (int i = 0; i < index; i++)
                newContainer.insertElement(elements[i]);
            for (int i = index + 1; i < this.elementsCount; i++)
                newContainer.insertElement(elements[i]);
            this.elements = (T[]) new Object[this.size];
            System.arraycopy(newContainer.elements, 0, this.elements, 0, this.size);
            this.elementsCount -= 1;
            return returnValue;
        }
    }
    /**
     * Default constructor without parameters, creates a container of a size defined in Container.DEFAULT_SIZE
     */
    @SuppressWarnings("unchecked")
    public Container() {
        this.elementsCount = 0;
        this.size = Container.DEFAULT_SIZE;
        this.elements = (T[]) new Object[size];
    }

    /**
     * Constructor with given size, creates a container of given size
     * @param size the size of a new container
     */
    @SuppressWarnings("unchecked")
    public Container(int size) {
        this.elementsCount = 0;
        this.size = size;
        this.elements = (T[]) new Object[size];
    }

    /**
     * Constructor with all given fields, creates a container with given elements count, 
     * given size and copies the array of type T
     * @param elementsCount the count of elements of a new container
     * @param size the size of a new container
     * @param elements the array of elements to be copied to a new container
     */
    public Container(int elementsCount, int size, T elements[]) {
        this.elementsCount = elementsCount;
        this.size = size;
        System.arraycopy(elements, 0, this.elements, 0, this.size);
    }

    /**
     * Returns the container's elements as a string
     * @return the container's elements
     */
    @Override
    public String toString() {
        return "Container [elements=" + Arrays.toString(elements) + "]";
    }
}