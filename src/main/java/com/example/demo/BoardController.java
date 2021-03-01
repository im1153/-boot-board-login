package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/list";
    }

    // 게시글 목록
    @RequestMapping("/list")
    public String index2(Model model) {
        List<Board> board = boardService.findAll();
        model.addAttribute("board", board);
        return "list";
    }

    // 게시글 상세보기
    @RequestMapping("/detail/{no}")
    public String detail(@PathVariable("no") Integer id, Model model) {
        Optional<Board> board = boardService.findById(id);
        model.addAttribute("board", board.get()); //Optional이라서 뷰에서 id못받는 현상 board.get()으로 해결
        return "detail";
    }

    //글쓰기 폼으로 이동
    @RequestMapping(value= "/writeform"  ,  method = RequestMethod.GET)
    public String writeform( Board board) { // Board board 바인딩시켜줘야한다.
        return "writeform";
    }

    //게시글 등록
    @RequestMapping("/write")
    public String write(Board board) {
        boardService.save(board);
        return "redirect:/list";
    }

    // 수정 폼으로 이동
    @RequestMapping(value="/detail/edit/{no}" ,  method = RequestMethod.GET)
    public String edit(@PathVariable("no") Integer id, Board board, Model model) {
        Optional<Board> board1 = boardService.findById(id);
        model.addAttribute("board", board1.get());  //꼭 이렇게 해줘야 이전내용이 뜬다.
        return "updateform";
    }

    // /customers/edit&id={id} 에 POST로 접속하여 특정고객의 정보만 업데이트
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    String edit(@RequestParam Integer id, @Validated Board board, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return edit(id, board, model);
        }
//        Board board1 = new Board();
//        BeanUtils.copyProperties(board, board1);
//        board1.setId(id);
//        //새로운 내용으로 업데이트한다.
        boardService.update(board);
        return "redirect:/list";
    }

    //특정 고객정보 삭제
    @RequestMapping(value = "delete/{no}", method = RequestMethod.POST)
    String delete(@PathVariable("no") Integer id) {
        boardService.deleteById(id);
        return "redirect:/list";
    }

}