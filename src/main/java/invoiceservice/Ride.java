package invoiceservice;

public class Ride {

    public RideType rideType;
    public int time;
    public double distance;

    public Ride(RideType rideType, double distance, int time) {
        this.rideType = rideType;
        this.distance = distance;
        this.time = time;
    }

}
