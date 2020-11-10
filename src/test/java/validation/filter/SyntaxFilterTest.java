package validation.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validation.exception.InvalidSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class SyntaxFilterTest {

    SyntaxFilter filter;

    @BeforeEach
    void setUp() {
        filter = new SyntaxFilter();
    }

    @Test
    void filtrate_test() {
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("^p"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("vp"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("^vp"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("v^p"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("p~"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("pv"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("p^"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("v^p"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("pp"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("qq"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("vp^qq"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate(")p^q("));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("p^()~q"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("()"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("p^qv((r)"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("(q)~(p)"));
        assertThrows(InvalidSyntaxException.class, () -> filter.filtrate("p~q"));

        assertDoesNotThrow(() -> filter.filtrate("p^q"));
        assertDoesNotThrow(() -> filter.filtrate("~p^q"));
        assertDoesNotThrow(() -> filter.filtrate("~pvq"));
        assertDoesNotThrow(() -> filter.filtrate("~(p^q)"));
        assertDoesNotThrow(() -> filter.filtrate("(pv~q)^(q^~p)"));
        assertDoesNotThrow(() -> filter.filtrate("~~p v q"));
    }
}