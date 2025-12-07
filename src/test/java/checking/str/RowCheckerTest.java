package checking.str;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RowCheckerTest {
    @Test
    void testEmail() {
        assertEquals(RowChecker.TYPE_EMAIL, RowChecker.check("user.name@example.com"));
        assertEquals(RowChecker.TYPE_EMAIL, RowChecker.check("user_name1@some.example.com"));
    }

    @Test
    void testNotEmail() {
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("@example.com"));
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("user.name@example"));
    }

    @Test
    void testPhone() {
        assertEquals(RowChecker.TYPE_PHONE, RowChecker.check("+7-(123)-456-78-90"));
        assertEquals(RowChecker.TYPE_PHONE, RowChecker.check("+7(123)456-78-90"));
        assertEquals(RowChecker.TYPE_PHONE, RowChecker.check("+7-123-456-78-90"));
        assertEquals(RowChecker.TYPE_PHONE, RowChecker.check("  +71234567890"));
    }

    @Test
    void testNotPhone() {
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("71234567890"));
    }

    @Test
    void testInn() {
        assertEquals(RowChecker.TYPE_INN, RowChecker.check("1234567890"));
        assertEquals(RowChecker.TYPE_INN, RowChecker.check("000000000000"));
    }

    @Test
    void testNotInn() {
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("7777-8888-9999"));
    }

    @Test
    void testUsername() {
        assertEquals(RowChecker.TYPE_USERNAME, RowChecker.check("user.name.example.com"));
        assertEquals(RowChecker.TYPE_USERNAME, RowChecker.check(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.$1234_")
        );
        assertEquals(RowChecker.TYPE_USERNAME, RowChecker.check("       aaaa1111"));
    }

    @Test
    void testNotUsername() {
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("a"));
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("qwerty 456"));
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("4abc"));
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("$asdfghjk"));
    }

    @Test
    void testWithSpaces() {
        assertEquals(RowChecker.TYPE_EMAIL, RowChecker.check("  user.name@example.com  "));
        assertEquals(RowChecker.TYPE_PHONE, RowChecker.check("  +71234567890  "));
        assertEquals(RowChecker.TYPE_INN, RowChecker.check("  1234567890  "));
        assertEquals(RowChecker.TYPE_USERNAME, RowChecker.check("  aaaa1111  "));
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check("       "));
    }

    @Test
    void testNull() {
        assertEquals(RowChecker.TYPE_NONE, RowChecker.check(null));
    }
}