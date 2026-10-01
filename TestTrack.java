import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// La interfaz: solo declara QUE métodos debe tener un "carro de control
// remoto", sin decir COMO funcionan. Cada clase que la implemente escribe
// su propio cuerpo para estos métodos.
interface RemoteControlCar {
    void drive();
    int getDistanceTravelled();
}

// Carro de producción: cada drive() avanza 10 unidades.
class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private static final int DISTANCE_PER_DRIVE = 10;

    private int distanceTravelled = 0;
    private int numberOfVictories = 0;

    public void drive() {
        distanceTravelled += DISTANCE_PER_DRIVE;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    // Comparable<T>: le dice a Java cómo ordenar estos carros "por
    // defecto" (ej. con Collections.sort()). Queremos orden
    // DESCENDENTE por victorias (el que tiene más, va primero).
    public int compareTo(ProductionRemoteControlCar other) {
        return other.numberOfVictories - this.numberOfVictories;
    }
}

// Carro experimental: cada drive() avanza el doble, 20 unidades.
class ExperimentalRemoteControlCar implements RemoteControlCar {
    private static final int DISTANCE_PER_DRIVE = 20;

    private int distanceTravelled = 0;

    public void drive() {
        distanceTravelled += DISTANCE_PER_DRIVE;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }
}

public class TestTrack {

    // Recibe el tipo de la INTERFAZ, no el de una clase concreta, así
    // que funciona igual para carros de producción o experimentales:
    // solo le importa que sepan hacer drive().
    public static void race(RemoteControlCar car) {
        car.drive();
    }

    // Ordena la lista de carros de producción usando el compareTo()
    // que definimos arriba (descendente por victorias).
    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        List<ProductionRemoteControlCar> ranked = new ArrayList<>(cars);
        Collections.sort(ranked);
        return ranked;
    }

         public static void main(String[] args) {
        ProductionRemoteControlCar prod = new ProductionRemoteControlCar();
        TestTrack.race(prod);
        System.out.println("Produccion tras 1 carrera: " + prod.getDistanceTravelled());

        ExperimentalRemoteControlCar exp = new ExperimentalRemoteControlCar();
        TestTrack.race(exp);
        System.out.println("Experimental tras 1 carrera: " + exp.getDistanceTravelled());

        ProductionRemoteControlCar prc1 = new ProductionRemoteControlCar();
        ProductionRemoteControlCar prc2 = new ProductionRemoteControlCar();
        prc1.setNumberOfVictories(2);
        prc2.setNumberOfVictories(3);
        List<ProductionRemoteControlCar> unsortedCars = new ArrayList<>();
        unsortedCars.add(prc1);
        unsortedCars.add(prc2);
        List<ProductionRemoteControlCar> rankings = TestTrack.getRankedCars(unsortedCars);
        System.out.println("Primer lugar tiene " + rankings.get(0).getNumberOfVictories() + " victorias");
        System.out.println("Segundo lugar tiene " + rankings.get(1).getNumberOfVictories() + " victorias");
    }
}