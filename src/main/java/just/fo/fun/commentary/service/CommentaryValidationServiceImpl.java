package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.exception.MessageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CommentaryValidationServiceImpl implements CommentaryValidationService {


    @Override
    public void validateCreate(CommentaryDto commentaryDto) {


        if(commentaryDto == null){
            throw new MessageException("empty data!");
        }

        if(commentaryDto.getId() != null){
            throw new MessageException("identifier must be empty!");
        }

        if(StringUtils.isEmpty(commentaryDto.getText())){
            throw new MessageException("text can not be empty!");
        }

        if(commentaryDto.getPostId() == null){
            throw new MessageException("post id can not be empty!");
        }

    }
}
