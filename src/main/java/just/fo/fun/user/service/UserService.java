package just.fo.fun.user.service;

import just.fo.fun.commentary.userCommentaryVoteHistory.UserCommentaryVoteHistoryRepository;
import just.fo.fun.post.userPostVoteHistory.UserPostVoteHistoryRepository;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.entities.User;
import just.fo.fun.post.model.ResultHolderTwoLong;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.model.CurrentUserDto;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.user.repository.UserRepository;
import just.fo.fun.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserPostVoteHistoryRepository userPostMapRepository;

    @Autowired
    private UserCommentaryVoteHistoryRepository userCommentaryVoteHistoryRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public UserDto findOne(Long id){
        final User user = userRepository.findOneNotDeleted(id);
        return Objects.isNull(user) ? null : new UserDto(user);
    }

    public User findOneEntity(Long id){
        return userRepository.findOneNotDeleted(id);
    }

    public User findOneEntityByLogin(String login){
        return userRepository.findOneByLogin(login);
    }

    public Page<UserDto> findAll(Pageable pageable){
        final Page<User> page = userRepository.findAll(pageable);
        return page.map(UserDto::new);
    }

    public void delete(Long id){
        userRepository.delete(id);
    }


    public CurrentUserDto getCurrentUserData() {

        User user = requestUtils.getUser();

        ResultHolderTwoLong aggregatedPostDataByUser = postRepository.getAggregatedDataByUser(user.getId());
        ResultHolderTwoLong aggregatedCommentaryDataByUser = commentaryRepository.getAggregatedDataByUser(user.getId());

        ResultHolderTwoLong aggregatedPostVotesDataByUser = userPostMapRepository.getAggregatedDataByUser(user.getId());
        ResultHolderTwoLong aggregatedCommentVotesDataByUser = userCommentaryVoteHistoryRepository.getAggregatedDataByUser(user.getId());

        CurrentUserDto currentUserDto = new CurrentUserDto();
        currentUserDto.setId(user.getId());
        currentUserDto.setLogin(user.getLogin());
        currentUserDto.setName(user.getName());
        currentUserDto.setPhotoUrl(user.getPhotoUrl());
        currentUserDto.setMyCommentRating(aggregatedCommentaryDataByUser.getFirst());
        currentUserDto.setMyCommentCount(aggregatedCommentaryDataByUser.getSecond());
        currentUserDto.setMyPostRating(aggregatedPostDataByUser.getFirst());
        currentUserDto.setMyPostCount(aggregatedPostDataByUser.getSecond());

        currentUserDto.setMyPostUpVotes(aggregatedPostVotesDataByUser.getFirst());
        currentUserDto.setMyPostDownVotes(aggregatedPostVotesDataByUser.getSecond());

        currentUserDto.setMyCommentUpVotes(aggregatedCommentVotesDataByUser.getFirst());
        currentUserDto.setMyCommentDownVotes(aggregatedCommentVotesDataByUser.getSecond());

        return currentUserDto;
    }

    public User save(UserLoginDto userLoginDto) {
        return userRepository.save(convertUserLoginDtoToUser(userLoginDto));
    }

    public static User convertUserLoginDtoToUser(UserLoginDto userLoginDto){
        User user = new User();
        user.setLogin(userLoginDto.getLogin());
        user.setName(userLoginDto.getName());
        user.setPassword(userLoginDto.getPassword());
        user.setPhotoUrl(userLoginDto.getPhotoUrl());
        user.setEmail(userLoginDto.getEmail());
        user.setPhone(userLoginDto.getPhone());
        return user;

    }
}
