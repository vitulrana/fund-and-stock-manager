package org.fsm.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.fsm.entities.Fund;

import java.util.List;

@Data
@NoArgsConstructor
public class AppConfig {
    private List<Fund> funds;
}
