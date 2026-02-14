package com.airtribe.meditrack.Interface;

import com.airtribe.meditrack.entity.Appointment;

public interface Observer {
    void update(Appointment appointment);
}
