/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parkingapp;

/**
 *
 * @author kyle
 */
  

    public class ParkingApp extends JFrame {

        private CustomDoublyLinkedList parkingList;
        private JLabel lblStatus;

        public ParkingApp() {
            parkingList = new CustomDoublyLinkedList();

            setTitle("Parking Vehicle Management System");
            setSize(880, 620);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            lblStatus = new JLabel(" Status: Ready");
            lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
            add(lblStatus, BorderLayout.SOUTH);
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
