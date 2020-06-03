package invoiceservice;

public enum RideType {

    NORMAL(10,1,5),PREMIUM(15,2,20);

    private final int costPerKiloMeter;
    private final int costPerMinute;
    private final int minimumCost;

    RideType(int costPerKiloMeter, int costPerMinute, int minimumCost) {
        this.costPerKiloMeter = costPerKiloMeter;
        this.costPerMinute = costPerMinute;
        this.minimumCost = minimumCost;
    }

    public int getCostPerKiloMeter() {
        return costPerKiloMeter;
    }

    public int getCostPerMinute() {
        return costPerMinute;
    }

    public int getMinimumCost() {
        return minimumCost;
    }

}
