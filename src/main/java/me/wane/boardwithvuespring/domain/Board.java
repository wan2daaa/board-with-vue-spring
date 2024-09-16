package me.wane.boardwithvuespring.domain;

import lombok.Getter;

@Getter
public class Board {

    private final Long id;

    private String title;

    private String content;

    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Board create(String title, String content) {
        return new Board(null, title, content);
    }
}
