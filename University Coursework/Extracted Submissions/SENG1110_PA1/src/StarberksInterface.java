import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

/**
 * The interface is the output of the system and the only class the user interacts with.
 * The interface calls various methods from the store class in order to fulfill many tasks.
 * @author Jordan Haigh
 * @version 2.0
 */
public class StarberksInterface {

    /**
     * Constructor for objects of class StarberksInterface
     */
    public StarberksInterface() {
        // initialise instance variables

        //So you're aware, I chose to create two Store Objects instead of an array of Stores
        // As I felt it was an easier approach when starting assignment 2.
        // I could've used arrays to make this more generic.
        //TODO If spare time, convert to implement Store Array
        this.callaghanStore = new Store("Callaghan");
        this.lambtonStore = new Store("Lambton");
        this.console = new Scanner(System.in);

    }

    /**
     * Main Method
     * Precondition: None
     * Postcondition: Will run interface
     * @param args
     */
    public static void main(String[] args) {
        StarberksInterface intFace = new StarberksInterface();
        intFace.run();
    }

    private Store callaghanStore;
    private Store lambtonStore;
    private Scanner console;


    /**
     * RUN INTERFACE
     * Runs the interface
     * Precondition: None
     * Postcondition: Program will exit
     */
    private void run() {
        int option;
        do{
            System.out.println("Welcome to the Starberks Coffee Replenishment System");
            option = inputBoundedInt(1,5,"[1] Choose Store, [2] Display Stores, [3] Open, [4] Save, [5] Exit");
            //System.out.println("[1] Choose Store, [2] Display Stores, [3] Open, [4] Save, [5] Exit");
            //option = console.nextInt();

            switch(option){
                case 1: chooseStore();
                        break;
                case 2: displayStores();
                        break;
                case 3: openDataFile();
                        break;
                case 4: saveDatafile();
                        break;
                case 5: exitProgramScreen();
                        break;
                default: System.out.println("Invalid Option. Please Try Again.");
            }

        }while(option!=5);

    }


    /**
     * MAIN TREE - CHOOSE STORE SCREEN
     * Runs the Store Selection Interface
     * Precondition: Callaghan Store and Lambton Store must exist
     * Postcondition: None
     */
    private void chooseStore()
    {
        do
        {
            String input;
            input = inputBoundedString(0,15, "Please enter the name of the store. Lambton or Callaghan");
            if (input.toLowerCase().equals("callaghan"))
            {
                secondTreeMenu(callaghanStore);
                return;
            }
            else if (input.toLowerCase().equals("lambton"))
            {
                secondTreeMenu(lambtonStore);
                return;
            }
            else
            {
                System.out.println("ERROR: Store name not found. Please try again");
            }
        }while(true);

    }

    /**
     * SECOND TREE - MAIN
     * Sub menu inside a specific store
     * Precondition: Store name must exist (Lambton or Callaghan)
     * Postcondition: None
     * @param targetStore - Defines the specific store associated with the method.
     */
    private void secondTreeMenu(Store targetStore)
    {
        int option;
        do{
            System.out.println("Welcome to the " + targetStore.getStoreName()+ " store.");
            option = inputBoundedInt(1,5,"[1]Add/Edit Product, [2] Delete Product, [3]Display Product, [4] Display All Products, [5]Exit Store");
            //System.out.println("[1]Add/Edit Product, [2] Delete Product, [3]Display Product, [4] Display All Products, [5]Exit Store");
            //option = console.nextInt();

            switch(option){
                case 1: addEditProductScreen(targetStore);
                        break;
                case 2: deleteProductScreen(targetStore);
                        break;
                case 3: displayProductScreen(targetStore);
                        break;
                case 4: displayAllProductsScreen(targetStore);
                        break;
                case 5: exitStore();
                        break;
                default:
            }

        }while(option!=5);
    }

    /**
     * SECOND TREE MENU - ADD/EDIT PRODUCT SCREEN
     * Add product to store. If the product name already exists in the store,
     * there is the option to update the data for that product
     * Precondition: None
     * Postcondition: Adds product to Store, or changes data is product already exists
     * @param targetStore - Defines the specific store associated with adding/editing a product.
     */
    private void addEditProductScreen(Store targetStore) {
        String inputName =inputBoundedString(3,10, "Enter the Product name");
        if (targetStore.checkProductExists(inputName) == true) {
            String input;
            System.out.println("ERROR: Product already exists.");
            updateData(inputName, targetStore);

/*            System.out.println("Would you like to update the data for this product? Y/N");
            input = console.next().toUpperCase();
            if (input.equals("Y")) {
                updateData(inputName, targetStore);
            }*/
        }
        else
        {
            double inputDemandRate, inputSetupCost, inputUnitCost, inputInventoryCost, inputSellingPrice;
            inputDemandRate = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the Demand Rate for the Product");
            inputSetupCost = inputBoundedDouble(0,Double.MAX_VALUE, "Enter the Setup Cost for the Product");
            inputUnitCost = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the Unit Cost for the Product");
            inputInventoryCost = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the Inventory Cost for the Product");
            inputSellingPrice = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the Selling Price for the Product");
            targetStore.addProduct(inputName, inputDemandRate,inputSetupCost,inputUnitCost,inputInventoryCost,inputSellingPrice);
            System.out.println("Product has been added to the store.");
        }
    }
    /**
     * Updating the data for a product. The user is given the option to change the name of the product or change
     * the product data.
     * Precondition: existingProductName must represent a product that exists in the store
     * Postcondition: Updates the data of a specific product given by the user
     * @param existingProductName - Defines the Product name that may already exist in the Store class
     * @param targetStore - Defines the specific store associated with updating a product.
     */
    private void updateData(String existingProductName, Store targetStore) {
        int input;
            input = inputBoundedInt(1,3, "[1] Change the Name of the Product, [2] Change the Data of the Product, [3] Return");
            //If user wants to change the name of the product
            if(input==1){
                String newProductName;
                newProductName = inputBoundedString(3,10,"Enter the new Product Name");
                while(targetStore.checkProductExists(newProductName)==true){
                    System.out.println("ERROR: Product name already exists. Please try again");
                    newProductName = inputBoundedString(3,10, "Enter the new Product name");
                }
                targetStore.setProductName(existingProductName, newProductName);
                System.out.println("The Product name has been modified.");

            }
            //If User wants to change data for the product
            else if (input==2)
            {
                double newProductDemandRate, newProductSetupCost, newProductUnitCost, newInventoryCost, newSellingPrice;
                newProductDemandRate = inputBoundedDouble( 0, Double.MAX_VALUE, "Enter the new Product Demand Rate");
                targetStore.setProductDemandRate(existingProductName, newProductDemandRate);

                newProductSetupCost = inputBoundedDouble( 0, Double.MAX_VALUE, "Enter the new Product Setup Cost");
                targetStore.setProductSetupCost(existingProductName, newProductSetupCost);

                newProductUnitCost = inputBoundedDouble(0,Double.MAX_VALUE, "Enter the new Product Unit Cost");
                targetStore.setProductUnitCost(existingProductName, newProductUnitCost);

                newInventoryCost = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the new Inventory Cost");
                targetStore.setInventoryCost(existingProductName, newInventoryCost);

                newSellingPrice = inputBoundedDouble(0, Double.MAX_VALUE, "Enter the new Selling Price");
                targetStore.setSellingPrice(existingProductName, newSellingPrice);
                System.out.println("The Product Data has been modified.");
            }
    }


    /**
     * SECOND TREE MENU - DELETE PRODUCT SCREEN
     * Method first checks if the store is empty or not.
     * If empty, it will return an error message. If products exist, it will continue to delete a specific product
     * Precondition: A product must exist in a specific store
     * Postcondition: Method will continue to delete specified product
     * @param targetStore - Defines the specific store that the product will be deleted from.
     */
    private void deleteProductScreen(Store targetStore) {
        if (!storeIsNotEmpty(targetStore))
            return;
        else
            deleteProduct(targetStore);
    }

    /**
     * Check if the store is empty or not and either returns to main menu or continues to show product data
     * Precondition: Store must not be empty
     * Postcondition: Checks whether the store is empty or not
     * @param targetStore - Defines the specific store associated with checking if the store is empty or not.
     * @return Whether the store is empty or not
     */
    private boolean storeIsNotEmpty(Store targetStore)
    {
        if(targetStore.isEmpty())
        {
            System.out.println("ERROR: No product data in store");
            return false;
        }
        else
            return true;
    }

    /**
     * Deletes a product in a specific Store. First displays the names of the products in the Store
     * Checks if product exists and deletes product is name exists
     * Precondition:None
     * Postcondition:Deletes product in store if user requests
     * @param targetStore - Defines the specific store associated with deleting a product.
     */
    private void deleteProduct(Store targetStore){
        String productInput;
        // Display Current Product Names
        System.out.println("Here are the current products in the Store");
        System.out.println(targetStore.displayProductNames());

        //Ask user for Product name
        productInput = inputBoundedString(0,13, "Which product would you like to delete?");

        // Check product exists in database
        if (targetStore.checkProductExists(productInput))
        {
            targetStore.deleteProduct(productInput);
            System.out.println("The product was deleted.");
        }
        else
            System.out.println("The product does not exist.");
    }

    /**
     * SECOND TREE - DISPLAY PRODUCT SCREEN
     * Checks whether the specified store is empty or not.
     * If empty, the program will return an error message. If not empty, it will continue to display data.
     * Precondition: None
     * Postcondition: Presents show data screen and continues if database is not empty
     * @param targetStore - Defines the specific store associated with displaying a product's data.
     */
    private void displayProductScreen(Store targetStore)
    {
        if(!storeIsNotEmpty(targetStore))
            return;
        showProductData(targetStore);
    }

    /**
     * Show data for a specific product. The user inputs the name of the product and is presented
     * with the product data for that specific product
     * Precondition: None
     * Postcondition: Presents user with product data from specific product
     * @param targetStore - Defines the specific store associated with showing the product data.
     */
    private void showProductData(Store targetStore)
    {
        String productName;
        String input;
        // Display Current Product Names
        System.out.println("Here are the current products in the Store");
        System.out.println(targetStore.displayProductNames());

        productName = inputBoundedString(3,10,"Enter the name of the product to view its data");
        if(targetStore.checkProductExists(productName)== true)
        {
            System.out.println("Name\t Demand Rate\t Setup Cost\t Unit Cost\t Inventory Cost\t Selling Price");
            System.out.println(targetStore.displayProductData(productName));
            input = inputBoundedString(1,1, "Would you like to view the Replenishment Strategy for this product? Y/N");
            if (input.equals("y"))
            {
                EOQWeeks(targetStore, productName);

            }
        }
        else
            System.out.println("The product does not exist.");
    }

    /**
     * Calculation of the replenishment strategy. User inputs the name of the product to be calculated as well as
     * the number of weeks for the system. Weeks must be a positive integer.
     * If the EOQ is less than the demand rate, the user is required to change the product data or the number of weeks.
     * If the EOQ is higher than the demand rate, it will run a for loop for the number of weeks
     * The profit calculation is calculated and set in this method to be used as the best replenishment strategy
     *
     * Precondition: Product must exist in the store with product data assigned
     * Postcondition: Product EOQ table will be displayed with strategy
     * @param targetStore - Defines the specific store associated with calculating the product's EOQ.
     * @param productName - Defines the productName associated with calculating the EOQ.
     */
    private void EOQWeeks(Store targetStore, String productName)
    {
        int numberOfWeeks;
        numberOfWeeks = inputBoundedInt(0, Integer.MAX_VALUE, "Enter the number of weeks for the Replenishment Strategy");

        int EOQ = targetStore.EOQ(productName,numberOfWeeks);
        double demand = targetStore.getDemandRate(productName);

        if (EOQ<demand)
        {
            System.out.println("ERROR: It is not possible to have a replacement strategy with the inputs given.\nPlease edit the product details.\n");
        }
        else
        {
            //Print header row of the data
            System.out.println("Week \t QuantityOrder \t Demand \t Inventory");
            //Setup a loop, so that for each row you can print the week number, the quantity order, the demand rate and the inventory.
            int  currentInventory = 0, numberPurchaseOrders = 0, runningOrderQuantity = 0, runningInventory = 0;

            for(int currentWeek=1; currentWeek<=numberOfWeeks; currentWeek++)
            {
                int quantityOrder = 0, weeksRemaining=0;
                //At start of week i, we need to order something or nothing
                if (currentInventory < demand)
                {
                    weeksRemaining = numberOfWeeks - currentWeek +1;
                    quantityOrder = Math.min(EOQ,(int)(demand*weeksRemaining)-currentInventory);
                    currentInventory += quantityOrder;
                    //Update order totals
                    numberPurchaseOrders ++;
                    //Running order quantity
                    runningOrderQuantity += quantityOrder;

                }
                //During week we sell demand rate
                currentInventory -= demand;
                //At end of week we have n inventory left
                //Running Inventory
                runningInventory +=currentInventory;
                // Print table
                System.out.println(currentWeek +"\t\t\t"+ quantityOrder+"\t\t\t"+ demand +"\t\t\t"+ currentInventory);
            }
            double unitCost = targetStore.getProductUnitCost(productName);
            double setupCost = targetStore.getSetupCost(productName);
            double sellingPrice = targetStore.getSellingPrice(productName);
            double h = targetStore.getInventoryCost(productName)*(numberOfWeeks/unitCost);

            //Profit Calculation
            double totalPurchaseCost = (numberPurchaseOrders*setupCost) + (runningOrderQuantity)* unitCost;
            double totalInventoryCost = (runningInventory*h);
            double totalCost = totalPurchaseCost + totalInventoryCost;
            double profit = (demand * numberOfWeeks * sellingPrice)-totalCost;
            targetStore.setProfit(productName, profit);

            //Printing Profit and Total Cost
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("The Total Cost of this product is: $" + df.format(totalCost));
            System.out.println("The Profit of this product is: $" + df.format(profit) +"\n");
        }
    }

    /**
     * SECOND TREE - DISPLAY ALL PRODUCTS SCREEN
     * Displays all products in a specific store
     * If store is empty, it will return an error message. If not empty, it will continue to print all product data
     * Precondition: Products must exist in the specified store
     * Postcondition: Table will be printed with all product data
     * @param targetStore - Defines the specific store associated with display all product data.
     */
    private void displayAllProductsScreen(Store targetStore)
    {
        if(!storeIsNotEmpty(targetStore))
            return;
        else
            displayAllProducts(targetStore);


    }

    /**
     * Displays all products in store
     * Precondition: Products must exist in specified store
     * Postcondition: Displays all products and their data
     * @param targetStore - Defines the specific store associated with displaying all product data.
     */
    private void displayAllProducts(Store targetStore)
    {
        int input = inputBoundedInt(1,3,"How would you like to sort the data? [1] By Product Name, [2] By Product Demand Rate, [3]No sorting");


        System.out.println("Here are the current products in the Store");
        System.out.println("Name\t Demand Rate\t Setup Cost\t Unit Cost\t Inventory Cost\t Selling Price");
        System.out.println(targetStore.printAllProductDataStringWithSortingOption(input));
    }


    /**
     * SECOND TREE - EXIT STORE SCREEN
     * Returns to the main menu
     * Precondition: None
     * Postcondition: Returns to main menu
     */
    private void exitStore()
    {
        return;
    }




    /**
     * MAIN TREE MENU - DISPLAY STORES SCREEN
     * Displays both stores in the system and the products associated with them
     * Precondition: None
     * Postcondition: Shows stores in the system with their products.
     */
    private void displayStores()
    {
        System.out.println("Here are the stores in the system:");

        System.out.println(lambtonStore.toString());
        System.out.println(callaghanStore.toString());
    }

    /**
     * MAIN TREE - OPEN DATA FILE
     * User inputs a file name into the system from the directory of the source files.
     * The method will then read and recognise whether the next line is a store or a product
     * If the next line is a product, it will read the following lines for the product data
     * If the next line is a store, it will remember the store name and add the next product to that store
     * Program will throw error messages if there is a problem with importing the file
     * Precondition: Input file must contain store names and product data
     * Postcondition: Data will be imported into the stores where there is data
     */
    private void openDataFile()
    {
        Store targetStore = null;
        //inputBoundedString input
        String input = inputBoundedString(1,Integer.MAX_VALUE, "Enter the name of the file you would like to import into the system. (Without '.dat' file extension)");
        input = input+".dat";

        //import bufferedReader for the readLine method
        //try-with-resources and catch block for exceptions
        try(Scanner reader = new Scanner(new File(input)))
        {
            //While not end of file
            while(reader.hasNextLine())
            {
                //detect next store name or detect next product name
                String storeOrProduct;   //We do not know whether we encounter a store or a product, this is a temporary placeholder
                storeOrProduct = reader.nextLine();

                //If the line is empty
                if(storeOrProduct.isEmpty())
                {
                    continue;
                }

                if(storeOrProduct.contains("Name: "))
                {
                    //Then the line is a product
                    //if product name - extract product information
                    String productName = storeOrProduct.substring((storeOrProduct.indexOf(": ")+2),storeOrProduct.length());
                    // insert into store
                    extractProductData(reader, targetStore, productName);

                }
                //if store name - remember store name
                else
                {
                    String storeName = storeOrProduct.substring(0,storeOrProduct.length()-1);

                    //Get the target store
                    //As per assignment specification, we add product to the list of existing products in the target store
                    if(storeName.toLowerCase().equals("callaghan"))
                    {
                        //callaghanStore = new Store("Callaghan");
                        targetStore = callaghanStore;
                    }
                    else if (storeName.toLowerCase().equals("lambton"))
                    {
                        //lambtonStore = new Store("Lambton");
                        targetStore = lambtonStore;
                    }
                    else
                    {
                        throw new Exception("Store name not recognised in System. '"+ storeName+"'.");
                    }
                }

            }


        }
        catch (FileNotFoundException fnf)
        {
            System.err.println("Error: The file does not exist. "+fnf.getMessage());
        }
        catch(Exception e)
        {
            System.err.println("Error: File was not imported. "+ e.getMessage());
        }
    }

    /**
     * Extract the product data (Demand Rate, Setup Cost, Unit Cost, Inventory Cost, Selling Price) from the file
     * Adds the product data to the specific store from the file
     * Precondition: Input file must have product data
     * Postcondition: Extracts double values of the product data
     * @param reader - Scanner class used in the previous method, reads the next line of the file
     * @param targetStore - Specific store to insert the product data into
     * @param productName - Name of the product collected from the previous method
     */
    private void extractProductData(Scanner reader, Store targetStore, String productName)
    {
        double demandRate = extractNumeric(reader.nextLine());
        double setupCost = extractNumeric(reader.nextLine());
        double unitCost = extractNumeric(reader.nextLine());
        double inventoryCost = extractNumeric(reader.nextLine());
        double sellingPrice = extractNumeric(reader.nextLine());
        //This shouldn't ever be null, but this acts as a safeguard in the unlikely event
        if(targetStore !=null)
        {
            targetStore.addProduct(productName,demandRate, setupCost, unitCost, inventoryCost, sellingPrice );
        }
    }

    /**
     *Extracts the numeric value from the string of the input file
     * Method discovers where the index of where the first appropriate digit starts and creates a substring
     * of only the numeric value
     * Precondition: Input file must contain product data with numeric values
     * Postcondition: Returns the numeric value from the string
     * @param inputString - Line in the file
     * @return - Parsed Double of the substring
     */
    private double extractNumeric(String inputString)
    {
        int numericStart = inputString.indexOf(": ")+2;
        String substring = inputString.substring(numericStart, inputString.length());
        return Double.parseDouble(substring);
    }

    /**
     * MAIN TREE - SAVE DATA FILE
     * User inputs the name of the file they would like the product data saved as. File is created with both stores and
     * associated product data.
     * Method will catch exceptions if there are errors saving the file
     * Precondition: Product data must exist in the stores
     * Postcondition: File is created with all product data in associated stores
     */
    private void saveDatafile()
    {
        String input = inputBoundedString(1,Integer.MAX_VALUE, "Enter the file name you would like the stores to be saved as. (Without '.dat' file extension)");
        input = input+".dat";
        try (PrintWriter outputStream = new PrintWriter(input))
        {
            outputStream.println(lambtonStore.buildFileString());
            outputStream.println(callaghanStore.buildFileString());
        }
        catch(FileNotFoundException fnf)
        {
            System.err.println("Error: File not found. " + fnf.getMessage());
        }
        catch(Exception e)
        {
            System.err.println("Error: Saving File was not completed. "+ e.getMessage());
        }
        System.out.println("Your file: '" + input + "' has been saved.");
    }

    /**
     * MAIN TREE - EXIT PROGRAM SCREEN
     * Exits the program
     * Precondition: None
     * Postcondition: Exits the program
     */
    private void exitProgramScreen()
    {
        return;
    }





    /**
     * inputBoundedString provides a safe means of validating the length of an input string from the user.
     * Precondition: None
     * Postcondition: Validates string within range
     * @param lowerBound - Defines the lower boundary for the range of the string length
     * @param upperBound - Defines the upper boundary for the range of the string length
     * @param inputMessage - Message printed to help user with input task
     * @return - Name input
     */
    private String inputBoundedString(int lowerBound, int upperBound, String inputMessage) {
        String name;
        do {
            //Ask for the name of the Product
            System.out.println(inputMessage);
            name = console.nextLine().toLowerCase();
            //Check string is 3-10 characters long
            //    If outside range, present error message and try again
            if ((name.length() < lowerBound) || (name.length() > upperBound)) {
                System.out.println("Input value is outside character range("+ lowerBound + "-" + upperBound +"). Please try again");
                name = null;
            }
        } while (name == null);
        return name;
    }

    /**
     * inputBoundedDouble provides a safe means of validating the value of a double given by the user.
     * Precondition: None
     * Postcondition: Validates double within range
     * @param lowerBound - Defines the lower boundary for the range of the double
     * @param upperBound - Defines the upper boundary for the range of the double
     * @param inputMessage - Message printed to help user with input task
     * @return - Double Input
     */
    private double inputBoundedDouble(double lowerBound, double upperBound, String inputMessage) {
        double input;
        do {
            //Ask input for Product Data
            System.out.println(inputMessage);
            input = getDoubleFromUser();

            //Check input value is within range
            //    If outside range, present error message and try again
            if ((input < lowerBound) || (input > upperBound))
            {
                System.out.println("Input Value is outside range("+ lowerBound + "-" + upperBound + "). Please try again");
            }
            else
            {
                break;
            }
        } while (true);
        return input;
    }

    /**
     * inputBoundedInt provides a safe means of validating the value of an int given by the user.
     * Precondition: None
     * Postcondition: Validates int within range
     * @param lowerBound - Defines the lower boundary for the range of the int
     * @param upperBound - Defines the upper boundary for the range of the int
     * @param inputMessage - Message printed to help user with input task
     * @return - Double Input
     */
    private int inputBoundedInt(int lowerBound, int upperBound, String inputMessage) {
        int input;
        do {
            //Ask input for Product Data
            System.out.println(inputMessage);

            input = getIntegerFromUser();

            //Check input value is within range
            //    If outside range, present error message and try again
            if ((input < lowerBound) || (input > upperBound))
            {
                System.out.println("Input value is outside range("+ lowerBound + "-" + upperBound + "). Please try again");
            }
            else
            {
                break;
            }
        } while (true);
        return input;
    }

    /**
     * User enters line of text that is parsed to an integer value
     * Will throw exception is string doesn't contain integer value
     * Precondition: None
     * Postcondition: Converts number in string to integer value
     * @return - Integer value from user's string
     */
    private int getIntegerFromUser()
    {
        do
        {
            //Receive line from user
            String input = console.nextLine();

            //Try and parse to int
            try
            {

                if(input.length()>0)
                {
                    int intInput = Integer.parseInt(input);
                    //Return new int where successful
                    return intInput;
                }
            }
            catch (Exception e)
            {
                System.err.println("Error: Invalid Integer. Error in "+ e.getMessage());
            }

        //Loop if unsuccessful
        }while(true);


    }

    /**
     * User enters line of text that is parsed to an double value
     * Will throw exception is string doesn't contain double value
     * Precondition: None
     * Postcondition: Converts number in string to double value
     * @return - Double value from user's string
     */
    private double getDoubleFromUser()
    {
        do
        {
            //Receive line from user
            String input = console.nextLine();

            //Try and parse to double
            try
            {
                if(input.length()>0)
                {
                    double doubleInput = Double.parseDouble(input);
                    //Return new double where successful
                    return doubleInput;
                }
            }
            catch(Exception e)
            {
                System.err.println("Error: Invalid Double. Error in "+e.getMessage());
            }
        }while(true);
    }
}