package com.tictactoe.gameserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GameState {
    public ArrayList<History> history;
    public int stepNumber;
    public boolean completed;

    @JsonProperty("xIsNext")
    public boolean xIsNext;

    public GameState() {
        this.history = new ArrayList<>();
        this.history.add(new History());
        this.stepNumber = 0;
        this.xIsNext = true;
    }
}
