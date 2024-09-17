package me.wane.boardwithvuespring.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.application.port.in.GetBoardListUseCase;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    //TODO: page ,size 이용해서 페이징 기능 구현해보자
    @GetMapping("/paging")
    public ResponseEntity<Page<Board>> getBoardListWithPaging(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "", required = false) String searchKeyword
    ) {
        return ResponseEntity.ok(getBoardListUseCase.executeWithPaging(PageRequest.of(page, size), searchKeyword));
    }

    //TODO: offset  기반 페이징 기능 구현해보자

    //TODO: 무한 스크롤에서 사용할 수 있는 페이징 기능 구현해보자
}
