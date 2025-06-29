package br.com.dougluciano.sudoku_web.controllers;

import br.com.dougluciano.sudoku_web.config.SudokuConfigurationString;
import br.com.dougluciano.sudoku_web.models.Board;
import br.com.dougluciano.sudoku_web.models.GameStatusEnum;
import br.com.dougluciano.sudoku_web.services.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        if (model.containsAttribute("message")) {
            model.addAttribute("message", model.getAttribute("message"));
            model.addAttribute("messageType", model.getAttribute("messageType"));
        }

        return "sudoku";
    }

    @PostMapping("/move")
    public String handleMove(@RequestParam("row") int row,
                             @RequestParam("col") int col,
                             @RequestParam("value") int value,
                             RedirectAttributes redirectAttributes) {
        boolean success;
        if (value == 0) { // Convenção: valor 0 para limpar a célula
            success = boardService.clearValue(col, row);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "Célula (" + (row+1) + "," + (col+1) + ") limpa com sucesso!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Não foi possível limpar a célula (" + (row+1) + "," + (col+1) + "). É uma posição fixa.");
                redirectAttributes.addFlashAttribute("messageType", "error");
            }
        } else {
            success = boardService.changeValue(col, row, value);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "Jogada (" + (row+1) + "," + (col+1) + ") = " + value + " realizada com sucesso!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Não foi possível alterar a célula (" + (row+1) + "," + (col+1) + "). É uma posição fixa.");
                redirectAttributes.addFlashAttribute("messageType", "error");
            }
        }

        return "redirect:/sudoku/play";
    }

    @PostMapping("/reset")
    public String handleReset(RedirectAttributes redirectAttributes) {
        boardService.reset();
        redirectAttributes.addFlashAttribute("message", "Jogo reiniciado com sucesso!");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/sudoku/play";
    }

    @GetMapping("/check")
    public String handleCheckStatus(RedirectAttributes redirectAttributes) {
        String message;
        String messageType;

        var hasErrors = boardService.hasErrors();
        var gameStatus = boardService.getStatus();

        message = "Status do Jogo: " + gameStatus.getLabel();
        if (hasErrors) {
            message += " e contém erros.";
            messageType = "error";
        } else {
            message += " e não contém erros.";
            if (gameStatus == GameStatusEnum.COMPLETE) {
                message += " Parabéns, o jogo foi concluído!";
                messageType = "success";
            } else {
                messageType = "info";
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("messageType", messageType);
        return "redirect:/sudoku/play";
    }

    @GetMapping("/finish")
    public String handleFinishGame(RedirectAttributes redirectAttributes) {
        String message;
        String messageType;

        boolean hasErrors = boardService.hasErrors();
        GameStatusEnum gameStatus = boardService.getStatus();
        boolean gameIsFinished = boardService.isGameFinished();

        if (gameIsFinished) {
            message = "Parabéns! Você concluiu o jogo com sucesso!";
            messageType = "success";
        } else if (hasErrors) {
            message = "Seu jogo contém erros! Ajuste-o antes de tentar concluir.";
            messageType = "error";
        } else if (gameStatus == GameStatusEnum.INCOMPLETE) {
            message = "O jogo ainda está incompleto. Preencha todas as células para concluir.";
            messageType = "info";
        } else {
            message = "Não foi possível concluir o jogo neste momento.";
            messageType = "info";
        }

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("messageType", messageType);
        return "redirect:/sudoku/play";
    }

}
