package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;

public class BookingDAO extends BaseDAO<Booking>{

	public BookingDAO(Connection conn) {
		super(conn);
	}
	
	public Integer addBooking(Booking booking) throws ClassNotFoundException, SQLException {
		return saveReturnPK("insert into booking values (?,?,?)", new Object[] {null, booking.getIsActive(), booking.getConfirmationCode()});
	}
	
	public List<Booking> readAllBookings() throws ClassNotFoundException, SQLException {
		return read("select * from booking", new Object[] {});
	}
	
	public Booking readBookingFromID(Integer id) throws ClassNotFoundException, SQLException {
		List<Booking> list = read("select * from booking where id = ?", new Object[] {id});
		return list.get(0);
	}
	
	public void cancelBookingByID(Integer id) throws ClassNotFoundException, SQLException {
		save("update booking set is_active = ? where id = ?", new Object[] {false, id});
	}

	@Override
	public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<Booking>();
		while(rs.next()) {
			Booking b = new Booking();
			b.setId(rs.getInt("id"));
			b.setIsActive(rs.getBoolean("is_active"));
			b.setConfirmationCode(rs.getString("confirmation_code"));
			bookings.add(b);
		}
		return bookings;
	}

}
