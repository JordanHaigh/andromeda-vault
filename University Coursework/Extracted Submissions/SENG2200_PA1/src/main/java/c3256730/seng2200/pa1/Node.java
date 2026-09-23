package c3256730.seng2200.pa1;

/**
 * Node Class
 * Used as Objects for the Circular Doubly Linked List (MyPolygons)
 * Created by Jordan on 08-Mar-17.
 */
public class Node
{
    private Object _data;
    private Node _next;
    private Node _previous;

    /**
     * public Node()
     * Default Constructor
     * Sets all private data to null
     */
    public Node()
    {
        _data = null;
        _next =  null;
        _previous = null;
    }

    /**
     * public Node(Object data, Node next, Node previous)
     * Overloaded Constructor
     * @param data - Type Object that can be type cast to any data type
     * @param next - Node Object defining the next node to follow in the LL
     * @param previous - Node Object defining the previous node to follow in the LL
     */
    public Node(Object data, Node next, Node previous)
    {
        this._data = data;
        this._next = next;
        this._previous = previous;
    }

    /**
     * public Object getData()
     * @return - Type Object of private _data variable
     */
    public Object getData() {return _data;}

    /**
     * public Node getNext()
     * @return - Node Object of private _next variable
     */
    public Node getNext() {return _next;}

    /**
     * public Node getPrevious()
     * @return - Node Object of private _previous variable
     */
    public Node getPrevious() {return _previous;}

    /**
     * public void setData(Object data)
     * @param data - Type Object that set the private _data variable to the parameter
     */
    public void setData(Object data) {this._data = data;}

    /**
     * public void setNext(Node next)
     * @param next - Type Node that sets the private _next variable to the parameter
     */
    public void setNext(Node next) {this._next = next;}

    /**
     * public void setPrevious(Node previous)
     * @param previous - Type Node that sets the private _previous variable to the parameter
     */
    public void setPrevious(Node previous) {this._previous = previous;}
}
