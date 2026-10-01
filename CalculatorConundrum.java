    // Excepción propia para cuando la operación no existe, o cuando se intenta
    // dividir entre cero. Extiende RuntimeException, así que es una excepción
    // "unchecked": quien llame a calculate() no está obligado a capturarla.
    class IllegalOperationException extends RuntimeException {

        public IllegalOperationException(String message) {
            super(message);
        }

        
        public IllegalOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class CalculatorConundrum {

        public String calculate(int operand1, int operand2, String operation) {
            // Validaciones de argumentos "ilegales": null o vacío.
            if (operation == null) {
                throw new IllegalArgumentException("Operation cannot be null");
            }
            if (operation.isEmpty()) {
                throw new IllegalArgumentException("Operation cannot be empty");
            }

            int result;
            switch (operation) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    try {
                        result = operand1 / operand2;
                    } catch (ArithmeticException e) {
                        // Java lanza ArithmeticException al dividir entre cero.
                        // La atrapamos y la convertimos en nuestra propia
                        // excepción, guardando la original como "cause".
                        throw new IllegalOperationException("Division by zero is not allowed", e);
                    }
                    break;
                default:
                    // Cualquier símbolo que no sea +, * o / no es una
                    // opexración soportada.
                    throw new IllegalOperationException("Operation '" + operation + "' does not exist");
            }

            return operand1 + " " + operation + " " + operand2 + " = " + result;
        }

        public static void main(String[] args) {
            CalculatorConundrum calculator = new CalculatorConundrum();

            System.out.println(calculator.calculate(56, 51, "+"));
            System.out.println(calculator.calculate(32, 6, "*"));
            System.out.println(calculator.calculate(512, 4, "/"));

            try {
                calculator.calculate(10, 1, null);
            } catch (IllegalArgumentException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            try {
                calculator.calculate(10, 1, "");
            } catch (IllegalArgumentException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            try {
                calculator.calculate(10, 1, "-");
            } catch (IllegalOperationException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            try {
                calculator.calculate(512, 0, "/");
            } catch (IllegalOperationException e) {
                System.out.println("Error esperado: " + e.getMessage() + " (causa: " + e.getCause() + ")");
            }
        }
    }