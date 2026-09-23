package c3256730.seng2200.pa1;

import java.util.*;

/**
 * Circular Doubly Linked List with a Single Sentinel
 * Contains methods to add/remove at relevant indexes and query methods
 */
public class MyPolygons
{
    private Node sentinel;
    private Node iteratorCursor;
    private int numberOfNodes;

    /**
     * public MyPolygons()
     * Default Constructor
     * Sets numberOfNodes to Zero and creates the links for the sentinel node to point to itself
     */
    public MyPolygons()
    {
        numberOfNodes = 0;

        sentinel = new Node();
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
        iteratorCursor = sentinel;
    }

    /**
     * public void prepend(Object data) //Add to Head method
     * Creates a new node with the parameter data at the start of the circular doubly linked list
     * Updates the appropriate links to accommodate the new node
     * @param data - Type Object that can be type cast to any data type
     */
    public void prepend(Object data) //Add to Head
    {
        //Add to sentinel.next
        Node nextNode = sentinel.getNext();
        Node newNode = new Node(data, nextNode, sentinel);

        //Fix links
        sentinel.setNext(newNode);
        nextNode.setPrevious(sentinel);
        numberOfNodes++;
    }

    /**
     * public void append(Object data) //Add to Tail method
     * Creates a new node with the parameter data at the end of the circular doubly linked list
     * Updates the appropriate links to accommodate the new node
     * @param data - Type Object that can be type cast to any data type
     */
    public void append(Object data) //Add to tail
    {
        //Add at sentinel.previous
        Node previousNode = sentinel.getPrevious();
        Node newNode = new Node(data, sentinel, previousNode);

        //Fix Links
        sentinel.setPrevious(newNode);
        previousNode.setNext(newNode);
        numberOfNodes++;
    }

    /**
     * public void insert(Object data, int index) //AddAt method
     * Creates a new node with the parameter data at one position before the specified index parameter
     * If the index is position 0, it will prepend the data.
     * If the index is equal to the size of the LL, it will append the data
     * Updates the appropriate links to accommodate the new node
     * @param data - Type Object that can be type cast to any data type
     * @param index - Integer specifying the index in the LL
     */

    /**INSERT METHOD IS USED FOR ADDING AT A CERTAIN POSITION SPECIFIED IN THE INSERTION SORT
     * CALL THE RESET METHOD FIRST TO RESET THE ITERATOR
     *
     * First reset the iterator
     * Then step through to the position where the node needs to be inserted from the insertion sort
     * So this method implementation is completely different to what Dan wanted in the spec??
     */
    public void insert(Object data, int index)
    {
        if(index == 0)
            prepend(data); //Without this check it would attempt to place the node at position -1 (Doesn't exist)

        else if(index == size())
            append(data);

        else
        {
            int counter = 0;
            if(index < 0 || index > size())
                throw new IndexOutOfBoundsException(String.format("Error. Index %d is out of bounds [0,%d)",index, size()));

            //If in range, continue to find the index
            for (Node cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
            {
                //If the counter has matched the index
                if (counter == index)
                {
                    //Insert before the current node
                    Node previousNode = cursor.getPrevious();
                    Node newNode = new Node(data, cursor, previousNode);

                    //Update links
                    cursor.setPrevious(newNode);
                    previousNode.setNext(newNode);
                    numberOfNodes++;
                    return;
                }
                //If not, increment and continue to loop
                else
                    counter++;
            }
        }
    }

    public void insertAfterCurrent(Object data)
    {
        Node nextNode = iteratorCursor.getNext();
        Node newNode = new Node(data, nextNode, iteratorCursor);

        iteratorCursor.setNext(newNode);
        nextNode.setPrevious(newNode);

        numberOfNodes++;
    }

    public void insertBeforeCurrent(Object data)
    {
        Node previousNode = iteratorCursor.getPrevious();
        Node newNode = new Node(data, iteratorCursor, previousNode);

        iteratorCursor.setPrevious(newNode);
        previousNode.setNext(newNode);

        numberOfNodes++;
    }

    /**
     * public Object removeFromHead()
     * Checks if the list is empty first - throws error if no nodes exist in the list
     * If not empty - method will remove the first node after the sentinel
     * Updates links to accommodate for the removal of a node
     * @return - Object data of the deleted node
     */
    public Object removeFromHead()
    {
        if(isEmpty())
            throw new NoSuchElementException("Error. No Nodes in the List");

        Node deleteNode = sentinel.getNext();
        Node nextNode = deleteNode.getNext();

        //Update links
        sentinel.setNext(nextNode);
        nextNode.setPrevious(sentinel);

        //Remove links from the deletion node
        Object data = deleteNode.getData();
        deleteNode.setData(null);
        deleteNode.setNext(null);
        deleteNode.setPrevious(null);

        numberOfNodes--;
        return data;
    }

    /**
     * public Object removeFromTail()
     * Checks if the list is empty first - throws error if no nodes exist in the list
     * If not empty - method will remove the last node in the list before the sentinel
     * Updates links to accommodate for the removal of a node
     * @return - Object data of the deleted node
     */
    public Object removeFromTail()
    {
        if(isEmpty())
            throw new NoSuchElementException("Error. No Nodes in the List");

        Node deleteNode = sentinel.getPrevious();
        Node previousNode = deleteNode.getNext();

        //Update links
        sentinel.setPrevious(previousNode);
        previousNode.setNext(sentinel);

        //Remove links from the deletion node
        Object data = deleteNode.getData();
        deleteNode.setData(null);
        deleteNode.setNext(null);
        deleteNode.setPrevious(null);

        numberOfNodes--;
        return data;
    }

    /**
     * public int indexOf(Object data)
     * Searches the linked list using a Node cursor and an integer counter to match the cursor's data with the input Object data
     * If cases are equal it will return the integer counter
     * If not found in the LL, it will return -1
     * @param data - Object data that can be type cast to any data type
     * @return - Integer of index where the data was found (-1 if not in LL)
     */
    public int indexOf(Object data)
    {
        int counter = 0;
        for(Node cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            if(cursor.getData() == data)
                return counter; //Return position in the list

            else
                counter++; //Increment until found
        }
        return -1; //If not found in the list
    }

    /**
     * public Object getDataAtIndex(int index)
     * Checks if the parameter index is out of range - If out of range, throws a new IndexOutOfBoundsException
     * Searches the linked list using a Node cursor and an integer counter to find iterate up to the specified index
     * Once the index has been reached, it will return the Node cursor's data
     * @param index - Integer specifying what position the data will be returned from
     * @return - Object data that can be type cast to any data type
     */
    public Object getDataAtIndex(int index)
    {
        int counter = 0;

        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException(String.format("Error. Index %d is out of bounds [0,%d)",index, size()));
        for(Node cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            if(counter == index)
                return cursor.getData();
            else
                counter++;
        }
        return null; //Shouldn't return null due to boundary check before the for loop
    }


    /**
     * public void clear()
     * Removes all nodes from the linked list whilst it is not empty
     */
    public void clear()
    {
        while(!isEmpty())
            removeFromHead();
    }

    /**
     * public boolean isEmpty()
     * Checks if there are no nodes in the linked list
     * @return - Boolean value
     */
    public boolean isEmpty()
    {
        return numberOfNodes == 0;
    }

    /**
     * public int size()
     * Returns the number of nodes in the linked list
     * @return - Integer value
     */
    public int size()
    {
        return numberOfNodes;
    }

    /**
     * public int getCurrentNodeIndex()
     * Creates a new node cursor and iterates over the linked list until it matches the iteratorCursor's data
     * @return - Integer value of the current position of the iteratorCursor
     */
    public int getCurrentNodeIndex()
    {
        int counter = 0;
        for(Node cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            if(cursor.getData() == iteratorCursor.getData())
                return counter;
            else
                counter++;
        }
        return -1; //Shouldn't be reached since the node must exist in the structure
    }

    /**
     * public void iteratorReset()
     * Sets the iteratorCursor back to the sentinel
     */
    public void iteratorReset()
    {
        iteratorCursor = sentinel;
    }

    /**
     * public void iteratorNext()
     * Checks if there is a next node
     * If true, moves the the following node
     */
    public Object iteratorNext()
    {
        iteratorCursor = iteratorCursor.getNext();
        return iteratorCursor.getData();
    }

    /**
     * public Object iteratorData()
     * Returns the iteratorCursor's data
     * @return - Type Object that can be type cast to any data type
     */
    public Object iteratorData()
    {
        return iteratorCursor.getData();
    }

    /**
     * public boolean iteratorHasNext()
     * Checks if the next node is not null, returns true if condition is satisfied
     * @return - Boolean value
     */
    public boolean iteratorHasNext()
    {
        return iteratorCursor.getNext() != sentinel;
    }

    /**
     * @Override
     * public String toString()
     * Overridden toString method to accommodate for the assignment specification
     * Iterates through the linked list of polygons and appends to the end of a StringBuilder
     * @return - String of all polygons found in the LL
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Node cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            sb.append(cursor.getData().toString())
              .append("\n");
        }

        return sb.toString();
    }
}