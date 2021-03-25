package com.ss.utopia.console;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;
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
		String options = "%n1. Manage flights%n2. Manage bookings%n3. Manage airports%n4. Manage accounts%n5. Exit%n";
		System.out.printf("What would you like to do?" + options);
		int choice = this.getSession().getDialogueManager().getIntResponse(5, options);
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
				System.out.println("What would you like to do with airports?");
				runManageAirports();
				break;
			case 4:
				System.out.println("What would you like to do with accounts?");
				runManageUsers();
				break;
			case 5:
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
			//New flight
			Flight newFlight = buildFlight(flights);
			System.out.println("Save flight " + newFlight.getId() + "?\n1. Yes\n2. No");
			int saveFlight = this.getSession().getDialogueManager().getIntResponse(2, "Yes%nNo%n");
			if (saveFlight == 1) {
				try {
					System.out.println(service.addFlight(newFlight));
				} catch (SQLException e) {
					System.out.println("Error: Flight could not be added");
					e.printStackTrace();
				}
			} else {
				System.out.println("Flight was not added");
			}
			runManageFlights();
			break;
		case 2:
			//Delete flight
			System.out.println("Choose a flight to delete:");
			try {
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
					System.out.println("Delete Flight " + chosenFlight.getId() + "?");
					System.out.println("1. Yes\n 2. No");
					int dchoice = this.getSession().getDialogueManager().getIntResponse(2, "Delete this flight?");
					if (dchoice == 1) {
						service.deleteFlight(chosenFlight);
						System.out.println("Flight deleted.");
						runManageFlights();
					} else {
						System.out.println("Flight was not deleted.");
						runManageFlights();
						break;
					}
				} else {
					runManageFlights();
					break;
				}
			} catch (SQLException e) {
				System.out.println("Something went wrong.");
				e.printStackTrace();
			}
			break;
		case 3:
			//Edit flight
			System.out.println("Choose a flight to edit:");
			try {
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
					editFlight(chosenFlight);
					runManageFlights();
				} else {
					runManageFlights();
					break;
				}
			} catch (SQLException e) {
				System.out.println("Something went wrong.");
				e.printStackTrace();
				}
			break;
		case 4:
			//View Flights
			try {
				System.out.println("Viewing all current flights:");
				System.out.format("%-16s%-16s%-16s%-16s%n", "     FLIGHT #","ROUTE","DEPARTURE DATE", "DEPARTURE TIME");
				for (Flight f : flights) {
					if(f.getTotalAvailableSeats() > 0) {
						Route r = service.getRouteFromID(f.getRouteID());
						String rs = r.getOrigin() + " -> " + r.getDestination();
						System.out.format("%-16d%-16s%-16s%-16s%n", f.getId(), rs,f.getDepartureDate(), f.getDepartureTime());
					}
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
		List<Booking> bookings;
		try {
			bookings = service.getAllBookings();
			
			String options = "%n1. Add booking%n2. Delete booking%n3. Edit booking%n4. View bookings%n5. Exit%n";
			System.out.printf(options);
			int choice = this.getSession().getDialogueManager().getIntResponse(5, options);
			switch(choice) {
				case 1:
					//New Booking
					Booking newBooking = buildBooking();
					System.out.println("Save this booking?\n1. Yes\n2. No");
					choice = this.getSession().getDialogueManager().getIntResponse(2, "Save this booking?");
					if (choice == 1) {
						service.addBooking(newBooking);
						System.out.println("Booking added.");
					} else {
						System.out.println("Booking was not added.");
					}
					runManageBookings();
					break;
				case 2:
					//Delete Booking
					System.out.println("Select a booking to delete:");
					int counter = 1;
					System.out.format("%-15s%-16s%-24s%-16s%-16s%n", "   FLIGHT #", "SEAT CLASS", "PASSENGER", "STATUS", "CONFIRMATION CODE");
					for (Booking b : bookings) {
						String status;
						if (b.getIsActive()) {
							status = "Active";
						} else {
							status = "Canceled";
						}
						String seatClass = null;
						switch(b.getSeatClass()) {
							case 1:
								seatClass = "FIRST";
								break;
							case 2:
								seatClass = "BUSINESS";
								break;
							case 3:
								seatClass = "ECONOMY";
								break;
						}
						Flight f = service.getFlightFromBookingID(b.getId());
						int fnum = f.getId();
						User user = service.getUserFromBookingID(b.getId());
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.print(counter + ". ");
						counter++;
						System.out.format("%-12d%-16s%-24s%-16s%-16s%n", fnum, seatClass, name, status, b.getConfirmationCode());		
					}
					System.out.println(counter + ". Canel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, "Choose a booking to delete.");
					if (choice < counter) {
						Booking chosenBooking = bookings.get(choice - 1);
						System.out.println("Delete this booking?\n1. Yes\n2. No");
						choice = this.getSession().getDialogueManager().getIntResponse(counter, "Delete this booking?");
						if (choice == 1) {
							service.deleteBooking(chosenBooking);
						} else {
							System.out.println("Canceled.");
						}
					} else {
						System.out.println("Canceled.");
					}
					runManageBookings();
					break;
				case 3:
					System.out.println("Select a booking to edit:");
					counter = 1;
					System.out.format("%-15s%-16s%-24s%-16s%-16s%n", "   FLIGHT #", "SEAT CLASS", "PASSENGER", "STATUS", "CONFIRMATION CODE");
					for (Booking b : bookings) {
						String status;
						if (b.getIsActive()) {
							status = "Active";
						} else {
							status = "Canceled";
						}
						String seatClass = null;
						switch(b.getSeatClass()) {
							case 1:
								seatClass = "FIRST";
								break;
							case 2:
								seatClass = "BUSINESS";
								break;
							case 3:
								seatClass = "ECONOMY";
								break;
						}
						Flight f = service.getFlightFromBookingID(b.getId());
						int fnum = f.getId();
						User user = service.getUserFromBookingID(b.getId());
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.print(counter + ". ");
						counter++;
						System.out.format("%-12d%-16s%-24s%-16s%-16s%n", fnum, seatClass, name, status, b.getConfirmationCode());		
					}
					System.out.println(counter + ". Canel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, "Choose a booking to edit.");
					if (choice < counter) {
						Booking chosenBooking = bookings.get(choice - 1);
						editBooking(chosenBooking);
					} else {
						System.out.println("Canceled.");
					}
					runManageBookings();
					break;
				case 4:
					//View Bookings
					System.out.println("All bookings:");
					System.out.format("%-12s%-16s%-24s%-16s%-16s%n", "FLIGHT #", "SEAT CLASS", "PASSENGER", "STATUS", "CONFIRMATION CODE");
					for (Booking b : bookings) {
						String status;
						if (b.getIsActive()) {
							status = "Active";
						} else {
							status = "Canceled";
						}
						String seatClass = null;
						switch(b.getSeatClass()) {
							case 1:
								seatClass = "FIRST";
								break;
							case 2:
								seatClass = "BUSINESS";
								break;
							case 3:
								seatClass = "ECONOMY";
								break;
						}
						Flight f = service.getFlightFromBookingID(b.getId());
						int fnum = f.getId();
						User user = service.getUserFromBookingID(b.getId());
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.format("%-12d%-16s%-24s%-16s%-16s%n", fnum, seatClass, name, status, b.getConfirmationCode());		
					}
					runManageBookings();
					break;
				case 5:
					runFirstMenu();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void runManageAirports() {
		try {
			List<Airport> airports = service.getAllAirports();
			String options = "%n1. Add airport%n2. Delete airport%n3. Edit airport%n4. View airports%n5. Exit%n";
			System.out.printf(options);
			int choice = this.getSession().getDialogueManager().getIntResponse(5, options);
			switch(choice) {
				case 1:
					//Add airport
					Airport newAirport = buildAirport();
					System.out.println("Airport saved!");
					runManageAirports();
					break;
				case 2:
					//Delete airport
					System.out.println("Select an airport to delete:");
					System.out.format("%-15s%-16s%n", "   IATA CODE", "CITY");
					int counter = 1;
					for (Airport a : airports) {
						System.out.print(counter + ". ");
						System.out.format("%-12s%-16s%n", a.getId(),a.getCity());
						counter++;
					}
					System.out.println(counter + ". Cancel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, options);
					if (choice < counter) {
						Airport chosenAirport = airports.get(choice - 1);
						System.out.println("Delete " + chosenAirport.getId() + "?\n1. Yes\n2. No");
						choice = this.getSession().getDialogueManager().getIntResponse(2, "Delete airport?");
						if (choice == 1) {
							service.deleteAirport(chosenAirport);
							System.out.println("Airport deleted.");
						} else {
							System.out.println("Airport was not deleted.");
						}
					} else {
						System.out.println("Operation canceled.");
					}
					runManageAirports();
					break;
				case 3:
					//Edit airport
					System.out.println("Select an airport to edit:");
					System.out.format("%-15s%-16s%n", "   IATA CODE", "CITY");
					counter = 1;
					for (Airport a : airports) {
						System.out.print(counter + ". ");
						System.out.format("%-12s%-16s%n", a.getId(),a.getCity());
						counter++;
					}
					System.out.println(counter + ". Cancel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, options);
					if (choice < counter) {
						Airport chosenAirport = airports.get(choice - 1);
						editAirport(chosenAirport);
					} else {
						System.out.println("Operation canceled.");
					}
					runManageAirports();
					
					break;
				case 4:
					//View airports
					System.out.println("Viewing all airports:");
					System.out.format("%-12s%-16s%n", "IATA CODE", "CITY");
					for (Airport a : airports) {
						System.out.format("%-12s%-16s%n", a.getId(),a.getCity());
					}
					runManageAirports();
					break;
				case 5:
					runFirstMenu();
					break;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		runFirstMenu();
	}
	
	private void runManageUsers() {
		String options = "%n1. Manage Travelers%n2. Manage Employees%n3. Exit%n";
		System.out.printf(options);
		int choice = this.getSession().getDialogueManager().getIntResponse(3, options);
		switch(choice) {
			case 1:
				//Manage traveler accounts
				manageAccounts(3);
				break;
			case 2:
				//Manage employee accounts
				manageAccounts(2);
				break;
			case 3:
				runFirstMenu();
				break;
		}
		
	}
	
	private Flight buildFlight(List<Flight> flights) {
		Flight newFlight = new Flight();
		Scanner sc = this.getSession().getScanner();
		System.out.println("Choose flight ID:");
		newFlight.setId(sc.nextInt());
		if (flights.contains(newFlight)){
			System.out.println("A flight with that ID already exists.");
			runManageFlights();
			return null;
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
		System.out.print(counter);
		System.out.println(". New route");
		int routeChoice = this.getSession().getDialogueManager().getIntResponse(counter, "Select a route.");
		if(routeChoice == counter) {
			Route newRoute = buildRoute();
			newFlight.setRouteID(newRoute.getId());
		} else {
			newFlight.setRouteID(routes.get(routeChoice - 1).getId());
		}
		System.out.println("Current planes in fleet:\n");
		try {
			List<Airplane> planes = service.getAllAirplanes();
			counter = 1;
			Collections.sort(planes, (p1,p2) -> p1.getId() - p2.getId());
			System.out.format("%-16s%-16s%n", "PLANE ID","PLANE MODEL");
			for(Airplane plane : planes) {
				System.out.format("%-16d%-16d%n",plane.getId(),plane.getTypeID());
			}
			System.out.println("Enter plane ID:");
			int planeChoice = this.getSession().getDialogueManager().getIntResponse(planes.size(), "Please choose a plane.");
			newFlight.setAirplaneID(planeChoice);
		} catch (SQLException e) {
			System.out.println("Error: could not access planes");
			e.printStackTrace();
		}
		System.out.println("Enter departure date (YYYY-MM-DD)");
		newFlight.setDepartureDate(sc.next());
		System.out.println("Enter departure time (HH:MM:SS)");
		newFlight.setDepartureTime(sc.next());
		Boolean seatsDone = false;
		while(!seatsDone) {
			System.out.println("How many economy class seats?");
			int econSeats = sc.nextInt();
			System.out.println("How many business class seats?");
			int busSeats = sc.nextInt();
			System.out.println("How many first class seats?");
			int firstSeats = sc.nextInt();
			try {
				if(econSeats + busSeats + firstSeats > service.getCapacity(newFlight)) {
					System.out.println("Seat amount exceeds plane capacity of " + service.getCapacity(newFlight));
				} else {
					newFlight.setTotalSeatsEconomy(econSeats);
					newFlight.setTotalSeatsBusiness(busSeats);
					newFlight.setTotalSeatsFirstclass(firstSeats);
					seatsDone = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Enter economy class seat price:");
		newFlight.setSeatPriceEconomy(sc.nextFloat());
		System.out.println("Enter business class seat price:");
		newFlight.setSeatPriceBusiness(sc.nextFloat());
		System.out.println("Enter first class seat price:");
		newFlight.setSeatPriceFirstclass(sc.nextFloat());
		newFlight.setReservedSeatsEconomy(0);
		newFlight.setReservedSeatsBusiness(0);
		newFlight.setReservedSeatsFirstclass(0);
		return newFlight;
	}
	
	private Route buildRoute() {
		Route newRoute = new Route();
		try {
			List<Airport> airports = service.getAllAirports();
			System.out.println("Select an origin airport");
			int counter = 1;
			System.out.format("%-3s%-12s%-16s%n", "","ID","CITY");
			for (Airport a : airports) {
				System.out.print(counter + ". ");
				System.out.format("%-12s%-16s%n", a.getId(),a.getCity());
				counter++;
			}
			System.out.println(counter + ". Create new");
			int choice = this.getSession().getDialogueManager().getIntResponse(airports.size()+1, "Choose an airport");
			if (choice == counter) {
				newRoute.setOrigin(buildAirport().getId());
			} else {
				newRoute.setOrigin(airports.get(choice).getId());
			}
			
			System.out.println("Select a destination airport");
			counter = 1;
			System.out.format("%-4s%-12s%-16s%n", "","ID","CITY");
			for (Airport a : airports) {
				System.out.print(counter + ". ");
				System.out.format("%-12s%-16s%n", a.getId(),a.getCity());
				counter++;
			}
			System.out.println(counter + ". Create new");
			choice = this.getSession().getDialogueManager().getIntResponse(airports.size() + 1, "Choose an airport");
			if (choice == counter) {
				newRoute.setDestination(buildAirport().getId());
			} else {
				newRoute.setDestination(airports.get(choice - 1).getId());
			}
		} catch (SQLException e) {
			System.out.println("Error: Could not retrieve airports");
			e.printStackTrace();
		}
		int routeID;
		try {
			routeID = service.addRoute(newRoute);
			newRoute.setId(routeID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newRoute;
		
	}
	
	private Airport buildAirport() {
		Scanner sc = this.getSession().getScanner();
		Airport newAirport = new Airport();
		Boolean goodCode = false;
		while(!goodCode) {
			System.out.println("Enter IATA code:");
			String code = sc.next();
			if(code.length()!=3 || !code.equals(code.toUpperCase())) {
				System.out.println("Invalid code. Must be 3 capital letters.");
			} else {
				goodCode = true;
				newAirport.setId(code);
			}
		}
		System.out.println("Enter city:");
		sc.nextLine();
		String city = sc.nextLine();
		newAirport.setCity(city);
		try {
			service.addAirport(newAirport);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAirport;
	}
	
	private Booking buildBooking() {
		Scanner sc = this.getSession().getScanner();
		Booking newBooking = new Booking();
		System.out.println("Enter a booking id:");
		newBooking.setId(sc.nextInt());
		newBooking.setIsActive(true);
		System.out.println("Enter an 8-character alphanumeric confirmation code:");
		newBooking.setConfirmationCode(sc.next());
		return newBooking;
	}
	
	private User buildUser(int userRole, String roleName) {
		Scanner sc = this.getSession().getScanner();
		User newUser = new User();
		newUser.setRole(userRole);
		System.out.println("Enter " + roleName + " first name:");
		newUser.setFirstName(sc.next());
		System.out.println("Enter " + roleName + " last name:");
		newUser.setLastName(sc.next());
		System.out.println("Enter " + roleName + " username:");
		newUser.setUserName(sc.next());
		System.out.println("Enter " + roleName + " password:");
		newUser.setPassword(sc.next());
		System.out.println("Enter " + roleName + " email:");
		newUser.setEmail(sc.next());
		System.out.println("Enter " + roleName + " phone:");
		newUser.setPhone(sc.next());
		return newUser;
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
			runManageFlights();
		}
	}

	private void editBooking(Booking b) {
		Boolean done = false;
		while(!done) {
			System.out.println("Select a parameter to edit:");
			System.out.println("1. Seat class\n2. Status\n3. Done");
			int choice = this.getSession().getDialogueManager().getIntResponse(3, "Choose a parameter to edit");
			switch(choice) {
				case 1:
					String seatClass;
					if (b.getSeatClass() == 1) {
						seatClass = "FIRST CLASS";
					} else if (b.getSeatClass() == 2) {
						seatClass = "BUSINESS";
					} else {
						seatClass = "ECONOMY";
					}
					System.out.println("Current seat class: " + seatClass);
					System.out.println("Select new seat class: \n1. First class\n2. Business\n3. Economy");
					choice = this.getSession().getDialogueManager().getIntResponse(2, "Choose a seat class");
					b.setSeatClass(choice);
					break;
				case 2:
					if (b.getIsActive()) {
						System.out.println("Current status: ACTIVE. Cancel booking?\n1. Yes\n2. No");
						choice = this.getSession().getDialogueManager().getIntResponse(2, "Cancel booking?");
						if (choice == 1) {
							b.setIsActive(false);
						} else {
							System.out.println("No change was made.");
						}
					} else if (!b.getIsActive()) {
						System.out.println("Current status: CANCELED. Reactivate booking?\n1. Yes\n2. No");
						choice = this.getSession().getDialogueManager().getIntResponse(2, "Reactivate booking?");
						if (choice == 1) {
							b.setIsActive(true);
						} else {
							System.out.println("No change was made.");
						}
					}
					break;
				case 3:
					done = true;
			}
		}
		System.out.println("Save changes?\n1. Yes\n2. No");
		int choice = this.getSession().getDialogueManager().getIntResponse(2, "Save changes?");
		if (choice == 1) {
			try {
				service.updateBooking(b);
			} catch (SQLException e) {
				System.out.println("Error: Could not update");
				e.printStackTrace();
			}
		} else {
			System.out.println("Changes were not saved.");
		}
		runManageBookings();
	}

	private void editAirport(Airport a) {
		Scanner sc = this.getSession().getScanner();
		Boolean done = false;
		while(!done) {
			System.out.println("What would you like top edit?\n1. IATA code\n2. City\n3. Done");
			int choice = this.getSession().getDialogueManager().getIntResponse(3, "Select a parameter");
			switch(choice) {
				case 1:
					System.out.println("Current code: " + a.getId() + "\nEnter a new code: ");
					a.setId(sc.next());
					break;
				case 2:
					System.out.println("Current city: " + a.getCity() + "\nEnter a new city: ");
					a.setId(sc.next());
					break;
				case 3:
					done = true;
					break;
			}
		}
		System.out.println("Save changes?\n1. Yes\n2. No");
		int choice = this.getSession().getDialogueManager().getIntResponse(2, "Save changes?");
		if (choice == 1) {
			try {
				service.updateAirport(a);
			} catch (SQLException e) {
				System.out.println("Error: unable to update airport");
				e.printStackTrace();
			}
		} else {
			System.out.println("Changes discarded.");
		}
	}

	private void editUser(User u) {
		Scanner sc = this.getSession().getScanner();
		Boolean done = false;
		while(!done) {
			System.out.println("Choose a parameter to edit:\n1. First name\n2. Last name\n"
					+ "3. Username\n4. Email\n5. Phone\n6. Done");
			int choice = this.getSession().getDialogueManager().getIntResponse(6, "Choose a parameter");
			switch (choice) {
				case 1:
					System.out.println("Current first name: " + u.getFirstName());
					System.out.println("Enter a new first name: ");
					u.setFirstName(sc.next());
					break;
				case 2:
					System.out.println("Current last name: " + u.getLastName());
					System.out.println("Enter a new last name: ");
					u.setLastName(sc.next());
					break;
				case 3:
					System.out.println("Current username: " + u.getUserName());
					System.out.println("Enter a new username: ");
					u.setUserName(sc.next());
					break;
				case 4:
					System.out.println("Current email address: " + u.getEmail());
					System.out.println("Enter a new email address: ");
					u.setEmail(sc.next());
					break;
				case 5:
					System.out.println("Current phone #: " + u.getPhone());
					System.out.println("Enter a new phone #: ");
					u.setPhone(sc.next());
					break;
				case 6:
					done = true;
					break;
			}
		}
		System.out.println("Save changes?\n1. Yes\n2. No");
		int choice = this.getSession().getDialogueManager().getIntResponse(2, "Save changes?");
		if (choice == 1) {
			try {
				service.updateUser(u);
				System.out.println("User updated.");
			} catch (SQLException e) {
				System.out.println("Error: couldn't update user.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Changes discarded.");
		}
		runManageUsers();
	}
	
	private void manageAccounts(int userRole) {
		Scanner sc = this.getSession().getScanner();
		String roleName;
		if (userRole == 2) {
			roleName = "employee";
		} else {
			roleName = "traveler";
		}
		try {
			List<User> users = service.getAllUsers();
			List<User> typeUsers = users.stream().filter(user -> user.getRole() == userRole).collect(Collectors.toList());
			String options = "%n1. Add account%n2. Delete account%n3. Edit acount%n4. View accounts%n5. Exit%n";
			System.out.printf(options);
			int choice = this.getSession().getDialogueManager().getIntResponse(5, options);
			switch(choice) {
				case 1:
					//Add account
					User newUser = buildUser(userRole, roleName);
					System.out.println("Save new " + roleName + "?\n1. Yes\n2. No");
					choice = this.getSession().getDialogueManager().getIntResponse(2,"Save?");
					if (choice == 1) {
						try {
							service.addUser(newUser);
							System.out.println("User added");
						} catch (SQLException e) {
							System.out.println("User could not be added");
							e.printStackTrace();
						}
					} else {
						System.out.println("User was not added.");
					}
					runManageUsers();
					break;
				case 2:
					//Delete account
					System.out.println("Choose an account to delete.");
					System.out.format("%-35s%-16s%-32s%-16s%n", "NAME", "USERNAME", "EMAIL", "PHONE");
					int counter = 1;
					for (User user : typeUsers) {
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.print(counter + ". ");
						System.out.format("%-32s%-16s%-32s%-16s%n", name, user.getUserName(), user.getEmail(), user.getPhone());
						counter++;
					}
					System.out.println(counter + ". Cancel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, "Select a user.");
					if (choice < counter) {
						User user = typeUsers.get(choice - 1);
						System.out.println("Delete user?\n1. Yes\n2. No");
						choice = this.getSession().getDialogueManager().getIntResponse(2, "Delete user?");
						if(choice == 1) {
							service.deleteUser(user);
							System.out.println("User deleted.");
						} else System.out.println("Operation canceled.");
					} else {
						System.out.println("Operation canceled.");
					}
					runManageUsers();
					break;
				case 3:
					//Edit account
					System.out.println("Choose an account to edit.");
					System.out.format("%-35s%-16s%-32s%-16s%n", "NAME", "USERNAME", "EMAIL", "PHONE");
					counter = 1;
					for (User user : typeUsers) {
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.print(counter + ". ");
						System.out.format("%-32s%-16s%-32s%-16s%n", name, user.getUserName(), user.getEmail(), user.getPhone());
						counter++;
					}
					System.out.println(counter + ". Cancel");
					choice = this.getSession().getDialogueManager().getIntResponse(counter, "Select a user.");
					if (choice < counter) {
						User user = typeUsers.get(choice - 1);
						editUser(user);
					} else {
						System.out.println("Canceled.");
					}
					runManageUsers();
					break;
				case 4:
					//View accounts
					System.out.println("Viewing all " + roleName + "s:");
					System.out.format("%-32s%-16s%-32s%-16s%n", "NAME", "USERNAME", "EMAIL", "PHONE");
					for (User user : typeUsers) {
						String name = user.getLastName() + ", " + user.getFirstName();
						System.out.format("%-32s%-16s%-32s%-16s%n", name, user.getUserName(), user.getEmail(), user.getPhone());
					}
					runManageUsers();
					break;
				case 5:
					runManageUsers();
					break;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
