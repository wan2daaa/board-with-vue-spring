package me.wane.boardwithvuespring.application.port.out;

import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Window;

import java.util.List;

public interface BoardPersistencePort {

    List<Board> getBoardListBySearchKeyword(String searchKeyword);

    Page<Board> getBoardPageBySearchKeyword(PageRequest pageRequest, String searchKeyword);

    Window<Board> getBoardWindow(int offset, int size);

    List<Board> getBoardListByCursor(long lastId, int size, String searchKeyword);
}
