package com.tictactoe.gameserver.util;

import com.tictactoe.gameserver.model.GameState;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class GameHelper {
    public static <T> T getRandomFromList(List<T> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }

    public static String getDefaultGameState() {
        ObjectMapper jsonMap = new ObjectMapper();
        String result = "";
        try {
            result = jsonMap.writeValueAsString(new GameState());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return result;
    }
}