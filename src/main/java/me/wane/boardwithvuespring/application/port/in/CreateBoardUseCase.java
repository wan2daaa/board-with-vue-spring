package me.wane.boardwithvuespring.application.port.in;

import me.wane.boardwithvuespring.adapter.in.web.dto.CreateBoardRequest;
import me.wane.boardwithvuespring.domain.Board;

public interface CreateBoardUseCase {
    Board execute(CreateBoardRequest request);
}
