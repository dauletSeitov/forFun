package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.common.validation.Validation;

public interface CommentaryValidationService extends Validation {
    void validateCreate(CommentaryDto commentaryDto);

}
