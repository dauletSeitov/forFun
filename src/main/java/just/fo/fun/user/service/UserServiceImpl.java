package just.fo.fun.user.service;

import just.fo.fun.commentary.userCommentaryVoteHistory.UserCommentaryVoteHistoryRepository;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.userPostVoteHistory.UserPostVoteHistoryRepository;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.entities.User;
import just.fo.fun.post.model.ResultHolderTwoLong;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.model.*;
import just.fo.fun.user.repository.UserRepository;
import just.fo.fun.utils.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RequestUtils requestUtils;
    private final PostRepository postRepository;
    private final UserPostVoteHistoryRepository userPostMapRepository;
    private final UserCommentaryVoteHistoryRepository userCommentaryVoteHistoryRepository;
    private final CommentaryRepository commentaryRepository;
    private final PropertyService propertyService;

    private Long userLockTime;
    private Long userIncorrectAttempt;

    @PostConstruct
    private void init(){
        userLockTime = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.USER_LOCK_TIME);
        userIncorrectAttempt = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.USER_INCORRECT_ATTEMPT);
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public UserDto findOne(Long id){
        final User user = userRepository.findOneByIdNotDeleted(id);
        return Objects.isNull(user) ? null : new UserDto(user);
    }

    public User findOneEntity(Long id){
        return userRepository.findOneByIdNotDeleted(id);
    }

    public User findOneEntityByLogin(String login){
        return userRepository.findOneByLoginNotDeleted(login);
    }

    public Page<UserDto> findAll(Pageable pageable){
        final Page<User> page = userRepository.findAll(pageable);
        return page.map(UserDto::new);
    }

    public void delete(Long userId){
        //TODO logout

        //TODO add if user deletes account change status post authos as deleted user
        postRepository.deleteByUserId(userId);
        commentaryRepository.deleteByUserId(userId);

        userRepository.delete(userId);
    }


    public CurrentUserDto getCurrentUserData() {

        User user = requestUtils.getUser();

        ResultHolderTwoLong aggregatedPostDataByUser = postRepository.getAggregatedDataByUserNotDeleted(user.getId());
        ResultHolderTwoLong aggregatedCommentaryDataByUser = commentaryRepository.getAggregatedDataByUserNotDeleted(user.getId());

        ResultHolderTwoLong aggregatedPostVotesDataByUser = userPostMapRepository.getAggregatedDataByUserIdNotDeleted(user.getId());
        ResultHolderTwoLong aggregatedCommentVotesDataByUser = userCommentaryVoteHistoryRepository.getAggregatedDataByUserIdNotDeleted(user.getId());

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

    public User create(UserLoginDto userLoginDto) {

        User user = userRepository.findOneByLoginNotDeleted(userLoginDto.getLogin());

        if(user != null){
            user.setIsDeleted(true);
            userRepository.save(user);
        }

        userLoginDto.setStatus(UserStatus.EXPECTED_CONFIRMATION);
        return userRepository.save(convertUserLoginDtoToUser(userLoginDto));
    }

    public User update(UserLoginDto userLoginDto) {

        User userToUpdate = userRepository.findOneByIdNotDeleted(userLoginDto.getId());

        Objects.requireNonNull(userToUpdate, "user not found!");

        User inputUser = convertUserLoginDtoToUser(userLoginDto);

        if (inputUser.getLogin() != null){
            userToUpdate.setLogin(inputUser.getLogin());
        }

        if (inputUser.getName() != null){
            userToUpdate.setName(inputUser.getName());
        }

        if (inputUser.getPhotoUrl() != null){
            userToUpdate.setPhotoUrl(inputUser.getPhotoUrl());
        }

        if (inputUser.getEmail() != null){
            userToUpdate.setEmail(inputUser.getEmail());
        }

        if (inputUser.getPhone() != null){
            userToUpdate.setPhone(inputUser.getPhone());
        }


        return userRepository.save(userToUpdate);
    }

    public static User convertUserLoginDtoToUser(UserLoginDto userLoginDto){
        User user = new User();
        user.setId(userLoginDto.getId());
        user.setLogin(userLoginDto.getLogin());
        user.setName(userLoginDto.getName());
        user.setPassword(userLoginDto.getPassword());
        user.setPhotoUrl(userLoginDto.getPhotoUrl());
        user.setEmail(userLoginDto.getEmail());
        user.setPhone(userLoginDto.getPhone());
        return user;

    }

    public void changePassword(UserChangePasswordDto userChangePasswordDto) {

        User user = requestUtils.getUser();


        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(userLockTime);

        if(user.getLockedTime() != null && localDateTime.isBefore(user.getLockedTime())){
            log.debug("user {} is locked until {}", user.getName(), user.getLockedTime().plusMinutes(userLockTime));
            throw new MessageException("user is locked until "  +  user.getLockedTime().plusMinutes(userLockTime));
        } else {
            user.setLockedTime(null);
            userRepository.save(user);
        }

        if (user.getIncorrectAttempt() > userIncorrectAttempt - 2){
            user.setLockedTime(LocalDateTime.now());
            user.setIncorrectAttempt(0);
            userRepository.save(user);

            log.debug("3 times incorrect attempt user is locked");
            //TODO add logout here
            throw new MessageException("user is locked until "  +  user.getLockedTime().plusMinutes(userLockTime));
        }


        if (user.getPassword().equals(userChangePasswordDto.getOldPassword())){
            user.setPassword(userChangePasswordDto.getNewPassword());
            user.setIncorrectAttempt(0);
            userRepository.save(user);

        } else {

                user.setIncorrectAttempt(user.getIncorrectAttempt() + 1);
                userRepository.save(user);

                log.debug("incorrect login or password for user!");

                throw new MessageException("incorrect login or password!");
        }

    }
}
