/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parkingapp;

/**
 *
 * @author kyle
 */
// Added in Commit 6: Declare input form components and fields
  

    public class ParkingApp extends JFrame {

        private CustomDoublyLinkedList parkingList;

        private JTextField txtPlate, txtOwner, txtSpot, txtNotes, txtSearch;
        private JComboBox<String> cbType;
        private DefaultComboBoxModel<String> typeModel;
        private JLabel lblStatus;
        private JTable tableRecords;
        private DefaultTableModel tableModel;

        public ParkingApp() {
            parkingList = new CustomDoublyLinkedList();

            setTitle("Parking Vehicle Management System");
            setSize(880, 620);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            // --- WEST PANEL: Form Inputs (GridBagLayout) ---
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(BorderFactory.createTitledBorder("Park New Vehicle"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(new JLabel("Plate Number:"), gbc);
            gbc.gridx = 1;
            txtPlate = new JTextField(12);
            formPanel.add(txtPlate, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(new JLabel("Owner Name:"), gbc);
            gbc.gridx = 1;
            txtOwner = new JTextField(12);
            formPanel.add(txtOwner, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(new JLabel("Spot ID:"), gbc);
            gbc.gridx = 1;
            txtSpot = new JTextField(12);
            formPanel.add(txtSpot, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            formPanel.add(new JLabel("Vehicle Type:"), gbc);
            gbc.gridx = 1;
            typeModel = new DefaultComboBoxModel<>(new String[]{"Car", "Motorcycle", "Truck", "+ Add New Type..."});
            cbType = new JComboBox<>(typeModel);
            formPanel.add(cbType, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            formPanel.add(new JLabel("Notes/Details:"), gbc);
            gbc.gridx = 1;
            txtNotes = new JTextField(12);
            formPanel.add(txtNotes, gbc);

            add(formPanel, BorderLayout.WEST);

            lblStatus = new JLabel(" Status: Ready");
            lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
            add(lblStatus, BorderLayout.SOUTH);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new ParkingApp().setVisible(true));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingApp().setVisible(true));
    }

    }

    public static void main(String[] args) {
        class Node {

            Vehicle data;
            Node prev;
            Node next;

            public Node(Vehicle data) {
                this.data = data;
                this.prev = null;
                this.next = null;

            }

            class CustomDoublyLinkedList {

                private Node head;
                private Node tail;
                private int size;

                public void add(Vehicle vehicle) {
                
            

            ... }
}
            public boolean removeByPlate(String plateNumber) {
                if (head == null || plateNumber == null || plateNumber.trim().isEmpty()) {
                    return false;
                }

                Node current = head;
                while (current != null) {
                    if (current.data != null && current.data.getPlateNumber() != null
                            && current.data.getPlateNumber().equalsIgnoreCase(plateNumber.trim())) {

                        if (current.prev != null) {
                            current.prev.next = current.next;
                        } else {
                            head = current.next;
                        }

                        if (current.next != null) {
                            class Car extends Vehicle {

                                public Car(String plateNumber, String ownerName, String spotId, String notes) {
                                    super(plateNumber, ownerName, spotId, notes);
                                }

                                @Override
                                public String getVehicleType() {
                                    return "Car";
                                }
                            }
                            current.next.prev = current.prev;
                        } else {
                            tail = current.prev;
                        }

                        size--;
                        return true;
                    }
                    current = current.next;
                }
                return false;
            }

            public Vehicle search(String query) {

                if (head == null || query == null || query.trim().isEmpty()) {
                    return null;
                }

                String cleanQuery = query.trim();
                Node current = head;
                while (current != null) {
                    if (current.data != null) {
                        if ((current.data.getPlateNumber() != null && current.data.getPlateNumber().equalsIgnoreCase(cleanQuery))
                                || (current.data.getSpotId() != null && current.data.getSpotId().equalsIgnoreCase(cleanQuery))
                                || (current.data.getOwnerName() != null && current.data.getOwnerName().equalsIgnoreCase(cleanQuery))
                                || (current.data.getVehicleType() != null && current.data.getVehicleType().equalsIgnoreCase(cleanQuery))) {
                            return current.data;
                        }
                    }
                    current = current.next;
                }
                return null;
            }

            if (current.data.getPlateNumber().equalsIgnoreCase(query) || 
    current.data.getSpotId().equalsIgnoreCase(query) ||
    current.data.getOwnerName().equalsIgnoreCase(query) ||
    current.data.getVehicleType().equalsIgnoreCase(query)) {
    return current.data;
}
abstract class Vehicle {

                private String plateNumber, ownerName, spotId, notes;

                public Vehicle(String plateNumber, String ownerName, String spotId, String notes) {
                    this.plateNumber = plateNumber;
                    this.ownerName = ownerName;
                    this.spotId = spotId;
                    this.notes = notes;
                }
                // Getters and setters omitted for brevity...

                public abstract String getVehicleType();
            }

            class Car extends Vehicle {

                public Car(String plateNumber, String ownerName, String spotId, String notes) {
                    super(plateNumber, ownerName, spotId, notes);
                }

                @Override
                public String getVehicleType() {
                    return "Car";
                }
            }

            class Motorcycle extends Vehicle {

                public Motorcycle(String plateNumber, String ownerName, String spotId, String notes) {
                    super(plateNumber, ownerName, spotId, notes);
                }

                @Override
                public String getVehicleType() {
                    return "Motorcycle";
                }
            }

            class CustomVehicle extends Vehicle {

                private String customType;

                public CustomVehicle(String plateNumber, String ownerName, String spotId, String customType, String notes) {
                    super(plateNumber, ownerName, spotId, notes);
                    this.customType = customType;
                }

                @Override
                public String getVehicleType() {
                    return customType;
                }
            }
        }
