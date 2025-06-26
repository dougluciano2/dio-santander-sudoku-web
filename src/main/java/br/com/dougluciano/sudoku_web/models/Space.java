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

    public Integer getCurrentPosition(){
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition){
        if (fixedPosition) return;
        this.currentPosition = currentPosition;
    }

    public void clearSpace(){
        setCurrentPosition(null);
    }

    public int getExpectedPosition(){
        return expectedPosition;
    }

    public boolean isFixedPosition(){
        return fixedPosition;
    }
}
