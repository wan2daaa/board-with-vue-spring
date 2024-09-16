# 기능 정리 

- 게시물 전체 조회 (최신순 조회)
- 게시물 검색어로 조회 (검색어 : 제목, 내용 포함) (최신순 조회)
- 게시물 작성
- 게시물 수정
- 게시물 삭제

- 댓글 작성
- 댓글 수정
- 댓글 삭제

---
# 위 기능 구현 후 추가로 구현 예정
- 로그인, 로그아웃 

# 고민 사항 

### 1. 객체를 생성할 때 정적 팩토리 메소드를 사용하는게 옳을까? 
- 컬럼이 많아지면 힘들것 같다. 
  - 단점: 수정시 사용하는 여러 곳에 수정이 된다.
- Builder 패턴을 써볼까? 
  - 단점: 컬럼을 누락할 수 있다.

### 2. 간단한 조회, 수정, 삭제는 묶어서 처리하는게 좋지 않을까?
- usecase 에서 -> out port 까지 이름이 동일하게 작성된다. 
- 그러면, 간단한 rud 는 하나로 묶어서 처리하는게 좋지 않을까?
  - 일단 BoardPersistencePort 로 묶어서 처리해보자

# full text search
```sql
SHOW PLUGINS;
```
```sql
SHOW VARIABLES LIKE 'ngram_token_size';
SHOW VARIABLES LIKE 'innodb_ft_min_token_size';
SHOW VARIABLES LIKE 'ft_min_word_len';
```

```sql
ALTER TABLE board_entity ADD FULLTEXT INDEX title_content_idx(title, content) WITH PARSER ngram;
```



# reference

[full-text-search](https://gist.github.com/Fabricio20/83c86ccf055c8efc359463dc8a1e895c)
