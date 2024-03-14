package kr.co.ch07.repository.board;

import kr.co.ch07.entity.board.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileReposiroty extends JpaRepository<File, Integer>{

}

