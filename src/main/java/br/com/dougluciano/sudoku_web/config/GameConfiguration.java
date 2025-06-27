package br.com.dougluciano.sudoku_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GameConfiguration {

    private final String SUDOKU_CONFIG_STRING = SudokuConfigurationString.getSudokuConfigString();

    @Bean
    public Map<String, String> sudokuGameConfig() {
        Map<String, String> configMap = new HashMap<>();
        String[] entries = SUDOKU_CONFIG_STRING.split(" ");
        for (String entry : entries) {
            String[] parts = entry.split(";");
            if (parts.length == 2) {
                configMap.put(parts[0], parts[1]);
            }
        }
        return configMap;
    }
}
