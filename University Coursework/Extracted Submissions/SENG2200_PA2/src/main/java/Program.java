public class Program
{
    /**
     * public static void main(String[]args)
     * Start of the program. Determines whether there is an argument for the file input
     * Moves the read the file, output the LinkedList result
     * Sorts the LinkedList object in ascending area order
     * @param args - File path
     */
    public static void main(String[]args)
    {
        //Include function for text.txt to be an argument in the main
        //eg java PA2a text.txt

        if(args.length < 1)
        {
            System.out.println("Error. File argument was not provided");
            System.exit(0);
        }


        String filePath = args[0];
        LinkedList<PlanarShape> unsortedList = new LinkedList<>();

        try
        {
            PlanarShapeFileReader reader = new PlanarShapeFileReader();
            unsortedList = reader.readPlanarShapeDataFromFile(filePath);
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
        SortedList<PlanarShape> sortedList = new SortedList<>();
        sortedList.insertInOrder(unsortedList);
        System.out.println(sortedList.toString());
    }






















}
