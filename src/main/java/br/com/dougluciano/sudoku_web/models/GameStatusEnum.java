package br.com.dougluciano.sudoku_web.models;


import lombok.Getter;


public enum GameStatusEnum {

    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    private String label;

    private GameStatusEnum(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
