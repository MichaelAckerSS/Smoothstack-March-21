package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;


public class AirportDAO extends BaseDAO<Airport> {

	public AirportDAO(Connection conn) {
		super(conn);
	}

	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("insert into airport values (?, ?)", new Object[] {airport.getId(), airport.getCity()});
	}

	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("update airport set city = ? where iata_id = ?", new Object[] {airport.getCity(), airport.getId()});
	}

	public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("delete from airport where iata_id = ?", new Object[] {airport.getId()});
	}

	public List<Airport> readAllAirports() throws ClassNotFoundException, SQLException {
		return read("select * from airport", new Object[] {});
	}
	
	public List<Airport> readAirportsByCode(Airport airport) throws ClassNotFoundException, SQLException {
		return read("select * from airport where iata_id = ", new Object[] {airport.getId()});
	}
	
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<Airport>();
		while(rs.next()) {
			Airport a = new Airport();
			a.setId(rs.getString("iata_id"));
			a.setCity(rs.getString("city"));
			airports.add(a);
		}
		return airports;
	}

}