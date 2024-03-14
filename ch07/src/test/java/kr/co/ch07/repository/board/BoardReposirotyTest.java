package kr.co.ch07.repository.board;

import jakarta.transaction.Transactional;
import kr.co.ch07.entity.board.Article;
import kr.co.ch07.entity.board.Comment;
import kr.co.ch07.entity.board.File;
import kr.co.ch07.entity.board.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BoardReposirotyTest {

    @Autowired
    private ArticleReposiroty articleReposiroty;
    @Autowired
    private CommentReposiroty commentReposiroty;
    @Autowired
    private FileReposiroty fileReposiroty;
    @Autowired
    private UserReposiroty userReposiroty;

    @Test
    public void insertUser(){
        User user = User.builder()
                .uid("a202")
                .name("에이공이")
                .hp("010-1234-1234")
                .build();

        userReposiroty.save(user);
    }

    @Test
    public void insertArticle() {
        User user = User.builder()
                .uid("a202").build();

        Article article = Article.builder()
                .title("제목테스트2")
                .content("내용테스트2")
                .user(user)
                .build();

        articleReposiroty.save(article);
    }

    @Test
    public void insertComment(){
        User user = User.builder()
                .uid("a101").build();

        Article article = Article.builder()
                .no(2)
                .build();

        Comment comment = Comment.builder()
                .content("댓글테스트1")
                .user(user)
                .article(article)
                .build();

        commentReposiroty.save(comment);
    }

    @Test
    public void insertFile(){
        Article article = Article.builder()
                .no(2)
                .build();

        File file = File.builder()
                .oName("원래파일명.txt")
                .sName("filetest.txt")
                .article(article)
                .build();
        fileReposiroty.save(file);
    }

    /*
    *   연관관계로 설정된 엔티티를 조회할 때 하나 이상의 SELECT가 실행되기 때문에
    * @Transactional 선언으로 한번의 실행으로 처리해야 no session 에러 방지
    * */
    
    @Test
    @Transactional
    public void selecArticles(){

        List<Article> articles = articleReposiroty.findAll();

        for (Article article : articles){
            log.info(article.toString());

        }

    }
}