package org.fsm.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioTest {
    @Test
    public void shouldReturnAssociatedFundsWithPortfolio() {
        String stock = "INDUS TOWERS LIMITED";
        Fund fund = new Fund("ICICI_PRU_NIFTY_NEXT_50_INDEX", List.of(stock));
        Portfolio portfolio = new Portfolio(List.of(fund));

        assertEquals(List.of(fund), portfolio.getFunds());
    }
}
