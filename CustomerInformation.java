// Import the respective packages
import java.util.ArrayList;
import java.util.List;

public class CustomerInformation 
{
    // Declaration of Attributes
    private String custID;
    private String custName;
    private int tableNumber;
    private List<OrderInformation> custOrders; // Updated attribute name to reflect multiple orders

    // Constructor without param
    public CustomerInformation() 
    {
        custID = null;
        custName = null;
        tableNumber = 0;
        custOrders = new ArrayList<>(); // Initialize custOrders as ArrayList
    }

    // Constructor with param
    public CustomerInformation(String custID, String custName, int tableNumber, List<OrderInformation> custOrders) 
    {
        this.custID = custID;
        this.custName = custName;
        this.tableNumber = tableNumber;
        this.custOrders = custOrders;
    }

    // Mutator/Setter for single order
    public void addOrder(OrderInformation order) 
    {
        custOrders.add(order);
    }

    // Mutator/Setter for multiple orders
    public void setCustOrders(List<OrderInformation> custOrders) 
    {
        this.custOrders = custOrders;
    }

    public void setCustID(String custID) 
    {
        this.custID = custID;
    }

    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public void setTableNumber(int tableNumber) 
    {
        this.tableNumber = tableNumber;
    }

    // Accessor/Getter for single order
    public OrderInformation getOrder(int index) 
    {
        return custOrders.get(index);
    }

    // Accessor/Getter for all orders
    public List<OrderInformation> getCustOrders() 
    {
        return custOrders;
    }

    public String getCustID() 
    {
        return custID;
    }

    public String getCustName() 
    {
        return custName;
    }

    public int getTableNumber() 
    {
        return tableNumber;
    }

    // Display info (toString method)
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Customer's Information]").append("\n");
        sb.append("Customer's ID : ").append(custID).append("\n");
        sb.append("Customer's Name : ").append(custName).append("\n");
        sb.append("Table : ").append(tableNumber).append("\n");
        sb.append("[Orders] ").append("\n");

        for (OrderInformation order : custOrders) 
        {
            sb.append(order.toString()).append("\n");
        }

        return sb.toString();
    }
}// End of class
