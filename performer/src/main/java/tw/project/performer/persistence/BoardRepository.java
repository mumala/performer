package tw.project.performer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.project.performer.model.BoardEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {
    @Query("select t from BoardEntity t where t.boardId = ?1")
    List<BoardEntity> findByBoardId(String boardId);
}
