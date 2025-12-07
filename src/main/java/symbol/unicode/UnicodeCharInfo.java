package symbol.unicode;

public class UnicodeCharInfo {
    public static final String TYPE_DIGIT = "DIGIT";
    public static final String TYPE_LETTER_UPPER = "LETTER_UPPER";
    public static final String TYPE_LETTER_LOWER = "LETTER_LOWER";
    public static final String TYPE_SPACE = "SPACE";
    public static final String TYPE_OTHER = "OTHER";

    private char symbol;

    public UnicodeCharInfo(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Возврат кода Unicode в виде целого числа
     */
    public int getUnicodeCode() {
        return symbol;
    }

    /**
     * Возврат кода Unicode в виде 16-ричного представления
     */
    public String getUnicodeHex() {
        return String.format("U+%04X", getUnicodeCode());
    }

    /**
     * Возврат следующего символа в Unicode
     */
    public char getNextChar() {
        return (char) ((int) symbol + 1);
    }

    /**
     * Возврат предыдущего символа в Unicode
     */
    public char getPreviousChar() {
        return (char) ((int) symbol - 1);
    }

    /**
     * Возврат типа символа
     */
    public String getCharType() {
        if (Character.isDigit(symbol)) {
            return TYPE_DIGIT;
        }
        boolean isLatinOrCyrillic = isLatin(symbol) || isCyrillic(symbol);
        if (Character.isUpperCase(symbol) && isLatinOrCyrillic) {
            return TYPE_LETTER_UPPER;
        } else if (Character.isLowerCase(symbol) && isLatinOrCyrillic) {
            return TYPE_LETTER_LOWER;
        } else if (Character.isWhitespace(symbol)) {
            return TYPE_SPACE;
        } else {
            return TYPE_OTHER;
        }
    }

    /**
     * Если символ является буквой латинского алфавита (в любом регистре),
     * то возврат ее номера в алфавите ('a' => 1, 'Z' => 26), иначе возвращает -1
     */
    public int getLatinAlphabetPosition() {
        if (isLatin(symbol)) {
            return Character.toLowerCase(symbol) - 'a' + 1;
        }
        return -1;
    }

    /**
     * Проверка, является ли символ буквой латинского алфавита
     */
    private boolean isLatin(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * Проверка, является ли символ буквой кириллицы
     */
    private boolean isCyrillic(char ch) {
        return (ch >= 'А' && ch <= 'Я') || (ch >= 'а' && ch <= 'я') || ch == 'Ё' || ch == 'ё';
    }
}
