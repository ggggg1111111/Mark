public class BruteForce {

    public String decryptAllKeys(String text, CaesarCip cipher) {
        StringBuilder result = new StringBuilder();

        //TODO - можно вместо cipher.getAlphabetLength() - сделать ручками ввод числа, но в задании не написано что так надо.
        for (int key = 1; key < cipher.getAlphabetLength(); key++) {
            String decryptedText = cipher.decrypt(text, key);
            result.append("Ключ: ").append(key).append("\n");
            result.append(decryptedText).append("\n");
            result.append("--------------------").append("\n");

        }

        return result.toString();
    }
}
