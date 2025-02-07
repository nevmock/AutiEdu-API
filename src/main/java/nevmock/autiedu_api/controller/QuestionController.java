package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nevmock.autiedu_api.entity.CreateQuestionRequest;
import nevmock.autiedu_api.entity.QuestionResponse;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping(
            path = "/api/v1/questions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<QuestionResponse>> get(User user) {
        List<QuestionResponse> questionResponses = questionService.get();

        return WebResponse.<List<QuestionResponse>>builder().data(questionResponses).build();
    }

    @PostMapping(
            path = "/api/v1/questions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<QuestionResponse> create(User user, @RequestBody CreateQuestionRequest request) {
        QuestionResponse questionResponse = questionService.create(request);
        return WebResponse.<QuestionResponse>builder().data(questionResponse).build();
    }
}
