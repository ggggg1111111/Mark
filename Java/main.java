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

        while (true) {

            try {

                System.out.println("Введите фразу для шифрования (она должна быть на русском, без чисел и не менее 20 символов):");
                String inputText = scanner.nextLine();

                if (inputText.trim().isEmpty()) {
                    System.out.println("Ошибка: фраза не должна быть пустой");
                    continue;
                }

                if (!inputText.matches("[А-Яа-яЁё\\s.,!?-]+")) {
                    System.out.println("Ошибка: допускаются только русские буквы, пробелы и знаки препинания.");
                    continue;
                }

                if (inputText.length() < 20) {
                    System.out.println("Предупреждение: текст короткий, статистический анализ может работать неточно");
                }

                workWithFiles.writeFile("Java/A-input.txt", inputText);
                System.out.println("Введите ключ:");

                if (!scanner.hasNextInt()) {
                    System.out.println("Ошибка: ключ должен быть числом");
                    scanner.nextLine();
                    continue;
                }

                int key = scanner.nextInt();
                scanner.nextLine();

                if (key < 0) {
                    System.out.println("Ошибка: ключ не может быть отрицательным");
                    continue;
                }

                String encryptedText = cipher.encrypt(inputText, key);
                workWithFiles.writeFile("Java/A-encrypted.txt", encryptedText);
                System.out.println("Готово - фраза записана и зашифрована");

                while (true) {
                    System.out.println();
                    System.out.println("1 - Расшифровать файл");
                    System.out.println("2 - Brute Force");
                    System.out.println("3 - Статистический анализ");
                    System.out.println("4 - Написать другую фразу");
                    System.out.println("0 - Выйти");

                    String command = scanner.nextLine();

                    if (command.equals("1")) {
                        if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                            System.out.println("Ошибка: файл Java/A-encrypted.txt не найден");
                            continue;
                        }

                        System.out.println("Введите ключ для расшифровки:");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Ошибка: ключ должен быть числом");
                            scanner.nextLine();
                            continue;
                        }

                        int decryptKey = scanner.nextInt();
                        scanner.nextLine();
                        String textFromFile = workWithFiles.readFile("Java/A-encrypted.txt");
                        String decryptedText = cipher.decrypt(textFromFile, decryptKey);
                        workWithFiles.writeFile("Java/A-decrypted.txt", decryptedText);
                        System.out.println("Готово - расшифровали");

                    } else if (command.equals("2")) {
                        if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                            System.out.println("Ошибка: файл Java/A-encrypted.txt не найден");
                            continue;
                        }

                        String textFromFile = workWithFiles.readFile("Java/A-encrypted.txt");
                        String result = bruteForce.decryptAllKeys(textFromFile, cipher);
                        workWithFiles.writeFile("Java/A-bruteforce.txt", result);
                        System.out.println("Готово - забрутфорсили");

                    } else if (command.equals("3")) {
                        if (!Files.exists(Path.of("Java/A-encrypted.txt"))) {
                            System.out.println("Ошибка: файл Java/A-encrypted.txt не найден");
                            continue;
                        }

                        String textFromFile = workWithFiles.readFile("Java/A-encrypted.txt");
                        int foundKey = statisticalAnalyzer.findKey(textFromFile);
                        String decryptedText = cipher.decrypt(textFromFile, foundKey);
                        workWithFiles.writeFile("Java/A-statistical.txt", decryptedText);
                        System.out.println("Готово - найден ключ: " + foundKey);

                    } else if (command.equals("4")) {
                        break;

                    } else if (command.equals("0")) {
                        System.out.println("Программа завершена");
                        return;

                    } else {
                        System.out.println("Такой команды нет. Выберите пункт от 0 до 4");
                    }
                }

            } catch (Exception e) {
                System.out.println("Ошибка в main через try: " + e.getMessage());
            }
        }
    }
}