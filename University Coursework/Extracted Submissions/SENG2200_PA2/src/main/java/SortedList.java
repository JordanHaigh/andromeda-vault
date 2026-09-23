import java.util.Iterator;

/**
 * SortedList<T extends Comparable<T>> class that extends from LinkedList<T>
 *     Includes the insertInOrder() method to sort a LL
 */
public class SortedList<T extends Comparable<T>> extends LinkedList<T>
{

    /**
     * Default Constructor calls parent
     */
    public SortedList()
    {
        super();
    }

    /**
     * Overloaded Constructor that passes a LL as a parameter
     * Calls insertInOrder() to sort list
     * @param data - LinkedList<T>
     */
    public SortedList(LinkedList<T> data)
    {
        super();
        insertInOrder(data);
    }

    /**
     * public void insertInOrder(LinkedList<T> data)
     * Determines if there is data to work with and calls the the overloaded insertInOrder() method for each shape
     * @param data - LinkedList With Generic T Type
     */
    public void insertInOrder(LinkedList<T> data)
    {
        if(data == null || data.isEmpty())
            return;

        Iterator<T> iterator = data.iterator();
        while(iterator.hasNext())
        {
            T shape = iterator.next();
            insertInOrder(shape);
        }

       /* for (T shape : data)
            insertInOrder(shape);*/
    }

    /**
     * public void insertInOrder(T data)
     * Determines if data parameter is not null and iterates through the current sorted list to determine position
     * @param data - T Data type
     */
    public void insertInOrder(T data)
    {
        //No Elements in the list so far
        // Null check. Putting all null values at the start of the list
        if(this.isEmpty() || data == null)
        {
            prepend(data);
            return;
        }

        int counter = 0;

        // Start iterating over the SORTED List (LinkedList)

        // Handled by foreach loop

        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext())
        {
            T sortedShape = iterator.next();


        /*for (T sortedShape : this)
        {*/
            // Determine if UNSORTED Shape .ComesBefore() SORTED Shape
            // - If we use sorted.comesBefore(unsorted) - We have to check WHILE (i.e. Loop) sorted DOES COME BEFORE unsorted and insert BEFORE
            // i.e sorted shape comes AFTER unsorted shape

            // Have to check for nulls
            if (sortedShape == null)
                counter++;
            else if(sortedShape.compareTo(data) == 1) //Comes after the unsorted shape
            {
                this.insert(data, counter);
                return;
            }
            else
                counter++;
        }
        //If data still hasn't been able to be inserted, append
        append(data);
    }
}
