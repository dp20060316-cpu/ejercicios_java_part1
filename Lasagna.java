public class Lasagna {

    public int expectedMinutesInOven() {
        return 40;
    }

    public int remainingMinutesInOven(int actualMinutesInOven) {
        return this.expectedMinutesInOven() - actualMinutesInOven;
    }

    public int preparationTimeInMinutes(int numberOfLayers) {
        return numberOfLayers * 2;
    }

    public int totalTimeInMinutes(int numberOfLayers, int actualMinutesInOven) {
        return this.preparationTimeInMinutes(numberOfLayers) + actualMinutesInOven;
    }

    public static void main(String[] args) {
        Lasagna lasagna = new Lasagna();

        int numberOfLayers = 3;
        int actualMinutesInOven = 20;

        System.out.println("Minutos esperados en el horno: " + lasagna.expectedMinutesInOven());
        System.out.println("Minutos restantes en el horno: " + lasagna.remainingMinutesInOven(actualMinutesInOven));
        System.out.println("Tiempo de preparacion (" + numberOfLayers + " capas): " + lasagna.preparationTimeInMinutes(numberOfLayers));
        System.out.println("Tiempo total: " + lasagna.totalTimeInMinutes(numberOfLayers, actualMinutesInOven));
    }
}