package nevmock.autiedu_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.*;
import nevmock.autiedu_api.model.*;
import nevmock.autiedu_api.repository.*;
import nevmock.autiedu_api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private LearningModuleRepository learningModuleRepository;
    @Autowired
    private UserQuestionRepository userQuestionRepository;

    @Autowired
    private ObjectMapper objectMapper;

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
        user.setRole("user");
        userRepository.save(user);

        List<String> defaultLearningModules = List.of("Interaksi Sosial", "Akademik");

        List<LearningModule> modules = learningModuleRepository.findByNameIn(defaultLearningModules);

        if (modules.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Default learning modules not found");
        }

        for (LearningModule module : modules) {
            List<Topic> topics = topicRepository.findByLearningModuleId(module.getId());

            for (Topic topic : topics) {
                UserTopic userTopic = new UserTopic();
                userTopic.setUser(user);
                userTopic.setTopic(topic);
                userTopic.setIsUnlocked(false);
                userTopicRepository.save(userTopic);

                List<Question> questions = topic.getQuestions();

                for (Question question : questions) {
                    UserQuestion userQuestion = new UserQuestion();
                    userQuestion.setUser(user);
                    userQuestion.setQuestion(question);
                    userQuestion.setIsUnlocked(false);
                    userQuestionRepository.save(userQuestion);
                }
            }
        }
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

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(
                userRes -> UserResponse.builder()
                        .id(userRes.getId())
                        .name(userRes.getName())
                        .email(userRes.getEmail())
                        .className(userRes.getClassName())
                        .role(userRes.getRole())
                        .phoneNumber(userRes.getPhoneNumber())
                        .isEnabledMusic(userRes.isEnabledMusic())
                        .age(userRes.getAge())
                        .build()
        ).toList();
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
    public List<TopicResponse> getTopics(User user, UUID learningModuleId, UUID userId) {
        List<UserTopic> userTopics = userTopicRepository.findAllByUserAndTopic_LearningModule_Id(userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")), learningModuleId);

        return userTopics.stream()
                .map(userTopic -> TopicResponse.builder()
                        .id(userTopic.getTopic().getId())
                        .name(userTopic.getTopic().getName())
                        .description(userTopic.getTopic().getDescription())
                        .method(userTopic.getTopic().getMethod())
                        .level(userTopic.getTopic().getLevel())
                        .learningModuleId(userTopic.getTopic().getLearningModule().getId())
                        .isUnlocked(userTopic.getIsUnlocked())
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
        userTopic.setIsUnlocked(false);
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
                .findByUserIdAndTopicId(request.getUserId(), request.getTopicId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserTopic not found"));

        userTopic.setIsUnlocked(request.isUnlocked());
        userTopicRepository.save(userTopic);

        return UserTopicResponse.builder()
                .topicId(userTopic.getTopic().getId())
                .isUnlocked(userTopic.getIsUnlocked())
                .build();
    }

    @Transactional
    public List<UserQuestionResponse> getQuestion(User user, UUID userId, UUID topicId) {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        List<UserQuestion> userQuestions = userQuestionRepository.findAllByUserAndQuestion_Topic_Id(userFound, topicId);

        List<UserQuestionResponse> userQuestionResponses = userQuestions.stream()
                .map(userQuestion -> UserQuestionResponse.builder()
                        .id(userQuestion.getQuestion().getId())
                        .mediaType(userQuestion.getQuestion().getMediaType())
                        .src(userQuestion.getQuestion().getSrc())
                        .isMultipleOption(userQuestion.getQuestion().getIsMultipleOption())
                        .text(userQuestion.getQuestion().getText())
                        .level(userQuestion.getQuestion().getLevel())
                        .topicId(userQuestion.getQuestion().getTopic().getId())
                        .options(userQuestion.getQuestion().getOptions().stream()
                                .map(option -> OptionResponse.builder()
                                        .id(option.getId())
                                        .text(option.getText())
                                        .questionId(userQuestion.getQuestion().getId())
                                        .build())
                                .toList())
                        .answers(userQuestion.getQuestion().getAnswers().stream()
                                .map(answer -> AnswerResponse.builder()
                                        .id(answer.getId())
                                        .questionId(userQuestion.getQuestion().getId())
                                        .optionId(answer.getOption().getId())
                                        .build())
                                .toList())
                        .isUnlocked(userQuestion.getIsUnlocked())
                        .build())
                .toList();
        return userQuestionResponses;
    }

    @Transactional
    public UserQuestionResponse updateQuestion(User user, UpdateUserQuestionRequest request) {
        validationService.validate(request);

        UserQuestion userQuestion = userQuestionRepository
                .findByUserIdAndQuestionId(request.getUserId(), request.getQuestionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserQuestion not found"));


        userQuestion.setIsUnlocked(request.getIsUnlocked());
        userQuestionRepository.save(userQuestion);

        return UserQuestionResponse.builder()
                .id(userQuestion.getId())
                .mediaType(userQuestion.getQuestion().getMediaType())
                .src(userQuestion.getQuestion().getSrc())
                .isMultipleOption(userQuestion.getQuestion().getIsMultipleOption())
                .text(userQuestion.getQuestion().getText())
                .level(userQuestion.getQuestion().getLevel())
                .topicId(userQuestion.getQuestion().getTopic().getId())
                .options(userQuestion.getQuestion().getOptions().stream()
                        .map(option -> OptionResponse.builder()
                                .id(option.getId())
                                .text(option.getText())
                                .questionId(userQuestion.getQuestion().getId())
                                .build())
                        .toList())
                .answers(userQuestion.getQuestion().getAnswers().stream()
                        .map(answer -> AnswerResponse.builder()
                                .id(answer.getId())
                                .questionId(userQuestion.getQuestion().getId())
                                .optionId(answer.getOption().getId())
                                .build())
                        .toList())
                .isUnlocked(userQuestion.getIsUnlocked())
                .build();
    }
}
