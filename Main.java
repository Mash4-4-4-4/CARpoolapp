package backend;

	public class Main {
		public static void main(String[] args) {
				RideBooking rideBookingSystem= new RideBooking();
				rideBookingSystem.createRide(1,"Delhi","Greater Noida",2,250.00);
				rideBookingSystem.createRide(2,"Jaipur","Ajmer",3,420.00);
				rideBookingSystem.createRide(3,"Gurugram","delhi",5,750);
				System.out.println(rideBookingSystem.showAllRides());
			}

		}

