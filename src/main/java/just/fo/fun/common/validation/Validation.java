package just.fo.fun.common.validation;

import just.fo.fun.exception.MessageException;

public interface Validation {

    default void validateId(Long id){
        if(id == null || id < 1){
            throw new MessageException("id cant be empty!");
        }
    }
}
