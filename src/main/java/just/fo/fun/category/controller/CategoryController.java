package just.fo.fun.category.controller;

import just.fo.fun.category.service.CategoryService;
import just.fo.fun.category.service.CategoryValidationService;
import just.fo.fun.entities.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryValidationService categoryValidationService;

    @PostMapping
    public ResponseEntity createCategory(@RequestBody final Category category) {

        categoryValidationService.validateCreate(category);

        Category result = categoryService.create(category);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

// not supported
//    @PutMapping
//    public ResponseEntity updateCategory(@Valid @RequestBody final Category category) {
//
//        if (category.getId() == null)
//            throw new MessageException("id must not be empty !");
//        Category result = categoryService.create(category);
//        return result == null
//                ? new ResponseEntity<>(HttpStatus.CONFLICT)
//                : new ResponseEntity<>(result, HttpStatus.OK);
//
//    }


    // not supported
//    @GetMapping("/{id}")
//    public ResponseEntity getCategory(@PathVariable final Integer id) {
//
//        Category category = categoryService.findOne(id);
//
//        return category == null
//                ? new ResponseEntity<>(HttpStatus.CONFLICT)
//                : new ResponseEntity<>(category, HttpStatus.OK);
//
//    }

    @GetMapping("/search/{searchText}")
    public ResponseEntity getCategories(@PathVariable String searchText) { //TODO rating for category how much post created to this category. and return by sorting by rating

        List<Category> categories = categoryService.findCategoryBySearchText(searchText);

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Integer id) {
        categoryValidationService.validateDelete(id);
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
