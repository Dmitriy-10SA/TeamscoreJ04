package checking.str;

import java.util.regex.Pattern;

public class RowChecker {
    public static final String TYPE_EMAIL = "email";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    public static final String TYPE_PHONE = "телефон";
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\+7[-\\s]?(\\(\\d{3}\\)|\\d{3})[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$"
    );

    public static final String TYPE_INN = "ИНН";
    private static final Pattern INN_PATTERN = Pattern.compile("^\\d{10}$|^\\d{12}$");

    public static final String TYPE_USERNAME = "username";
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_$.]{7,}$");

    public static final String TYPE_NONE = "none";

    /**
     * Проверка, чем является переданная строка с помощью регулярных выражений
     *
     * @param row строка для проверки
     * @return тип строки
     */
    public static String check(String row) {
        if (row == null) {
            return TYPE_NONE;
        }
        String trimmedRow = row.trim();
        if (EMAIL_PATTERN.matcher(trimmedRow).matches()) {
            return TYPE_EMAIL;
        } else if (PHONE_PATTERN.matcher(trimmedRow).matches()) {
            return TYPE_PHONE;
        } else if (INN_PATTERN.matcher(trimmedRow).matches()) {
            return TYPE_INN;
        } else if (USERNAME_PATTERN.matcher(trimmedRow).matches()) {
            return TYPE_USERNAME;
        } else {
            return TYPE_NONE;
        }
    }
}
