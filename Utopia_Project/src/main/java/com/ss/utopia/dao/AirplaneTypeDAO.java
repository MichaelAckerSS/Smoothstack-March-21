package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ss.utopia.entity.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}
	
	public AirplaneType getAirplaneTypeByID(Integer id) throws ClassNotFoundException, SQLException {
		List<AirplaneType> list = read("select * from airplane_type where id = ?", new Object[] {id});
		return list.get(0);
	}

	@Override
	public List<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<AirplaneType> types = new ArrayList<AirplaneType>();
		while(rs.next()) {
			AirplaneType t = new AirplaneType();
			t.setId(rs.getInt("id"));
			t.setCapacity(rs.getInt("max_capacity"));
			types.add(t);
		}
		return types;
	}

}
