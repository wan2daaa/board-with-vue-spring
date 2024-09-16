package me.wane.boardwithvuespring.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CreateBoardRequest {
    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "content is required")
    private String content;

    public CreateBoardRequest(String title, String content) {
        this.title = Objects.requireNonNull(title, "title is required");
        this.content = Objects.requireNonNull(content, "content is required");
    }
}

