public class Secrets {

    // Desplaza los bits hacia la derecha "amount" posiciones, insertando
    // siempre 0s por la izquierda (shift logico). Por eso usamos >>>
    // y no >>, que insertaria el bit de signo en vez de 0.
    public static int shiftBack(int value, int amount) {
        return value >>> amount;
    }

    // Pone en 1 los bits de "value" donde el "mask" tenga un 1,
    // dejando el resto igual. Eso es justo lo que hace el OR (|).
    public static int setBits(int value, int mask) {
        return value | mask;
    }

    // Invierte (flip) los bits de "value" donde el "mask" tenga un 1,
    // y deja el resto igual. Eso es justo lo que hace el XOR (^).
    public static int flipBits(int value, int mask) {
        return value ^ mask;
    }

    // Pone en 0 los bits de "value" donde el "mask" tenga un 1,
    // dejando el resto igual. Primero invertimos el mask con ~ (asi
    // donde habia un 1 queda un 0 y viceversa), y luego hacemos AND:
    // el AND con un 0 siempre apaga ese bit, y el AND con un 1 deja
    // el bit original sin cambios.
    public static int clearBits(int value, int mask) {
        return value & ~mask;
    }

   
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Secrets.shiftBack(0b1001, 2)));
        System.out.println(Integer.toBinaryString(Secrets.setBits(0b0110, 0b0101)));
        System.out.println(Integer.toBinaryString(Secrets.flipBits(0b1100, 0b0101)));
        System.out.println(Integer.toBinaryString(Secrets.clearBits(0b0110, 0b0101)));
    }
}