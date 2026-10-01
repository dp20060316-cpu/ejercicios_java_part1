public class JedliksToyCar {

    private int distanceDriven;
    private int batteryPercentage;

    /**
     * Constructor público: los tests crean el carrito directamente con
     * "new JedliksToyCar()", así que debe poder llamarse desde fuera.
     * Inicializa el carrito nuevo: 0 metros recorridos y batería al 100%.
     */
    public JedliksToyCar() {
        this.distanceDriven = 0;
        this.batteryPercentage = 100;
    }

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + distanceDriven + " meters";
    }

    public String batteryDisplay() {
        if (batteryPercentage == 0) {
            return "Battery empty";
        }
        return "Battery at " + batteryPercentage + "%";
    }

    public void drive() {
        if (batteryPercentage > 0) {
            distanceDriven += 20;
            batteryPercentage -= 1;
        }
    }

    public static void main(String[] args) {
        JedliksToyCar car = JedliksToyCar.buy();
        System.out.println(car.distanceDisplay());
        System.out.println(car.batteryDisplay());

        car.drive();
        car.drive();
        System.out.println(car.distanceDisplay());
        System.out.println(car.batteryDisplay());


        JedliksToyCar drainedCar = JedliksToyCar.buy();
        for (int i = 0; i < 100; i++) {
            drainedCar.drive();
        }
        drainedCar.drive();

        System.out.println(drainedCar.distanceDisplay());
        System.out.println(drainedCar.batteryDisplay());
    }
}