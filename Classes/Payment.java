package Classes;


import Classes.BkashPay;
import javax.swing.*;
import java.util.HashMap;

public class Payment {
    private HashMap<String, Double> services;
    private double totalAmount;

    public Payment(HashMap<String, Double> services) {
        this.services = services;
        this.totalAmount = 0.0;
        
		calculateTotal();
    }


    private void calculateTotal() {
        for (double price : services.values()) {
            totalAmount += price;
        }
    }

    // Display the invoice with all selected services and the total amount
    public void showInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice:\n\n");
        
        for (String service : services.keySet()) {
            invoice.append(service).append(": ").append(services.get(service)).append("\n");
        }
        
        invoice.append("\n------------------\n");
        invoice.append("Total Amount: ").append(totalAmount).append("\n");

        JOptionPane.showMessageDialog(null, invoice.toString(), "Invoice", JOptionPane.INFORMATION_MESSAGE);
    }

    public void processPayment() {
        showInvoice(); // Display the invoice first

        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to proceed with the payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
           
		   
		   //JOptionPane.showMessageDialog(null, "Payment Successful!", "Payment", JOptionPane.INFORMATION_MESSAGE);
		   

		    new BkashPay();
		   
        } else {
            JOptionPane.showMessageDialog(null, "Payment Canceled!", "Payment", JOptionPane.WARNING_MESSAGE);
        }
    }
}
 
 