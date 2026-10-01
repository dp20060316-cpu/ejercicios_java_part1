class SqueakyClean {

    static String clean(String identifier) {
        // Paso 1: cambiar espacios por guiones bajos.
        String step1 = identifier.replace(' ', '_');

        // Paso 2: convertir kebab-case a camelCase.
        // Cada vez que encontramos un '-', lo quitamos y ponemos en
        // mayúscula la siguiente letra (si existe).
        StringBuilder step2 = new StringBuilder();
        for (int i = 0; i < step1.length(); i++) {
            char c = step1.charAt(i);
            if (c == '-') {
                if (i + 1 < step1.length()) {
                    step2.append(Character.toUpperCase(step1.charAt(i + 1)));
                    i++; // nos saltamos el carácter que ya usamos
                }
                // si el guion está al final de la cadena, simplemente se descarta
            } else {
                step2.append(c);
            }
        }

       
        StringBuilder step3 = new StringBuilder();
        for (char c : step2.toString().toCharArray()) {
            switch (c) {
                case '4': step3.append('a'); break;
                case '3': step3.append('e'); break;
                case '0': step3.append('o'); break;
                case '1': step3.append('l'); break;
                case '7': step3.append('t'); break;
                default: step3.append(c);
            }
        }

        // Paso 4: quedarnos solo con letras y guiones bajos (se descarta
        // cualquier otro símbolo: $, #, ., números que quedaron, etc.).
        StringBuilder result = new StringBuilder();
        for (char c : step3.toString().toCharArray()) {
            if (Character.isLetter(c) || c == '_') {
                result.append(c);
            }
        }

        return result.toString();
    }

   
    public static void main(String[] args) {
        System.out.println(SqueakyClean.clean("my   Id"));          // my___Id
        System.out.println(SqueakyClean.clean("a-bc"));             // aBc
        System.out.println(SqueakyClean.clean("H3ll0 W0rld"));      // Hello_World
        System.out.println(SqueakyClean.clean("4 73s7"));           // a_test
        System.out.println(SqueakyClean.clean("a$#.b"));            // ab
    }
}