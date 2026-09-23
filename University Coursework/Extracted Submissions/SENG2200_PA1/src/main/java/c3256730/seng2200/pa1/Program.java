package c3256730.seng2200.pa1;

public class Program
{
    /**
     * public static void main(String[]args)
     * Start of the program. Determines whether there is an argument for the file input
     * Moves the read the file, output the MyPolygons result
     * Sorts the MyPolygons object in ascending area order
     * @param args - File path
     */
    public static void main(String[]args)
    {
        //Include function for text.txt to be an argument in the main
        //eg java PA1 text.txt

        if(args.length < 1)
        {
            System.out.println("Error. File argument was not provided");
            System.exit(0);
        }


        String filePath = args[0];
        MyPolygons unsortedList = new MyPolygons();

        try
        {
            PolygonFileReader reader = new PolygonFileReader();
            unsortedList = reader.readPolygonDataFromFile(filePath);
        }
        catch (Exception e)
        {
            System.out.println("Error. File data could not be loaded. Error: " + e.getMessage());
            System.exit(0);
        }

        System.out.print("###UNSORTED LIST###\n");
        System.out.println(unsortedList.toString());

        System.out.print("\n\n\n");

        System.out.print("###SORTED LIST###\n");
        /*while(unsortedList.iteratorHasNext())
        {
            System.out.println(unsortedList.iteratorNext().toString());

        }*/
        //MyPolygons sortedList = insertionSort(unsortedList);
        //System.out.println(sortedList.toString());
    }

    /**
     * private static MyPolygons insertionSort(MyPolygons myPolygonsSource)
     * Creates a new MyPolygons Object and sorts the source parameter in ascending area order
     * Returns the new MyPolygons Object
     * @param unsortedPolygonList - Original MyPolygons Object to be sorted
     * @return - new MyPolygons Object
     */
/*    private static MyPolygons insertionSort(MyPolygons unsortedPolygonList)
    {
        MyPolygons sortedPolygonList = new MyPolygons();
        sortedPolygonList.append(unsortedPolygonList.getDataAtIndex(0)); //Treat first element as "Sorted" and continue to add and check

        //Algorithm from http://www.algolist.net/Algorithms/Sorting/Insertion_sort
        int currentUnsortedIndex;
        for (currentUnsortedIndex = 1; currentUnsortedIndex < unsortedPolygonList.size(); currentUnsortedIndex++) //Iterate through unsorted list for all indexes
        {
            boolean found = false;
            Polygon anUnsortedPolygonThatIWantToSort = (Polygon)unsortedPolygonList.getDataAtIndex(currentUnsortedIndex); //First polygon in the unsorted list

            sortedPolygonList.iteratorReset();
            while(sortedPolygonList.iteratorHasNext() && !found)
            {
                Polygon nextPolygon = (Polygon)sortedPolygonList.iteratorNext();
                 if(anUnsortedPolygonThatIWantToSort.comesBefore(nextPolygon))
                 {
                 }
            }
        }

        return sortedPolygonList;
    }*/
    private static MyPolygons insertionSort(MyPolygons unsortedPolygonList)
    {
        //1. Treat the first element of the UNSORTED List (LinkedList) as SORTED
        //- i.e. Take the first element in the UNSORTED list and add to the SORTED List at position 0
        MyPolygons sortedPolygonList = new MyPolygons();
        sortedPolygonList.append(unsortedPolygonList.getDataAtIndex(0)); //Treat first element as "Sorted" and continue to add and check

        //    2. Start iterating over the UNSORTED List
        int currentUnsortedIndex;
        for(currentUnsortedIndex = 1; currentUnsortedIndex < unsortedPolygonList.size(); currentUnsortedIndex++)
        {
            unsortedPolygonList.iteratorReset(); //Reset to head

            while(unsortedPolygonList.iteratorHasNext())         //- Use List.iteratorHasNext() to check if we have reached the end of the Circular LinkedList
            {
                unsortedPolygonList.iteratorNext();
            }
            Polygon unsortedPolygonToSort = (Polygon)unsortedPolygonList.iteratorData(); //Convert to concrete polygon


            //2. Get the next element from the UNSORTED List
            //- Can use a FOR or WHILE loop. Up to you
            //- Use the .iteratorNext() method to get the next object in the list sequence from the UNSORTED List
            //- Convert from a Java Object type to a concrete Polygon type



            //3. Start iterating over the SORTED List (LinkedList)
            //- Reset the SORTED List iterator back to the HEAD by using the iteratorReset() method
            //- Can use a FOR or WHILE loop to iterate over elements of the SORTED List
            sortedPolygonList.iteratorReset();
            while(sortedPolygonList.iteratorHasNext())
            {
                //4. Get the next element from the SORTED List
                //- Use the iteratorNext() method to get the next Polygon object in the SORTED List sequence
                //- Convert from Java Object type to concrete Polygon type
                sortedPolygonList.iteratorNext();
                Polygon sortedPolygon = (Polygon)sortedPolygonList.iteratorData(); //Convert to concrete polygon

                //5. Determine if UNSORTED Polygon .ComesBefore() SORTED Polygon
                //- Use .ComesBefore() - But with which order?
                //   unsorted.comesBefore(sorted)   or
                //sorted.comesBefore(unsorted)?
                //if(unsortedPolygonToSort.comesBefore(sortedPolygon))
                //  - If we use unsorted.comesBefore(sorted) - We have to check UNTIL (i.e. Loop) unsorted DOES NOT come before sorted and insert BEFORE???
                // - If we use sorted.comesBefore(unsorted) - We have to check WHILE (i.e. Loop) sorted DOES COME BEFORE unsorted and insert BEFORE???
                while(!unsortedPolygonToSort.comesBefore(sortedPolygon))
                {
                    //   6. Once we have identified that unsorted.doesNotComeBefore(sorted) (i.e. we have found the insertion index):
                    //      - We insert BEFORE the current element in the SORTED List
                    sortedPolygonList.insertBeforeCurrent(unsortedPolygonToSort);
                }
            }


        }
        return sortedPolygonList;

    }



















}
