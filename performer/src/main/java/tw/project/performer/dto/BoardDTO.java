package tw.project.performer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.project.performer.model.BoardEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
    private String id;
    private String title;
    private String userId;

    public BoardDTO(final BoardEntity entity){
        this.id = entity.getBoardId();
        this.title = entity.getTitle();
        this.userId = entity.getUserId();
    }

    public static BoardEntity toEntity(final BoardDTO dto){
        return BoardEntity.builder()
                .boardId(dto.getId())
                .title(dto.getTitle())
                .userId(dto.getUserId())
                .build();
    }
}
