package br.com.dougluciano.sudoku_web.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotifierService {

    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
       put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscribe(EventEnum eventType, EventListener listener){
        var selectedListeners = listeners.get(eventType);
        selectedListeners.add(listener);
    }

    public void notify(EventEnum eventType){
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }
}
