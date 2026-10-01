/**
 * Clase base para cualquier luchador del juego. Define el
 * "contrato" que todo luchador debe cumplir: si es vulnerable o no,
 * cuánto daño hace, y cómo se describe a sí mismo como texto.
 *
 * Cualquier clase que use "extends Fighter" HEREDA estos métodos y
 * puede quedarse con el comportamiento por defecto, o sobrescribirlo
 * (esto se llama "herencia" y "sobrescritura de métodos").
 */
class FighterDemo {

    /**
     * Por defecto, cualquier luchador es vulnerable (puede recibir daño
     * completo). Las subclases pueden cambiar esta regla.
     */
    boolean isVulnerable() {
        return true;
    }

    /**
     * Por defecto, el daño que hace un luchador es 1. Cada subclase
     * decide su propia fórmula de daño.
     *
     * Recibe al "fighter" contra el que está peleando como parámetro,
     * porque el daño puede depender de si el rival es vulnerable o no.
     */
    int getDamagePoints(FighterDemo fighter) {
        return 1;
    }

    /**
     * @Override le avisa al compilador (y a quien lea el código) que
     * este método YA EXISTE en una clase de más arriba (en este caso,
     * en Object, la clase de la que todas las clases de Java heredan
     * automáticamente) y que aquí lo estamos reemplazando a propósito.
     * Si escribieras mal el nombre del método, el compilador te
     * avisaría del error, en vez de crear un método nuevo sin darte
     * cuenta.
     *
     * getClass().getSimpleName() obtiene el nombre de la clase REAL del
     * objeto en tiempo de ejecución (por ejemplo "Warrior" o "Wizard"),
     * aunque la variable esté declarada como tipo Fighter.
     */
    @Override
    public String toString() {
        String description = "Fighter is a " + getClass().getSimpleName();
        System.out.println("toString: " + description);
        return description;
    }
}

/**
 * Un tipo de luchador específico: el Warrior (guerrero).
 *
 * "extends Fighter" significa que Warrior ES UN Fighter, y hereda todo
 * lo que Fighter ya tiene, pero aquí se sobrescriben los tres métodos
 * para darles un comportamiento propio.
 */
class Warrior extends FighterDemo {

    /**
     * A diferencia del comportamiento por defecto de Fighter (siempre
     * vulnerable), el Warrior NUNCA es vulnerable: siempre devuelve
     * false, gracias a su armadura.
     */
    @Override
    boolean isVulnerable() {
        System.out.println("Warrior isVulnerable: false");
        return false;
    }

    /**
     * El Warrior hace más daño (10) si su oponente es vulnerable, y
     * menos (6) si no lo es. Usamos el operador ternario (condición ?
     * valorSiTrue : valorSiFalse) para elegir entre los dos casos en
     * una sola línea.
     *
     * Nota importante: aquí se llama fighter.isVulnerable(), es decir,
     * el método SOBRESCRITO de quien sea que reciba como parámetro (un
     * Warrior, un Wizard, o cualquier otro Fighter). Esto es
     * "polimorfismo": Java decide en tiempo de ejecución cuál versión
     * de isVulnerable() ejecutar, según el tipo REAL del objeto, no
     * según cómo esté declarada la variable.
     */
    @Override
    int getDamagePoints(FighterDemo fighter) {
        int damage = fighter.isVulnerable() ? 10 : 6;
        System.out.println("Warrior getDamagePoints: " + damage + " (vulnerable opponent: " + fighter.isVulnerable() + ")");
        return damage;
    }

    @Override
    public String toString() {
        String description = "Fighter is a Warrior";
        System.out.println("Warrior toString: " + description);
        return description;
    }
}

/**
 * Otro tipo de luchador: el Wizard (mago).
 *
 * A diferencia de Warrior, Wizard tiene un campo PROPIO que no existe
 * en Fighter: spellPrepared. Esto demuestra que una subclase no solo
 * puede sobrescribir métodos, también puede agregar sus propios campos
 * y métodos nuevos que la clase base no tiene.
 */
class Wizard extends FighterDemo {

    // Empieza en false: el mago nace sin ningún hechizo preparado.
    private boolean spellPrepared = false;

    /**
     * Método NUEVO, exclusivo de Wizard (Fighter no lo tiene). Prepara
     * el hechizo, cambiando el estado interno del mago.
     */
    public void prepareSpell() {
        this.spellPrepared = true;
        System.out.println("Wizard prepareSpell: hechizo preparado");
    }

    /**
     * El mago es vulnerable SOLO si no tiene un hechizo preparado (el
     * hechizo lo protege). Por eso se usa "!spellPrepared" (lo
     * contrario del valor del campo).
     */
    @Override
    boolean isVulnerable() {
        boolean vulnerable = !spellPrepared;
        System.out.println("Wizard isVulnerable: " + vulnerable);
        return vulnerable;
    }

    /**
     * El mago hace mucho más daño (12) si tiene el hechizo preparado, y
     * muy poco (3) si no. A diferencia de Warrior, aquí la condición no
     * depende del rival, sino del propio estado del mago (spellPrepared).
     */
    @Override
    int getDamagePoints(FighterDemo fighter) {
        int damage = spellPrepared ? 12 : 3;
        System.out.println("Wizard getDamagePoints: " + damage + " (spell prepared: " + spellPrepared + ")");
        return damage;
    }

    @Override
    public String toString() {
        String description = "Fighter is a Wizard";
        System.out.println("Wizard toString: " + description);
        return description;
    }
}

// Clase con el main, para poder probar Fighter, Warrior y Wizard juntos.
public class FightersDemo {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        Wizard wizard = new Wizard();

        System.out.println("--- Presentacion ---");
        System.out.println(warrior); // llama automaticamente a toString()
        System.out.println(wizard);

        System.out.println("--- Warrior ataca a Wizard (sin hechizo) ---");
        warrior.getDamagePoints(wizard); // Wizard es vulnerable (no ha preparado hechizo) => 10

        System.out.println("--- Wizard prepara su hechizo ---");
        wizard.prepareSpell();

        System.out.println("--- Warrior ataca a Wizard (con hechizo) ---");
        warrior.getDamagePoints(wizard); // Wizard ya no es vulnerable => 6

        System.out.println("--- Wizard ataca a Warrior ---");
        wizard.getDamagePoints(warrior); // Wizard tiene hechizo preparado => 12
    }
}