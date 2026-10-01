/**
 * Contiene la lógica de la misión de rescate de Annalyn.
 *
 * Todos los métodos son "static", tal como pide el ejercicio, lo que
 * significa que se pueden llamar directamente sobre la clase
 * (AnnalynsInfiltration.metodo(...)) sin necesidad de crear un objeto
 * con "new".
 */
class AnnalynsInfiltration {

    public static boolean canFastAttack(boolean knightIsAwake) {
        return !knightIsAwake;
    }

    public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
        return knightIsAwake || archerIsAwake || prisonerIsAwake;
    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
        return prisonerIsAwake && !archerIsAwake;
    }

    public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake, boolean petDogIsPresent) {
        boolean canFreeWithDog = petDogIsPresent && !archerIsAwake;
        boolean canFreeWithoutDog = !petDogIsPresent && prisonerIsAwake && !knightIsAwake && !archerIsAwake;

        return canFreeWithDog || canFreeWithoutDog;
    }

    public static void main(String[] args) {
        System.out.println("canFastAttack(false): " + AnnalynsInfiltration.canFastAttack(false));
        System.out.println("canSpy(false, true, false): " + AnnalynsInfiltration.canSpy(false, true, false));
        System.out.println("canSignalPrisoner(false, true): " + AnnalynsInfiltration.canSignalPrisoner(false, true));
        System.out.println("canFreePrisoner(false, true, false, false): " + AnnalynsInfiltration.canFreePrisoner(false, true, false, false));
    }
}