import java.util.Random;

/**
 * Simula el generador de datos aleatorios de un "diario de capitán" al
 * estilo Star Trek: clases de planetas, números de registro de naves,
 * y fechas estelares (stardates).
 */
class CaptainsLog {

    /**
     * Letras válidas para clasificar un planeta (clase D, H, J, K, etc.,
     * un sistema real usado en el universo de Star Trek).
     *
     * Es "static" porque este dato NO depende de ningún CaptainsLog en
     * particular: es el mismo catálogo de letras para todos. Es "final"
     * porque nunca debe reasignarse una vez creado (no evita que
     * alguien modifique el contenido del arreglo, pero sí evita que la
     * variable misma vuelva a apuntar a otro arreglo distinto).
     */
    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    // El generador de números aleatorios que usará este CaptainsLog.
    private Random random;

    /**
     * El constructor RECIBE el Random desde afuera, en vez de crear uno
     * nuevo aquí adentro (como sería "this.random = new Random();").
     *
     * Esto se llama "inyección de dependencias" y es clave para poder
     * probar esta clase: en los tests automáticos, se le puede pasar un
     * Random con una "semilla" fija (new Random(42), por ejemplo) para
     * que siempre genere los mismos números "aleatorios", y así el test
     * pueda verificar un resultado exacto. En el programa real, se le
     * pasa un Random normal, sin semilla fija.
     */
    CaptainsLog(Random random) {
        this.random = random;
    }

    /**
     * Elige una clase de planeta al azar, de las que hay en PLANET_CLASSES.
     *
     * random.nextInt(PLANET_CLASSES.length) devuelve un número entero
     * aleatorio entre 0 (incluido) y la longitud del arreglo (excluido),
     * es decir, siempre un índice válido para ese arreglo. Con ese
     * índice, sacamos la letra correspondiente.
     */
    char randomPlanetClass() {
        int index = random.nextInt(PLANET_CLASSES.length);
        char result = PLANET_CLASSES[index];
        System.out.println("randomPlanetClass: " + result);
        return result;
    }

    /**
     * Genera un número de registro de nave al azar, con el formato
     * "NCC-XXXX" (el prefijo real que usa la flota estelar de Star Trek),
     * donde XXXX es un número entre 1000 y 9999.
     *
     * random.nextInt(9000) da un número entre 0 y 8999. Al sumarle 1000,
     * el rango final queda entre 1000 y 9999 (siempre de 4 dígitos).
     */
    String randomShipRegistryNumber() {
        int number = 1000 + random.nextInt(9000);
        String result = "NCC-" + number;
        System.out.println("randomShipRegistryNumber: " + result);
        return result;
    }

    /**
     * Genera una fecha estelar (stardate) al azar, como número decimal
     * entre 41000.0 y 42000.0.
     *
     * random.nextDouble() devuelve siempre un decimal entre 0.0
     * (incluido) y 1.0 (excluido). Al multiplicarlo por 1000.0 y
     * sumarle 41000.0, el resultado queda dentro del rango deseado
     * (41000.0 a 42000.0).
     */
    double randomStardate() {
        double result = 41000.0 + 1000.0 * random.nextDouble();
        System.out.println("randomStardate: " + result);
        return result;
    }

    // Este main permite ejecutar y probar SOLO este archivo.
    public static void main(String[] args) {
        // Random "normal", sin semilla fija: cada vez que corras esto,
        // los resultados van a ser distintos (por eso es aleatorio de verdad).
        CaptainsLog log = new CaptainsLog(new Random());

        log.randomPlanetClass();
        log.randomShipRegistryNumber();
        log.randomStardate();
    }
}