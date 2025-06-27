package br.com.dougluciano.sudoku_web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum GameStatusEnum {

    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    private String label;

    GameStatusEnum(String label){
        this.label = label;
    }
}
