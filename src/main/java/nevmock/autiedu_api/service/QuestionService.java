package nevmock.autiedu_api.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.*;
import nevmock.autiedu_api.model.LearningModuleResponse;
import nevmock.autiedu_api.repository.QuestionRepository;
import nevmock.autiedu_api.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public List<QuestionResponse> get() {
        List<Question> questions = questionRepository.findAll();

        return questions.stream()
                .map(question -> QuestionResponse.builder()
                        .id(question.getId())
                        .topicId(question.getTopic().getId())
                        .level(question.getLevel())
                        .mediaType(question.getMediaType())
                        .src(question.getSrc())
                        .isMultipleOption(question.getIsMultipleOption())
                        .text(question.getText())
                        .build())
                .toList();
    }

    @Transactional
    public QuestionResponse create(CreateQuestionRequest request) {
        validationService.validate(request);

        Question question = new Question();
        question.setTopic(topicRepository.findById(request.getTopicId()).orElseThrow());
        question.setLevel(request.getLevel());
        question.setMediaType(request.getMediaType());
        question.setSrc(request.getSrc());
        question.setIsMultipleOption(request.isMultipleOption());
        question.setText(request.getText());

        questionRepository.save(question);

        log.info("Question created: {}", question);

        return QuestionResponse.builder()
                .id(question.getId())
                .topicId(question.getTopic().getId())
                .level(question.getLevel())
                .mediaType(question.getMediaType())
                .src(question.getSrc())
                .isMultipleOption(question.getIsMultipleOption())
                .text(question.getText())
                .build();
    }

    @Transactional
    public QuestionResponse update(UpdateQuestionRequest request) {
        Question question = questionRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));

        question.setTopic(topicRepository.findById(request.getTopicId()).orElseThrow());
        question.setLevel(request.getLevel());
        question.setMediaType(request.getMediaType());
        question.setSrc(request.getSrc());
        question.setIsMultipleOption(request.isMultipleOption());
        question.setText(request.getText());

        questionRepository.save(question);

        return QuestionResponse.builder()
                .id(question.getId())
                .topicId(question.getTopic().getId())
                .level(question.getLevel())
                .mediaType(question.getMediaType())
                .src(question.getSrc())
                .isMultipleOption(question.getIsMultipleOption())
                .text(question.getText())
                .build();
    }
}
