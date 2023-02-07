package org.fsm.services;

import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundServiceTest {
    @Test
    public void shouldAddStock() {
        Fund fund = new Fund(
            "ICICI_PRU_NIFTY_NEXT_50_INDEX",
            List.of(
                "INDRAPRASTHA GAS LIMITED",
                "COLGATE - PALMOLIVE (INDIA) LIMITED",
                "INDUS TOWERS LIMITED",
                "INTERGLOBE AVIATION LIMITED"
            )
        );
        FundService fundService = new FundService();

        fundService.addStock(fund, "BATA INDIA LIMITED");

        assertEquals(
            List.of(
                "INDRAPRASTHA GAS LIMITED",
                "COLGATE - PALMOLIVE (INDIA) LIMITED",
                "INDUS TOWERS LIMITED",
                "INTERGLOBE AVIATION LIMITED",
                "BATA INDIA LIMITED"
            ),
            fund.getStocks()
        );
    }

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

        FundService fundService = new FundService();

        assertEquals(
            "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_CONSERVATIVE_HYBRID 25.00%",
            fundService.calculateOverlap(fund1, fund2)
        );
    }
}
