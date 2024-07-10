// Class definition
public class Order 
{
    // Declaration of attributes
    private String orderID;
    private String cusName;
    private String cusAddress;
    private String accessories;
    private int quantity;
    private double pricePerUnit;
    private String orderStatus;
    
    // Constructor without param
    public Order()
    {
        orderID = null;
        cusName = null;
        cusAddress = null;
        accessories = null;
        quantity = 0;
        pricePerUnit = 0.0;
        orderStatus = null;
    }
    
    // Constructor with param
    public Order(String orderID, String cusName, String cusAddress, String accessories, int quantity, double pricePerUnit, String orderStatus) 
    {
        this.orderID = orderID;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.accessories = accessories;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.orderStatus = orderStatus;
    }
    
    // Mutator/Setter for each attribute
    public void setOrderID(String orderID) 
    {
        this.orderID = orderID;
    }
    
    public void setCusName(String cusName) 
    {
        this.cusName = cusName;
    }
    
    public void setCusAddress(String cusAddress) 
    {
        this.cusAddress = cusAddress;
    }
    
    public void setAccessories(String accessories) 
    {
        this.accessories = accessories;
    }
    
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
    
    public void setPricePerUnit(double pricePerUnit) 
    {
        this.pricePerUnit = pricePerUnit;
    }
    
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }
    
    // Accessor/Retriever/Getter method
    public String getOrderID() 
    {
        return orderID;
    }
    
    public String getCusName() 
    {
        return cusName;
    }
    
    public String getCusAddress() 
    {
        return cusAddress;
    }
    
    public String getAccessories() 
    {
        return accessories;
    }
    
    public int getQuantity() 
    {
        return quantity;
    }
    
    public double getPricePerUnit() 
    {
        return pricePerUnit;
    }
    
    public double getTotalPrice() 
    {
        return quantity * pricePerUnit;
    }
    
    public String getOrderStatus() 
    {
        return orderStatus;
    }
    
    // Display info (toString method)
    public String toString() 
    {
        return "Order's ID : " + orderID + "\n" + "Customer's Name : " +
                cusName + "\n" + "Customer's Address : " + cusAddress + "\n" +
                "Accessory Ordered : " + accessories + "\n" + "Quantity Ordered : " + quantity + "\n" +
                "Price Per Unit : " + pricePerUnit + "\n" + "Total Price : " + getTotalPrice() + "\n" +
                "Order's Status : " + orderStatus + "\n";
    }
}// End of class