package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    //게시글 목록
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    //한 게시글 보기
    public Optional<Board> findById(Integer id) {
        return boardRepository.findById(id);
    }

    //게시글 등록
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    //게시글 수정
    public Board update(Board board) {
        return boardRepository.save(board);
    }

   //게시글 삭제
    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }

}