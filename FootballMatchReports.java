public class FootballMatchReports {

    public static String onField(int shirtNum) {
        switch (shirtNum) {
            case 1:
                return "goalie";
            case 2:
                return "left back";
            case 3:
            case 4:
                // 3 y 4 comparten la misma descripción, por eso caen
                // en el mismo case (fall-through) sin nada entre ellos.
                return "center back";
            case 5:
                return "right back";
            case 6:
            case 7:
            case 8:
                return "midfielder";
            case 9:
                return "left wing";
            case 10:
                return "striker";
            case 11:
                return "right wing";
            default:
                // Cualquier número fuera del rango 1-11 no es válido.
                return "invalid";
        }
    }

    public static void main(String[] args) {
        System.out.println("Camiseta 1: " + FootballMatchReports.onField(1));
        System.out.println("Camiseta 4: " + FootballMatchReports.onField(4));
        System.out.println("Camiseta 7: " + FootballMatchReports.onField(7));
        System.out.println("Camiseta 10: " + FootballMatchReports.onField(10));
        System.out.println("Camiseta 13 (fuera de rango): " + FootballMatchReports.onField(13));
    }
}