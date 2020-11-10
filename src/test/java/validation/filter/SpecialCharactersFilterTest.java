package validation.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.exception.SpecialCharactersException;

import static org.junit.jupiter.api.Assertions.*;

class SpecialCharactersFilterTest {

    SpecialCharactersFilter filter;

    @BeforeEach
    void setUp() {
        filter = new SpecialCharactersFilter();
    }

    @Test
    void filtrate() {
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate("#"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate("!"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate("-"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate(">"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate(">"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate("?"));
        assertThrows(SpecialCharactersException.class, () -> filter.filtrate("!"));
        assertDoesNotThrow(() -> filter.filtrate("p^q"));
        assertDoesNotThrow(() -> filter.filtrate("p^~q"));
    }
}