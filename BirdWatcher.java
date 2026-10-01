import java.util.Arrays;

/**
 * Registra y analiza cuántas aves visitaron el jardín cada día.
 *
 * El arreglo "birdsPerDay" guarda un conteo por día, donde el ÚLTIMO
 * elemento siempre representa el día de HOY, y el PRIMERO el día más
 * antiguo registrado.
 */
class BirdWatcher {
    private final int[] birdsPerDay;

    /**
     * Guarda una COPIA del arreglo recibido (con .clone()), no la
     * referencia original. Esto protege a BirdWatcher: si alguien
     * modifica el arreglo que pasó como argumento desde afuera, no
     * afecta los datos internos de este objeto.
     */
    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    /**
     * Devuelve los conteos fijos de la semana pasada.
     */
    public static int[] getLastWeek() {
        return new int[] { 0, 2, 5, 3, 7, 8, 4 };
    }

    /**
     * Devuelve el conteo de HOY, que siempre es el último elemento
     * del arreglo.
     */
    public int getToday() {
        return birdsPerDay[birdsPerDay.length - 1];
    }

    /**
     * Aumenta en 1 el conteo de hoy (el último elemento).
     */
    public void incrementTodaysCount() {
        birdsPerDay[birdsPerDay.length - 1]++;
    }

    /**
     * Revisa si hubo al menos un día sin ninguna visita (conteo == 0).
     */
    public boolean hasDayWithoutBirds() {
        for (int count : birdsPerDay) {
            if (count == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Suma los conteos desde el inicio de la semana, limitando a los
     * primeros "numberOfDays" días.
     *
     * CORRECCIÓN: si "numberOfDays" es mayor que la cantidad de días
     * que realmente tenemos guardados (birdsPerDay.length), el test
     * espera que devolvamos el total de TODOS los días disponibles,
     * en vez de intentar leer posiciones que no existen (lo cual
     * lanzaría un IndexOutOfBoundsException).
     *
     * Math.min(numberOfDays, birdsPerDay.length) se queda con el
     * valor MÁS PEQUEÑO entre los dos: si numberOfDays es 100 pero
     * solo hay 7 días registrados, usamos 7 como límite real del
     * bucle.
     */
    public int getCountForFirstDays(int numberOfDays) {
        int total = 0;
        int daysToCount = Math.min(numberOfDays, birdsPerDay.length);

        for (int i = 0; i < daysToCount; i++) {
            total += birdsPerDay[i];
        }
        return total;
    }

    /**
     * Cuenta cuántos días fueron "ocupados", es decir, días en los
     * que visitaron 5 aves o más.
     */
    public int getBusyDays() {
        int busyDays = 0;
        for (int count : birdsPerDay) {
            if (count >= 5) {
                busyDays++;
            }
        }
        return busyDays;
    }

    // Este main permite ejecutar y probar SOLO este archivo.
    public static void main(String[] args) {
        System.out.println("getLastWeek(): " + Arrays.toString(BirdWatcher.getLastWeek()));

        int[] birdsPerDay = { 2, 5, 0, 7, 4, 1 };
        BirdWatcher birdCount = new BirdWatcher(birdsPerDay);

        System.out.println("getToday(): " + birdCount.getToday());

        birdCount.incrementTodaysCount();
        System.out.println("Despues de incrementTodaysCount(), getToday(): " + birdCount.getToday());

        System.out.println("hasDayWithoutBirds(): " + birdCount.hasDayWithoutBirds());
        System.out.println("getCountForFirstDays(4): " + birdCount.getCountForFirstDays(4));
        System.out.println("getBusyDays(): " + birdCount.getBusyDays());

        // Caso del test que fallaba: pedir más días de los que hay
        int[] counts = { 5, 9, 12, 6, 10, 8, 17 };
        BirdWatcher birdWatcher2 = new BirdWatcher(counts);
        System.out.println("getCountForFirstDays(100) con 7 dias: " + birdWatcher2.getCountForFirstDays(100));
        // => 5+9+12+6+8+8+17 = 65
    }
}