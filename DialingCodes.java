import java.util.HashMap;
import java.util.Map;

public class DialingCodes {

    // El diccionario real: codigo de marcacion (clave) -> pais (valor).
    private Map<Integer, String> codes = new HashMap<>();

    // Devuelve el mapa completo tal cual esta ahora.
    public Map<Integer, String> getCodes() {
        return codes;
    }

    // Agrega (o actualiza, si ya existia) el codigo con el pais dado.
    // put() se encarga de ambos casos: si la clave no existe, la crea;
    // si ya existe, reemplaza el valor anterior.
    public void setDialingCode(Integer code, String country) {
        codes.put(code, country);
    }

    // Busca el pais asociado a un codigo. get() devuelve null si la
    // clave no esta en el mapa.
    public String getCountry(Integer code) {
        return codes.get(code);
    }

    // Igual que setDialingCode, pero NO sobreescribe nada: si el
    // codigo ya esta en el mapa, o si el pais ya esta asociado a
    // OTRO codigo, no hace nada.
    public void addNewDialingCode(Integer code, String country) {
        if (!codes.containsKey(code) && !codes.containsValue(country)) {
            codes.put(code, country);
        }
    }

    // Recorre todas las entradas del mapa buscando cual tiene ese
    // pais como valor, y devuelve su clave (el codigo). Si no lo
    // encuentra, devuelve null.
    public Integer findDialingCode(String country) {
        for (Map.Entry<Integer, String> entry : codes.entrySet()) {
            if (entry.getValue().equals(country)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Corrige el codigo de un pais que ya esta en el mapa: primero
    // encontramos su codigo actual con findDialingCode(); si existe,
    // quitamos la entrada vieja y agregamos la nueva con el codigo
    // correcto. Si el pais no esta en el mapa, no hacemos nada.
    public void updateCountryDialingCode(Integer code, String country) {
        Integer oldCode = findDialingCode(country);
        if (oldCode != null) {
            codes.remove(oldCode);
            codes.put(code, country);
        }
    }

    // --- Solo para correrlo en VS Code. Borra este metodo antes de
    // enviar la solucion a Exercism. ---
    public static void main(String[] args) {
        DialingCodes dialingCodes = new DialingCodes();

        dialingCodes.setDialingCode(679, "Unknown");
        System.out.println(dialingCodes.getCodes());
        dialingCodes.setDialingCode(679, "Fiji");
        System.out.println(dialingCodes.getCodes());

        dialingCodes.setDialingCode(55, "Brazil");
        System.out.println(dialingCodes.getCountry(55));

        dialingCodes.addNewDialingCode(32, "Belgium");
        dialingCodes.addNewDialingCode(379, "Vatican City");
        dialingCodes.addNewDialingCode(32, "Other");
        dialingCodes.addNewDialingCode(39, "Vatican City");
        System.out.println(dialingCodes.getCodes());

        dialingCodes.addNewDialingCode(44, "UK");
        System.out.println(dialingCodes.findDialingCode("UK"));
        System.out.println(dialingCodes.findDialingCode("Unlisted"));

        dialingCodes.addNewDialingCode(88, "Japan");
        System.out.println(dialingCodes.getCodes());
        dialingCodes.updateCountryDialingCode(81, "Japan");
        System.out.println(dialingCodes.getCodes());
        dialingCodes.updateCountryDialingCode(32, "Mars");
        System.out.println(dialingCodes.getCodes());
    }
}