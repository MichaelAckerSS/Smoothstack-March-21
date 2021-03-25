package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingUser;

public class BookingUserDAO extends BaseDAO<BookingUser>{

	public BookingUserDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookingUser(BookingUser bu) throws ClassNotFoundException, SQLException {
		save("insert into booking_user values (?, ?)", new Object[] {bu.getBookingID(), bu.getUserID()});
	}
	
	public List<BookingUser> readBookingUsersByUserID(int id) throws ClassNotFoundException, SQLException {
		return read("select * from booking_user where user_id = ?", new Object[] {id});
	}
	
	public BookingUser readBookingUsersByBookingID(int id) throws ClassNotFoundException, SQLException {
		List<BookingUser> list = read("select * from booking_user where booking_id = ?", new Object[] {id});
		return list.get(0);
	}

	@Override
	public List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingUser> bookings = new ArrayList<BookingUser>();
		while(rs.next()) {
			BookingUser b = new BookingUser();
			b.setBookingID(rs.getInt("booking_id"));
			b.setUserID(rs.getInt("user_id"));
			bookings.add(b);
		}
		return bookings;
	}

}