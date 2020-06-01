package invoiceservice;

public class InvoiceService {

    private RideRepository rideRepository;

    enum ServiceType {NORMAL,PREMIUM}

    public double calculateFare(ServiceType serviceType, double distance, int time) {
        CabSubscription cabSubscription = new CabSubscription(serviceType);
        double totalFare = distance*cabSubscription.MINIMUM_COST_PER_KILOMETER + time*cabSubscription.COST_PER_TIME;
        return Math.max(totalFare,cabSubscription.MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride:rides) {
            totalFare += this.calculateFare(ride.serviceType,ride.distance,ride.time);
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
