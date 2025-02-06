import java.io.File
import java.io.PrintWriter
import java.util.Scanner

fun caesarEncrypt(text: String, shift: Int): String {
    val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    val result = StringBuilder()

    for (char in text) {
        if (char.isLetter()) {
            val index = alphabet.indexOf(char.lowercaseChar())
            val newIndex = (index + shift) % alphabet.length
            result.append(if (char.isUpperCase()) alphabet[newIndex].uppercaseChar() else alphabet[newIndex])
        } else {
            result.append(char)
        }
    }

    return result.toString()
}
fun main() {
    val scanner = Scanner(System.`in`)
    println("Выберите способ:")
    println("1. Шифровка текста")
    println("2. Расшифровка текста с ключом")
    println("3. Расшифровка текста с помощью brute force")
    println("4. Расшифровка текста с помощью статистического анализа")

    val choice = scanner.nextInt()
    scanner.nextLine()

    when (choice) {
        1 -> encrypt(scanner)
        2 -> decrypt(scanner)
        3 -> bruteForceDecrypt(scanner)
        4 -> statisticalDecrypt(scanner)
        else -> println("Неправильный выбор. Пожалуйста, попробуйте снова.")
    }
}

fun encrypt(scanner: Scanner) {
    println("Введите путь к файлу с текстом для шифрования:")
    val inputFilePath = scanner.nextLine()

    println("Введите ключ для шифрования:")
    val shift = scanner.nextInt()
    scanner.nextLine()

    println("Введите путь к файлу для записи зашифрованного текста:")
    val outputFilePath = scanner.nextLine()

    val input = File(inputFilePath).readText()
    val encrypted = caesarEncrypt(input, shift)

    PrintWriter(File(outputFilePath)).use { writer ->
        writer.println(encrypted)
    }

    println("Текст успешно зашифрован и записан в файл.")
}

fun decrypt(scanner: Scanner) {
    println("Введите путь к файлу с зашифрованным текстом:")
    val inputFilePath = scanner.nextLine()

    println("Введите ключ для расшифровки:")
    val shift = scanner.nextInt()
    scanner.nextLine()

    println("Введите путь к файлу для записи расшифрованного текста:")
    val outputFilePath = scanner.nextLine()

    val input = File(inputFilePath).readText()
    val decrypted = caesarDecrypt(input, shift)

    PrintWriter(File(outputFilePath)).use { writer ->
        writer.println(decrypted)
    }

    println("Текст успешно расшифрован и записан в файл.")
}

fun bruteForceDecrypt(scanner: Scanner) {
    println("Введите путь к файлу с зашифрованным текстом:")
    val inputFilePath = scanner.nextLine()

    println("Введите путь к файлу для записи расшифрованного текста:")
    val outputFilePath = scanner.nextLine()

    val input = File(inputFilePath).readText()

    for (shift in 0 until 32) {
        val decrypted = caesarDecrypt(input, shift)
        println("Попытка расшифровки с ключом $shift: $decrypted")
    }

    println("Все возможные расшифровки выведены.")
}

fun statisticalDecrypt(scanner: Scanner) {
    println("Введите путь к файлу с зашифрованным текстом:")
    val inputFilePath = scanner.nextLine()

    println("Введите путь к файлу для записи расшифрованного текста:")
    val outputFilePath = scanner.nextLine()

    val input = File(inputFilePath).readText()

    // Пример простого статистического анализа: поиск наиболее частого символа
    val frequency = input.groupingBy { it }.eachCount()
    val mostFrequentChar = frequency.entries.maxByOrNull { it.value }?.key

    if (mostFrequentChar != null) {
        val shift = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".indexOf(mostFrequentChar.lowercaseChar())
        val decrypted = caesarDecrypt(input, shift)
        PrintWriter(File(outputFilePath)).use { writer ->
            writer.println(decrypted)
        }
        println("Текст успешно расшифрован с помощью статистического анализа и записан в файл.")
    } else {
        println("Не удалось определить наиболее частый символ.")
    }
}

fun caesarDecrypt(text: String, shift: Int): String {
    return caesarEncrypt(text, 32 - shift)
}