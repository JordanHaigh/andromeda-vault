
/**
 * Product class stores all instance variables about the product.
 * This includes productName, productDemandRate, productSetupCost,
 * productUnitCost, productInventoryCost,productSellingPrice.
 * The class also contains accessor and mutator methods to be used
 * in the Store class.
 * @author Jordan Haigh (c3256730)
 * @version 2.0
 */
public class Product
{
    // instance variables - replace the example below with your own
    private String name;            //Product Name
    private double demandRate;      //Product Demand Rate
    private double setupCost;       //Product Setup Cost
    private double unitCost;        //Product Unit Cost
    private double inventoryCost;   //Inventory Cost
    private double sellingPrice;    //Product Selling Price
    private Double profit;          //Product Profit

    /**
     * Constructor for objects of Class Product
     * Initialise the instance variables by setting the name as an empty string
     * and the other variables to zero
     */
    public Product()
    {
        name = "";
        demandRate = 0;
        setupCost = 0;
        unitCost = 0;
        inventoryCost = 0;
        sellingPrice = 0;
        profit = null;
    }

    /**
     * Overloaded constructor
     * @param name - Name of the product
     * @param demandRate - Demand Rate of the product
     * @param setupCost - Setup Cost of the product
     * @param unitCost - Unit Cost of the product
     * @param inventoryCost - Inventory Cost of the product
     * @param sellingPrice - Selling Price of the product
     * @param profit - Profit of the product
     */
    public Product(String name, double demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice, Double profit)
    {
        this.name = name;
        this.demandRate = demandRate;
        this.setupCost = setupCost;
        this.unitCost = unitCost;
        this.inventoryCost = inventoryCost;
        this.sellingPrice = sellingPrice;
        this.profit = profit;
    }


    //Getter Functions
    //Used to access data required in the interface class
    /**
     * Get Name of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Return name of product
     * @return - String name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get Demand Rate of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Demand Rate of the Product
     * @return - double demandRate
     */
    public double getDemandRate()
    {
        return demandRate;
    }

    /**
     * Get Setup Cost of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Setup Cost of the Product
     * @return - double setupCost
     */
    public double getSetupCost()
    {
        return setupCost;
    }

    /**
     * Get Unit Cost of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Unit Cost of the Product
     * @return - double unitCost
     */
    public double getUnitCost()
    {
        return unitCost;
    }

    /**
     * Get Inventory Cost of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Inventory Cost of the Product
     * @return - double inventoryCost
     */
    public double getInventoryCost()
    {
        return inventoryCost;
    }

    /**
     * Get Selling Price of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Selling Price of the Product
     * @return - double sellingPrice
     */
    public double getSellingPrice()
    {
        return sellingPrice;
    }

    /**
     * Get the Profit of Product
     * Precondition: Valid product exists, cannot equal null
     * Postcondition: Returns the Profit of the Product
     * @return - double profit
     */
    public Double getProfit()
    {
        return profit;
    }


    //Setter Functions
    //Used to set Product Details used in the Interface
    /**
     * Set Name of Product
     * Precondition: Valid products exists, cannot equal null
     * Postcondition: Assigns the original 'name' variable to 'newName' to be used in the Store Class
     * @param newName - Sets the name of a product instance
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * Set Demand Rate of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'demandRate' variable to 'newDemandRate' to be used in the Store Class
     * @param newDemandRate - Sets the Demand Rate of a product instance
     */
    public void setDemandRate(double newDemandRate)
    {
        demandRate = newDemandRate;
    }

    /**
     * Set Setup Cost of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'setupCost' variable to 'newSetupCost' to be used in the Store Class
     * @param newSetupCost - Sets the Setup Cost of a product instance
     */
    public void setSetupCost(double newSetupCost)
    {
        setupCost = newSetupCost;
    }

    /**
     * Set Unit Cost of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'unitCost' variable to 'newUnitCost' to be used in the Store Class
     * @param newUnitCost - Sets the Unit Cost of a product instance
     */
    public void setUnitCost(double newUnitCost)
    {
        unitCost = newUnitCost;
    }

    /**
     * Set Inventory Cost of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'inventoryCost' to 'newInventoryCost' to be used in the Store Class
     * @param newInventoryCost - Sets the Inventory Cost of a product instance
     */
    public void setInventoryCost(double newInventoryCost)
    {
        inventoryCost = newInventoryCost;
    }

    /**
     * Set Selling Price of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'sellingPrice' to 'newSellingPrice' to be used in the Store Class
     * @param newSellingPrice  - Sets the Selling Price of a product instance
     */
    public void setSellingPrice(double newSellingPrice)
    {
        sellingPrice = newSellingPrice;
    }

    /**
     * Set Profit of Product
     * Precondition: Must have Double Input
     * Postcondition: Assigns the original 'profit' to 'newProfit' to be used in the Store Class
     * @param newProfit - Sets the Profit of a product instance
     */
    public void setProfit(Double newProfit)
    {
        profit = newProfit;
    }

    /**
     * toString Method of a Product
     * Precondition: Product data must exist
     * Postcondition: Returns string of product data
     * @return Returns string of Product Name, Demand Rate, Setup Cost, Unit Cost, Inventory Cost and Selling Price
     */
    public String toString()
    {
        return "Product Name: "+name+" Demand Rate: "+demandRate+" Setup Cost: "+setupCost+" Unit Cost: "+unitCost+" Inventory Cost: "+inventoryCost+" Selling Price: "+sellingPrice;
    }
}