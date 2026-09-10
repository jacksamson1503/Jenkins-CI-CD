import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void calculateTotalAddsPriceAndTax() {
        assertEquals(120, Main.calculateTotal(100, 20));
    }
}
