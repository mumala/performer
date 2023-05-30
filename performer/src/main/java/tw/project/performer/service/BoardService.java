package tw.project.performer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.project.performer.model.BoardEntity;
import tw.project.performer.persistence.BoardRepository;

import java.util.List;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;
    public String testService(){
        BoardEntity entity = BoardEntity.builder().title("My first todo item").build();
        repository.save(entity);
        BoardEntity savedEntity = repository.findById(entity.getBoardId()).get();
        return savedEntity.getTitle();
    }

    public List<BoardEntity> create(final BoardEntity entity){
        validate(entity);

        repository.save(entity);

        log.info("Entity id {} is saved.", entity.getBoardId());

        return repository.findByBoardId(entity.getBoardId());
    }

    private void validate(BoardEntity entity) {
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }
}
