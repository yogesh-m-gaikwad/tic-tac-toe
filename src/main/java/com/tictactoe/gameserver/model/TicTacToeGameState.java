package com.tictactoe.gameserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class TicTacToeGameState {
    public ArrayList<History> history;
    public int stepNumber;
    public boolean completed;
    public long userX;
    public long userO;

    @JsonProperty("xIsNext")
    public boolean xIsNext;

    public TicTacToeGameState() {
        this.history = new ArrayList<>();
        this.history.add(new History());
        this.stepNumber = 0;
        this.xIsNext = true;
    }

    public TicTacToeGameState(long userX, long userO) {
        this.history = new ArrayList<>();
        this.history.add(new History());
        this.stepNumber = 0;
        this.xIsNext = true;
        this.userX = userX;
        this.userO = userO;
    }
}
