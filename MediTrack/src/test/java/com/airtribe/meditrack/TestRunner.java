package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.entity.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.Date;

public class TestRunner {
    public static void main(String[] args) {

        System.out.println("===== MediTrack Manual Test Runner =====");
        System.out.println(Constants.APP_NAME);
        System.out.println("---------------------------------------");

        // =====================
        // ENTITY TEST
        // =====================
        Doctor doctor = new Doctor(
                "D1",
                "Dr Rao",
                45,
                Specialization.GENERAL,
                500
        );

        Patient patient = new Patient(
                "P1",
                "Amit",
                30
        );

        System.out.println("\n[ENTITY TEST]");
        System.out.println(doctor.getSummary());
        System.out.println(patient.getSummary());

        // =====================
        // SERVICE TEST
        // =====================
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();

        patientService.addPatient(patient);
        doctorService.addDoctor(doctor);

        System.out.println("\n[SERVICE TEST]");
        patientService.viewPatients();
        doctorService.viewDoctors();

        // =====================
        // APPOINTMENT TEST
        // =====================
        AppointmentService appointmentService = new AppointmentService();
        Appointment appointment = new Appointment("A1", doctor, patient, new Date());

        appointmentService.createAppointment("A1", doctor, patient, new Date());

        System.out.println("\n[APPOINTMENT TEST]");
        appointmentService.viewAppointments();

        appointment.cancel();
        System.out.println("After cancellation:");
        System.out.println(appointment.summary());

        // =====================
        // BILLING TEST
        // =====================
        Bill bill = new Bill(doctor);
        double totalAmount = bill.calculateTotal();

        System.out.println("\n[BILLING TEST]");
        bill.printReceipt(totalAmount);
        BillSummary summary = new BillSummary(patient.getId(), doctor.getId(), totalAmount, 200.00);

        System.out.println("Doctor: " + summary.getDoctorId());
        System.out.println("Final Amount: ₹" + summary.getTotalAmount());

        System.out.println("\n===== ALL TESTS COMPLETED SUCCESSFULLY =====");

        System.out.println("\n[STREAM TEST]");
        doctorService.filterBySpecialization(Specialization.GENERAL)
                .forEach(d -> System.out.println(d.getSummary()));

        System.out.println("Average Fee: " + doctorService.averageConsultationFee());
        IdGenerator idGen = IdGenerator.getInstance();

        System.out.println(idGen.generateId("P"));
        System.out.println(idGen.generateId("D"));
        System.out.println(idGen.generateId("A"));

    }
}
