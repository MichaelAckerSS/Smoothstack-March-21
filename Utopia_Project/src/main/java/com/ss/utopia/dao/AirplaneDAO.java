package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airplane;

public class AirplaneDAO extends BaseDAO<Airplane> {

	public AirplaneDAO(Connection conn) {
		super(conn);
	}
	
	public Airplane getAirplaneByID(Integer id) throws ClassNotFoundException, SQLException {
		List<Airplane> list = read("select * from airplane where id = ?", new Object[] {id});
		return list.get(0);
	}
	
	public List<Airplane> readAllAirplanes() throws ClassNotFoundException, SQLException {
		return read("select * from airplane", new Object[] {});
	}

	@Override
	public List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> planes = new ArrayList<Airplane>();
		while(rs.next()) {
			Airplane a = new Airplane();
			a.setId(rs.getInt("id"));
			a.setType(rs.getInt("type_id"));
			planes.add(a);
		}
		return planes;
	}

}
