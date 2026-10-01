import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class AppointmentScheduler {

    // El texto llega como "7/25/2019 13:45:00": mes/dia/anio hora:min:seg.
    // Usamos un DateTimeFormatter con ese patron exacto para poder
    // convertirlo (parsear) a un LocalDateTime real.
    public LocalDateTime schedule(String appointmentDateDescription) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
        return LocalDateTime.parse(appointmentDateDescription, parser);
    }

    // "Ya paso" simplemente significa que la fecha de la cita es
    // anterior al momento actual.
    public boolean hasPassed(LocalDateTime appointmentDate) {
        return appointmentDate.isBefore(LocalDateTime.now());
    }

    // Es una cita "de tarde" si la hora esta entre las 12:00 (inclusive)
    // y las 18:00 (exclusive), es decir, de 12pm a 5:59pm.
    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        int hour = appointmentDate.getHour();
        return hour >= 12 && hour < 18;
    }

    // Formato esperado: "You have an appointment on Friday, March 29, 2019, at 3:00 PM."
    // EEEE  -> nombre completo del dia (Friday)
    // MMMM  -> nombre completo del mes (March)
    // d     -> dia del mes sin cero adelante (29)
    // yyyy  -> anio con 4 digitos (2019)
    // h:mm a -> hora en formato 12h con AM/PM (3:00 PM)
    // Usamos Locale.US para asegurarnos de que los nombres salgan en
    // ingles sin importar el idioma configurado en la maquina.
    public String getDescription(LocalDateTime appointmentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE, MMMM d, yyyy, 'at' h:mm a", Locale.US);
        return "You have an appointment on " + appointmentDate.format(formatter) + ".";
    }

    // El salon abrio el 15 de septiembre de 2012. El aniversario de
    // "este anio" siempre es el 15 de septiembre del anio actual.
    public LocalDate getAnniversaryDate() {
        return LocalDate.of(LocalDate.now().getYear(), 9, 15);
    }

    // --- Solo para correrlo en VS Code. Borra este metodo antes de
    // enviar la solucion a Exercism. ---
    public static void main(String[] args) {
        AppointmentScheduler scheduler = new AppointmentScheduler();

        System.out.println(scheduler.schedule("7/25/2019 13:45:00"));
        System.out.println(scheduler.hasPassed(LocalDateTime.of(1999, 12, 31, 9, 0, 0)));
        System.out.println(scheduler.isAfternoonAppointment(LocalDateTime.of(2019, 3, 29, 15, 0, 0)));
        System.out.println(scheduler.getDescription(LocalDateTime.of(2019, 3, 29, 15, 0, 0)));
        System.out.println(scheduler.getAnniversaryDate());
    }
}