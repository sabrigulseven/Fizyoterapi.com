package com.sabrigulseven.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sabrigulseven.entity.Appointment;
import com.sabrigulseven.entity.Patient;
import com.sabrigulseven.entity.Physiotherapist;

public class AppointmentManager extends BaseManager {

	public Appointment findByPatientToday(Patient patient) throws SQLException {
		Appointment appointment = null;
		connect();
		String sql = "SELECT * FROM Appointment WHERE patient_id = ? AND date = CURDATE()";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, patient.getId());
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			appointment = parse(resultSet);
		}
		disconnect();
		return appointment;
	}
	public void update(Appointment appointment) throws SQLException {
        connect();
        String sql = "UPDATE Appointment SET attendance = ? WHERE appointment_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, appointment.getAttendance());
        statement.setLong(2, appointment.getAppointmentId());
        statement.executeUpdate();
        disconnect();
    }

	private Appointment parse(ResultSet resultset) throws SQLException {
		long appointmentId = resultset.getLong("appointment_id");
		long patientId = resultset.getLong("patient_id");
		long physId = resultset.getLong("physiotherapist_id");
		LocalDate date = resultset.getDate("date").toLocalDate();
		LocalTime time = resultset.getTime("time").toLocalTime();
		Boolean attendance = resultset.getBoolean("attendance");
		
		PatientManager patientManager = new PatientManager();
		Patient patient = patientManager.findById(patientId);
		
		PhysiotherapistManager physiotherapistManager = new PhysiotherapistManager();
		Physiotherapist physiotherapist = physiotherapistManager.find(physId);
		
		Appointment appointment = new Appointment(appointmentId, patient, physiotherapist, date, time, attendance);
		return appointment;
	}
}
