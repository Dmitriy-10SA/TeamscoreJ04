package symbol.unicode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnicodeCharInfoTest {
    private static UnicodeCharInfo testUnicodeCharInfo(char ch) {
        return new UnicodeCharInfo(ch);
    }

    @Test
    void testZero() {
        UnicodeCharInfo info = testUnicodeCharInfo('0');
        assertEquals(48, info.getUnicodeCode());
        assertEquals("U+0030", info.getUnicodeHex());
        assertEquals('1', info.getNextChar());
        assertEquals('/', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_DIGIT, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testN() {
        UnicodeCharInfo info = testUnicodeCharInfo('N');
        assertEquals(78, info.getUnicodeCode());
        assertEquals("U+004E", info.getUnicodeHex());
        assertEquals('O', info.getNextChar());
        assertEquals('M', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_LETTER_UPPER, info.getCharType());
        assertEquals(14, info.getLatinAlphabetPosition());
    }

    @Test
    void testZ() {
        UnicodeCharInfo info = testUnicodeCharInfo('z');
        assertEquals(122, info.getUnicodeCode());
        assertEquals("U+007A", info.getUnicodeHex());
        assertEquals('{', info.getNextChar());
        assertEquals('y', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_LETTER_LOWER, info.getCharType());
        assertEquals(26, info.getLatinAlphabetPosition());
    }

    @Test
    void testZhe() {
        UnicodeCharInfo info = testUnicodeCharInfo('Ж');
        assertEquals(1046, info.getUnicodeCode());
        assertEquals("U+0416", info.getUnicodeHex());
        assertEquals('З', info.getNextChar());
        assertEquals('Е', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_LETTER_UPPER, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testSpace() {
        UnicodeCharInfo info = testUnicodeCharInfo(' ');
        assertEquals(32, info.getUnicodeCode());
        assertEquals("U+0020", info.getUnicodeHex());
        assertEquals('!', info.getNextChar());
        assertEquals((char) 31, info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_SPACE, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testTab() {
        UnicodeCharInfo info = testUnicodeCharInfo('\t');
        assertEquals(9, info.getUnicodeCode());
        assertEquals("U+0009", info.getUnicodeHex());
        assertEquals((char) 10, info.getNextChar());
        assertEquals((char) 8, info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_SPACE, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testAmpersand() {
        UnicodeCharInfo info = testUnicodeCharInfo('&');
        assertEquals(38, info.getUnicodeCode());
        assertEquals("U+0026", info.getUnicodeHex());
        assertEquals('\'', info.getNextChar());
        assertEquals('%', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_OTHER, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testLambda() {
        UnicodeCharInfo info = testUnicodeCharInfo('λ');
        assertEquals(955, info.getUnicodeCode());
        assertEquals("U+03BB", info.getUnicodeHex());
        assertEquals('μ', info.getNextChar());
        assertEquals('κ', info.getPreviousChar());
        assertEquals(UnicodeCharInfo.TYPE_OTHER, info.getCharType());
        assertEquals(-1, info.getLatinAlphabetPosition());
    }

    @Test
    void testCanChangeSymbol() {
        UnicodeCharInfo info = testUnicodeCharInfo('Ж');
        assertEquals('Ж', info.getSymbol());
        info.setSymbol('A');
        assertEquals('A', info.getSymbol());
    }
}