package just.fo.fun.commentary.service;

import com.querydsl.sql.SQLQueryFactory;
import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.common.vote.VoteService;
import just.fo.fun.entities.Commentary;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.repository.UserRepository;
import just.fo.fun.utils.RequestUtils;
import just.fo.fun.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
public class CommentaryService {

    @Autowired
    private SQLQueryFactory queryFactory;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private VoteService voteService;

    public Commentary save(CommentaryDto commentaryDto){
        return commentaryRepository.save(commentaryDtoToCommentary(commentaryDto));
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

        //QCommentary commentary = QCommentary.commentary;
        //List<DBCommentary> fetch = queryFactory.select(commentary).from(commentary).fetch();

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

        if (CollectionUtils.isEmpty(children))
            return;

        commentaryDto.setChildren(children);
        for (CommentaryDto dto : children) {
            recursion(dto);
        }

    }
    //converter
    private Commentary commentaryDtoToCommentary(CommentaryDto commentaryDto) {

        Commentary parentCommentary = commentaryRepository.findOne(commentaryDto.getParentId());
        Post post = postRepository.findOne(commentaryDto.getPostId());
        User user = userRepository.findOne(commentaryDto.getUserId());

        Commentary commentary = new Commentary();
        commentary.setImageUrl(commentaryDto.getImageUrl());
        commentary.setParent(parentCommentary);
        commentary.setPost(post);
        commentary.setRating(0L);
        commentary.setText(commentaryDto.getText());
        commentary.setUser(user);

        return commentary;
    }
    //converter


    public void changeRating(Long commentId, Boolean isUpVote) {

        voteService.commentChangeRating(isUpVote, requestUtils.getUser(), commentId);
    }

    public Long getCommentaryCountByPostId(Long postId) {
        return commentaryRepository.getCommentaryCountByPostId(postId);
    }
}
