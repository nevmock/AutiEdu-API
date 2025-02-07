package nevmock.autiedu_api.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.LearningModule;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateLearningModuleRequest;
import nevmock.autiedu_api.model.LearningModuleResponse;
import nevmock.autiedu_api.repository.LearningModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LearningModuleService {
    @Autowired
    private ValidationService validationService;

    @Autowired
    private LearningModuleRepository learningModuleRepository;

    @Transactional
    public LearningModuleResponse create(CreateLearningModuleRequest request) {
        validationService.validate(request);

        LearningModule learningModule = new LearningModule();
        learningModule.setName(request.getName());
        learningModule.setDescription(request.getDescription());
        learningModule.setMethod(request.getMethod());

        learningModuleRepository.save(learningModule);

        return LearningModuleResponse.builder()
                .id(learningModule.getId())
                .name(learningModule.getName())
                .description(learningModule.getDescription())
                .method(learningModule.getMethod())
                .build();
    }

    public List<LearningModuleResponse> get() {
        List<LearningModule> learningModules = learningModuleRepository.findAll();

        return learningModules.stream()
                .map(learningModule -> LearningModuleResponse.builder()
                        .id(learningModule.getId())
                        .name(learningModule.getName())
                        .description(learningModule.getDescription())
                        .method(learningModule.getMethod())
                        .build())
                .toList();
    }
}
