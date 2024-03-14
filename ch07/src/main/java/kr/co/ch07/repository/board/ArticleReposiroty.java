package kr.co.ch07.repository.board;

import kr.co.ch07.entity.board.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleReposiroty extends JpaRepository<Article, Integer>{

}

