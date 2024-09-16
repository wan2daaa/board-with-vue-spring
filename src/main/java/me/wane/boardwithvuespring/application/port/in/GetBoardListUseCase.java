package me.wane.boardwithvuespring.application.port.in;

import me.wane.boardwithvuespring.domain.Board;

import java.util.List;

public interface GetBoardListUseCase {
    List<Board> execute(String searchKeyword);
}
