package tw.project.performer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import tw.project.performer.dto.BoardDTO;
import tw.project.performer.dto.ResponseDTO;
import tw.project.performer.model.BoardEntity;
import tw.project.performer.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("board")
public class BoardController {
    @Autowired
    private BoardService service;

    @PostMapping
    public ResponseEntity<?> createBoard(@AuthenticationPrincipal String boardId, @RequestBody BoardDTO dto){
        BoardEntity entity = BoardDTO.toEntity(dto);

        entity.setBoardId(boardId);

        List<BoardEntity> entities = service.create(entity);

        List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());

        ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}
