package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.entity.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.DateUtil;

import java.util.Date;
import java.util.List;
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
                        case 5 -> searchPatient();
                        case 6 -> searchDoctor();
                        case 7 -> createAppointment();
                        case 8 -> viewAppointments();
                        case 9 -> cancelAppointment();
                        case 10 -> generateBill();
                        case 11 -> clonePatientOrAppointment();
                        case 12 -> immutableBillSummary();
                        case 13 -> saveDataToCSV();
                        case 14 -> loadDataFromCSV();
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
                5. Search Patient (by ID/Name/Age)
                6. Search Doctor (by Name/Specialization)"
                7. Create Appointment
                8. View Appointments
                9. Cancel Appointment
                10. Generate Bill
                11. Clone Patient/Appointment
                12. Immutable Bill Summary
                13. Save Data to CSV
                14. Load Data from CSV
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

            // Show specialization options
            System.out.println("Choose Specialization:");
            Specialization[] specs = Specialization.values();
            for (int i = 0; i < specs.length; i++) {
                System.out.println((i + 1) + ". " + specs[i]);
            }
            System.out.print("Enter choice (1-" + specs.length + "): ");
            int specChoice = scanner.nextInt();
            scanner.nextLine();
            Specialization specialization;
            if (specChoice >= 1 && specChoice <= specs.length) {
                specialization = specs[specChoice - 1];
            } else {
                System.out.println("Invalid choice. Defaulting to GENERAL_MEDICINE.");
                specialization = Specialization.GENERAL;
            }

            System.out.print("Fee: ");
            double fee = readDouble();

            doctorService.addDoctor(
                    new Doctor(id, name, age, specialization, fee)
            );
            System.out.println("Doctor added successfully.");
        }

        private static void viewDoctors() {
            doctorService.viewDoctors();
        }

        private static void searchPatient() {
            System.out.println("Search Patient by: 1.ID 2.Name 3.Age");
            Patient patient = null;
            int psearchChoice = scanner.nextInt();
            scanner.nextLine();
            switch (psearchChoice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String searchPid = scanner.nextLine();
                    patient = patientService.searchById(searchPid);
                    break;
                case 2:
                    System.out.print("Enter Patient Name: ");
                    String searchPname = scanner.nextLine();
                    patient = patientService.searchPatient(searchPname);
                    break;
                case 3:
                    System.out.print("Enter Patient Age: ");
                    int searchPage = scanner.nextInt();
                    patient = patientService.searchPatient(searchPage);
                    break;
            }

            if(patient != null){
                System.out.println(patient.getSummary());
            } else {
                System.out.println("Patient not found!");
            }
        }

        private static void searchDoctor() {
            System.out.println("Search Doctor by: 1.Name 2.Specialization");
            int dsearchChoice = scanner.nextInt();
            scanner.nextLine();
            switch (dsearchChoice) {
                case 1:
                    System.out.print("Enter Doctor Name: ");
                    String searchDname = scanner.nextLine();
                    Doctor doctor = doctorService.searchDoctor(searchDname);
                    if(doctor != null){
                        System.out.println(doctor.getSummary());
                    } else {
                        System.out.println("Doctor not found!");
                    }
                    break;
                case 2:
                    // Show specialization options
                    System.out.println("Choose Specialization:");
                    Specialization[] specs = Specialization.values();
                    for (int i = 0; i < specs.length; i++) {
                        System.out.println((i + 1) + ". " + specs[i]);
                    }
                    System.out.print("Enter choice (1-" + specs.length + "): ");
                    int specChoice = scanner.nextInt();
                    scanner.nextLine();
                    Specialization specialization;
                    if (specChoice >= 1 && specChoice <= specs.length) {
                        specialization = specs[specChoice - 1];
                    } else {
                        System.out.println("Invalid choice. Defaulting to GENERAL_MEDICINE.");
                        specialization = Specialization.GENERAL;
                    }
                    List<Doctor> doctorsBySpec = doctorService.searchDoctor(specialization);
                    if (doctorsBySpec.isEmpty()) {
                        System.out.println("No doctors found with specialization: " + specialization.name());
                    } else {
                        doctorsBySpec.forEach(System.out::println);
                    }
                    break;
            }
        }

        // ---------------- APPOINTMENT ----------------
        private static void createAppointment() {

            System.out.print("Enter Appointment ID: ");
            String apptId = scanner.nextLine();
            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
            Doctor selectedDoctor = doctorService.searchById(doctorId);
            if (selectedDoctor == null) {
                System.out.println("Doctor not found!");
            } else {
                System.out.print("Enter Patient ID: ");
                String patientId = scanner.nextLine();
                Patient selectedPatient = patientService.searchById(patientId);
                if (selectedPatient == null) {
                    System.out.println("Patient not found!");
                } else {
                    System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
                    String dateStr = scanner.nextLine();
                    Date date = DateUtil.parseDate(dateStr);
                    // utility to parse string → Date
                    appointmentService.createAppointment(apptId, selectedDoctor, selectedPatient, date);
                    System.out.println("Appointment created successfully.");
                }
            }
        }

        private static void viewAppointments() {
            appointmentService.viewAppointments();
        }

        private static void cancelAppointment() {
            System.out.print("Enter Appointment ID to cancel: ");
            String cancelId = scanner.nextLine();
            appointmentService.cancelAppointment(cancelId);
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

        private static void clonePatientOrAppointment() {
            System.out.println("Clone Options: 1. Patient 2. Appointment");
            int cloneChoice = scanner.nextInt();
            scanner.nextLine();
            switch (cloneChoice) {
                case 1:
                    System.out.print("Enter Patient ID to clone: ");
                    String clonePid = scanner.nextLine();
                    Patient originalPatient = patientService.searchById(clonePid);
                    if (originalPatient != null) {
                        Patient clonedPatient = originalPatient.clone();
                        System.out.println("Original: " + originalPatient);
                        System.out.println("Cloned: " + clonedPatient);
                    } else {
                        System.out.println("Patient not found with ID: " + clonePid);
                    }
                    break;
                case 2:
                    System.out.print("Enter Appointment ID to clone: ");
                    String cloneAid = scanner.nextLine();
                    Appointment originalAppt = appointmentService.getAppointmentById(cloneAid);
                    if (originalAppt != null) {
                        Appointment clonedAppt = originalAppt.clone();
                        System.out.println("Original: " + originalAppt);
                        System.out.println("Cloned: " + clonedAppt);
                    } else {
                        System.out.println("Appointment not found with ID: " + cloneAid);
                    }
                    break;
                default:
                    System.out.println("Invalid clone option.");
            }
        }

        private static void immutableBillSummary() {
            System.out.print("Enter Patient ID: ");
            String billPid = scanner.nextLine();
            System.out.print("Enter Doctor ID: ");
            String billDid = scanner.nextLine();
            System.out.print("Enter Consultation Fee: ");
            double consultFee = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Medicine Charges: ");
            double medCharges = scanner.nextDouble();
            scanner.nextLine();
            BillSummary bill = new BillSummary(billPid, billDid, consultFee, medCharges);
            System.out.println("Immutable Bill Summary created:");
            System.out.println(bill);
        }

        private static void saveDataToCSV() {
            System.out.println("Save Options: 1. Patients 2. Doctors 3. Appointments");
            int saveChoice = scanner.nextInt();
            scanner.nextLine();
            switch (saveChoice) {
                case 1:
                    patientService.savePatientsToCSV("src/main/resources/patients.csv");
                    break;
                case 2:
                    doctorService.saveDoctorsToCSV("src/main/resources/doctors.csv");
                    break;
                case 3:
                    appointmentService.saveAppointmentsToCSV("src/main/resources/appointments.csv");
                    break;
                default:
                    System.out.println("Invalid save option.");
            }
        }

        private static void loadDataFromCSV() {
            System.out.println("Load Options: 1. Patients 2. Doctors 3. Appointments");
            int loadChoice = scanner.nextInt();
            scanner.nextLine();
            switch (loadChoice) {
                case 1:
                    patientService.loadPatientsFromCSV("src/resources/patients.csv");
                    break;
                case 2:
                    doctorService.loadDoctorsFromCSV("src/resources/doctors.csv");
                    break;
                case 3:
                    appointmentService.loadAppointmentsFromCSV("src/resources/appointments.csv", doctorService, patientService);
                    break;
                default:
                    System.out.println("Invalid load option.");
            }
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
