package br.com.dougluciano.sudoku_web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
@Setter
@ToString
public class Board {

    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces){ this.spaces = spaces; }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatusGame(){
        if (spaces.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> s.isFixedPosition()
                && nonNull(s.getCurrentPosition()))){
            return GameStatusEnum.NON_STARTED;
        }
        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> isNull(s.getCurrentPosition())) ?
                GameStatusEnum.INCOMPLETE :GameStatusEnum.COMPLETE;
    }

    public boolean hasErrors(){
        if(getStatusGame() == GameStatusEnum.NON_STARTED){
            return false;
        }
        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getCurrentPosition()) &&
                        !s.getCurrentPosition().equals(s.getExpectedPosition()));
    }

    public boolean changeValue(int col, int row, int value){
        var space = spaces.get(col).get(row);
        if (space.isFixedPosition()){
            return false;
        }

        space.setCurrentPosition(value);
        return true;
    }

    public boolean clearValue(int col, int row, int value){
        var space = spaces.get(col).get(row);
        if (space.isFixedPosition()){
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean isGameFinished(){
        return !hasErrors() && getStatusGame().equals(GameStatusEnum.COMPLETE);
    }
}
