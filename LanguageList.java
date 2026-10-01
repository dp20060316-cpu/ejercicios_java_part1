import java.util.ArrayList;
import java.util.List;

public class LanguageList {
    private final List<String> languages = new ArrayList<>();

    /**
     * Indica si la lista no tiene ningún lenguaje todavía.
     *
     * List ya trae un método isEmpty() propio, así que simplemente lo
     * delegamos: no hace falta comparar languages.size() == 0 a mano.
     */
    public boolean isEmpty() {
        return languages.isEmpty();
    }

    /**
     * Agrega un lenguaje al final de la lista.
     *
     * add() es el método estándar de List para insertar un elemento.
     * Como la lista es de tipo List<String> (gracias a los genéricos),
     * el compilador no dejaría agregar nada que no sea un String.
     */
    public void addLanguage(String language) {
        languages.add(language);
    }

    /**
     * Quita un lenguaje de la lista, si está presente.
     *
     * remove() busca la PRIMERA aparición de ese valor y la elimina.
     * Como languages es List<String>, Java sabe que "language" es un
     * objeto a buscar (no una posición), así que no hay ambigüedad con
     * la otra versión de remove() que borra por índice (int).
     */
    public void removeLanguage(String language) {
        languages.remove(language);
    }

    /**
     * Devuelve el primer lenguaje de la lista (el más antiguo que
     * sigue ahí).
     *
     * get(0) accede al elemento en el índice 0, es decir, el primero
     * que fue agregado y todavía no se ha eliminado.
     */
    public String firstLanguage() {
        return languages.get(0);
    }

    /**
     * Devuelve cuántos lenguajes hay actualmente en la lista.
     *
     * size() es el equivalente de "length" para List (los arreglos
     * usan length, pero List, al ser una interfaz con más
     * funcionalidad, usa un método: size()).
     */
    public int count() {
        return languages.size();
    }

    /**
     * Indica si un lenguaje específico ya está en la lista.
     *
     * contains() revisa todos los elementos y devuelve true si
     * encuentra una coincidencia exacta (usando equals(), no ==),
     * lo cual funciona bien para comparar Strings por contenido.
     */
    public boolean containsLanguage(String language) {
        return languages.contains(language);
    }

    /**
     * Indica si Karl quiere aprender algo "emocionante": Java o Kotlin.
     *
     * Reutilizamos containsLanguage() dos veces en vez de repetir la
     * lógica de búsqueda, y las combinamos con || (OR): basta con que
     * uno de los dos esté en la lista.
     */
    public boolean isExciting() {
        return containsLanguage("Java") || containsLanguage("Kotlin");
    }

    // Este main permite ejecutar y probar SOLO este archivo.
    public static void main(String[] args) {
        LanguageList languageList = new LanguageList();

        System.out.println("isEmpty() al inicio: " + languageList.isEmpty()); // true

        languageList.addLanguage("Kotlin");
        languageList.addLanguage("Python");
        languageList.addLanguage("Scala");

        System.out.println("isEmpty() con datos: " + languageList.isEmpty()); // false
        System.out.println("count(): " + languageList.count()); // 3
        System.out.println("firstLanguage(): " + languageList.firstLanguage()); // Kotlin

        languageList.removeLanguage("Scala");
        System.out.println("count() despues de remover Scala: " + languageList.count()); // 2

        System.out.println("containsLanguage(Python): " + languageList.containsLanguage("Python")); // true
        System.out.println("containsLanguage(Ruby): " + languageList.containsLanguage("Ruby")); // false

        System.out.println("isExciting() (tiene Kotlin): " + languageList.isExciting()); // true
    }
}