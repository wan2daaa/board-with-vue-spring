package me.wane.boardwithvuespring.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.application.port.in.GetBoardListUseCase;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class GetBoardListController {

    private final GetBoardListUseCase getBoardListUseCase;

    @GetMapping
    public ResponseEntity<List<Board>> getBoardList(
            @RequestParam(defaultValue = "", required = false) String searchKeyword
    ) {
        return ResponseEntity.ok(getBoardListUseCase.execute(searchKeyword));
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<Board>> getBoardListWithPaging(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "", required = false) String searchKeyword
    ) {
        return ResponseEntity.ok(getBoardListUseCase.executeWithPaging(PageRequest.of(page, size), searchKeyword));
    }

    //Window는 Scroll queries are not supported using String-based queries
    @GetMapping("/offset")
    public ResponseEntity<Window<Board>> getBoardListWithOffset(
            @RequestParam(defaultValue = "-1", required = false) int offset,
            @RequestParam(defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(getBoardListUseCase.executeWithOffset(offset, size));
    }

    @GetMapping("/cursor")
    public ResponseEntity<List<Board>> getBoardListWithCursor(
            @RequestParam(defaultValue = "-1", required = false) long lastId,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "", required = false) String searchKeyword
    ) {
        return ResponseEntity.ok(getBoardListUseCase.executeWithCursor(lastId, size, searchKeyword));
    }

}
