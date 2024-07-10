// Import the respective packages
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.awt.Dimension; // Panel GUI

// Class Definition
public class ClienteleRegistry 
{
    // Static variables for counters
    static Queue<CustomerInformation> counter1Queue = new LinkedList<>();
    static Queue<CustomerInformation> counter2Queue = new LinkedList<>();
    static Queue<CustomerInformation> counter3Queue = new LinkedList<>();
    static Stack<CustomerInformation> completeStack = new Stack<>();
    
    public static void main(String[] args) 
    {
        try 
        {
            // File reading setup
            BufferedReader ind = new BufferedReader(new FileReader("Customer's Information.txt"));
            ArrayList<CustomerInformation> customerList = new ArrayList<>();
            String inData = null;
            
            // Read customer information from file
            while ((inData = ind.readLine()) != null) 
            {
                StringTokenizer st = new StringTokenizer(inData, "|");
                
                // Customer's Information
                String custID = st.nextToken();
                String custName = st.nextToken();
                int tableNumber = Integer.parseInt(st.nextToken());
                
                // Orders information
                List<OrderInformation> orders = new ArrayList<>();
                
                // Loop through remaining tokens for orders
                while (st.hasMoreTokens()) 
                {
                    String orderID = st.nextToken();
                    String itemName = st.nextToken();
                    double itemPrice = Double.parseDouble(st.nextToken().trim());
                    int quantity = Integer.parseInt(st.nextToken().trim());
                    String orderTimeStr = st.nextToken().trim();
                    LocalDateTime orderTime = LocalDateTime.parse(orderTimeStr);
                    
                    // Create OrderInformation object
                    OrderInformation order = new OrderInformation(orderID, itemName, itemPrice, quantity, orderTime);
                    
                    // Add order to the list of orders
                    orders.add(order);
                }
                
                // Create CustomerInformation object with orders
                CustomerInformation customer = new CustomerInformation(custID, custName, tableNumber, orders);
                
                // Add customer to the list
                customerList.add(customer);
            }
            ind.close();
            
            int menu = 0;
            String input;
            
            // Menu-driven interface
            do 
            {
                // Display menu options
                System.out.println("Menu:");
                System.out.println("1- Add a new customer");
                System.out.println("2- Remove a customer");
                System.out.println("3- Remove an order");
                System.out.println("4- Display orders");
                System.out.println("5- Process Orders");
                System.out.println("6- Exit");
                
                // Prompt user for menu option
                input = JOptionPane.showInputDialog("Enter menu option:\n"
                        + "1- Add a new customer\n"
                        + "2- Remove a customer\n"
                        + "3- Remove an order\n"
                        + "4- Display orders\n"
                        + "5- Process Orders\n"
                        + "6- Exit");
                
                // Parse user input
                menu = Integer.parseInt(input);
                
                // Process menu option
                switch (menu) 
                {
                    case 1:
                        // Adding a new customer to the list
                        String custID = JOptionPane.showInputDialog("Enter the customer's ID:");
                        String custName = JOptionPane.showInputDialog("Enter the customer's name:");
                        int tableNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the customer's assigned table number:"));
                        
                        // Orders for the new customer
                        List<OrderInformation> newOrders = new ArrayList<>();
                        
                        // Prompt user to add orders
                        boolean addAnotherOrder = true;
                        do 
                        {
                            String orderID = JOptionPane.showInputDialog("Enter the order's ID:");
                            String itemName = JOptionPane.showInputDialog("Enter the customer's ordered item:");
                            double itemPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the item's price:"));
                            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity ordered:"));
                            String orderTimeStr = JOptionPane.showInputDialog("Enter the order's timestamp:");
                            LocalDateTime orderTime = LocalDateTime.parse(orderTimeStr);
                            
                            // Create OrderInformation object
                            OrderInformation order = new OrderInformation(orderID, itemName, itemPrice, quantity, orderTime);
                            
                            // Add order to the list of orders
                            newOrders.add(order);
                            
                            // Ask if user wants to add another order
                            int option = JOptionPane.showConfirmDialog(null, "Add another order?", "Add Order", JOptionPane.YES_NO_OPTION);
                            if (option != JOptionPane.YES_OPTION) {
                                addAnotherOrder = false;
                            }
                        } while (addAnotherOrder);
                        
                        // Create CustomerInformation object with orders
                        CustomerInformation newCustomer = new CustomerInformation(custID, custName, tableNumber, newOrders);
                        
                        // Add new customer to the list
                        customerList.add(newCustomer);
                        JOptionPane.showMessageDialog(null, "Customer added successfully.");
                        break;
                        
                    case 2:
                        // Removing a customer from the list
                        String removeCustID = JOptionPane.showInputDialog("Enter the customer's ID to be removed:");
                        boolean removed = customerList.removeIf(customer -> customer.getCustID().equalsIgnoreCase(removeCustID));
                        
                        if (removed) 
                        {
                            JOptionPane.showMessageDialog(null, "Customer removed successfully.");
                        } else 
                        {
                            JOptionPane.showMessageDialog(null, "Customer not found.");
                        }
                        break;
                    
                    case 3:
                        // Removing an order
                        String removeOrderID = JOptionPane.showInputDialog("Enter the order's ID to delete:");
                        boolean orderRemoved = false;
                        
                        // Iterate through customers and remove order if found
                        for (CustomerInformation customer : customerList) 
                        {
                            boolean found = customer.getCustOrders().stream()
                                    .anyMatch(order -> order.getOrderID().equalsIgnoreCase(removeOrderID));
                            
                            if (found) 
                            {
                                customer.getCustOrders().removeIf(order -> order.getOrderID().equalsIgnoreCase(removeOrderID));
                                orderRemoved = true;
                                break;
                            }
                        }
                        
                        if (orderRemoved) 
                        {
                            JOptionPane.showMessageDialog(null, "Customer's order removed successfully.");
                        } else 
                        {
                            JOptionPane.showMessageDialog(null, "Customer's order not found.");
                        }
                        break;
                    
                    case 4:
                        // Displaying orders
                        StringBuilder sb = new StringBuilder();
                        for (CustomerInformation customer : customerList) 
                        {
                            sb.append(customer.toString()).append("\n");
                        }
                        
                        // Create a JTextArea to display the orders
                        JTextArea textArea = new JTextArea(sb.toString());
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        scrollPane.setPreferredSize(new Dimension(400, 300));
                        
                        // Display orders in a scrollable dialog
                        JOptionPane.showMessageDialog(null, scrollPane, "Customer Orders", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    
                    case 5:
                        // Processing Orders
                        processOrders(customerList);
                        break;
                    
                    case 6:
                        JOptionPane.showMessageDialog(null, "Exiting...");
                        break;
                        
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a number from 1 to 6.");
                        break;
                }
                
            } while (menu != 6); // Exit loop when menu option 6 is selected
            
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        }
    }
    
    // Method to process orders and distribute to counters
    private static void processOrders(List<CustomerInformation> customerList) 
    {
        // Initialize counters
        int counter1Count = 0;
        int counter2Count = 0;
        int counter3Count = 0;
        
        // Distribute customers to queues based on items ordered
        for (CustomerInformation customer : customerList) 
        {
            int numItems = customer.getCustOrders().size();
            
            if (numItems <= 5) 
            {
                if (counter1Count <= counter2Count) 
                {
                    counter1Queue.add(customer);
                    counter1Count++;
                } else 
                {
                    counter2Queue.add(customer);
                    counter2Count++;
                }
            } else 
            {
                counter3Queue.add(customer);
                counter3Count++;
            }
        }
        
        // Process counters and display in GUI
        StringBuilder processingInfo = new StringBuilder();
        processCounterGUI(customerList, counter1Queue, "Counter 1", processingInfo);
        processCounterGUI(customerList, counter2Queue, "Counter 2", processingInfo);
        processCounterGUI(customerList, counter3Queue, "Counter 3", processingInfo);
        
        // Display processing information in a scrollable dialog
        JTextArea processingTextArea = new JTextArea(processingInfo.toString());
        JScrollPane processingScrollPane = new JScrollPane(processingTextArea);
        processingTextArea.setLineWrap(true);
        processingTextArea.setWrapStyleWord(true);
        processingScrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, processingScrollPane, "Processing Orders", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to process a counter queue and display in GUI
    private static void processCounterGUI(List<CustomerInformation> customerList, Queue<CustomerInformation> queue, String counterName, StringBuilder processingInfo) 
    {
        int count = 0;
        int customersProcessed = 0;
        
        // Process each customer in the queue
        Iterator<CustomerInformation> iterator = queue.iterator();
        while (iterator.hasNext() && customersProcessed < 5) 
        {
            CustomerInformation customer = iterator.next();
            if (customerList.contains(customer)) {
                processingInfo.append(counterName).append(" - Customer: ").append(customer.getCustName()).append("\n");
                for (OrderInformation order : customer.getCustOrders()) {
                    processingInfo.append("Ordered Items: ").append(order.getItemName()).append(", Quantity: ").append(order.getQuantity()).append("\n");
                    double totalAmount = order.getItemPrice() * order.getQuantity();
                    processingInfo.append("Total Amount: $").append(totalAmount).append("\n\n");
                }
                completeStack.push(customer); // Store completed transaction in completeStack
                iterator.remove();
                
                // Notify processing of next set of customers
                if (++count % 5 == 0 && customersProcessed < 4) 
                {
                    processingInfo.append("Processing next set of customers...\n\n");
                    customersProcessed++;
                }
            }
        }
        
        // Display message if all customers processed
        if (!iterator.hasNext()) 
        {
            processingInfo.append("All customers at ").append(counterName).append(" have been processed.\n\n");
        }
    }
}// End of Class

