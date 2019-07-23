package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;

public interface CommentaryValidationService {
    void validateCreate(CommentaryDto commentaryDto);
}
