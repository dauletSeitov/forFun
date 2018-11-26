package just.fo.fun.category.service;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
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

}
