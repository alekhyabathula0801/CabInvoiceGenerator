package invoiceservice;

public class InvoiceService {

    private RideRepository rideRepository;

    public double calculateFare(RideType rideType, double distance, int time) {
        double totalFare = distance* rideType.getCostPerKiloMeter() + time* rideType.getCostPerMinute();
        return Math.max(totalFare, rideType.getMinimumCost());
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride:rides) {
            totalFare += this.calculateFare(ride.rideType,ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

}
