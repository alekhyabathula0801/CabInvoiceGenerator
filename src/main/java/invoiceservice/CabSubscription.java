package invoiceservice;

public class CabSubscription {

    public double MINIMUM_COST_PER_KILOMETER;
    public int COST_PER_TIME;
    public double MINIMUM_FARE;
    private double MINIMUM_COST_PER_KILOMETER_NORMAL = 10;
    private int COST_PER_TIME_NORMAL = 1;
    private double MINIMUM_FARE_NORMAL = 5;
    private double MINIMUM_COST_PER_KILOMETER_PREMIUM = 15;
    private int COST_PER_TIME_PREMIUM = 2;
    private double MINIMUM_FARE_PREMIUM = 20;

    public CabSubscription(InvoiceService.ServiceType serviceType) {
        if (serviceType.equals(InvoiceService.ServiceType.NORMAL)) {
            this.MINIMUM_COST_PER_KILOMETER = MINIMUM_COST_PER_KILOMETER_NORMAL;
            this.COST_PER_TIME = COST_PER_TIME_NORMAL;
            this.MINIMUM_FARE = MINIMUM_FARE_NORMAL;
        } else {
            this.MINIMUM_COST_PER_KILOMETER = MINIMUM_COST_PER_KILOMETER_PREMIUM;
            this.COST_PER_TIME = COST_PER_TIME_PREMIUM;
            this.MINIMUM_FARE = MINIMUM_FARE_PREMIUM;
        }
    }

}
