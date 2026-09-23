import java.util.*;

/**
 * Circular Doubly Linked List with a Single Sentinel
 * Contains methods to add/remove at relevant indexes and query methods
 * Contains private classes such as DataNode<T>, SentinelNode<T> and Iterator<T>
 */
public class LinkedList<T> implements Iterable<T>
{
    private Node<T> sentinel;
    private int numberOfNodes;
    private int modCount;

    /**
     * public LinkedList()
     * Default Constructor
     * Sets numberOfNodes to Zero and creates the links for the sentinel node to point to itself
     */
    public LinkedList()
    {
        numberOfNodes = 0;
        modCount = 0;

        sentinel = new SentinelNode<>();
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
    }

    /**
     * public void prepend(T data) //Add to Head method
     * Calls the addBefore method, adding after the sentinel node.getNext()
     * @param data - Generic Data Type
     */
    public void prepend(T data) //Add to Head
    {
        addBefore(data,sentinel.getNext());
    }

    /**
     * public void append(T data) //Add to Tail method
     * Calls the  addBefore method, adding before the sentinel node
     * @param data - Generic Data type
     */
    public void append(T data) //Add to tail
    {
        addBefore(data, sentinel);
    }

    /**
     * private void addBefore(T data, Node<T> currentNode)
     * Creates a new node with the parameter data before the specified current Node
     * Updates the appropriate links to accommodate the new node
     * @param data - Generic Data Type
     * @param currentNode - Current Node object to insert before
     */
    private void addBefore(T data, Node<T> currentNode)
    {
        Node<T> previousNode = currentNode.getPrevious();
        Node<T> newNode = new DataNode<>(data, currentNode, previousNode);

        previousNode.setNext(newNode);
        currentNode.setPrevious(newNode);

        numberOfNodes++;
        modCount++;
    }

    /**
     * public void insert(T data, int index) //AddAt method
     * Creates a new node with the parameter data at one position before the specified index parameter
     * If the index is position 0, it will prepend the data.
     * If the index is equal to the size of the LL, it will append the data
     * Updates the appropriate links to accommodate the new node
     * @param data - Generic Data type
     * @param index - Integer specifying the index in the LL
     */
    public void insert(T data, int index)
    {
        if(index == 0)
            prepend(data); //Without this check it would attempt to place the node at position -1 (Doesn't exist)

        else if(index == size())
            append(data);

        else
        {
            int counter = 0;
            checkBounds(index);

            //If in range, continue to find the index
            for (Node<T> cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
            {
                //If the counter has matched the index
                if (counter == index)
                {
                    addBefore(data, cursor);
                    return;
                }
                //If not, increment and continue to loop
                else
                    counter++;
            }
        }
    }

    /**
     * public T removeFromHead()
     * Calls the removeNode method to remove sentinel.getNext()
     * @return - T data of the deleted node
     */
    public T removeFromHead()
    {
        return removeNode(sentinel.getNext());
    }

    /**
     * public T removeFromTail()
     * Calls the removeNode method to remove sentinel.getPrevious()
     * @return - T data of the deleted node
     */
    public T removeFromTail()
    {
        return removeNode(sentinel.getPrevious());
    }

    /**
     * private T removeNode(Node<T> deleteNode)
     * Checks if the list is empty first - throws error if no nodes exist in the list
     * If not empty - method will specified node
     * Updates links to accommodate for the removal of a node
     * @param deleteNode - Node to be deleted
     * @return
     */
    private T removeNode(Node<T> deleteNode)
    {
        if(isEmpty())
            throw new NoSuchElementException("Error. No Nodes in the List");

        T nodeData = deleteNode.getData();
        Node<T> nextNode = deleteNode.getNext();
        Node<T> previousNode = deleteNode.getPrevious();

        previousNode.setNext(nextNode);
        nextNode.setPrevious(previousNode);

        // Clean up delete node references
        deleteNode.setData(null);

        // If iterator cursor is pointing to deleteNode and we concurrently set currentNode/previous to null,
        // the iterator will break
        deleteNode.setNext(null);
        deleteNode.setPrevious(null);

        numberOfNodes--;
        modCount++;
        return nodeData;
    }

    /**
     * public int indexOf(T data)
     * Searches the linked list using a Node<T> cursor and an integer counter to match the cursor's data with the input T data
     * If cases are equal it will return the integer counter
     * If not found in the LL, it will return -1
     * @param data - Generic Type T
     * @return - Integer of index where the data was found (-1 if not in LL)
     */
    public int indexOf(T data)
    {
        int counter = 0;
        for(Node<T> cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            if (data == null && cursor.getData() == null)
                return counter;
            else if(cursor.getData().equals(data))
                return counter; //Return position in the list
            else
                counter++; //Increment until found
        }
        return -1; //If not found in the list
    }

    /**
     * public T get(int index)
     * Searches the linked list using a Node<T> cursor and an integer counter to find iterate up to the specified index
     * Once the index has been reached, it will return the Node<T> cursor's data
     * @param index - Integer specifying what position the data will be returned from
     * @return - T data that can be type cast to any data type
     */
    public T get(int index)
    {
        checkBounds(index);

        int counter = 0;
        for(Node<T> cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            if(counter == index)
                return cursor.getData();
            else
                counter++;
        }
        return null; //Shouldn't return null due to boundary check before the for loop
    }

    /**
     * private void checkBounds(int index)
     * Checks if the parameter index is out of range - If out of range, throws a new IndexOutOfBoundsException
     * @param index - Index of node
     */
    private void checkBounds(int index)
    {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException(String.format("Error. Index %d is out of bounds [0,%d)",index, size()));
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
        for(Node<T> cursor = sentinel.getNext(); cursor != sentinel; cursor = cursor.getNext())
        {
            sb.append(cursor.getData())
              .append("\n");
        }

        return sb.toString();
    }

    /**
     * Constructor for Iterator
     * Creates new Iterator object
     * @return
     */
    public Iterator<T> iterator(){return new MyIterator<>();}

    /**
     * PRIVATE CLASS MYITERATOR
     *  Generic Iterator class that only exists inside Linked List Class
     */
    private class MyIterator<E> implements Iterator<T>
    {
        private Node<T> currentNode;
        private int expectedModCount;

        /**
         * Default constructor
         */
        private MyIterator()
        {
            currentNode = sentinel;
            expectedModCount = modCount;
        }

        /**
         * public boolean hasNext()
         * Overridden method from Iterator Interface
         * Determines if there is another node following the current node
         * @return - Boolean if node exists
         */
        @Override
        public boolean hasNext(){ return (currentNode.getNext() != sentinel); }

        /**
         * public T next()
         * Overridden method from Iterator Interface
         * Moves to next node
         * @return - Next Node's Data
         */
        @Override
        public T next()
        {
            checkForConcurrentModification();
            if(!hasNext())
                throw new NoSuchElementException("Element does not exist");
            currentNode = currentNode.getNext();
            return currentNode.getData();
        }

        /**
         * public void remove()
         * Overridden method from Iterator Interface
         * Removes the current node
         */
        @Override
        public void remove()
        {
            /*
            From http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Iterator.java#Iterator.remove%28%29

            Removes from the list the last element that was returned by next() or previous() (optional operation).
            This call can only be made once per call to next or previous. It can be made only if
            add(java.lang.Object) has not been called after the last call to next or previous.
            */

            checkForConcurrentModification();
            Node<T> previousNode = currentNode.getPrevious();

            LinkedList.this.removeNode(currentNode);

            // to preserve the linked list order we have to move the iterator back
            //Reassign current node to previous node
            currentNode = previousNode;

            //Linkages are handled by the removeNode method
            // modCount will be updated in LinkedList.remove()
            // have to update expectedModCount to allow continued use of iterator
            expectedModCount++;
        }

        /**
         * private void checkForConcurrentModification()
         * Determines whether the mod count is exact to the expected mod count
         */
        private void checkForConcurrentModification()
        {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException("Concurrent modification on Linked List is not permissible.");
        }

    }




    /**
     * PRIVATE CLASS DATANODE<T>
     *     Generic Private DataNode class that only exists inside the Linked List Class
     */
    private class DataNode<T> implements Node<T>
    {
        private T _data;
        private Node<T> _next;
        private Node<T> _previous;

        /**
         * public DataNode()
         * Default Constructor
         * Sets all private data to null
         */
        public DataNode()
        {
            _data = null;
            _next =  null;
            _previous = null;
        }

        /**
         * public DataNode(T data, Node<T> next, Node<T> previous)
         * Overloaded Constructor
         * @param data - Type T that can be type cast to any data type
         * @param next - Node<T> T defining the currentNode node to follow in the LL
         * @param previous - Node<T> T defining the previous node to follow in the LL
         */
        public DataNode(T data, Node<T> next, Node<T> previous)
        {
            this._data = data;
            this._next = next;
            this._previous = previous;
        }

        /**
         * public T getData()
         * @return - T Data of private _data variable
         */
        public T getData() {return _data;}

        /**
         * public Node<T> getNext()
         * @return - Node<T> T of private _next variable
         */
        public Node<T> getNext() {return _next;}

        /**
         * public Node<T> getPrevious()
         * @return - Node<T> T of private _previous variable
         */
        public Node<T> getPrevious() {return _previous;}

        /**
         * public void setData(T data)
         * @param data - Type T that set the private _data variable to the parameter
         */
        public void setData(T data) {this._data = data;}

        /**
         * public void setNext(Node<T> currentNode)
         * @param next - Type Node<T> that sets the private _next variable to the parameter
         */
        public void setNext(Node<T> next) {this._next = next;}

        /**
         * public void setPrevious(Node<T> previous)
         * @param previous - Type Node<T> that sets the private _previous variable to the parameter
         */
        public void setPrevious(Node<T> previous) {this._previous = previous;}
    }

    /**
     * PRIVATE CLASS SENTINELNODE<T>
     *     Generic Private SentinelNode class that only exists inside the Linked List Class
     */
    private class SentinelNode<T> implements Node<T>
    {
        private Node<T> _next;
        private Node<T> _previous;

        /**
         * public SentinelNode()
         * Default Constructor
         * Sets all private data to null
         */
        public SentinelNode()
        {
            _next =  null;
            _previous = null;
        }

        /**
         * public SentinelNode(Node<T> currentNode, Node<T> previous)
         * Overloaded Constructor
         * @param next - Node<T> T defining the currentNode node to follow in the LL
         * @param previous - Node<T> T defining the previous node to follow in the LL
         */
        public SentinelNode(Node<T> next, Node<T> previous)
        {
            this._next = next;
            this._previous = previous;
        }

        /**
         * public T getData()
         * @return - Throw exception since sentinel node should have no data
         */
        public T getData() {throw new UnsupportedOperationException("Trying to access sentinel node");}

        /**
         * public Node<T> getNext()
         * @return - Node<T> T of private _next variable
         */
        public Node<T> getNext() {return _next;}

        /**
         * public Node<T> getPrevious()
         * @return - Node<T> T of private _previous variable
         */
        public Node<T> getPrevious() {return _previous;}

        /**
         * public void setData(T data)
         * Throw exception since sentinel node should not set data
         * @param data - Type T that set the private _data variable to the parameter
         */
        public void setData(T data) {throw new UnsupportedOperationException("Trying to set data on sentinel");}

        /**
         * public void setNext(Node<T> currentNode)
         * @param next - Type Node<T> that sets the private _next variable to the parameter
         */
        public void setNext(Node<T> next) {this._next = next;}

        /**
         * public void setPrevious(Node<T> previous)
         * @param previous - Type Node<T> that sets the private _previous variable to the parameter
         */
        public void setPrevious(Node<T> previous) {this._previous = previous;}
    }

}