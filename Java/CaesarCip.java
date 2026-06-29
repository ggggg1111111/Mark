public class CaesarCip {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            int index = ALPHABET.indexOf(symbol);

            if (index == -1) {
                result.append(symbol);
            } else {
                int newIndex = (index + key) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            }
        }
        return result.toString();
    }

    public String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            int index = ALPHABET.indexOf(symbol);

            if (index == -1) {
                result.append(symbol);
            } else {
                int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            }
        }
        return result.toString();
    }

    public int getAlphabetLength() {
        return ALPHABET.length();
    }

}
