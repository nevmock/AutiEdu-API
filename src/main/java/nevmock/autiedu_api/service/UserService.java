package nevmock.autiedu_api.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import nevmock.autiedu_api.entity.LearningModule;
import nevmock.autiedu_api.entity.Topic;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserTopic;
import nevmock.autiedu_api.model.*;
import nevmock.autiedu_api.repository.TopicRepository;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.repository.UserTopicRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private Validator validator;

    @Autowired
    private UserTopicRepository userTopicRepository;

    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setClassName(request.getClassName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEnabledMusic(request.isEnabledMusic());

        userRepository.save(user);
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .className(user.getClassName())
                .phoneNumber(user.getPhoneNumber())
                .isEnabledMusic(user.isEnabledMusic())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request) {
        validationService.validate(request);


        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        if (Objects.nonNull(request.getRole())) {
            user.setRole(request.getRole());
        }

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getClassName())) {
            user.setClassName(request.getClassName());
        }

        if (Objects.nonNull(request.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .className(user.getClassName())
                .phoneNumber(user.getPhoneNumber())
                .isEnabledMusic(user.isEnabledMusic())
                .build();
    }

    @Transactional
    public TopicResponse createTopic(User user, CreateTopicRequest request) {
        validationService.validate(request);

        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setDescription(request.getDescription());
        topic.setMethod(request.getMethod());
        topic.setLevel(request.getLevel());
        topic.setLearningModule(LearningModule.builder().id(request.getLearningModuleId()).build());

        topicRepository.save(topic);

        // save user_topic
        UserTopic userTopic = new UserTopic();
        userTopic.setUser(user);
        userTopic.setTopic(topic);
        userTopic.setUnlocked(false);
        userTopic.setFinished(false);
        userTopicRepository.save(userTopic);

        return TopicResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .method(topic.getMethod())
                .level(topic.getLevel())
                .learningModuleId(topic.getLearningModule().getId())
                .build();
    }
}
