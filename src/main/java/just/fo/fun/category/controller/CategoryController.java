package just.fo.fun.category.controller;

import just.fo.fun.category.service.CategoryService;
import just.fo.fun.entities.Category;
import just.fo.fun.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; /// servics koi mynai degengit

    @PostMapping
    public ResponseEntity insertCategory(@Valid @RequestBody final Category category) {

        if (category.getId() != null)
            throw new MessageException("id must be empty !");

        Category result;
        try {
            result = categoryService.save(category);
        }catch (Exception e){
            throw new MessageException("can not save category" + e.getMessage());
        }
        return result == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity updateCategory(@Valid @RequestBody final Category category) {

        if (category.getId() == null)
            throw new MessageException("id must not be empty !");
        Category result = categoryService.save(category);
        return result == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getCategory(@PathVariable final Integer id) {

        Category category = categoryService.findOne(id);

        return category == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(category, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity getCategorys() {

        List<Category> categorys = categoryService.findAll();

        return categorys == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(categorys, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
