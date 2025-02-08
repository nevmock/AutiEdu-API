package nevmock.autiedu_api.service;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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
        user.setName(request.getName());

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
                .age(user.getAge())
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

        if (Objects.nonNull(request.getAge())) {
            user.setAge(request.getAge());
        }

        if (Objects.nonNull(request.isEnabledMusic())) {
            user.setEnabledMusic(request.isEnabledMusic());
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
                .age(user.getAge())
                .build();
    }


    @Transactional
    public List<TopicResponse> getTopics(User user) {
        List<UserTopic> userTopics = userTopicRepository.findAllByUser(user);

        return userTopics.stream()
                .map(userTopic -> TopicResponse.builder()
                        .id(userTopic.getId())
                        .name(userTopic.getTopic().getName())
                        .description(userTopic.getTopic().getDescription())
                        .method(userTopic.getTopic().getMethod())
                        .level(userTopic.getTopic().getLevel())
                        .learningModuleId(userTopic.getTopic().getLearningModule().getId())
                        .build())
                .toList();
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
        UserTopic userTopic = new UserTopic();
        userTopic.setUser(user);
        userTopic.setTopic(topic);
        userTopic.setUnlocked(false);
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

    @Transactional
    public UserTopicResponse updateTopic(User user, UpdateUserTopicRequest request) {
        validationService.validate(request);

        UserTopic userTopic = userTopicRepository
                .findByUserIdAndTopicId(user.getId(), request.getTopicId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserTopic not found"));

        log.info("userTopic: {}", userTopic);

        userTopic.setUnlocked(request.isUnlocked());
        userTopicRepository.save(userTopic);

        return UserTopicResponse.builder()
                .topicId(userTopic.getTopic().getId())
                .isUnlocked(userTopic.isUnlocked())
                .build();
    }
}
