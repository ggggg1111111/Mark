import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {

    public static void main(String[] args) {

        CaesarCip cipher = new CaesarCip();
        WorkWithFiles workWithFiles = new WorkWithFiles();
        Scanner scanner = new Scanner(System.in);
        BruteForce bruteForce = new BruteForce();
        StatisticalAnalyzer statisticalAnalyzer = new StatisticalAnalyzer();

        System.out.println("Выберите режим:");
        System.out.println("1 - Зашифровать файл");
        System.out.println("2 - Расшифровать файл");
        System.out.println("3 - Brute Force");
        System.out.println("4 - Статистический анализ");

        int command = scanner.nextInt();

        try {

            if (command == 1) {

                if (!Files.exists(Path.of("Java/A-input.txt"))) {
                    System.out.println("Ошибка в path: Java/A-input.txt");
                    return;
                }

                System.out.println("Введите ключ:");
                int key = scanner.nextInt();

                if (key < 0) {
                    System.out.println("Ключ не может быть отрицательным");
                    return;
                }

                String inputText = workWithFiles.readFile("Java/A-input.txt");
                String encryptedText = cipher.encrypt(inputText, key);
                workWithFiles.writeFile("Java/A-encrypted.txt", encryptedText);

                System.out.println("Готово - зашифровали");

            } else if (command == 2) {

                if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                    System.out.println("Ошибка в path: Java/A-input.txt");
                    return;
                }

                System.out.println("Введите ключ:");
                int key = scanner.nextInt();

                if (key < 0) {
                    System.out.println("Ключ не может быть отрицательным");
                    return;
                }

                String encryptedText = workWithFiles.readFile("Java/A-encrypted.txt");
                String decryptedText = cipher.decrypt(encryptedText, key);
                workWithFiles.writeFile("Java/A-decrypted.txt", decryptedText);

                System.out.println("Готово - расшифровали");

            } else if (command == 3) {

                if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                    System.out.println("Ошибка в path: Java/A-input.txt");
                    return;
                }

                String encryptedText = workWithFiles.readFile("Java/A-encrypted.txt");
                String result = bruteForce.decryptAllKeys(encryptedText, cipher);
                workWithFiles.writeFile("Java/A-bruteforce.txt", result);

                System.out.println("Готово - забрутфорсили");

            } else if (command == 4) {

                if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                    System.out.println("Ошибка в path: Java/A-input.txt");
                    return;
                }

                String encryptedText = workWithFiles.readFile("Java/A-encrypted.txt");
                int key = statisticalAnalyzer.findKey(encryptedText);

                String decryptedText = cipher.decrypt(encryptedText, key);
                workWithFiles.writeFile("Java/A-statistical.txt", decryptedText);

                System.out.println("Готово - ключ: " + key);

            } else {
                System.out.println("Такой команды нет");
            }

        } catch (Exception e) {
            System.out.println("Ошибка в main через try: " + e.getMessage());
        }
    }
}
