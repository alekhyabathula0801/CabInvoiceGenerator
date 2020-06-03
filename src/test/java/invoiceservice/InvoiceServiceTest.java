package invoiceservice;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    RideRepository rideRepository;

    @InjectMocks
    public InvoiceService invoiceService;

    @Test
    public void givenNormalRideWithDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(RideType.NORMAL,distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenNormalRideWithLessDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(RideType.NORMAL,distance,time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenNormalMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5),
                        new Ride(RideType.NORMAL,0.1,1)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndNormalRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL,2.0,5),
                        new Ride(RideType.NORMAL,0.1,1)};
        when(rideRepository.getRides("anju@b.com")).thenReturn(rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary("anju@b.com");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
        verify(rideRepository).getRides("anju@b.com");
    }

    @Test
    public void givenPremiumRideWithDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(RideType.PREMIUM,distance,time);
        Assert.assertEquals(40,fare,0.0);
    }

    @Test
    public void givenPremiumRideWithLessDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(RideType.PREMIUM,distance,time);
        Assert.assertEquals(20,fare,0.0);
    }

    @Test
    public void givenMultiplePremiumRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.PREMIUM,2.0, 5),
                        new Ride(RideType.PREMIUM,0.1,1)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,60.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() {
        String userId = "anju@b.com";
        Ride[] rides = {new Ride(RideType.PREMIUM,2.0,5),
                        new Ride(RideType.NORMAL,0.1,1)};
        when(rideRepository.getRides("anju@b.com")).thenReturn(rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,45.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
        verify(rideRepository).getRides("anju@b.com");
    }

    @Test
    public void givenUserId_whenInvalid_shouldThrowException() {
        try {
            when(rideRepository.getRides(null)).thenThrow(new RuntimeException("Entered Null"));
            invoiceService.getInvoiceSummary(null);
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(),"Entered Null");
        }
    }

}
