package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // CREATE
    public void createAppointment(String id, Doctor doctor, Patient patient, Date date) {
        store.add(new Appointment(id, doctor, patient, date));
    }

    // READ
    public void viewAppointments() {
        store.getAll().forEach(a -> System.out.println(a.summary()));
    }

    // SEARCH
    public Appointment getAppointmentById(String id) {
        return store.getAll().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found: " + id)
                );
    }

    // CANCEL
    public void cancelAppointment(String id) {
        Appointment appt = getAppointmentById(id);
        if (appt != null) {
            appt.setStatus(AppointmentStatus.CANCELLED);
            System.out.println("Appointment cancelled: " + appt);
        } else {
            System.out.println("Appointment not found with ID: " + id);
        }
    }

    public void saveAppointmentsToCSV(String filename) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // consistent format
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ID,DoctorID,PatientID,Date,Status");
            for (Appointment a : store.getAll()) {
                writer.printf("%s,%s,%s,%s,%s%n",
                        a.getId(),
                        a.getDoctor().getId(),
                        a.getPatient().getId(),
                        sdf.format(a.getDate()),   // <-- formatted date
                        a.getStatus());
            }
            System.out.println("Appointments saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving appointments to CSV: " + e.getMessage());
        }
    }

    public void loadAppointmentsFromCSV(String filename, DoctorService doctorService, PatientService patientService) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    Doctor doctor = doctorService.searchById(parts[1]);
                    Patient patient = patientService.searchById(parts[2]);
                    Date date = sdf.parse(parts[3]);
                    // or use SimpleDateFormat for parsing
                    AppointmentStatus status = AppointmentStatus.valueOf(parts[4].toUpperCase());
                    Appointment appt = new Appointment(id, doctor, patient, date);
                    if (status == AppointmentStatus.CONFIRMED) appt.confirm();
                    if (status == AppointmentStatus.CANCELLED) appt.cancel();
                    store.add(appt);
                }
            }
            System.out.println("Appointments loaded from " + filename);
            viewAppointments(); // <-- show all appointments after loading
        } catch (IOException e) {
            System.out.println("Error loading appointments from CSV: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
