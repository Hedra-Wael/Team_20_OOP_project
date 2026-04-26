package com.mycompany.oop_project;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int reservationCounter = 1001;
    private static int invoiceCounter = 5001;

    public static void main(String[] args) {
        // 1. Initialize Data
        HotelDatabase.loaddata();

        System.out.println("--- Hotel Reception Management System ---");
        boolean running = true;

        //DISPLAY TO THE RECEPTIONIST
        while (running) {
            System.out.println("\n1. Check-in Guest (Create Reservation)");
            System.out.println("2. Process Checkout & Payment");
            System.out.println("3. View All Rooms Status");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> handleCheckIn();
                case 2 -> handleCheckOut();
                case 3 -> viewRoomStatus();
                case 4 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("System closed.");
    }
    //TAKING THE NAMES OF THE GUESTS TO CHECK IN THE ROOM WITH HIS NAME
    //CHECKING IN THE GUESTS
    private static void handleCheckIn() {
        System.out.print("Enter Lead Guest (Account Holder) Username: ");
        String leadUsername = scanner.nextLine();

        Guest leadGuest = findGuest(leadUsername);
        if (leadGuest == null) {
            leadGuest = new Guest();
            leadGuest.setUsername(leadUsername);
            leadGuest.setPassword("default123");
            leadGuest.setBalance(20000.0);
            HotelDatabase.guests.add(leadGuest);
        }
        //CHECKING THE NUMBER OF GUESTS TO KNOW HOW MANY ROOMS IS NEEDED ALSO IF THERE IS ANY DISCOUNT
        System.out.print("Total number of persons in the group: ");
        int totalPeople = scanner.nextInt();

        // "Trip" Policy: Over 20 people
        if (totalPeople > 20) {
            System.out.println("\n[NOTICE]: Groups larger than 20 are considered a 'Trip'.");
            System.out.println("Please contact the Hotel Manager for group rates and manual booking.");
            return; // Exit the method
        }

        int peopleRemaining = totalPeople;
        double groupSubtotal = 0; // To track total cost before discount
        //REMINDING THE RECEPTIONIST THE NUMBER OF GUESTS REMAINING
        while (peopleRemaining > 0) {
            System.out.println("\n--- Booking for Room Space (Remaining: " + peopleRemaining + ") ---");

            scanner.nextLine();
            System.out.print("Enter occupant name: ");
            String occupantName = scanner.nextLine();
            //ASKING THE GUEST OF THE TYPE OF ROOM HE NEEDS
            System.out.println("Select Room Type (1: Single, 2: Double, 3: Suite): ");
            int typeChoice = scanner.nextInt();

            int capacity = (typeChoice == 1) ? 1 : 2;
            double nightlyRate = switch (typeChoice) {
                case 2 -> 120.0;
                case 3 -> 250.0;
                default -> 80.0;
            };

            // Showing rooms matching your numbering rules (10X, 20X, 30X)
            System.out.print("Available Rooms: ");
            for (Room r : HotelDatabase.rooms) {
                if (r.isAvailable() && r.getRoomNumber() % 10 == typeChoice) {
                    System.out.print("[" + r.getRoomNumber() + "] ");
                }
            }

            System.out.print("\nEnter Room Number: ");
            int roomNum = scanner.nextInt();
            Room selectedRoom = findRoom(roomNum);

            if (selectedRoom != null && selectedRoom.isAvailable()) {
                System.out.print("How many nights? ");
                int nights = scanner.nextInt();

                System.out.print("All-Inclusive? (1: Yes / 2: No): ");
                boolean isAllInc = (scanner.nextInt() == 1);

                double roomPrice = (nights * nightlyRate) + (isAllInc ? (nights * 50 * capacity) : 0);
                groupSubtotal += roomPrice; // Adding to the group's bill

                System.out.println("Price for this room (" + occupantName + "): $" + roomPrice);

                selectedRoom.setAvailable(false);
                new Reservation(reservationCounter++, leadGuest, selectedRoom,
                        LocalDate.now(), LocalDate.now().plusDays(nights));

                peopleRemaining -= capacity;
                if (peopleRemaining < 0) peopleRemaining = 0;
            }
        }

        // Apply Discount FOR WHO ARE MORE THAN 5 GUESTS TOGETHER
        double discount = 0.0;
        if (totalPeople >= 6 && totalPeople <= 10) {
            discount = 0.05; // 5%
        } else if (totalPeople >= 11 && totalPeople <= 20) {
            discount = 0.10; // 10%
        }

        double discountAmount = groupSubtotal * discount;
        double finalTotal = groupSubtotal - discountAmount;
        
        
        System.out.println("\n=========================================");
        System.out.println("         GROUP CHECK-IN COMPLETE         ");
        System.out.println("=========================================");
        System.out.println("Total People:    " + totalPeople);
        System.out.println("Subtotal:        $" + groupSubtotal);

        if (discount > 0) {
            System.out.println("Discount (" + (int)(discount * 100) + "%): -$" + discountAmount);
        }

        System.out.println("FINAL TOTAL:     $" + finalTotal);
        System.out.println("Price per person: $" + (finalTotal / totalPeople));
        System.out.println("=========================================");
    }



    //FOR GUESTS WHO ARE CHECKING OUT
    private static void handleCheckOut() {
        System.out.print("Enter Guest Username for Checkout: ");
        String username = scanner.nextLine();

        // In a full system, you'd find the active Reservation linked to this guest
        // For this demo, let's simulate the billing process
        System.out.print("Enter Nightly Rate for this stay: ");
        double rate = scanner.nextDouble();

        // Simulate finding the reservation (Simplification for the Main logic)
        Guest guest = findGuest(username);
        if (guest != null) {
            // We create a temporary reservation to demonstrate the Invoice logic
            Room dummyRoom = new Room(0, new RoomType("Standard", rate));
            Reservation res = new Reservation(999, guest, dummyRoom, LocalDate.now(), LocalDate.now().plusDays(2));

            Invoice invoice = new Invoice(invoiceCounter++, res, rate, 0.1);
            System.out.println("Total Amount Due: $" + invoice.getTotalAmount());
            System.out.println("Guest Current Balance: $" + guest.getBalance());

            System.out.println("Select Payment (1. CASH, 2. CREDIT_CARD): ");
            int pChoice = scanner.nextInt();
            Invoice.PaymentMethod method = (pChoice == 2) ? Invoice.PaymentMethod.CREDIT_CARD : Invoice.PaymentMethod.CASH;

            invoice.processPayment(method);
        } else {
            System.out.println("Guest not found.");
        }
    }

    //VIEW ALL ROOMS AVAILABLE OR OCCUPIED
    private static void viewRoomStatus() {
        System.out.println("\n--- Current Room Status ---");
        for (Room r : HotelDatabase.rooms) {
            String status = r.isAvailable() ? "[Available]" : "[Occupied]";
            System.out.println("Room " + r.getRoomNumber() + " : " + status);
        }
    }

    private static Guest findGuest(String name) {
        return HotelDatabase.guests.stream()
                .filter(g -> g.getUsername().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    private static Room findRoom(int num) {
        return HotelDatabase.rooms.stream()
                .filter(r -> r.getRoomNumber() == num)
                .findFirst().orElse(null);
    }
}
