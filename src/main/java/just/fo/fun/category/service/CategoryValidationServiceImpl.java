package just.fo.fun.category.service;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.entities.Category;
import just.fo.fun.entities.Post;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class CategoryValidationServiceImpl implements CategoryValidationService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void validateCreate(Category category) {

        if(category == null){
            throw new MessageException("no data!");
        }

        if(category.getId() != null){
            throw new MessageException("there should not be identifier!");
        }

        if(StringUtils.isEmpty(category.getName())){
            throw new MessageException("name can not be empty!");
        }

        Category categoryFromDb = categoryRepository.findOneNotDeletedByNameNotDeleted(category.getName().trim());

        if(categoryFromDb != null){
            throw new MessageException("category already exists!");
        }
    }


    @Override
    public void validateDelete(Integer categoryId) {

        if (categoryId == null || categoryId < 1){
            throw new MessageException("id cant be empty!");
        }

        Page<Post> post = postRepository.findAllPostByCategoryNotDeleted(categoryId, new PageRequest(0, 20));

        if(!CollectionUtils.isEmpty(post.getContent())){
            throw new MessageException("There are posts with this category!");
        }
    }

}
