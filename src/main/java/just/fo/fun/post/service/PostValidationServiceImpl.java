package just.fo.fun.post.service;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.entities.Category;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PostValidationServiceImpl implements PostValidationService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void validateCreate(PostDto postDto) {

        if (postDto == null){
            throw new MessageException("there is no data!");
        }

        if (postDto.getId() != null){
            throw new MessageException("should not be identifier!");
        }

        if(StringUtils.isEmpty(postDto.getTitle())){
            throw new MessageException("title can not be empty!");
        }

        Category category = categoryRepository.findOneNotDeletedByName(postDto.getCategory());

        if(category == null){
            throw new MessageException(String.format("category {%s} does not exists!", postDto.getCategory()));
        }



    }
}
