package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.entities.Commentary;
import just.fo.fun.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    public Commentary save(Commentary commentary){
        return commentaryRepository.save(commentary);
    }

    public Commentary findOne(Long id){
        return commentaryRepository.findOne(id);
    }

    public List<Commentary> findAll(){
        return commentaryRepository.findAll();
    }

    public void delete(Long id){
        commentaryRepository.delete(id);
    }

    public List<CommentaryDto> getAll(Long id){

        List<CommentaryDto> parentles = commentaryRepository.getAllByParentIsNullAndPostIdOrderByRatingDesc(id)
                .stream().map(itm -> Utils.copyProperties(itm, new CommentaryDto())).collect(Collectors.toList());

        for (CommentaryDto commentaryDto : parentles) {
            recursion (commentaryDto);
        }

        return  parentles;
    }

    private void recursion (CommentaryDto commentaryDto){

        List<CommentaryDto> children = commentaryRepository.getAllByParentIdOrderByRatingDesc(commentaryDto.getId())
                .stream().map(itm -> Utils.copyProperties(itm, new CommentaryDto())).collect(Collectors.toList());

        if (children == null || children.isEmpty()) return;
        commentaryDto.setChildren(children);
        for (CommentaryDto dto : children) {
            recursion(dto);
        }

    }
}
