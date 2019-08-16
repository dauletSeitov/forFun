package just.fo.fun.category.service;

import just.fo.fun.entities.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List<Category> findCategoryBySearchText(String searchText);

    void delete(Integer id);
}
