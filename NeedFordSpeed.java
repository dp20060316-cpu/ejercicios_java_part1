class NeedForSpeed {
    // Cuánto avanza (en metros) y cuánta batería (%) gasta cada vez que se conduce.
    private final int speed;
    private final int batteryDrain;

    // Estado del carro: cuánto ha recorrido y cuánta batería le queda.
    private int distanceDriven;
    private int battery;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distanceDriven = 0;
        // Todo carro empieza con la batería al 100%.
        this.battery = 100;
    }

    // true si a la batería le queda menos de lo que gasta un solo manejo,
    // es decir, ya no se puede seguir conduciendo.
    public boolean batteryDrained() {
        return battery < batteryDrain;
    }

    public int distanceDriven() {
        return distanceDriven;
    }

    public void drive() {
        // Si la batería está muy baja para otro manejo, no hace nada.
        if (batteryDrained()) {
            return;
        }
        distanceDriven += speed;
        battery -= batteryDrain;
    }

    // El carro más vendido: velocidad 50, gasto de batería 4%.
    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }

    public static void main(String[] args) {
        int speed = 90;
        int batteryDrain = 2;
        var car = new NeedForSpeed(speed, batteryDrain);
        car.drive();
        System.out.println("Distancia recorrida tras un manejo: " + car.distanceDriven());
        System.out.println("¿Batería agotada?: " + car.batteryDrained());

        var nitro = NeedForSpeed.nitro();
        nitro.drive();
        System.out.println("Distancia del Nitro tras un manejo: " + nitro.distanceDriven());

        var raceCar = new NeedForSpeed(speed, batteryDrain);
        var race1 = new RaceTrack(100);
        var race2 = new RaceTrack(300);
        System.out.println("¿Termina la pista de 100m?: " + race1.canFinishRace(raceCar));
        System.out.println("¿Termina la pista de 300m?: " + race2.canFinishRace(raceCar));
    }
}

class RaceTrack {
    private final int distance;

    RaceTrack(int distance) {
        this.distance = distance;
    }

    // Va "conduciendo" el carro paso a paso: si se queda sin batería antes
    // de llegar a la meta, no termina la carrera (false); si llega a la
    // distancia de la pista, sí la termina (true).
    public boolean canFinishRace(NeedForSpeed car) {
        while (car.distanceDriven() < distance) {
            if (car.batteryDrained()) {
                return false;
            }
            car.drive();
        }
        return true;
    }
}