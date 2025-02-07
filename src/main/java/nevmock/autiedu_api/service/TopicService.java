package nevmock.autiedu_api.service;

import jakarta.transaction.Transactional;
import nevmock.autiedu_api.entity.LearningModule;
import nevmock.autiedu_api.entity.Topic;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateLearningModuleRequest;
import nevmock.autiedu_api.model.CreateTopicRequest;
import nevmock.autiedu_api.model.LearningModuleResponse;
import nevmock.autiedu_api.model.TopicResponse;
import nevmock.autiedu_api.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TopicResponse create(User user, CreateTopicRequest request) {
        validationService.validate(request);

        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setDescription(request.getDescription());
        topic.setMethod(request.getMethod());
        topic.setLevel(request.getLevel());
        topic.setLearningModule(LearningModule.builder().id(request.getLearningModuleId()).build());

        topicRepository.save(topic);

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
