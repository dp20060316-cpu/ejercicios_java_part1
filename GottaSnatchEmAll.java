import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    // Un Set no permite duplicados, asi que simplemente metemos todas
    // las cartas de la lista en un HashSet nuevo: las repetidas se
    // eliminan solas.
    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    // Set.add() ya devuelve un boolean: true si el elemento no estaba
    // y se agrego, false si ya estaba y no cambio nada. Es justo lo
    // que pide el metodo, asi que solo delegamos.
    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    // Solo se puede intercambiar si YO tengo algo que mi amigo no
    // tiene, Y mi amigo tiene algo que yo no tengo.
    // Hacemos una copia de cada coleccion y le quitamos (removeAll)
    // todo lo que la otra coleccion ya tiene; lo que sobra son las
    // cartas "exclusivas" de cada uno. Usamos copias para no
    // modificar las colecciones originales del que nos llama.
    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        Set<String> onlyMine = new HashSet<>(myCollection);
        onlyMine.removeAll(theirCollection);

        Set<String> onlyTheirs = new HashSet<>(theirCollection);
        onlyTheirs.removeAll(myCollection);

        return !onlyMine.isEmpty() && !onlyTheirs.isEmpty();
    }

    // La interseccion de todas las colecciones: empezamos con una
    // copia de la primera coleccion y vamos usando retainAll con cada
    // una de las demas. retainAll se queda solo con los elementos que
    // tambien estan en el set que le pasas, es decir, hace la
    // interseccion poco a poco.
    static Set<String> commonCards(List<Set<String>> collections) {
        if (collections.isEmpty()) {
            return new HashSet<>();
        }
        Set<String> common = new HashSet<>(collections.get(0));
        for (int i = 1; i < collections.size(); i++) {
            common.retainAll(collections.get(i));
        }
        return common;
    }

    // La union de todas las colecciones: un set vacio al que le vamos
    // agregando (addAll) el contenido de cada coleccion. Como es un
    // Set, los duplicados entre colecciones se eliminan solos.
    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> all = new HashSet<>();
        for (Set<String> collection : collections) {
            all.addAll(collection);
        }
        return all;
    }

    
    public static void main(String[] args) {
        System.out.println(GottaSnatchEmAll.newCollection(List.of("Newthree", "Newthree", "Newthree")));

        Set<String> collection = GottaSnatchEmAll.newCollection(List.of("Newthree"));
        System.out.println(GottaSnatchEmAll.addCard("Scientuna", collection));
        System.out.println(collection.contains("Scientuna"));

        Set<String> myCollection = Set.of("Newthree");
        Set<String> theirCollection = Set.of("Scientuna");
        System.out.println(GottaSnatchEmAll.canTrade(myCollection, theirCollection));

        System.out.println(GottaSnatchEmAll.commonCards(List.of(Set.of("Scientuna"), Set.of("Newthree", "Scientuna"))));
        System.out.println(GottaSnatchEmAll.allCards(List.of(Set.of("Scientuna"), Set.of("Newthree", "Scientuna"))));
    }
}