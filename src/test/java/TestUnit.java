import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.StepDefinitions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnit {

    @Test
    void simpleTest() {
        assertEquals(2, 1 + 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    void parameterizedTest(String word) {
        assertTrue(isPalindrome(word));
    }

    boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
    @Test
    void testOnlyOnSpecificJavaVersion() {
       // System.out.println(System.getProperty("java.version"));
        Assumptions.assumeTrue("17.0.6".equals(System.getProperty("java.version")),
                () -> "not Java 17");

        assertEquals(2, 1 + 1);
    }
    @Test
    void testOnlyOnCiServer() {
        Assumptions.assumeTrue("6".equals(System.getenv("PROCESSOR_LEVEL")),
                () -> "Test not not not");

        assertThat(1 + 1).isEqualTo(2);
    }


}
