import java.text.DecimalFormat;

/**
 * The Store class contains most methods required to communicate with the interface.
 * The store uses an array for the products in the system.
 * The array initialises at three products and will expand if more products are added.
 * Various methods are used to add a product or check if the store is empty,
 * as well as the EOQ required in the assignment
 * @author Jordan Haigh (c3256730) 
 * @version 2.0
 */
public class Store
{
    // Instance Variables
    private String storeName;
    private Product[] productArray;
    private int numberOfProductsInStore;


    /**
     * Constructor for objects of class Store
     */
    public Store(String storeName)
    {
        // Initialise instance variables
        this.storeName = storeName;
        this.productArray = new Product[3];
        numberOfProductsInStore = 0;

    }


    /**
     * Adding a Product to the Store
     * If the number of products in the store are equal to the array length, the array will be resized and add a new element
     * Precondition: Must have space in Store
     * Postcondition: Product will exist in store
     * @param startName - Name of new Product being added
     * @param startDemandRate - Demand Rate of new Product being added
     * @param startSetupCost - Setup Cost of new Product being added
     * @param startUnitCost - Unit Cost of new Product being added
     * @param startInventoryCost - Inventory Cost of new Product being added
     * @param startSellingPrice - Selling Price of new Product being added
     */
    public void addProduct(String startName,double startDemandRate, double startSetupCost,
                    double startUnitCost, double startInventoryCost, double startSellingPrice)
    {

        Product newProduct = new Product();
        newProduct.setName(startName);
        newProduct.setDemandRate(startDemandRate);
        newProduct.setSetupCost(startSetupCost);
        newProduct.setUnitCost(startUnitCost);
        newProduct.setInventoryCost(startInventoryCost);
        newProduct.setSellingPrice(startSellingPrice);
        setFirstNullProduct(newProduct);
        numberOfProductsInStore++;
        if(numberOfProductsInStore==productArray.length)
        {
            resizeArray();
        }
    }


    /**
     * Set the First Null product in the store. This method will find the first available slot for creating product data
     * and assign it to that location
     * Precondition: Must be space in Store class
     * Postcondition: Product set to first available slot
     * @param newProduct - Defines the product that is passed in to find the first empty slot.
     */
    private void setFirstNullProduct(Product newProduct)
    {
        for (int i = 0; i<productArray.length; i++)
        {
            if(productArray[i] == null)
            {
                productArray[i] = newProduct;
                break;
            }
        }
    }

    /**
     * Check whether the store is empty or not
     * Precondition: None
     * Postcondition: Return whether empty or not
     * @return - Database is empty or not
     */
    public boolean isEmpty()
    {
        if(numberOfProductsInStore == 0)
            return true;
        else
            return false;
    }

    /**
     * Check whether a product name exists in the store
     * Precondition: None
     * Postcondition: Return whether product exists or not
     * @param productName - Defines the name of the product that is passed into the method
     * @return - Return if certain product exists
     */
    public boolean checkProductExists(String productName)
    {
        if (getProduct(productName)== null)
            return false;
        else
            return true;
    }

    /**
     *
     * Resolves the specified product name to a product in the store
     * Precondition: Product name entered by user must not be null
     * Postcondition: Resolves product based on name
     * @param productName - Name of the product
     * @return - Returns the specific product in the array
     */
    private Product getProduct(String productName)
    {
        for (int i = 0; i<productArray.length;i++) {
            if (productArray[i] != null && productArray[i].getName().equals(productName))
                return productArray[i];
        }
        return null;
    }

    /**
     * Display all product names in the store
     * Precondition: None
     * Postcondition: Generates a list of all product names in the store
     * @return - Returns all product names listed in the store
     */
    public String displayProductNames()
    {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<numberOfProductsInStore;i++)
            sb.append((productArray[i] ==null) ? "\n" : productArray[i].getName()+"\n");
        return sb.toString();
    }

    /**
     * Displays the product information for a product in the store
     * Precondition: The productName parameter must exist in the store
     * Postcondition: Generates a string representing the product's data
     * @param productName - Defines the specific name of the product
     * @return - Returns the data for either the product
     */
    public String displayProductData(String productName)
    {
        for (int i =0;i<numberOfProductsInStore;i++)
        {
            if(productArray[i]!=null && productArray[i].getName().equals(productName))
                return buildSingleProductDataString(productArray[i]);
        }
        return null; //It is assumed that there is already a product in the store with a name
    }

    /**
     * Build single product data string
     * Precondition:Product parameter must not be null
     * Postcondition: Generates Product data printed for a specific product
     * @param input - Name of the product
     * @return - Returns Demand Rate, Setup Cost, Unit Cost, Inventory Cost and Selling Price as a String
     */
    private String buildSingleProductDataString(Product input)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(input.getName());
        sb.append("\t\t"+ input.getDemandRate());
        sb.append("\t\t\t"+ input.getSetupCost());
        sb.append("\t\t"+ input.getUnitCost());
        sb.append("\t\t\t"+ input.getInventoryCost());
        sb.append("\t\t\t\t"+ input.getSellingPrice());
        return sb.toString();
    }

    /**
     * Print a string of all product data in the store
     * Precondition: Products must exist in store
     * Postcondition: Builds product string
     * @return - String of all product Data
     */
    public String printAllProductDataString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<numberOfProductsInStore;i++)
        {
            if(productArray[i]!=null)
            {
                sb.append(buildSingleProductDataString(productArray[i]));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks user input to see whether the user wants to sort the array or not
     * Array can be sorted by name or demand rate
     * Precondition: Products must exist in the store
     * Postcondition: Products will be sorted by name or demand rate or not sorted
     * @param sortingOption - Input entered by user in interface
     * @return - Return string of all product data
     */
    public String printAllProductDataStringWithSortingOption(int sortingOption)
    {
        if(sortingOption == 1)
        {
            return printSortedArrayByName();
        }
        else if(sortingOption == 2)
        {
            return printSortedArrayByDemandRate();
        }
        else if(sortingOption == 3)
        {
            return printAllProductDataString();
        }
        else
        {
            throw new IllegalArgumentException("Invalid parameter sorting option '" + sortingOption+"'.");
        }
    }

    /**
     * Current store array with products is cloned
     * Precondition: Products must exist in the store
     * Postcondition: New array is created with the same product data to be sorted
     * @param inputArray - Name of the array to be cloned
     * @return - Cloned Array
     */
    private Product[] cloneArray(Product[] inputArray)
    {
        //Create temporary array
        Product[] temporaryArray = new Product[inputArray.length];
        //Clone data into temporary array
        for(int i =0;i<inputArray.length;i++)
        {
            if(inputArray[i] != null)
            {
                Product tempProduct = inputArray[i];

                temporaryArray[i] = new Product(tempProduct.getName(),tempProduct.getDemandRate(),
                        tempProduct.getSetupCost(), tempProduct.getUnitCost(), tempProduct.getInventoryCost(),
                        tempProduct.getSellingPrice(), tempProduct.getProfit());
            }

        }
        return temporaryArray;

    }

    /**
     * Print Array sorted by name
     * Utilises a bubble sort method to sort the products
     * Precondition: Products must exist in the store
     * Postcondition: Products will be sorted alphabetically
     * @return - Return string of sorted products
     */
    private String printSortedArrayByName()
    {
        //Clone the array to not modify the original
        Product[] clonedArray = cloneArray(productArray);
        //Sort array by name
        bubbleSortName(clonedArray, numberOfProductsInStore);
        //Generate array string
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<numberOfProductsInStore;i++)
        {
            if(clonedArray[i]!=null)
            {
                sb.append(buildSingleProductDataString(clonedArray[i]));
                sb.append("\n");
            }
        }
        return sb.toString();

    }

    /**
     * Bubble sort method to sort the products by name
     * Precondition: Products must exist in the store
     * Postcondition: Products are sorted alphabetically
     * @param inputArray - Store that will be sorted
     */
    private void bubbleSortName(Product[] inputArray, int arrayLength)
    {
        for (int i = arrayLength - 1; i > 0;i--)
        {
            boolean swappedElement = false;
            for(int j = 0;j<i;j++)
            {
                if(inputArray[j].getName().compareTo(inputArray[j+1].getName())>0)
                {
                    Product tempProduct = inputArray[j];
                    inputArray[j] = inputArray[j+1];
                    inputArray[j+1] = tempProduct;
                    swappedElement = true;
                }
            }
            if(!swappedElement)
            {
                break;
            }

        }
    }

    /**
     * Print Array sorted by demand rate
     * Utilises a bubble sort method to sort the products
     * Precondition: Products must exist in the store
     * Postcondition: Products will be sorted by demand rate
     * @return - Return string of sorted products
     */
    private String printSortedArrayByDemandRate()
    {
        //Clone the array to not modify the original
        Product[] clonedArray = cloneArray(productArray);
        //Sort array by demand rate
        bubbleSortDemandRate(clonedArray, numberOfProductsInStore);
        //Generate array string
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<numberOfProductsInStore;i++)
        {
            if(clonedArray[i]!=null)
            {
                sb.append(buildSingleProductDataString(clonedArray[i]));
                sb.append("\n");
            }
        }
        return sb.toString();

    }

    /**
     * Bubble sort method to sort the products by demand rate
     * Precondition: Products must exist in the store
     * Postcondition: Products are sorted by demand rate
     * @param inputArray - Store that will be sorted
     */
    private void bubbleSortDemandRate(Product[] inputArray, int arrayLength)
    {
        for (int i = arrayLength -1; i > 0;i--)
        {
            boolean swappedElement = false;
            for(int j = 0;j<i;j++)
            {
                if(inputArray[j].getDemandRate()>(inputArray[j+1].getDemandRate()))
                {
                    Product tempProduct = inputArray[j];
                    inputArray[j] = inputArray[j+1];
                    inputArray[j+1] = tempProduct;
                    swappedElement = true;
                }
            }
            if(!swappedElement)
            {
                break;
            }

        }
    }

    /**
     * Deletes Product with matching product name
     * Precondition: Product must exist in store class
     * Postcondition: Product deleted from store
     * @param productName - The product to delete
     */
    public void deleteProduct(String productName)
    {
        //Find the index of deletion
        int foundProduct = findIndexOf(productName);
        if(foundProduct <0)
            return;

        //Delete product at delete index
        productArray[foundProduct] = null;

        // Shift all things that are to the right of the deletion, left one position
        for (int i = foundProduct;i<numberOfProductsInStore;i++)
        {
            productArray[i] = productArray[i+1];
        }

        //Delete last element that would have duplicated
        productArray[numberOfProductsInStore] = null;

        //Decrement number of products in store
        numberOfProductsInStore--;

    }

    /**
     * Helper method used to help find the index of a product in the array
     * Precondition: Product name must exist
     * Postcondition: Returns integer value of element in array
     * @param productName - Name of product used to find element in array
     * @return Integer value of the element where the specific product is stored.
     */
    private int findIndexOf (String productName)
    {
        for (int i  = 0; i< numberOfProductsInStore;i++)
        {
            if(productArray[i]!=null && productArray[i].getName().equals(productName))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Calculates the best profit of a product's replenishment strategy
     * Precondition: None
     * Postcondition:None
     * @return - Returns the profit value
     */
    public String bestProfit()
    {
        double maxProfit = -1 * Double.MAX_VALUE;
        String returnValue ="";
        DecimalFormat df = new DecimalFormat("#.00");
        //Calculate maxProfit
        //Product 1 Profit
        for (int i = 0; i<numberOfProductsInStore;i++)
        {
            if(productArray[i] !=null && productArray[i].getProfit()!=null && productArray[i].getProfit()>maxProfit)
            {
                returnValue = productArray[i].getName()+" "+df.format(productArray[i].getProfit())+"\n";
                maxProfit = productArray[i].getProfit();
            }
            else if (productArray[i] !=null && productArray[i].getProfit()!=null && productArray[i].getProfit()>=maxProfit)
            {
                returnValue += productArray[i].getName()+" "+df.format(productArray[i].getProfit())+"\n";
            }
        }
        return returnValue;
    }

    /**
     *Copies and resizes the current product array and adds one element
     * Precondition: numberOfProductsInStore must equals the array length
     * Postcondition: Copy and resize array adding one extra element
     */
    public void resizeArray()
    {
        Product[] temporaryArray;
        temporaryArray = new Product[productArray.length+1];

        for (int i =0;i<productArray.length;i++)
        {
            temporaryArray[i] = productArray[i];
        }
        productArray = temporaryArray;
    }

     //Getter Methods
     //Used to access data required in the interface class
    /**
     * Get Store name
     * Precondition: None
     * Postcondition: Return name of store
     * @return Name of the store
     */
    public String getStoreName()
    {
        return storeName;
    }

    /**
     * Get the Product Name of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns name of Product
     * @param index - Name of the Product entered by user
     * @return - Return name of specific product
     */
    public String getProductName(int index)
    {
        Product targetProduct = productArray[index];
        if (targetProduct !=null)
            return targetProduct.getName();
        else
            return "";
    }



    /**
     *Get number of products in the Store
     * Precondition: Valid product/s exist within store
     * Postcondition: Returns number of products in store
     * @return Integer value of number of products in store
     */
    public int getNumberOfProductsInStore()
    {
        return numberOfProductsInStore;
    }

    /**
     * Get the Product Name of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns name of Product
     * @param productID - Name of the Product entered by user
     * @return - Return name of specific product
     */
    public String getProductName(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct !=null)
            return targetProduct.getName();
        else
            return "";
    }

    /**
     * Get the Demand Rate of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Demand Rate of Product
     * @param productID - Name of the Product entered by user
     * @return - Return Demand Rate of specific product
     */
    public double getDemandRate(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct !=null)
            return targetProduct.getDemandRate();
        else
            return 0;
    }

    /**
     * Get the Setup Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Setup Cost of Product
     * @param productID - Name of the Product entered by the user
     * @return - Return Setup Cost of specific product
     */
    public double getSetupCost(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct != null)
            return targetProduct.getSetupCost();
        else
            return 0;
    }

    /**
     * Get the Unit Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Unit Cost of Product
     * @param productID - Name of the Product entered by the user
     * @return - Return Unit Cost of a specific Product
     */
    public double getProductUnitCost(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct != null)
            return targetProduct.getUnitCost();
        else
            return 0;
    }

    /**
     * Get the Inventory Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Inventory Cost of specific Product
     * @param productID - Name of the Product entered by the user
     * @return - Return Inventory Cost of a specific Product
     */
    public double getInventoryCost(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct != null)
            return targetProduct.getInventoryCost();
        else
            return 0;
    }

    /**
     * Get the Selling Price of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Selling Price of specific Product
     * @param productID - Name of the Product entered by the user
     * @return - Return Selling Price of a specific Product
     */
    public double getSellingPrice (String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct != null)
            return targetProduct.getSellingPrice();
        else
            return 0;
    }

    /**
     * Get the Profit of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Returns Profit of specific product
     * @param productID - Name of the Product entered by the user
     * @return - Return Profit of a specific Product
     */
    public double getProfit(String productID)
    {
        Product targetProduct = getProduct(productID);
        if (targetProduct!= null)
            return targetProduct.getProfit();
        else
            return 0;
    }

    //Setter Methods
    //Used to set specific Product details to be used in the Interface

    /**
     * Set the Name of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the name of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newName - Return the new Name of the Product
     */
    public void setProductName (String ProductID, String newName)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setName(newName);
    }

    /**
     * Set the Demand Rate of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Demand Rate of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newDemandRate - Return the new Demand Rate of the Product
     */
    public void setProductDemandRate(String ProductID, double newDemandRate)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setDemandRate(newDemandRate);
    }

    /**
     * Set the Setup Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Setup Cost of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newSetupCost - Return the new Setup Cost of the Product
     */
    public void setProductSetupCost(String ProductID, double newSetupCost)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setSetupCost(newSetupCost);
    }

    /**
     * Set the Unit Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Unit Cost of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newUnitCost - Return the new Unit Cost of the Product
     */
    public void setProductUnitCost(String ProductID, double newUnitCost)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setUnitCost(newUnitCost);
    }

    /**
     * Set the Inventory Cost of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Inventory Cost of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newInventoryCost - Return the new Inventory Cost of a specific Product
     */
    public void setInventoryCost(String ProductID, double newInventoryCost)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setInventoryCost(newInventoryCost);
    }

    /**
     * Set the Selling Price of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Selling Price of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newSellingPrice - Return the new Selling Price of a specific Product
     */
    public void setSellingPrice(String ProductID, double newSellingPrice)
    {
        Product targetProduct = getProduct(ProductID);
        if (targetProduct != null)
            targetProduct.setSellingPrice(newSellingPrice);
    }

    /**
     * Set the Profit of a specific Product
     * Precondition: Valid product exists within store that has matching product name
     * Postcondition: Sets the Profit of a specific Product
     * @param ProductID - Name of the Product entered by the user
     * @param newProfit - Return the new Profit of a specific Product
     */
    public void setProfit(String ProductID, double newProfit)
    {
        Product targetProduct = getProduct(ProductID);
        if(targetProduct !=null)
            targetProduct.setProfit(newProfit);
    }

    /**
     * EOQ Equation used to determine the quantity to order
     * Precondition: Valid product exists within store that has valid data
     * Postcondition: Calculate EOQ
     * @param ProductID - Name of the Product entered by the user
     * @param numberOfWeeks - Number of weeks the equation calculates for
     * @return - Return the quantity to order
     */
    public int EOQ(String ProductID, int numberOfWeeks)
    {
        //Equation to determine the Replenishment Strategy       
        Product targetProduct = getProduct(ProductID);
        if(targetProduct !=null)
        {
            int quantity;
            double _2sd,h;
            _2sd = 2*targetProduct.getSetupCost()*targetProduct.getDemandRate();
            h = targetProduct.getInventoryCost()*(numberOfWeeks/targetProduct.getUnitCost());
            quantity = (int)Math.round(Math.sqrt(_2sd/h));
            return quantity;
        }
        else
            return 0;
    }

    /**
     * toString Method returns stores with their associated product data
     * Precondition: None
     * Postcondition: Returns string of store and product data
     * @return - String of both stores and product data where it exists
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        // Store
        sb.append("Store: " + getStoreName());
        sb.append("\n");

        //Number of Products
        sb.append("\tNumber of Products: " + getNumberOfProductsInStore());
        sb.append("\n");

        //Product Number and Name
        for (int i = 0; i<getNumberOfProductsInStore();i++)
        {
            sb.append("\tProduct "+(i+1)+": "+ productArray[i].getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Build file string - Used to save the store and product data to a file
     * Precondition: Product data and Store name must exist
     * Postcondition: Builds string of all product data inside their associated store
     * @return - String of all product and store data
     */
    public String buildFileString()
    {
        StringBuilder sb = new StringBuilder();

        //Store Name
        sb.append(getStoreName()+":\n");

        //Products in store
        for (int i = 0;i<numberOfProductsInStore;i++)
        {
            sb.append("\n");
            sb.append("Name: "+productArray[i].getName()+"\n");
            sb.append("demand rate: "+productArray[i].getDemandRate()+"\n");
            sb.append("setup cost: "+productArray[i].getSetupCost()+"\n");
            sb.append("unit cost: "+productArray[i].getUnitCost()+"\n");
            sb.append("inventory cost: "+productArray[i].getInventoryCost()+"\n");
            sb.append("selling price: "+productArray[i].getSellingPrice()+"\n");
        }

        return sb.toString();
    }

}

