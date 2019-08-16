package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.entities.Commentary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentaryService {
    void delete(Long id);

    void changeRating(Long commentId, Boolean isUpVote);

    Page findAllByPostId(Long postId, Pageable pageable);

    Commentary create(CommentaryDto commentaryDto);
}
