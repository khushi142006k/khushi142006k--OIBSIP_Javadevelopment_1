import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Reservation {
    String pnr;
    String passengerName;
    int trainNumber;
    String trainName;
    String classType;
    String journeyDate;
    String fromPlace;
    String destination;

    public Reservation(String pnr, String passengerName, int trainNumber,
                       String trainName, String classType,
                       String journeyDate, String fromPlace,
                       String destination) {
        this.pnr = pnr;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.fromPlace = fromPlace;
        this.destination = destination;
    }

    public void display() {
        System.out.println("\n----- Reservation Details -----");
        System.out.println("PNR Number      : " + pnr);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Train Number    : " + trainNumber);
        System.out.println("Train Name      : " + trainName);
        System.out.println("Class Type      : " + classType);
        System.out.println("Journey Date    : " + journeyDate);
        System.out.println("From            : " + fromPlace);
        System.out.println("Destination     : " + destination);
    }
}

public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);

    
    static Map<String, Reservation> reservations = new HashMap<>();

    static Map<Integer, String> trains = new HashMap<>();

    static {
        trains.put(101, "Rajdhani Express");
        trains.put(102, "Shatabdi Express");
        trains.put(103, "Duronto Express");
        trains.put(104, "Garib Rath");
    }

    public static void main(String[] args) {

        System.out.println("===== ONLINE RESERVATION SYSTEM =====");

        if (login()) {
            int choice;

            do {
                System.out.println("\n1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        reservationForm();
                        break;

                    case 2:
                        cancellationForm();
                        break;

                    case 3:
                        System.out.println("Thank You!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 3);
        } else {
            System.out.println("Invalid Login Credentials!");
        }
    }


    static boolean login() {
        String validUser = "system";
        String validPass = "1122";

        System.out.print("Login ID: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        return user.equals(validUser) && pass.equals(validPass);
    }

    
    static void reservationForm() {

        System.out.println("\n===== Reservation Form =====");

        System.out.print("Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Train Number: ");
        int trainNo = sc.nextInt();
        sc.nextLine();

        String trainName = trains.get(trainNo);

        if (trainName == null) {
            System.out.println("Invalid Train Number!");
            return;
        }

        System.out.println("Train Name: " + trainName);

        System.out.print("Class Type (Sleeper/AC): ");
        String classType = sc.nextLine();

        System.out.print("Date of Journey: ");
        String date = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("Destination: ");
        String destination = sc.nextLine();

        String pnr = generatePNR();

        Reservation reservation = new Reservation(
                pnr, name, trainNo, trainName,
                classType, date, from, destination);

        reservations.put(pnr, reservation);

        System.out.println("\nReservation Successful!");
        System.out.println("Generated PNR Number: " + pnr);
    }

    
    static void cancellationForm() {

        System.out.println("\n===== Cancellation Form =====");

        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine();

        Reservation reservation = reservations.get(pnr);

        if (reservation == null) {
            System.out.println("No Reservation Found!");
            return;
        }

        reservation.display();

        System.out.print("\nConfirm Cancellation? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            reservations.remove(pnr);
            System.out.println("Ticket Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Aborted.");
        }
    }

    static String generatePNR() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }
}