import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void toPostfix() {
        assertEquals("pqv", parser.toPostfix("pvq"));
        assertEquals("pq^", parser.toPostfix("p^q"));
        assertEquals("pq~v", parser.toPostfix("pv~q"));
        assertEquals("p~qv", parser.toPostfix("~pvq"));
        assertEquals("p~q~^", parser.toPostfix("~p^~q"));
        assertEquals("pqv", parser.toPostfix("(p)v(q)"));
        assertEquals("pqv~", parser.toPostfix("~(pvq)"));
        assertEquals("p~q~^", parser.toPostfix("(~p)^(~q)"));
        assertEquals("pqvrv", parser.toPostfix("pvqvr"));
        assertEquals("pq~vrv", parser.toPostfix("pv~qvr"));
        assertEquals("pqvqr^^", parser.toPostfix("(pvq)^(q^r)"));
        assertEquals("pqvqr^~^", parser.toPostfix("(pvq)^~(q^r)"));
    }

    @Test
    void precedence() {
        assertEquals(2, parser.precedence('~'));
        assertEquals(1, parser.precedence('^'));
        assertEquals(1, parser.precedence('v'));
    }
}