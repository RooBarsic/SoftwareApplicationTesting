import com.company.ArcTgFunction;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Testing for implemented realisation of arcTg
 *
 * Author: Farrukh Karimov
 * Last modification: 05/10/2020
 */
@RunWith(DataProviderRunner.class)
public class ArcTgFunctionTest {
    private final double EPS = 0.001;
    private ArcTgFunction arcTgFunction;

    @Before
    public void init() {
        arcTgFunction = new ArcTgFunction();
    }

    @DataProvider
    public static Object[][] pointsDataProvider() {
        return new Object[][] {
                {0.0, "Пересечение с осью ординат"},

                {0.17, "Положительный интервал [0; 1]"},
                {0.18, "Положительный интервал [0; 1]"},
                {0.67, "Положительный интервал [0; 1]"},

                {-0.17, "Отрицательный интервал [-1; 0]"},
                {-0.18, "Отрицательный интервал [-1; 0]"},
                {-0.67, "Отрицательный интервал [-1; 0]"},

                {17.0, "Положительный интервал [1; +inf]"},
                {18.0, "Положительный интервал [1; +inf]"},
                {167.0, "Положительный интервал [1; +inf]"},

                {-17.0, "Отрицательный интервал [-inf; -1]"},
                {-18.0, "Отрицательный интервал [-inf; -1]"},
                {-167.0, "Отрицательный интервал [-inf; -1]"},

                {Double.MAX_VALUE, "Положительный интервал, max double [1; +inf]"},
                {Double.MIN_VALUE, "Отрицательный интервал, min double [1; +inf]"}
        };
    }

    @Test
    @UseDataProvider("pointsDataProvider")
    public void calculationAcrTgIsCorrect(final double point, @NotNull final String message) {
        final double arcTgByImplementedFunction = arcTgFunction.calc(point);
        final double arcTgByLibraryFunction = Math.atan(point);
        final double diffAbs = Math.abs(arcTgByImplementedFunction - arcTgByLibraryFunction);
        assertTrue(message, diffAbs < EPS);
    }

    /**
     * Formula : arctg(x) = - arctg(-x)
     */
    @Test
    @UseDataProvider("pointsDataProvider")
    public void testCalculationCorrectnessByFormula(final double point, @NotNull final String message) {
        final double arcTgOfPoint = arcTgFunction.calc(point);
        final double arcTgOfNegatedPoint = arcTgFunction.calc(-point);
        final double diffAbsByFormula = Math.abs(arcTgOfPoint - (-arcTgOfNegatedPoint));
        assertTrue(message, diffAbsByFormula < EPS);
    }
}
