package just.fo.fun.post.service;

import just.fo.fun.common.validation.Validation;
import just.fo.fun.post.model.PostDto;

public interface PostValidationService extends Validation {
    void validateCreate(PostDto postDto);
}
