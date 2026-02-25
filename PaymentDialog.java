import java.awt.*;
import javax.swing.*;

public class PaymentDialog extends JDialog {

    public boolean paymentSuccess = false;

    public PaymentDialog(JFrame parent, String owner, String vehicle, int hours, String slot) {

        super(parent, "Smart Park Payment", true);

        if (hours <= 0) {
            JOptionPane.showMessageDialog(parent, "Invalid hours!");
            dispose();
            return;
        }

        int amount = hours * 50;

        setSize(320, 350);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SMART PARK PAYMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(5, 1));

        center.add(new JLabel("Owner: " + owner));
        center.add(new JLabel("Vehicle: " + vehicle));
        center.add(new JLabel("Slot: " + slot));
        center.add(new JLabel("Hours: " + hours));
        center.add(new JLabel("Total Amount: ₹" + amount));

        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2, 1));

        JComboBox<String> apps =
                new JComboBox<>(new String[]{"GPay", "PhonePe", "Paytm"});
        bottom.add(apps);

        JButton pay = new JButton("Pay Now");
        bottom.add(pay);

        pay.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Payment Successful ✅");
            paymentSuccess = true;
            dispose();
        });

        add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(parent);
    }
}