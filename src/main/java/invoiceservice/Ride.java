package invoiceservice;

public class Ride {

    public InvoiceService.ServiceType serviceType;
    public int time;
    public double distance;

    public Ride(InvoiceService.ServiceType serviceType, double distance, int time) {
        this.serviceType = serviceType;
        this.distance = distance;
        this.time = time;
    }

}
