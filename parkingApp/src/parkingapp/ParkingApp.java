
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// ============================================================================
// IT21: OOP DOMAIN MODELS (Parent & Child Classes)
// ============================================================================
/**
 * Base abstract class representing a parked vehicle. Enforces encapsulation
 * with private attributes and controlled access.
 */
abstract class Vehicle {

    private String plateNumber;
    private String ownerName;
    private String spotId;
    private String notes;

    public Vehicle(String plateNumber, String ownerName, String spotId, String notes) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.spotId = spotId;
        this.notes = notes;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public abstract String getVehicleType();
}

/**
 * Child Class 1: Car representing standard 4-wheel passenger vehicles.
 */
class Car extends Vehicle {

    public Car(String plateNumber, String ownerName, String spotId, String notes) {
        super(plateNumber, ownerName, spotId, notes);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

/**
 * Child Class 2: Motorcycle representing 2-wheel vehicles.
 */
class Motorcycle extends Vehicle {

    public Motorcycle(String plateNumber, String ownerName, String spotId, String notes) {
        super(plateNumber, ownerName, spotId, notes);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }
}

/**
 * Child Class 3: Dynamic Custom Vehicle type allowing user-defined vehicle
 * categories.
 */
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

// ============================================================================
// IT20: DATA STRUCTURES & SEARCHING ALGORITHM (No java.util)
// ============================================================================
/**
 * Node wrapper for Doubly Linked List element.
 */
class Node {

    Vehicle data;
    Node prev;
    Node next;

    public Node(Vehicle data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

/**
 * Custom Doubly Linked List implementation for IT20 (Data Structures &
 * Algorithms). Built completely from scratch without using any java.util
 * collection frameworks. Manages dynamic memory using bidirectional node
 * references (head and tail pointers).
 */
class CustomDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructs an empty CustomDoublyLinkedList.
     */
    public CustomDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Appends a new Vehicle node to the end of the list. Time Complexity: O(1)
     *
     * @param vehicle Vehicle record to add
     */
    public void add(Vehicle vehicle) {
        Node newNode = new Node(vehicle);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes a vehicle matching the plate number and adjusts node links. Time
     * Complexity: O(n)
     *
     * @param plateNumber Target plate number to remove
     * @return true if removed, false otherwise
     */
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

    /**
     * IT20 Searching Algorithm: Performs a manual linear search across node
     * pointers. Time Complexity: O(n)
     *
     * @param query Term matching plate, spot ID, owner, or vehicle type
     * @return Vehicle if match found, null otherwise
     */
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

    /**
     * Converts linked node data to an array for GUI display.
     *
     * @return Vehicle array containing all records
     */
    public Vehicle[] toArray() {
        Vehicle[] array = new Vehicle[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    public int getSize() {
        return size;
    }
}

// ============================================================================
// GUI & APPLICATION CONTROLLER (Java Swing)
// ============================================================================
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

        // Seed Initial Records
        parkingList.add(new Car("ABC-1234", "John Doe", "A-01", "Sedan - Blue"));
        parkingList.add(new Motorcycle("XYZ-9876", "Jane Smith", "B-12", "150cc Helmet Included"));
        parkingList.add(new CustomVehicle("TRK-5555", "Mark Wilson", "C-09", "Truck", "Heavy Duty"));

        setTitle("Parking Vehicle Management System");
        setSize(880, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL: Search Control ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Vehicle (IT20 Manual Linear Search)"));

        searchPanel.add(new JLabel("Plate / Spot / Owner / Type:"));
        txtSearch = new JTextField(15);
        searchPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        JButton btnResetSearch = new JButton("Show All");
        searchPanel.add(btnSearch);
        searchPanel.add(btnResetSearch);

        lblStatus = new JLabel(" Status: Ready");
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchPanel.add(lblStatus);

        add(searchPanel, BorderLayout.NORTH);

        // --- WEST PANEL: Input Form ---
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

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton btnAddType = new JButton("+ Manage/Add Vehicle Type");
        formPanel.add(btnAddType, gbc);

        gbc.gridy = 6;
        JButton btnAdd = new JButton("Park Vehicle");
        formPanel.add(btnAdd, gbc);

        gbc.gridy = 7;
        JButton btnDelete = new JButton("Unpark Selected/Plate");
        formPanel.add(btnDelete, gbc);

        add(formPanel, BorderLayout.WEST);

        // --- CENTER PANEL: Table Display ---
        String[] columns = {"Plate Number", "Owner Name", "Spot ID", "Type", "Notes / Details"};
        tableModel = new DefaultTableModel(columns, 0);
        tableRecords = new JTable(tableModel);
        add(new JScrollPane(tableRecords), BorderLayout.CENTER);

        // --- EVENT HANDLING ---
        cbType.addActionListener(e -> {
            String selected = (String) cbType.getSelectedItem();
            if (selected != null && selected.equals("+ Add New Type...")) {
                promptAddNewType();
            }
        });

        btnAddType.addActionListener(e -> promptAddNewType());
        btnAdd.addActionListener(e -> addRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnSearch.addActionListener(e -> performSearch());
        btnResetSearch.addActionListener(e -> refreshTableData(parkingList.toArray()));

        // Initial Table Load
        refreshTableData(parkingList.toArray());
    }

    private void promptAddNewType() {
        String newType = JOptionPane.showInputDialog(this, "Enter new Vehicle Type (e.g., Van, Scooter, Bus):", "Add Vehicle Type", JOptionPane.QUESTION_MESSAGE);

        if (newType != null && !newType.trim().isEmpty()) {
            newType = newType.trim();

            for (int i = 0; i < typeModel.getSize(); i++) {
                if (typeModel.getElementAt(i).equalsIgnoreCase(newType)) {
                    cbType.setSelectedItem(typeModel.getElementAt(i));
                    return;
                }
            }

            int insertIndex = typeModel.getSize() - 1;
            typeModel.insertElementAt(newType, insertIndex);
            cbType.setSelectedItem(newType);
            lblStatus.setText(" Status: Added new vehicle type '" + newType + "'");
            lblStatus.setForeground(new Color(0, 128, 0));
        } else {
            cbType.setSelectedIndex(0);
        }
    }

    private void refreshTableData(Vehicle[] vehicles) {
        tableModel.setRowCount(0);
        for (Vehicle v : vehicles) {
            tableModel.addRow(new Object[]{
                v.getPlateNumber(),
                v.getOwnerName(),
                v.getSpotId(),
                v.getVehicleType(),
                v.getNotes()
            });
        }
    }

    private void addRecord() {
        String plate = txtPlate.getText().trim();
        String owner = txtOwner.getText().trim();
        String spot = txtSpot.getText().trim();
        String selectedType = (String) cbType.getSelectedItem();
        String notes = txtNotes.getText().trim();

        // Validation Checks
        if (selectedType == null || selectedType.equals("+ Add New Type...")) {
            JOptionPane.showMessageDialog(this, "Please select or add a valid vehicle type.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (plate.isEmpty() || owner.isEmpty() || spot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plate Number, Owner Name, and Spot ID are required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (notes.isEmpty()) {
            notes = "N/A";
        }

        if (selectedType.equals("Car")) {
            parkingList.add(new Car(plate, owner, spot, notes));
        } else if (selectedType.equals("Motorcycle")) {
            parkingList.add(new Motorcycle(plate, owner, spot, notes));
        } else {
            parkingList.add(new CustomVehicle(plate, owner, spot, selectedType, notes));
        }

        refreshTableData(parkingList.toArray());
        clearInputs();
        lblStatus.setText(" Status: Parked " + selectedType + " (" + plate + ")");
        lblStatus.setForeground(new Color(0, 128, 0));
    }

    private void deleteRecord() {
        int selectedRow = tableRecords.getSelectedRow();
        String plateToRemove = "";

        if (selectedRow != -1) {
            plateToRemove = tableModel.getValueAt(selectedRow, 0).toString();
        } else {
            plateToRemove = txtPlate.getText().trim();
        }

        if (plateToRemove.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a row or enter a Plate Number to unpark.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean removed = parkingList.removeByPlate(plateToRemove);
        if (removed) {
            refreshTableData(parkingList.toArray());
            clearInputs();
            lblStatus.setText(" Status: Unparked Vehicle " + plateToRemove);
            lblStatus.setForeground(Color.BLUE);
        } else {
            JOptionPane.showMessageDialog(this, "Plate Number not found in system.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performSearch() {
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a search query.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle result = parkingList.search(query);
        if (result != null) {
            refreshTableData(new Vehicle[]{result});
            lblStatus.setText(" Status: Found vehicle '" + query + "'");
            lblStatus.setForeground(new Color(0, 128, 0));
        } else {
            tableModel.setRowCount(0);
            lblStatus.setText(" Status: No match found for '" + query + "'");
            lblStatus.setForeground(Color.RED);
        }
    }

    private void clearInputs() {
        txtPlate.setText("");
        txtOwner.setText("");
        txtSpot.setText("");
        txtNotes.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingApp().setVisible(true));
    }
}
