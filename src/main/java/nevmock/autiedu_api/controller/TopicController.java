package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateTopicRequest;
import nevmock.autiedu_api.model.TopicResponse;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.repository.TopicRepository;
import nevmock.autiedu_api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(
            path = "/api/v1/topics",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TopicResponse> create(User user, @RequestBody CreateTopicRequest request) {
        TopicResponse topicResponse = topicService.create(user, request);

        return WebResponse.<TopicResponse>builder().data(topicResponse).build();
    }


}
