/**
 * public interface Node<T>
 * Generic Node interface used for creating DataNode and SentinelNode private classes
 * @param <T>
 */
public interface Node<T>
{
    /**
     * Node<T> getNext();
     * @return - Returns next node
     */
    Node<T> getNext();

    /**
     * Node<T> getPrevious();
     * @return - Returns previous node
     */
    Node<T> getPrevious();

    /**
     * T getData();
     * @return - Returns data
     */
    T getData();

    /**
     * void setNext(Node<T> next)
     * @param next - Next Node
     */
    void setNext(Node<T> next);

    /**
     * void setPrevious(Node<T> previous)
     * @param previous - Previous Node
     */
    void setPrevious(Node<T> previous);

    /**
     * void setData(T data);
     * @param data - Data
     */
    void setData(T data);


}
