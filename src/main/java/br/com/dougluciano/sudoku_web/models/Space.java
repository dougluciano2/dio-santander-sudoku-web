package br.com.dougluciano.sudoku_web.models;

public class Space {

    private Integer currentPosition;
    private final Integer expectedPosition;
    private final boolean fixedPosition;

    public Space(Integer expectedPosition, boolean fixedPosition){
        this.expectedPosition = expectedPosition;
        this.fixedPosition = fixedPosition;
        if (fixedPosition){
            currentPosition = expectedPosition;
        }
    }

    // Getters
    public Integer getCurrentPosition(){
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition){
        if (fixedPosition) return; // Não permite mudar se for fixa
        this.currentPosition = currentPosition;
    }

    public void clearSpace(){
        setCurrentPosition(null); // Usa o setter, que respeita 'fixedPosition'
    }

    public int getExpectedPosition(){ // Retorna int, ok
        return expectedPosition;
    }

    public boolean isFixedPosition(){ // Retorna boolean, ok
        return fixedPosition;
    }
}