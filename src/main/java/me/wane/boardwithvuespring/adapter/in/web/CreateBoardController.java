package me.wane.boardwithvuespring.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.adapter.in.web.dto.CreateBoardRequest;
import me.wane.boardwithvuespring.application.port.in.CreateBoardUseCase;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class CreateBoardController {

    private final CreateBoardUseCase createBoardUseCase;

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody @Validated CreateBoardRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createBoardUseCase.execute(request));
    }
}
