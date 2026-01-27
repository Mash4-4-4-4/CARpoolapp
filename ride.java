package backend;

public class ride {
		int id;
		String source;
		String destination;
		int seats;
		double fare;
		int available_seats;
		int total_seats;
		user user;
		public ride(int id,String source, String destination, int seats, double fare) {
			this.source = source;
			this.destination = destination;
			this.seats = seats;
			this.fare = fare;
		}
		@Override
		public String toString() {
			return "ride [id=" + id + ", source=" + source + ", destination=" + destination + ", seats=" + seats
					+ ", fare=" + fare + "]";
		}
	}
