/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parkingapp;

/**
 *
 * @author kyle
 */
public class ParkingApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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

        }
