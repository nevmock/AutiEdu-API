package nevmock.autiedu_api.controller;

import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.QuestionResponse;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserTopic;
import nevmock.autiedu_api.model.*;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(
        path = "/api/v1/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);

        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/v1/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(User user) {
        UserResponse userResponse = userService.get(user);

        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @GetMapping(
            path = "/api/v1/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UserResponse>> getAll(User user) {
        List<UserResponse> userResponse = userService.getAll();

        return WebResponse.<List<UserResponse>>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/api/v1/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(user, request);

        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }


    @PostMapping(
            path = "/api/v1/users/topic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TopicResponse> createTopic(User user, @RequestBody CreateTopicRequest request) {
        TopicResponse topicResponse = userService.createTopic(user, request);

        return WebResponse.<TopicResponse>builder().data(topicResponse).build();
    }


    @GetMapping(
            path = "/api/v1/users/topic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<TopicResponse>> getUserTopic(User user, @RequestParam(required = true) UUID learningModuleId, @RequestParam(required = true) UUID userId) {
        List<TopicResponse> userTopics = userService.getTopics(user, learningModuleId, userId);
        return WebResponse.<List<TopicResponse>>builder().data(userTopics).build();
    }


    @PatchMapping(
            path = "/api/v1/users/topic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserTopicResponse> updateUserTopic(User user, @RequestBody UpdateUserTopicRequest request) {
        UserTopicResponse userTopicResponse = userService.updateTopic(user, request);
        return WebResponse.<UserTopicResponse>builder().data(userTopicResponse).build();
    }

    @GetMapping(
            path = "/api/v1/users/question",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UserQuestionResponse>> getUserQuestion(User user, @RequestParam(required = true) UUID topicId, @RequestParam(required = true) UUID userId) {
        List<UserQuestionResponse> userQuestions = userService.getQuestion(user, userId, topicId);
        return WebResponse.<List<UserQuestionResponse>>builder().data(userQuestions).build();
    }

    @PatchMapping(
            path = "/api/v1/users/question",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserQuestionResponse> updateUserQuestion(User user, @RequestBody UpdateUserQuestionRequest request) {
        UserQuestionResponse userQuestionResponse = userService.updateQuestion(user, request);
        return WebResponse.<UserQuestionResponse>builder().data(userQuestionResponse).build();
    }
}
