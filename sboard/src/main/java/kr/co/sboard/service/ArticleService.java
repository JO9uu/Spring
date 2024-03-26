package kr.co.sboard.service;

import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.File;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;

    // RootConfig Bean 생성/등록
    private final ModelMapper modelMapper;

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Article> pageArticle = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);


        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                                                        .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                                                        .toList();

        int total = (int) pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public ArticleDTO findById(int no){

        Optional<Article> optArticle = articleRepository.findById(no);
        log.info("findById...1");
        ArticleDTO articleDTO = null;

        if(optArticle.isPresent()){

            log.info("findById...2");
            Article article = optArticle.get();

            log.info("findById...3");
            articleDTO = modelMapper.map(article, ArticleDTO.class);
            log.info("findById...4");

        }

        log.info("articleDTO : " + articleDTO.toString());

        return articleDTO;
    }

    public void insertArticle(ArticleDTO articleDTO){

        // 파일 첨부 처리
        List<FileDTO> files = fileService.fileUpload(articleDTO);

        // 파일 첨부 갯수 초기화
        articleDTO.setFile(files.size());

        // articleDTO를 articleEntity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());

        // 저장 후 저장한 엔티티 객체 반환(사실 JPA sava() 메서드는 default로 저장한 Entity를 반환)
        Article savedArticle = articleRepository.save(article);
        log.info("insertArticle : " + savedArticle.toString());

        // 파일 insert
        for(FileDTO fileDTO : files){

            fileDTO.setAno(savedArticle.getNo());

            // 여기서 에러나는데 RootConfig 파일에 ModelMapper 설정에 이거 추가 -> .setMatchingStrategy(MatchingStrategies.STRICT)
            File file = modelMapper.map(fileDTO, File.class);

            fileRepository.save(file);
        }
    }

    @Transactional
    public void modifyArticle(ArticleDTO articleDTO){
        // 글 번호로 수정할 글 찾기
        Article article = articleRepository.findById(articleDTO.getNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. no= " + articleDTO.getNo()));

        // 받은 DTO의 정보로 해당 글을 수정합니다.
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());

        // 수정된 내용을 저장합니다.
        articleRepository.save(article);

    }

    @Transactional
    public void deleteArticle(Integer no){
        Article article = articleRepository.findById(no).orElseThrow(() ->
                new IllegalArgumentException("해당 게시물이 없습니다. no= " + no));
        articleRepository.delete(article);
    }





}