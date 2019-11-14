package just.fo.fun.commentary.service;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.common.vote.VoteService;
import just.fo.fun.entities.Commentary;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.utils.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentaryServiceImpl implements CommentaryService{

    private final CommentaryRepository commentaryRepository;
    private final PostRepository postRepository;
    private final RequestUtils requestUtils;
    private final VoteService voteService;

    public Commentary create(CommentaryDto commentaryDto){
        commentaryDto.setRating(0L);
        return commentaryRepository.save(commentaryDtoToCommentary(commentaryDto));
    }

    public Commentary findOne(Long id){
        return commentaryRepository.findOne(id);
    }

    public List<Commentary> findAll(){
        return commentaryRepository.findAll();
    }

    public void delete(Long commentaryId){

        Commentary commentary = commentaryRepository.findOneByIdNotDeleted(commentaryId);

        Objects.requireNonNull(commentary, "commentary not found");

        if (commentary.getUser().getId().equals(requestUtils.getUser().getId())){
            commentaryRepository.delete(commentaryId);
        } else {
            throw new MessageException("it's not your commentary");
        }
    }

    public Page findAllByPostId(Long postId, Pageable pageable){

        Page<Commentary> commentaries = commentaryRepository.findAllParentlessByPostIdNotDeleted(postId, pageable);

        if (CollectionUtils.isEmpty(commentaries.getContent())){
            return commentaries;
        }

        List<CommentaryDto> parentles = commentaries.getContent()
                .stream().map(CommentaryServiceImpl::commentaryToCommentaryDto).collect(Collectors.toList());

        for (CommentaryDto commentaryDto : parentles) {
            recursion (commentaryDto);
        }

        return new PageImpl<>(parentles, pageable, commentaries.getTotalElements());
    }

    private void recursion (CommentaryDto commentaryDto){

        List<CommentaryDto> children = commentaryRepository.findAllByParentIdNotDeleted(commentaryDto.getId())
                .stream().map(CommentaryServiceImpl::commentaryToCommentaryDto).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(children))
            return;

        commentaryDto.setChildren(children);
        for (CommentaryDto dto : children) {
            recursion(dto);
        }

    }

    public void changeRating(Long commentId, Boolean isUpVote) {

        voteService.commentChangeRating(isUpVote, requestUtils.getUser(), commentId);
    }

    public Long getCommentaryCountByPostId(Long postId) {
        return commentaryRepository.getCommentaryCountByPostIdNotDeleted(postId);
    }

    //converter
    private Commentary commentaryDtoToCommentary(CommentaryDto commentaryDto) {

        Commentary parentCommentary = null;

        if(commentaryDto.getParentId() != null){
            parentCommentary = commentaryRepository.findOne(commentaryDto.getParentId());
        }

        Post post = postRepository.findOne(commentaryDto.getPostId());
        User user = requestUtils.getUser();

        Commentary commentary = new Commentary();
        commentary.setImageUrl(commentaryDto.getImageUrl());
        commentary.setParent(parentCommentary);
        commentary.setPost(post);
        commentary.setRating(commentaryDto.getRating());
        commentary.setText(commentaryDto.getText());
        commentary.setUser(user);

        return commentary;
    }


    private static CommentaryDto commentaryToCommentaryDto(Commentary commentary) {

        CommentaryDto commentaryDto = new CommentaryDto();

        commentaryDto.setId(commentary.getId());
        commentaryDto.setImageUrl(commentary.getImageUrl());
        commentaryDto.setParentId(commentary.getParent() == null ? null :commentary.getParent().getId());
        commentaryDto.setPostId(commentary.getPost().getId());
        commentaryDto.setText(commentary.getText());
        commentaryDto.setRating(commentary.getRating());
        commentaryDto.setUser(new UserDto(commentary.getUser()));

        return commentaryDto;
    }
    //converter

}
