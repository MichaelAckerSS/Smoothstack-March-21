package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

//Copied from Pramod. Read and understand line by line.

public class RouteDAO extends BaseDAO<Route>{

	public RouteDAO(Connection conn) {
		super(conn);
	}
	
	public List<Route> readRouteFromID(int id) throws ClassNotFoundException, SQLException{
		return read("select * from route where id = ?", new Object[] {id});
	}
	
	public List<Route> readAllRoutes() throws ClassNotFoundException, SQLException{
		return read("select * from route", new Object[] {});
	}

	@Override
	public List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<Route>();
		while(rs.next()) {
			Route r = new Route();
			r.setId(rs.getInt("id"));
			r.setOrigin(rs.getString("origin_id"));
			r.setDestination(rs.getString("destination_id"));
			routes.add(r);
		}
		return routes;
	}

}