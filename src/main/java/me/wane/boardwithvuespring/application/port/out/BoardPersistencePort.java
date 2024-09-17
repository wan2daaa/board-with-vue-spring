package me.wane.boardwithvuespring.application.port.out;

import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BoardPersistencePort {

    List<Board> getBoardListBySearchKeyword(String searchKeyword);

    Page<Board> getBoardPageBySearchKeyword(PageRequest pageRequest, String searchKeyword);
}
