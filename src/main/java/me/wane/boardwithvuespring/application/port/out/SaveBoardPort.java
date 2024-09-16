package me.wane.boardwithvuespring.application.port.out;

import me.wane.boardwithvuespring.domain.Board;

public interface SaveBoardPort {

    public Board execute(Board board);
}
