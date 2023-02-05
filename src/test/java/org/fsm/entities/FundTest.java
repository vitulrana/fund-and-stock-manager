package org.fsm.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundTest {
    @Test
    public void shouldReturnFundNameWithGetter() {
        Fund fund = new Fund("ICICI_PRU_NIFTY_NEXT_50_INDEX", List.of("INDUS TOWERS LIMITED"));

        assertEquals("ICICI_PRU_NIFTY_NEXT_50_INDEX", fund.getName());
    }

    @Test
    public void shouldReturnAssociatedStocksWithFund() {
        String stock = "INDUS TOWERS LIMITED";
        Fund fund = new Fund("ICICI_PRU_NIFTY_NEXT_50_INDEX", List.of(stock));

        assertEquals(List.of(stock), fund.getStocks());
    }
}
