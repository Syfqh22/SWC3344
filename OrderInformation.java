// Import the respective packages
import java.time.LocalDateTime; //baru blh guna data type utk tarikh/masa

// Class Definition
public class OrderInformation
{
    // Declaration of Attributes
    private String orderID;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private LocalDateTime orderTime;
    
    // Constructor without param
    public OrderInformation()
    {
        orderID = null;
        itemName = null;
        itemPrice = 0.0;
        quantity = 0;
        orderTime = LocalDateTime.now(); //set current date/time
    }
    
    // Constructor with param
    public OrderInformation( String orderID, String itemName, double itemPrice, int quantity, LocalDateTime orderTime)
    {
        this.orderID = orderID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.orderTime = orderTime;
    }
    
    // Mutator/Setter
    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    public void setItemPrice(double itemPrice)
    {
        this.itemPrice = itemPrice;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public void setOrderTime(LocalDateTime orderTime)
    {
        this.orderTime = orderTime;
    }
    
    // Accessor/Retriever/Getter
    public String getOrderID()
    {
        return orderID;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public double getItemPrice()
    {
        return itemPrice;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public LocalDateTime getOrderTime()
    {
        return orderTime;
    }
    
    // Display info (toString method)
    public String toString() 
    {
        return "\n" + "Order's ID : " + orderID + "\n" + "Item's Name : " +
                itemName + "\n" + "Item's price : " + itemPrice + "\n" +
                "Quantity : " + quantity + "\n" + "Timestamp : " + orderTime + "\n";
    }
}// End of Class
