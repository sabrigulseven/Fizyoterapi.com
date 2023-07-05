package com.sabrigulseven.database;

import com.sabrigulseven.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PatientManager extends BaseManager{
	
	public Patient findById(long id) throws SQLException {
		Patient patient=null;
		connect();
		String sql = "select * from Patient where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			patient = parse(resultSet);
		}
		disconnect();
		return patient;
		
	}
	public Patient findByIdentityNumber(long identityNumber) throws SQLException {
		Patient patient=null;
		connect();
		String sql = "select * from Patient where identity_number = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, identityNumber);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			patient = parse(resultSet);
		}
		disconnect();
		return patient;
		
	}
	
	private Patient parse(ResultSet resultset) throws SQLException {
		long id = resultset.getLong("id");
		long identityNumber = resultset.getLong("identity_number");
		String name = resultset.getString("name");
		Patient patient = new Patient(id, identityNumber, name);
		return patient;
	}
}
