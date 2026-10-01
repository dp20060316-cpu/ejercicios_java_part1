public class SalaryCalculator {

    // Si el empleado falto 5 dias o mas, se le aplica una penalizacion
    // del 15% (multiplicador 0.85). Si no, cobra completo (1.0).
    public double salaryMultiplier(int daysSkipped) {
        return daysSkipped >= 5 ? 0.85 : 1.0;
    }

    // Si vendio 20 productos o mas, el multiplicador de bono sube a 13.
    // Si no, se queda en 10.
    public int bonusMultiplier(int productsSold) {
        return productsSold >= 20 ? 13 : 10;
    }

    // El bono total es: productos vendidos * multiplicador de bono.
    public double bonusForProductsSold(int productsSold) {
        return productsSold * bonusMultiplier(productsSold);
    }

    // Salario final = (salario base 1000.00 * multiplicador por dias
    // faltados) + bono por ventas, pero nunca puede pasar de 2000.00.
    public double finalSalary(int daysSkipped, int productsSold) {
        double baseSalary = 1000.00;
        double total = (baseSalary * salaryMultiplier(daysSkipped)) + bonusForProductsSold(productsSold);
        return total > 2000.00 ? 2000.00 : total;
    }

    // --- Solo para correrlo en VS Code. Borra este metodo antes de
    // enviar la solucion a Exercism. ---
    public static void main(String[] args) {
        SalaryCalculator calc = new SalaryCalculator();

        System.out.println("salaryMultiplier(3) = " + calc.salaryMultiplier(3));   // 1.0
        System.out.println("salaryMultiplier(7) = " + calc.salaryMultiplier(7));   // 0.85

        System.out.println("bonusMultiplier(21) = " + calc.bonusMultiplier(21));   // 13
        System.out.println("bonusForProductsSold(21) = " + calc.bonusForProductsSold(21)); // 273.0

        System.out.println("bonusMultiplier(5) = " + calc.bonusMultiplier(5));     // 10
        System.out.println("bonusForProductsSold(5) = " + calc.bonusForProductsSold(5));   // 50.0

        System.out.println("finalSalary(2, 3) = " + calc.finalSalary(2, 3));       // 1030.0
        System.out.println("finalSalary(2, 90) = " + calc.finalSalary(2, 90));     // 2000.0
    }
}