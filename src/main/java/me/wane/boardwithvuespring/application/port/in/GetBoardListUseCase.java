package me.wane.boardwithvuespring.application.port.in;

import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface GetBoardListUseCase {
    List<Board> execute(String searchKeyword);

    Page<Board> executeWithPaging(PageRequest pageRequest, String searchKeyword);
}
