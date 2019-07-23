package just.fo.fun.post.service;

import just.fo.fun.post.model.PostDto;

public interface PostValidationService {
    void validateCreate(PostDto postDto);
}
