package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    /*
    * @ModelAttribute("cate")
    *  - modelAttribute("cate", cate)와 동일
    * */
    @GetMapping("/article/list")
    public String list(Model model, String cate, PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = articleService.findByParentAndCate(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }
    @GetMapping("article/write")
    public String write(Model model, String cate){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO) {
        /*
         *   글 작성을 테스트 할 때는 로그인 해야하기 때문에
         *   SecurityConfig 인가 설정 수정할 것
         */

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info(articleDTO.toString());

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list";
    }

    @GetMapping("/article/view")
    public String view(Model model, String cate, int no) {

        ArticleDTO articleDTO = articleService.findById(no);
        model.addAttribute(articleDTO);

        return "/article/view";

    }


    @PostMapping("/article/modify")
    public String modify(ArticleDTO articleDTO){
        articleService.modifyArticle(articleDTO);
        return "redirect:/article/view?no=" + articleDTO.getNo();
    }

    @DeleteMapping("/deleteArticle/")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer no){
        articleService.deleteArticle(no);
        return ResponseEntity.ok().body("삭제 성공...");
    }





}
