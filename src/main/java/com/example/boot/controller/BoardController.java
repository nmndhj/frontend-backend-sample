package com.example.boot.controller;

import com.example.boot.dto.BoardDto;
import com.example.boot.dto.OrderDto;
import com.example.boot.entity.Board;
import com.example.boot.entity.Member;
import com.example.boot.repository.BoardRepository;
import com.example.boot.repository.MemberRepository;
import com.example.boot.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
public class BoardController {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    JwtService jwtService;

    @GetMapping("/api/board/list")
    public ResponseEntity getBoardList(@CookieValue(value = "token", required = false) String token
    ){
        if ( !jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        List<Board> list = boardRepository.findAllByOrderByIdDesc();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity getBoardDetail(
            @PathVariable("id") Integer id,
            @CookieValue(value = "token", required = false) String token
    ){
        if ( !jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

//        Optional<Board> boardOptional = boardRepository.findById(id);
//        Board board = boardOptional.get();

        System.out.println(board.getTitle());
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    @Transactional
    @PatchMapping("/api/board")
    public ResponseEntity update(
            @RequestBody BoardDto dto,
            @CookieValue(value = "token", required = false) String token
    ){
        if ( !jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
//        Optional<Board> newBoard = boardRepository.findById(dto.getId());
//        Board board = newBoard.get();

        Board board = boardRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setContent(dto.getContent());
        board.setTitle(dto.getTitle());

        //boardRepository.save(board);

        return new ResponseEntity<>(boardRepository.save(board),HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api/board")
    public ResponseEntity create(
            @RequestBody Board dto,
            @CookieValue(value ="token", required = false) String token
    ){
        if( !jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Board newboard = new Board();
        int memberId = jwtService.getId(token);
        String writer = memberRepository.findById(memberId).get().getEmail();

        newboard.setTitle(dto.getTitle());
        newboard.setContent(dto.getTitle());
        newboard.setWriter(writer);
        //boardRepository.save(newboard);

        //builder로 만들기
//
//        Board builderBoard = Board.builder()
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .writer(writer)
//                .build();


        return new ResponseEntity(boardRepository.save(newboard),HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/api/board/{id}")
    public ResponseEntity delete(
            @PathVariable Integer id,
            @CookieValue(value="token", required = false)String token
    ){
        if( !jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);

        return new ResponseEntity(HttpStatus.OK);
    }
}
