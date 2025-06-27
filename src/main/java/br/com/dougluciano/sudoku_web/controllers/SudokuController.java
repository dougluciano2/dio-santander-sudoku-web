package br.com.dougluciano.sudoku_web.controllers;

import br.com.dougluciano.sudoku_web.config.SudokuConfigurationString;
import br.com.dougluciano.sudoku_web.models.Board;
import br.com.dougluciano.sudoku_web.services.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {

    private final BoardService boardService;


    public SudokuController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/play")
    public String showSudokuBoard(Model model) {
        model.addAttribute("boardSpaces", boardService.getSpaces());
        model.addAttribute("gameStatus", boardService.getStatus());
        model.addAttribute("hasErrors", boardService.hasErrors());

        return "sudoku";
    }

}
