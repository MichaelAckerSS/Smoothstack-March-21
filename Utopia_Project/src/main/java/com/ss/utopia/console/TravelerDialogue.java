package com.ss.utopia.console;

import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.service.TravelerService;
import com.ss.utopia.session.Session;

public class TravelerDialogue extends Dialogue {
	
	private TravelerService service;

	public TravelerDialogue(Session session) {
		super(session);
		service = (TravelerService) session.getService();
	}

	@Override
	public void launch() {
		String name = this.getSession().getCurrentUser().getFirstName();
		System.out.println("Welcome, " + name);
		runFirstMenu();
		
	}
	
	private void runFirstMenu() {
		String options = "%n1. Book a ticket%n2. Cancel an upcoming trip%n3. Exit%n";
		System.out.printf("What would you like to do?" + options);
		int choice = this.getSession().getDialogueManager().getIntResponse(3, options);
		switch(choice) {
			case 1:
				System.out.println("Let's book a new ticket.");
				bookTicket();
				break;
			case 2:
				System.out.println("Which trip should we cancel?");
				cancelTrip();
				break;
			case 3:
				System.out.printf("Exiting. See you soon!%n%n");
				this.getSession().getDialogueManager().launchStartup();
				break;
		}
	}
	
	private void bookTicket() {
		System.out.println("These are the flights available now:");
		try {
			List<Flight> flights = service.getAllFlights();
			int count = 1;
			for (Flight f : flights) {
				int seatsLeft = service.getCapacity(f) - f.getReservedSeats();
				if(seatsLeft > 0) {
					Route r = service.getRouteFromID(f.getRouteID());
					String origin = r.getOrigin();
					String dest = r.getDestination();
					System.out.printf("%d.   FLIGHT #: ",count);
					System.out.print(f.getId());
					System.out.print("   ROUTE: " + origin + " -> " + dest);
					System.out.print("   DEPARTURE TIME: ");
					System.out.print(f.getDepartureTime());
					System.out.print("   SEATS LEFT: ");
					System.out.println(seatsLeft);
					count++;
				}
			}
			int cancel = count;
			System.out.print(cancel);
			System.out.println(". Cancel");
			int choice = this.getSession().getDialogueManager().getIntResponse(cancel, "Please choose a flight or cancel%n");
			if (choice < cancel) {
				Flight chosenFlight = flights.get(choice - 1);
				System.out.println("Booked!");
				String conf = service.bookFlight(chosenFlight, this.getSession().getCurrentUser());
				System.out.println("Confirmation code: " + conf);
				runFirstMenu();
			} else {
				System.out.println("Operation canceled.");
				runFirstMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to access flights");
		}
	}
	
	private void cancelTrip() {
		System.out.println("You currently have tickets booked on these flights. Choose one to cancel:");
		try {
			List<Flight> flights = service.getFlightsFromUser(this.getSession().getCurrentUser());
			int count = 1;
			for (Flight f : flights) {
				Route r = service.getRouteFromID(f.getRouteID());
				String origin = r.getOrigin();
				String dest = r.getDestination();
				System.out.printf("%d.   FLIGHT #: ",count);
				System.out.print(f.getId());
				System.out.print("   ROUTE: " + origin + " -> " + dest);
				System.out.print("   DEPARTURE TIME: ");
				System.out.println(f.getDepartureTime());
				count++;
			}
			int cancel = count;
			System.out.print(cancel);
			System.out.println(". Cancel");
			int choice = this.getSession().getDialogueManager().getIntResponse(cancel, "Please choose a flight or cancel%n");
			if (choice < cancel) {
				service.cancelFlight(flights.get(choice - 1), this.getSession().getCurrentUser());
				System.out.println("Your flight has been canceled.");
				runFirstMenu();
			} else {
				runFirstMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Flights could not be found!");
		}
	}

}
