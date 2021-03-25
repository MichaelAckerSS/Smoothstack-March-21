package com.ss.utopia.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.service.EmployeeService;
import com.ss.utopia.session.Session;

public class EmployeeDialogue extends Dialogue{
	EmployeeService service;

	public EmployeeDialogue(Session session) {
		super(session);
		service = (EmployeeService) session.getService();
	}

	@Override
	public void launch() {
		String name = this.getSession().getCurrentUser().getFirstName();
		System.out.println("Welcome, " + name);
		runFirstMenu();
		
	}
	
	private void runFirstMenu() {
		String options = "%n1. Manage flights%n2. Exit%n";
		System.out.printf("What would you like to do?" + options);
		int choice = this.getSession().getDialogueManager().getIntResponse(2, options);
		switch(choice) {
			case 1:
				System.out.println("Select a flight:");
				runViewFlights();
				break;
			case 2:
				System.out.printf("Exiting. See you soon!%n%n");
				this.getSession().getDialogueManager().launchStartup();
				break;
		}
	}
	
	private void runViewFlights() {
		try {
			List<Flight> flights = service.getAllFlights();
			int count = 1;
			System.out.format("%-21s%-16s%-16s%-16s%n", "     FLIGHT #","ROUTE","DEPARTURE DATE", "DEPARTURE TIME");
			for (Flight f : flights) {
				Route r = service.getRouteFromID(f.getRouteID());	
				String rs = r.getOrigin() + " -> " + r.getDestination();
				System.out.format("%d%-4s%-16d%-16s%-16s%-16s%n", count,".", f.getId(), rs,f.getDepartureDate(), f.getDepartureTime());
				count++;
			}
			int cancel = count;
			System.out.print(cancel);
			System.out.println(". Cancel");
			int fchoice = this.getSession().getDialogueManager().getIntResponse(cancel, "Please choose a flight or cancel%n");
			if (fchoice < cancel) {
				Flight chosenFlight = flights.get(fchoice - 1);
				manageFlight(chosenFlight);
			} else {
				runFirstMenu();
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
			}
	}
	
	private void manageFlight(Flight f) {
		String options = "%n1. View more details%n2. Edit flight%n3. Exit%n";
		System.out.printf("What would you like to do with this flight?" + options);
		int choice = this.getSession().getDialogueManager().getIntResponse(3, options);
		switch(choice) {
			case 1:
				System.out.println("Flight #: " + f.getId());
				System.out.println("Route ID: " + f.getRouteID());
				System.out.println("Airplane ID: " + f.getAirplaneID());
				System.out.println("Departure date: " + f.getDepartureDate());
				System.out.println("Departure time: " + f.getDepartureTime());
				System.out.println("Reserved seats in first class: " + f.getReservedSeatsFirstclass());
				System.out.println("Reserved seats in business class: " + f.getReservedSeatsBusiness());
				System.out.println("Reserved seats in economy class: " + f.getReservedSeatsEconomy());
				System.out.println("Total seats in first class: " + f.getTotalSeatsFirstclass());
				System.out.println("Total seats in business class: " + f.getTotalSeatsBusiness());
				System.out.println("Total seats in economy class: " + f.getTotalSeatsEconomy());
				System.out.println("First class seat price: " + f.getSeatPriceFirstclass());
				System.out.println("Business class seat price: " + f.getSeatPriceBusiness());
				System.out.println("Economy class seat price: " + f.getSeatPriceEconomy());
				manageFlight(f);
				break;
			case 2:
				editFlight(f);
				manageFlight(f);
				break;
			case 3:
				runViewFlights();
				break;
		}
	}
	
	private void editFlight(Flight f) {
		Boolean done = false;
		Scanner sc = this.getSession().getScanner();
		while (!done) {
			System.out.println("Choose a parameter to edit:");
			System.out.println("1. Flight #\n2. Route ID\n3. Airplane ID\n4. Departure date\n5. Departure time\n6. Available seats\n7. Seat prices\n8. Done");
			int choice = this.getSession().getDialogueManager().getIntResponse(8, "Choose what to edit.");
			switch(choice) {
				case 1:
				int oldID = f.getId();
				System.out.println("Current flight #: " + oldID);
				System.out.println("Enter new flight #:");
				f.setId(sc.nextInt());
				try {
					if (service.getAllFlights().contains(f)) {
						System.out.println("Another flight is already using that #.");
						f.setId(oldID);
					}
				} catch (SQLException e) {
					System.out.println("Error: could not access flights");
					e.printStackTrace();
				}
				break;
				case 2:
					System.out.println("Current route ID: " + f.getRouteID());
					System.out.println("Enter new route ID: ");
					f.setRouteID(sc.nextInt());
					break;
				case 3:
					System.out.println("Current airplane ID: " + f.getAirplaneID());
					System.out.println("Enter new airplane ID: ");
					f.setRouteID(sc.nextInt());
					break;
				case 4:
					System.out.println("Current departure date: " + f.getDepartureDate());
					System.out.println("Enter new departure date: ");
					f.setDepartureDate(sc.next());
					break;
				case 5:
					System.out.println("Current departure time: " + f.getDepartureTime());
					System.out.println("Enter new departure time: ");
					f.setDepartureTime(sc.next());
					break;
				case 6:
					System.out.println("Choose seat class: ");
					System.out.println("1. First class\n2. Business class\n3.Economy class");
					choice = this.getSession().getDialogueManager().getIntResponse(8, "Choose a seat class.");
					switch(choice) {
						case 1:
							int oldSeatsF = f.getTotalSeatsFirstclass();
							System.out.println("Current first class seats available: " + oldSeatsF);
							System.out.println("Enter new quantity of seats: ");
							f.setTotalSeatsFirstclass(sc.nextInt());
							try {
								if(f.getTotalAvailableSeats() > service.getCapacity(f)) {
									System.out.println("Seating exceeds plane capacity. No change was made.");
									f.setTotalSeatsFirstclass(oldSeatsF);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						case 2:
							int oldSeatsB = f.getTotalSeatsBusiness();
							System.out.println("Current business class seats available: " + oldSeatsB);
							System.out.println("Enter new quantity of seats: ");
							f.setTotalSeatsBusiness(sc.nextInt());
							try {
								if(f.getTotalAvailableSeats() > service.getCapacity(f)) {
									System.out.println("Seating exceeds plane capacity. No change was made.");
									f.setTotalSeatsBusiness(oldSeatsB);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						case 3:
							int oldSeatsE = f.getTotalSeatsEconomy();
							System.out.println("Current economy class seats available: " + oldSeatsE);
							System.out.println("Enter new quantity of seats: ");
							f.setTotalSeatsEconomy(sc.nextInt());
							try {
								if(f.getTotalAvailableSeats() > service.getCapacity(f)) {
									System.out.println("Seating exceeds plane capacity. No change was made.");
									f.setTotalSeatsEconomy(oldSeatsE);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
					}
					break;
				case 7:
					System.out.println("Choose seat class: ");
					System.out.println("1. First class\n2. Business class\n3.Economy class");
					choice = this.getSession().getDialogueManager().getIntResponse(8, "Choose a seat class.");
					switch(choice) {
						case 1:
							System.out.println("Current first class seat price: " + f.getSeatPriceFirstclass());
							System.out.println("Enter new price: ");
							f.setSeatPriceFirstclass(sc.nextFloat());
							break;
						case 2:
							System.out.println("Current business class seat price: " + f.getSeatPriceBusiness());
							System.out.println("Enter new price: ");
							f.setSeatPriceBusiness(sc.nextFloat());
							break;
						case 3:
							System.out.println("Current first class seat price: " + f.getSeatPriceEconomy());
							System.out.println("Enter new price: ");
							f.setSeatPriceEconomy(sc.nextFloat());
							break;
					}
					break;
				case 8:
					done = true;
					break;
			}
		}
		System.out.println("Save changes?\n1. Yes\n2. No");
		int choice = this.getSession().getDialogueManager().getIntResponse(2, "Save changes?");
		if (choice == 1) {
			try {
				System.out.println(service.updateFlight(f));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Flight not saved.");
		}
	}
}
