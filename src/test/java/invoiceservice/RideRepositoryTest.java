package invoiceservice;

import org.junit.Assert;
import org.junit.Test;

import static invoiceservice.InvoiceServiceException.ExceptionType.ENTERED_EMPTY;
import static invoiceservice.InvoiceServiceException.ExceptionType.ENTERED_NULL;

public class RideRepositoryTest {

    RideRepository rideRepository = new RideRepository();

    @Test
    public void givenUserId_whenFound_shouldReturnRideList() {
        Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                        new Ride(RideType.NORMAL,0.1,1)};
        rideRepository.addRide("abc",rides);
        Assert.assertEquals(rides.length,rideRepository.getRides("abc").length);
    }

    @Test
    public void givenUserIdAndMultipleNormalRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                        new Ride(RideType.NORMAL,0.1,1)};
        rideRepository.addRide("anju@b.com",rides);
        Ride[] rides1 = {new Ride(RideType.NORMAL,3.0,5),
                         new Ride(RideType.NORMAL,0.1,1)};
        rideRepository.addRide("anju@b.com",rides1);
        Assert.assertEquals(4,rideRepository.getRides("anju@b.com").length);
    }

    @Test
    public void givenUserIdAndMultipleRides_shouldReturnInvoiceSummary() {
        String userId = "anju@b.com";
        Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                        new Ride(RideType.NORMAL,0.1,1)};
        rideRepository.addRide(userId,rides);
        Ride[] rides1 = {new Ride(RideType.NORMAL,2.0,5),
                         new Ride(RideType.PREMIUM,0.1,1)};
        rideRepository.addRide(userId,rides1);
        Assert.assertEquals(4,rideRepository.getRides("anju@b.com").length);
    }

    @Test
    public void givenUserId_whenNull_shouldThrowException() {
        try {
            Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                            new Ride(RideType.NORMAL,0.1,1)};
            rideRepository.addRide(null,rides);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenUserId_whenEmpty_shouldThrowException() {
        try {
            Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                            new Ride(RideType.NORMAL,0.1,1)};
            rideRepository.addRide("",rides);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenUserId_whenEmptyRides_shouldThrowException() {
        try {
            Ride[] rides = {};
            rideRepository.addRide("",rides);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(ENTERED_EMPTY,e.type);
        }
    }

}
