package just.fo.fun.category.service;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public Category findOne(Integer id){
        return categoryRepository.findOne(id);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void delete(Integer id){
        categoryRepository.delete(id);
    }

    public List<Category> findCategoryBySearchText(String searchText) {
        return categoryRepository.findAllBySearchTextNotDeleted(searchText);
    }
}
