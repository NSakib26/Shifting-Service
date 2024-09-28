package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class BkashPay {
    private String BkashNumber;
    private String Otp;
    private String Pin;

     public BkashPay () {
     
        getBkashDetails();
        processPayment();
    }

    private void getBkashDetails() {
        BkashNumber = JOptionPane.showInputDialog(null, "Enter Bkash Number:", "Bkash Payment", JOptionPane.QUESTION_MESSAGE);
       
        Otp = JOptionPane.showInputDialog(null, "Enter OTP:", "Bkash Payment", JOptionPane.QUESTION_MESSAGE);
        Pin = JOptionPane.showInputDialog(null, "Enter Bkash PIN:", "Bkash Payment", JOptionPane.QUESTION_MESSAGE);
    }

    private void processPayment() {
        
        if (isValid()) {
            JOptionPane.showMessageDialog(null, "Payment Processed Successfully!\nThank you for your payment.", "Payment", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Payment Failed! Invalid card details.", "Payment", JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean isValid() {
        
        return BkashNumber != null && !BkashNumber.isEmpty() &&
               Otp != null && !Otp.isEmpty() &&
               Pin != null && !Pin.isEmpty();
    }
}
