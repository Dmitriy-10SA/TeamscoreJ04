package transliteration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransliteratorTest {
    @ParameterizedTest
    @CsvSource({
            "А, A", "а, a",
            "Б, B", "б, b",
            "В, V", "в, v",
            "Г, G", "г, g",
            "Д, D", "д, d",
            "Е, E", "е, e",
            "З, Z", "з, z",
            "И, I", "и, i",
            "К, K", "к, k",
            "Л, L", "л, l",
            "М, M", "м, m",
            "Н, N", "н, n",
            "О, O", "о, o",
            "П, P", "п, p",
            "Р, R", "р, r",
            "С, S", "с, s",
            "Т, T", "т, t",
            "У, U", "у, u",
            "Ф, F", "ф, f",
            "Ц, C", "ц, c",
            "Э, Eh", "э, eh",
            "Й, J", "й, j"
    })
    void testSingleLetterCyrillicToLatin(String cyrillic, String expectedLatin) {
        assertEquals(expectedLatin, Transliterator.cyrillicToLatin(cyrillic));
    }

    @ParameterizedTest
    @CsvSource({
            "Щ, Shh", "щ, shh",
            "Ш, Sh", "ш, sh",
            "Ч, Ch", "ч, ch",
            "Х, Kh", "х, kh",
            "Ж, Zh", "ж, zh",
            "Ю, Yu", "ю, yu",
            "Я, Ya", "я, ya",
            "Ё, Yo", "ё, yo",
            "Ы, Y", "ы, y"
    })
    void testMultiCharCyrillicToLatin(String cyrillic, String expectedLatin) {
        assertEquals(expectedLatin, Transliterator.cyrillicToLatin(cyrillic));
    }

    @ParameterizedTest
    @CsvSource({
            "A, А", "a, а",
            "B, Б", "b, б",
            "V, В", "v, в",
            "G, Г", "g, г",
            "D, Д", "d, д",
            "E, Е", "e, е",
            "Z, З", "z, з",
            "I, И", "i, и",
            "J, Й", "j, й",
            "K, К", "k, к",
            "L, Л", "l, л",
            "M, М", "m, м",
            "N, Н", "n, н",
            "O, О", "o, о",
            "P, П", "p, п",
            "R, Р", "r, р",
            "S, С", "s, с",
            "T, Т", "t, т",
            "U, У", "u, у",
            "F, Ф", "f, ф",
            "C, Ц", "c, ц"
    })
    void testSingleLetterLatinToCyrillic(String latin, String expectedCyrillic) {
        assertEquals(expectedCyrillic, Transliterator.latinToCyrillic(latin));
    }

    @ParameterizedTest
    @CsvSource({
            "Shh, Щ", "shh, щ",
            "Sh, Ш", "sh, ш",
            "Ch, Ч", "ch, ч",
            "Kh, Х", "kh, х",
            "Zh, Ж", "zh, ж",
            "Yu, Ю", "yu, ю",
            "Ya, Я", "ya, я",
            "Yo, Ё", "yo, ё",
            "Y, Ы", "y, ы"
    })
    void testMultiCharLatinToCyrillic(String latin, String expectedCyrillic) {
        assertEquals(expectedCyrillic, Transliterator.latinToCyrillic(latin));
    }

    @ParameterizedTest
    @CsvSource({
            "Привет, Privet",
            "Москва, Moskva",
            "Россия, Rossiya",
            "Юрий, Yurij",
            "Яша, Yasha",
            "Щука, Shhuka",
            "Чай, Chaj",
            "Жизнь, Zhizn'",
            "Хорошо, Khorosho"
    })
    void testWordsCyrillicToLatin(String cyrillic, String expectedLatin) {
        assertEquals(expectedLatin, Transliterator.cyrillicToLatin(cyrillic));
    }

    @ParameterizedTest
    @CsvSource({
            "Privet, Привет",
            "Moskva, Москва",
            "Rossiya, Россия",
            "Yurij, Юрий",
            "Yasha, Яша",
            "Shhuka, Щука",
            "Chaj, Чай",
            "Zhizn', Жизнь",
            "Khorosho, Хорошо"
    })
    void testWordsLatinToCyrillic(String latin, String expectedCyrillic) {
        assertEquals(expectedCyrillic, Transliterator.latinToCyrillic(latin));
    }

    @Test
    void testCasePreservationCyrillicToLatin() {
        assertEquals("Yu", Transliterator.cyrillicToLatin("Ю"));
        assertEquals("yu", Transliterator.cyrillicToLatin("ю"));
        assertEquals("Sh", Transliterator.cyrillicToLatin("Ш"));
        assertEquals("sh", Transliterator.cyrillicToLatin("ш"));
        assertEquals("Ya", Transliterator.cyrillicToLatin("Я"));
        assertEquals("ya", Transliterator.cyrillicToLatin("я"));
    }

    @Test
    void testMixedTextCyrillicToLatin() {
        assertEquals("Privet 123!", Transliterator.cyrillicToLatin("Привет 123!"));
        assertEquals("Moskva - stolica", Transliterator.cyrillicToLatin("Москва - столица"));
    }

    @Test
    void testMixedTextLatinToCyrillic() {
        assertEquals("Привет 123!", Transliterator.latinToCyrillic("Privet 123!"));
        assertEquals("Москва - столица", Transliterator.latinToCyrillic("Moskva - stolica"));
    }

    @Test
    void testNonTransliteratedSymbols() {
        assertEquals("123", Transliterator.cyrillicToLatin("123"));
        assertEquals("!@#$%", Transliterator.cyrillicToLatin("!@#$%"));
        assertEquals("123", Transliterator.latinToCyrillic("123"));
        assertEquals("!@#$%", Transliterator.latinToCyrillic("!@#$%"));
    }

    @Test
    void testNullAndEmpty() {
        assertNull(Transliterator.cyrillicToLatin(null));
        assertNull(Transliterator.latinToCyrillic(null));
        assertEquals("", Transliterator.cyrillicToLatin(""));
        assertEquals("", Transliterator.latinToCyrillic(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Привет", "Москва", "Юрий", "Яша", "Щука", "Чай", "Жизнь",
            "Россия", "Хорошо", "Ёлка", "Эхо", "Ы"
    })
    void testRoundTripCyrillic(String cyrillic) {
        String latin = Transliterator.cyrillicToLatin(cyrillic);
        String cyrillicBack = Transliterator.latinToCyrillic(latin);
        assertEquals(cyrillic, cyrillicBack);
    }

    @Test
    void testSpecialCharacters() {
        assertEquals("'", Transliterator.cyrillicToLatin("ь"));
        assertEquals("\"", Transliterator.cyrillicToLatin("ъ"));
        assertEquals("ь", Transliterator.latinToCyrillic("'"));
    }

    @Test
    void testSentencesCyrillicToLatin() {
        assertEquals("Privet, kak dela?", Transliterator.cyrillicToLatin("Привет, как дела?"));
        assertEquals(
                "Ya izuchayu russkij yazyk.",
                Transliterator.cyrillicToLatin("Я изучаю русский язык.")
        );
    }

    @Test
    void testSentencesLatinToCyrillic() {
        assertEquals("Привет, как дела?", Transliterator.latinToCyrillic("Privet, kak dela?"));
        assertEquals(
                "Я изучаю русский язык.",
                Transliterator.latinToCyrillic("Ya izuchayu russkij yazyk.")
        );
    }

    @Test
    void testLongText() {
        String longCyrillic = "Это длинный текст на русском языке для проверки транслитерации.";
        String latin = Transliterator.cyrillicToLatin(longCyrillic);
        String back = Transliterator.latinToCyrillic(latin);
        assertEquals(longCyrillic, back);
    }

    @Test
    void testWithSpaces() {
        assertEquals("  Privet  ", Transliterator.cyrillicToLatin("  Привет  "));
        assertEquals("  Привет  ", Transliterator.latinToCyrillic("  Privet  "));
    }

    @Test
    void testNumbersWithLetters() {
        assertEquals("Moskva2024", Transliterator.cyrillicToLatin("Москва2024"));
        assertEquals("Москва2024", Transliterator.latinToCyrillic("Moskva2024"));
    }

    @Test
    void testRepeatedCharacters() {
        assertEquals("aaa", Transliterator.cyrillicToLatin("ааа"));
        assertEquals("ShShSh", Transliterator.cyrillicToLatin("ШШШ"));
        assertEquals("ааа", Transliterator.latinToCyrillic("aaa"));
        assertEquals("ШШШ", Transliterator.latinToCyrillic("ShShSh"));
        assertEquals("shshsh", Transliterator.cyrillicToLatin("шшш"));
        assertEquals("шшш", Transliterator.latinToCyrillic("shshsh"));
    }

    @ParameterizedTest
    @CsvSource({
            "транслитерация, transliteraciya",
            "программирование, programmirovanie",
            "компьютер, komp'yuter"
    })
    void testComplexWords(String cyrillic, String expectedLatin) {
        assertEquals(expectedLatin, Transliterator.cyrillicToLatin(cyrillic));
    }
}

