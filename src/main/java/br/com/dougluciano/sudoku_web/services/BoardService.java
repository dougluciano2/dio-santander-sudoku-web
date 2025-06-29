package br.com.dougluciano.sudoku_web.services;

import br.com.dougluciano.sudoku_web.models.Board;
import br.com.dougluciano.sudoku_web.models.GameStatusEnum;
import br.com.dougluciano.sudoku_web.models.Space;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final static int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces(){
        return board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatusEnum getStatus(){
        return board.getStatusGame();
    }

    public boolean gameIsFinished(){
        return board.isGameFinished();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        return spaces;
    }

    public boolean clearValue(int col, int row){
        return board.clearValue(col, row, 0);
    }

    public boolean changeValue(int col, int row, int value) {
        return board.changeValue(col, row, value);
    }

    public boolean isGameFinished() {
        return board.isGameFinished();
    }

}
