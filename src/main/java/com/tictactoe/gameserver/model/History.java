package com.tictactoe.gameserver.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class History {
    public ArrayList<Object> squares = new ArrayList<Object>() {
    };

    public History() {
        for (int i = 0; i < 9; i++) {
            squares.add(null);
        }
    }
}
