package tw.project.performer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.project.performer.model.TodoEntity;
import tw.project.performer.persistence.TodoRepository;

import java.util.List;

@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;
    public String testService(){
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }

        repository.save(entity);

        log.info("Entity id {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }
}
