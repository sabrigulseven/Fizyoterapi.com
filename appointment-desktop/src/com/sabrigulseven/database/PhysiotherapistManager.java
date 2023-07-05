package com.sabrigulseven.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sabrigulseven.entity.Physiotherapist;

public class PhysiotherapistManager extends BaseManager {
	
	public Physiotherapist find(long id) throws SQLException {
		Physiotherapist physiotherapist=null;
		connect();
		String sql = "select * from Physiotherapist where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			physiotherapist = parse(resultSet);
		}
		disconnect();
		return physiotherapist;
		
	}
	
	private Physiotherapist parse(ResultSet resultset) throws SQLException {
		long id = resultset.getLong("id");
		String name = resultset.getString("name");
		String profession = resultset.getString("profession");
		Physiotherapist physiotherapist = new Physiotherapist(id, name, profession);
		return physiotherapist;
	}
}
