package Simplify;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

import java.util.Arrays;

public class SolutionTest {

    private static String[][] examples = {
            new String[] {"a + a = b", "b - d = c", "a + b = d"},
            new String[] {"a + 3g = k", "-70a = g"},
            new String[] {"-j -j -j + j = b"}};

    private static String[] formula = {"c + a + b",
            "-k + a",
            "-j - b"};

    private static String[] expected = {"2a",
            "210a",
            "1j"};

    @Test
    public void sampleTests() {
        for (int i = 0 ; i < 3 ; i++) {
            System.out.println(String.format("---------\nExamples:\t%s\nFormula:\t%s\nExpected:\t%s",
                    Arrays.toString(examples[i]),
                    formula[i],
                    expected[i]));
            assertEquals(expected[i], EqSystem.simplify(examples[i], formula[i]));
        }
    }
}