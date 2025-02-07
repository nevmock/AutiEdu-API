package nevmock.autiedu_api.controller;

import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserTopic;
import nevmock.autiedu_api.model.*;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

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
}
