public class StatisticalAnalyzer {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public int findKey(String text) {

        int[] counts = new int[ALPHABET.length()];
        for (char symbol : text.toCharArray()) {
            int index = ALPHABET.indexOf(symbol);

            if (index != -1) {
                counts[index]++;
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }

        int letterIndex = ALPHABET.indexOf('а');
        int key = maxIndex - letterIndex;
        if (key < 0) {
            key = key + ALPHABET.length();
        }

        return key;
    }

}
