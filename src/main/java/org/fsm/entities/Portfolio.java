package org.fsm.entities;

import java.util.List;

public class Portfolio {
    private List<Fund> funds;

    public Portfolio(List<Fund> funds) {
        this.funds = funds;
    }

    public List<Fund> getFunds() {
        return funds;
    }
}
