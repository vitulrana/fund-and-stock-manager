package org.fsm.services;

import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioServiceTest {
    @Test
    public void shouldCalculateOverlap() {
        Fund fund1 = new Fund(
            "ICICI_PRU_NIFTY_NEXT_50_INDEX",
            List.of(
                "INDRAPRASTHA GAS LIMITED",
                "COLGATE - PALMOLIVE (INDIA) LIMITED",
                "INDUS TOWERS LIMITED",
                "INTERGLOBE AVIATION LIMITED"
            )
        );
        Fund fund2 = new Fund(
            "PARAG_PARIKH_CONSERVATIVE_HYBRID",
            List.of(
                "INDRAPRASTHA GAS LIMITED",
                "SONA BLW PRECISION FORGINGS LIMITED",
                "ADITYA BIRLA FASHION AND RETAIL LIMITED",
                "GRINDWELL NORTON LIMITED"
            )
        );
        Portfolio portfolio = new Portfolio(List.of(fund1, fund2));
        Fund newFund = new Fund(
            "AXIS_MIDCAP",
            List.of(
                "ADITYA BIRLA FASHION AND RETAIL LIMITED",
                "GRINDWELL NORTON LIMITED",
                "3M INDIA LIMITED",
                "COROMANDEL INTERNATIONAL LIMITED"
            )
        );

        PortfolioService portfolioService = new PortfolioService();

        assertEquals(
            List.of(
                "AXIS_MIDCAP ICICI_PRU_NIFTY_NEXT_50_INDEX 0%",
                "AXIS_MIDCAP PARAG_PARIKH_CONSERVATIVE_HYBRID 50%"
            ),
            portfolioService.calculateOverlap(portfolio, newFund)
        );
    }
}
