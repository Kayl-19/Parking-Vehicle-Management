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

                public CustomDoublyLinkedList() {
                    this.head = null;
                    this.tail = null;
                    this.size = 0;
                }

                public int getSize() {
                    return size;
                }
            }

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

            public boolean removeByPlate(String plateNumber) {
                Node current = head;
                while (current != null) {
                    if (current.data.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                        if (current.prev != null) {
                            current.prev.next = current.next;
                        } else {
                            head = current.next; // Head update
                        }
                        if (current.next != null) {
                            current.next.prev = current.prev;
                        } else {
                            tail = current.prev; // Tail update
                        }
                        size--;
                        return true;
                    }
                    current = current.next;
                }
                return false;
            }

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

        }
