package nevmock.autiedu_api.controller;

import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateLearningModuleRequest;
import nevmock.autiedu_api.model.LearningModuleResponse;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.service.LearningModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class LearningModuleController {
    @Autowired
    private LearningModuleService learningModuleService;

    @PostMapping(
            path = "/api/v1/learning-modules",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<LearningModuleResponse> create(User user, @RequestBody CreateLearningModuleRequest request) {
        LearningModuleResponse learningModuleResponse = learningModuleService.create(request);
        return WebResponse.<LearningModuleResponse>builder().data(learningModuleResponse).build();
    }

    @GetMapping(
            path = "/api/v1/learning-modules",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<LearningModuleResponse>> get(User user) {
        List<LearningModuleResponse> learningModuleResponses = learningModuleService.get();

        if (learningModuleResponses == null) {
            learningModuleResponses = new ArrayList<>();
        }
        return WebResponse.<List<LearningModuleResponse>>builder().data(learningModuleResponses).build();
    }
}
