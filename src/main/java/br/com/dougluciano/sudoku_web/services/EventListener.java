package br.com.dougluciano.sudoku_web.services;

import org.springframework.stereotype.Service;


public interface EventListener {

    void update(EventEnum eventType);
}
