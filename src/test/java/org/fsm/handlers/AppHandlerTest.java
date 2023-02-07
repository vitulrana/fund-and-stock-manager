package org.fsm.handlers;

import org.fsm.config.AppConfig;
import org.fsm.entities.Fund;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppHandlerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldHandleProvidedInputStream() throws IOException {
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
        Fund fund3 = new Fund(
            "AXIS_MIDCAP",
            List.of(
                "ADITYA BIRLA FASHION AND RETAIL LIMITED",
                "GRINDWELL NORTON LIMITED",
                "3M INDIA LIMITED",
                "COROMANDEL INTERNATIONAL LIMITED"
            )
        );
        var appConfig = new AppConfig();
        appConfig.setFunds(List.of(fund1, fund2, fund3));
        var inputString = String.join(
            "\n",
            "CURRENT_PORTFOLIO ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_CONSERVATIVE_HYBRID",
            "CALCULATE_OVERLAP AXIS_MIDCAP",
            "ADD_STOCK AXIS_BLUECHIP TCS"
        );
        var inputStream = new ByteArrayInputStream(inputString.getBytes());
        AppHandler appHandler = new AppHandler(appConfig, inputStream);

        appHandler.handle();

        assertEquals("AXIS_MIDCAP ICICI_PRU_NIFTY_NEXT_50_INDEX 0.00%\n" +
            "AXIS_MIDCAP PARAG_PARIKH_CONSERVATIVE_HYBRID 50.00%\n" +
            "FUND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }
}
