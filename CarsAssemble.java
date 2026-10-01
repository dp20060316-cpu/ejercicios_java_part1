public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        double successRate;

        if (speed <= 4) {
            successRate = 1.0;
        } else if (speed <= 8) {
            successRate = 0.9;
        } else if (speed == 9) {
            successRate = 0.8;
        } else {
            successRate = 0.77;
        }

        return speed * 221 * successRate;
    }

    public int workingItemsPerMinute(int speed) {
        double ratePerMinute = productionRatePerHour(speed) / 60;
        return (int) ratePerMinute;
    }

    public static void main(String[] args) {
        CarsAssemble ca = new CarsAssemble();
        System.out.println("productionRatePerHour(6): " + ca.productionRatePerHour(6));
        System.out.println("workingItemsPerMinute(6): " + ca.workingItemsPerMinute(6));
    }
}
