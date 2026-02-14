package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.Interface.Observer;
import com.airtribe.meditrack.entity.Appointment;

public class AppointmentNotifier implements Observer {

    @Override
    public void update(Appointment appointment) {
        System.out.println("Notification: Appointment booked for "
                + appointment.getPatient().getName()
                + " with Dr. "
                + appointment.getDoctor().getName());
    }
}
