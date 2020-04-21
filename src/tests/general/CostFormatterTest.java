package tests.general;

import GameApp.java.general.CostFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CostFormatterTest {
    final double d1 = 5.789;
    final double d2 = 9.7;

    @Test
    void formatterRemovesExtraDigits_RoundsDoubleAndFormatsToTwoDecimalPlaces(){
        Assertions.assertEquals("5.79", CostFormatter.format(d1));
    }
    @Test
    void formatterFormatsAOneDecimalPlaceDoubleToATwoDecimalPlaceDouble(){
        Assertions.assertEquals("9.70", CostFormatter.format(d2));
    }
}
