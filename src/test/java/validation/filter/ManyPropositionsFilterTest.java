package validation.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.exception.ManyPropositionsException;

import static org.junit.jupiter.api.Assertions.*;
class ManyPropositionsFilterTest {

    ManyPropositionsFilter filter;

    @BeforeEach
    void setUp() {
        filter = new ManyPropositionsFilter();
    }

    @Test
    void filtrate() {
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("p"));
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("~p"));
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("p^p"));
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("p-p"));
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("p^pp"));
        assertThrows(ManyPropositionsException.class, () -> filter.filtrate("pvp"));
        assertDoesNotThrow(() -> filter.filtrate("p^q"));
        assertDoesNotThrow(() -> filter.filtrate("pv~q"));
    }
}