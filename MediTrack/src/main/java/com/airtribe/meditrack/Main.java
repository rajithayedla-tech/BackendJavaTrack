package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.entity.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.util.Scanner;

public class Main {

        private static final Scanner scanner = new Scanner(System.in);

        private static final PatientService patientService = new PatientService();
        private static final DoctorService doctorService = new DoctorService();
        private static final AppointmentService appointmentService = new AppointmentService();

        public static void main(String[] args) {

            System.out.println(Constants.APP_NAME);

            while (true) {
                printMenu();
                int choice = readInt();

                try {
                    switch (choice) {
                        case 1 -> addPatient();
                        case 2 -> addDoctor();
                        case 3 -> viewPatients();
                        case 4 -> viewDoctors();
                        case 5 -> createAppointment();
                        case 6 -> viewAppointments();
                        case 7 -> generateBill();
                        case 0 -> exitApp();
                        default -> System.out.println("Invalid option. Try again.");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        private static void printMenu() {
            System.out.println("""
                
                ===== MENU =====
                1. Add Patient
                2. Add Doctor
                3. View Patients
                4. View Doctors
                5. Create Appointment
                6. View Appointments
                7. Generate Bill
                0. Exit
                """);
        }

        // ---------------- PATIENT ----------------
        private static void addPatient() {
            System.out.print("Patient ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = readInt();

            patientService.addPatient(new Patient(id, name, age));
            System.out.println("Patient added successfully.");
        }

        private static void viewPatients() {
            patientService.viewPatients();
        }

        // ---------------- DOCTOR ----------------
        private static void addDoctor() {
            System.out.print("Doctor ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = readInt();

            System.out.print("Fee: ");
            double fee = readDouble();

            doctorService.addDoctor(
                    new Doctor(id, name, age, Specialization.GENERAL, fee)
            );
            System.out.println("Doctor added successfully.");
        }

        private static void viewDoctors() {
            doctorService.viewDoctors();
        }

        // ---------------- APPOINTMENT ----------------
        private static void createAppointment() {

            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine();

            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();

            Patient patient = patientService.searchById(patientId);
            Doctor doctor = doctorService.searchById(doctorId);

            if (patient == null || doctor == null) {
                throw new InvalidDataException("Invalid Patient or Doctor ID");
            }

            appointmentService.createAppointment(patient, doctor);
            System.out.println("Appointment created successfully.");
        }

        private static void viewAppointments() {
            appointmentService.viewAppointments();
        }

        // ---------------- BILLING ----------------
        private static void generateBill() {

            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();

            Doctor doctor = doctorService.searchById(doctorId);

            if (doctor == null) {
                throw new InvalidDataException("Doctor not found");
            }

            Bill bill = new Bill(doctor);
            double total = bill.calculateTotal();

            bill.printReceipt(total);
        }

        // ---------------- HELPERS ----------------
        private static int readInt() {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Invalid number input");
            }
        }

        private static double readDouble() {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Invalid decimal input");
            }
        }

        private static void exitApp() {
            System.out.println("Thank you for using MediTrack.");
            System.exit(0);
        }
}
