package just.fo.fun.category.service;

import just.fo.fun.common.validation.Validation;
import just.fo.fun.entities.Category;

public interface CategoryValidationService extends Validation {
    void validateCreate(Category category);

    void validateDelete(Integer id);
}
