import java.awt.*;
import javax.swing.*;

public class SmartParkGUI extends JFrame implements ParkingOperations {

    JTextField vehicleField, ownerField, hoursField;
    JPanel[] slots = new JPanel[8 ];
    String selectedSlot = "";

    public SmartParkGUI() {

        ParkingDAO.createTable();

        setTitle("Smart Parking System");
        setSize(650, 500);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(5, 2, 5, 5));

        vehicleField = new JTextField();
        ownerField = new JTextField();
        hoursField = new JTextField();

        JButton bookBtn = new JButton("BOOK");
        JButton reserveBtn = new JButton("RESERVE");
        JButton releaseBtn = new JButton("RELEASE");

        top.add(new JLabel("Vehicle"));
        top.add(vehicleField);
        top.add(new JLabel("Owner"));
        top.add(ownerField);
        top.add(new JLabel("Hours"));
        top.add(hoursField);
        top.add(bookBtn);
        top.add(reserveBtn);
        top.add(releaseBtn);

        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2, 3, 10, 10));

        for (int i = 0; i < 8; i++) {
            int index = i;

            slots[i] = new JPanel();
            slots[i].setBackground(Color.GREEN);
            slots[i].add(new JLabel("S" + (i + 1)));

            slots[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {

                    resetSelection();
                    selectedSlot = "S" + (index + 1);
                    slots[index].setBackground(Color.YELLOW);
                }
            });

            center.add(slots[i]);
        }

        add(center, BorderLayout.CENTER);

        bookBtn.addActionListener(e -> bookSlot());
        reserveBtn.addActionListener(e -> reserveSlot());
        releaseBtn.addActionListener(e -> releaseSlot());

        new LiveUpdater(this).start();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetSelection() {
        for (JPanel slot : slots) {
            if (slot.getBackground() == Color.YELLOW)
                slot.setBackground(Color.GREEN);
        }
    }

    @Override
    

    

        public void bookSlot() {

    try {

        if (selectedSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a slot first!");
            return;
        }

        String vehicle = vehicleField.getText().trim();
        String owner = ownerField.getText().trim();
        String hoursText = hoursField.getText().trim();

        if (vehicle.isEmpty() || owner.isEmpty() || hoursText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields required!");
            return;
        }

        int hours = Integer.parseInt(hoursText);

        if (hours <= 0) {
            JOptionPane.showMessageDialog(this, "Hours must be > 0");
            return;
        }

        // 🔹 CHECK IF VEHICLE ALREADY EXISTS
        if (ParkingDAO.isVehicleExists(vehicle)) {

            int choice = JOptionPane.showConfirmDialog(this,
                    "Vehicle already booked.\nDo you want to extend time?",
                    "Extend Booking",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {

                String extraInput =
                        JOptionPane.showInputDialog("Enter extra hours:");

                if (extraInput == null) return;

                int extraHours = Integer.parseInt(extraInput);

                if (extraHours <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid hours!");
                    return;
                }

                int extraAmount = extraHours * 50;

                PaymentDialog pd =
                        new PaymentDialog(this, owner, vehicle,
                                extraHours, selectedSlot);

                pd.setVisible(true);

                if (pd.paymentSuccess) {

                    ParkingDAO.extendBooking(vehicle,
                            extraHours, extraAmount);

                    JOptionPane.showMessageDialog(this,
                            "Time Extended Successfully!\n" +
                                    "Extra Hours: " + extraHours +
                                    "\nAmount Paid: ₹" + extraAmount);
                }
            }
            return;
        }

        // 🔹 NORMAL BOOKING
        int slot = Integer.parseInt(selectedSlot.replace("S", ""));

        PaymentDialog pd =
                new PaymentDialog(this, owner, vehicle,
                        hours, selectedSlot);

        pd.setVisible(true);

        if (pd.paymentSuccess) {

            int amount = hours * 50;

            ParkingDAO.insertBooking(slot,
                    vehicle, owner,
                    hours, amount, "BOOKED");

            slots[slot - 1].setBackground(Color.RED);

            JOptionPane.showMessageDialog(this,
                    "Booking Successful!\n\n" +
                            "Vehicle: " + vehicle +
                            "\nOwner: " + owner +
                            "\nSlot: " + selectedSlot +
                            "\nHours: " + hours +
                            "\nAmount: ₹" + amount);
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid number format!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error occurred!");
    }
}

    @Override
    public void reserveSlot() {

        if (selectedSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a slot first!");
            return;
        }

        int slot = Integer.parseInt(selectedSlot.replace("S", ""));
        ParkingDAO.insertBooking(slot, "", "", 0, 0, "RESERVED");
        slots[slot - 1].setBackground(Color.BLUE);
    }

    @Override
    public void releaseSlot() {

        if (selectedSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a slot first!");
            return;
        }

        int slot = Integer.parseInt(selectedSlot.replace("S", ""));
        ParkingDAO.releaseSlot(slot);
        slots[slot - 1].setBackground(Color.GREEN);
    }

    public void refreshUI() {
        repaint();
    }

    public static void main(String[] args) {
        new SmartParkGUI();
    }
}

