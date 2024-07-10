// Import the respective packages
import java.util.*;
import java.awt.Dimension;// Panel GUI
import java.io.*;
import javax.swing.*; // GUI

public class OrderLogistics 
{
    public static void main(String[] args) 
    {
        try 
        {
            // Create a file reader to read the input file
            BufferedReader ind = new BufferedReader(new FileReader("orders.txt"));
            
            // Create ArrayList to store orders
            ArrayList<Order> orderList = new ArrayList<>();
            
            // Declare inData to read a line in input file
            String inData = null;
            
            // Read file line by line
            while ((inData = ind.readLine()) != null) 
            {
                StringTokenizer st = new StringTokenizer(inData, "|");
                String orderID = st.nextToken();
                String cusName = st.nextToken();
                String cusAddress = st.nextToken();
                String accessories = st.nextToken();
                int quantity = Integer.parseInt(st.nextToken());
                double pricePerUnit = Double.parseDouble(st.nextToken());
                String orderStatus = st.nextToken();
                
                // Create a new Order object and add to the list
                Order comp = new Order(orderID, cusName, cusAddress, accessories, quantity, pricePerUnit, orderStatus);
                orderList.add(comp);
            }
            ind.close();
            
            int menu = 0;
            String input;
            
            do 
            {
                // Display menu options 
                System.out.println("Menu:");
                System.out.println("1- Add a new order");
                System.out.println("2- Update an order");
                System.out.println("3- Delete an order");
                System.out.println("4- Display orders");
                System.out.println("5- Exit");
                
                // Ask user to enter menu choice
                input = JOptionPane.showInputDialog("Enter menu option:");
                menu = Integer.parseInt(input);
                
                switch (menu) 
                
                {
                    case 1:
                        // Add a new order
                        String orderID = JOptionPane.showInputDialog("Enter the order's ID:");
                        String cusName = JOptionPane.showInputDialog("Enter the customer's name:");
                        String cusAddress = JOptionPane.showInputDialog("Enter the customer's address:");
                        String accessories = JOptionPane.showInputDialog("Enter the accessory ordered:");
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity ordered:"));
                        double pricePerUnit = Double.parseDouble(JOptionPane.showInputDialog("Enter the price per unit:"));
                        String orderStatus = JOptionPane.showInputDialog("Enter the order's status:");
                        
                        Order newOrder = new Order(orderID, cusName, cusAddress, accessories, quantity, pricePerUnit, orderStatus);
                        orderList.add(newOrder);
                        break;
                    
                    case 2:
                        // Update an order
                        orderID = JOptionPane.showInputDialog("Enter the order's ID to update:");
                        
                        for (Order order : orderList) 
                        {
                            if (order.getOrderID().equalsIgnoreCase(orderID)) 
                            {
                                cusName = JOptionPane.showInputDialog("Enter the customer's name:", order.getCusName());
                                cusAddress = JOptionPane.showInputDialog("Enter the customer's address:", order.getCusAddress());
                                accessories = JOptionPane.showInputDialog("Enter the accessory ordered:", order.getAccessories());

                                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity ordered:", order.getQuantity()));
                                pricePerUnit = Double.parseDouble(JOptionPane.showInputDialog("Enter the price per unit:", order.getPricePerUnit()));
                                orderStatus = JOptionPane.showInputDialog("Enter the order's status:", order.getOrderStatus());
                                
                                // Update the existing order
                                order.setCusName(cusName);
                                order.setCusAddress(cusAddress);
                                order.setAccessories(accessories);
                                order.setQuantity(quantity);
                                order.setPricePerUnit(pricePerUnit);
                                order.setOrderStatus(orderStatus);
                                break;
                            }
                        }
                        break;
                    
                    case 3:
                        // Delete an order
                        orderID = JOptionPane.showInputDialog("Enter the order's ID to delete:");
                        
                        orderList.removeIf(order -> order.getOrderID().equalsIgnoreCase(orderID));
                        break;
                    
                    case 4:
                        // Display orders
                        String[] options = {"Display all orders", "Display orders by name", "Display orders by product", "Display orders by status"};
                        int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Display Orders", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                        switch (choice) 
                        {
                            case 0: // Display all orders
                                displayOrders(orderList, null, null, null);
                                break;
                            case 1: // Display orders by name
                                String name = JOptionPane.showInputDialog("Enter the customer's name:");
                                displayOrders(orderList, name, null, null);
                                break;
                            case 2: // Display orders by product
                                String productName = JOptionPane.showInputDialog("Enter the accessory's ordered:");
                                displayOrders(orderList, null, productName, null);
                                break;
                            case 3: // Display orders by status
                                String status = JOptionPane.showInputDialog("Enter the order's status:");
                                displayOrders(orderList, null, null, status);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option");
                                break;
                        }
                        break;
                    
                    case 5:
                        // Exit the program
                        break;
                    
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid menu option. Please try again.");
                        break;
                }
            } while (menu != 5);
            
        } catch (FileNotFoundException fnfe) 
        {
            System.out.println("File not found");
        } catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        }
    }
    
    private static void displayOrders(List<Order> orderList, String cusName, String accessories, String orderStatus) 
    {
    StringBuilder message = new StringBuilder();
    
    for (Order order : orderList) 
    {
        if ((cusName == null || order.getCusName().equalsIgnoreCase(cusName)) &&
            (accessories == null || order.getAccessories().equalsIgnoreCase(accessories)) &&
            (orderStatus == null || order.getOrderStatus().equalsIgnoreCase(orderStatus))) 
            {
                message.append(order.toString()).append("\n-----------------------------------------------\n");
            }
    }
        
        JTextArea textArea = new JTextArea(message.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Orders", JOptionPane.INFORMATION_MESSAGE);
    }
}// End of class