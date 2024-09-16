package me.wane.boardwithvuespring.application.port.out;

import me.wane.boardwithvuespring.domain.Board;

import java.util.List;

public interface BoardPersistencePort {

    List<Board> getBoardListIdDesc();

    List<Board> getBoardListBySearchKeyword(String searchKeyword);
}
