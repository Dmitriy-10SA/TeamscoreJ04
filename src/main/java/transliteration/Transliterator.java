package transliteration;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для транслитерации русского текста в латиницу и обратно по ГОСТ 7.79-2000
 */
public class Transliterator {
    private static final char[] CYRILLIC_LETTERS = {
            'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Д', 'д', 'Е', 'е', 'З', 'з', 'И', 'и', 'Й', 'й', 'К',
            'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о', 'П', 'п', 'Р', 'р', 'С', 'с', 'Т', 'т', 'У', 'у',
            'Ф', 'ф', 'Ё', 'ё', 'Ж', 'ж', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ', 'Ю', 'ю', 'Я',
            'я', 'Э', 'э', 'Ы', 'ы', 'Ь', 'ь', 'Ъ', 'ъ'
    };

    private static final String[] LATIN_LETTERS = {
            "A", "a", "B", "b", "V", "v", "G", "g", "D", "d", "E", "e", "Z", "z", "I", "i", "J", "j", "K",
            "k", "L", "l", "M", "m", "N", "n", "O", "o", "P", "p", "R", "r", "S", "s", "T", "t", "U", "u",
            "F", "f", "Yo", "yo", "Zh", "zh", "Kh", "kh", "C", "c", "Ch", "ch", "Sh", "sh", "Shh", "shh",
            "Yu", "yu", "Ya", "ya", "Eh", "eh", "Y", "y", "'", "'", "\"", "\""
    };

    private static final Map<Character, String> CYRILLIC_LATIN = new HashMap<>();
    private static final Map<String, Character> LATIN_CYRILLIC = new HashMap<>();

    static {
        for (int i = 0; i < CYRILLIC_LETTERS.length; i++) {
            CYRILLIC_LATIN.put(CYRILLIC_LETTERS[i], LATIN_LETTERS[i]);
            LATIN_CYRILLIC.put(LATIN_LETTERS[i], CYRILLIC_LETTERS[i]);
        }
    }

    /**
     * Транслитерация из кириллицы в латиницу по ГОСТ 7.79-2000
     *
     * @param cyrillicText текст на кириллице
     * @return текст на латинице
     */
    public static String cyrillicToLatin(String cyrillicText) {
        if (cyrillicText == null) {
            return null;
        }
        StringBuilder latinText = new StringBuilder();
        for (char ch : cyrillicText.toCharArray()) {
            String latin = CYRILLIC_LATIN.getOrDefault(ch, null);
            latinText.append(latin != null ? latin : ch);
        }
        return latinText.toString();
    }

    /**
     * Транслитерация из латиницы в кириллицу по ГОСТ 7.79-2000
     *
     * @param latinText текст на латинице
     * @return текст на кириллице
     */
    public static String latinToCyrillic(String latinText) {
        if (latinText == null) {
            return null;
        }
        StringBuilder cyrillicText = new StringBuilder();
        for (int i = 0; i < latinText.length(); ) {
            boolean found = false;
            for (int len = 3; len >= 1; len--) {
                if (i + len > latinText.length()) {
                    continue;
                }
                String substr = latinText.substring(i, i + len);
                Character cyrillic = LATIN_CYRILLIC.getOrDefault(substr, null);
                if (cyrillic != null) {
                    cyrillicText.append(cyrillic);
                    i += len;
                    found = true;
                    break;
                }
            }
            if (!found) {
                cyrillicText.append(latinText.charAt(i));
                i++;
            }
        }
        return cyrillicText.toString();
    }
}