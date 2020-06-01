package invoiceservice;

import java.util.*;

public class RideRepository {

    public Map<String, ArrayList<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRide(String userId, Ride[] rides) {
        try {
            if (userId.length() == 0 | rides.length == 0)
                throw new InvoiceServiceException("Entered Empty", InvoiceServiceException.ExceptionType.ENTERED_EMPTY);
            ArrayList<Ride> rideList = this.userRides.get(userId);
            if (rideList == null)
                this.userRides.put(userId, new ArrayList<>(Arrays.asList(rides)));
            else
                this.userRides.get(userId).addAll(new ArrayList<>(Arrays.asList(rides)));
        } catch (NullPointerException e) {
            throw new InvoiceServiceException("Entered Null", InvoiceServiceException.ExceptionType.ENTERED_NULL);
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }

}
