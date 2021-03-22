package com.ss.utopia.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.service.AdminService;
import com.ss.utopia.session.Session;

public class AdminDialogue extends Dialogue {
	
	private AdminService service;

	public AdminDialogue(Session session) {
		super(session);
		service = (AdminService) session.getService();
	}

	@Override
	public void launch() {
		String name = this.getSession().getCurrentUser().getFirstName();
		System.out.println("Welcome, " + name);
		runFirstMenu();
		
	}
	
	private void runFirstMenu() {
		String options = "%n1. Manage flights%n2. Manage bookings%n3. Manage employees%n4. Manage travelers%n5. Override booking cancellation%n6. Exit%n";
		System.out.printf("What would you like to do?" + options);
		int choice = this.getSession().getDialogueManager().getIntResponse(6, options);
		switch(choice) {
			case 1:
				System.out.println("What would you like to do with flights?");
				runManageFlights();
				break;
			case 2:
				System.out.println("What would you like to do with bookings?");
				runManageBookings();
				break;
			case 3:
				System.out.println("What would you like to do with employees?");
				runManageEmployees();
				break;
			case 4:
				System.out.println("What would you like to do with travelers?");
				runManageTravelers();
				break;
			case 5:
				System.out.println("Which cancellation would you like to override?");
				runOverrideCancel();
				break;
			case 6:
				System.out.printf("Exiting. See you soon!%n%n");
				this.getSession().getDialogueManager().launchStartup();
				break;
		}
	}
	
	private void runManageFlights() {
		List<Flight> flights = null;
		try {
			flights = service.getAllFlights();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String options = "%n1. Add flight%n2. Delete flight%n3. Edit flight%n4. View flights%n5. Exit%n";
		System.out.printf(options);
		int choice = this.getSession().getDialogueManager().getIntResponse(5, options);
		switch(choice) {
		case 1:
			Scanner sc = this.getSession().getScanner();
			Flight newFlight = new Flight();
			System.out.println("Choose flight ID:");
			newFlight.setId(sc.nextInt());
			if (flights.contains(newFlight)){
				System.out.println("A flight with that ID already exists.");
				runManageFlights();
				break;
			}
			System.out.println("Choose route:");
			List<Route> routes = null;
			try {
				routes = service.getAllRoutes();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			int counter = 1;
			for (Route r : routes) {
				System.out.print(counter);
				System.out.println(". " + r.getOrigin() + " -> " + r.getDestination());
				counter++;
			}
			int routeChoice = this.getSession().getDialogueManager().getIntResponse(routes.size(), "Select a route.");
			newFlight.setRouteID(routes.get(routeChoice - 1).getId());
			System.out.println("Enter plane ID:");
			newFlight.setAirplaneID(sc.nextInt());
			//System.out.println("Enter departure date and time:");
			newFlight.setDepartureTime("21-06-07 12:30:00");
			newFlight.setReservedSeats(0);
			System.out.println("Enter seat price:");
			newFlight.setSeatPrice(sc.nextFloat());
			try {
				System.out.println(service.addFlight(newFlight));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			runManageFlights();
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			try {
				for (Flight f : flights) {
					Route r = service.getRouteFromID(f.getRouteID());
					String origin = r.getOrigin();
					String dest = r.getDestination();
					System.out.printf("   FLIGHT #: ");
					System.out.print(f.getId());
					System.out.print("   ROUTE: " + origin + " -> " + dest);
					System.out.print("   DEPARTURE TIME: ");
					System.out.println(f.getDepartureTime());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't access flights");
			}
			runManageFlights();
			break;
		case 5:
			runFirstMenu();
			break;
		}
	}
	
	private void runManageBookings() {
		
	}
	
	private void runManageEmployees() {
		
	}
	
	private void runManageTravelers() {
		
	}
	
	private void runOverrideCancel() {
		
	}

}
