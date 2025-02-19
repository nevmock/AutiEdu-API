package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nevmock.autiedu_api.entity.*;
import nevmock.autiedu_api.model.CreateTopicRequest;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.repository.LearningModuleRepository;
import nevmock.autiedu_api.repository.QuestionRepository;
import nevmock.autiedu_api.repository.TopicRepository;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.security.BCrypt;
import nevmock.autiedu_api.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private LearningModuleRepository learningModuleRepository;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
        topicRepository.deleteAll();
        learningModuleRepository.deleteAll();
        userRepository.deleteAll();

        userRepository.save(User.builder()
                .email("kevin@autiedu.test")
                .password(BCrypt.hashpw("rahasia", BCrypt.gensalt()))
                .role("user")
                .name("Kevin")
                .className("test")
                .phoneNumber("08511111111111")
                .isEnabledMusic(true)
                .token("token")
                .tokenExpiredAt(System.currentTimeMillis() + 1000 * 60 * 60)
                .build());

        LearningModule learningModule = learningModuleRepository.save(LearningModule.builder()
                .name("Interaksi Sosial")
                .description("Description interaksi sosial")
                .method("Method 1")
                .build());

        topicRepository.save(Topic.builder()
                .name("Mencuci tangan")
                .description("Description 1")
                .method("Method 1")
                .level(1)
                .learningModule(learningModule)
                .build());
    }

    @Test
    void getUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/v1/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "notfound")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getSuccess() throws Exception {
        mockMvc.perform(
                get("/api/v1/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<QuestionResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
        });
    }

    @Test
    void createBadRequest() throws Exception {
        CreateQuestionRequest request = new CreateQuestionRequest();
        request.setTopicId(topicRepository.findAll().get(0).getId());
        request.setLevel(1);
        request.setMediaType("");
        request.setSrc("src");
        request.setMultipleOption(false);


        mockMvc.perform(
                post("/api/v1/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createSuccess() throws Exception {
        CreateQuestionRequest request = new CreateQuestionRequest();
        request.setTopicId(topicRepository.findAll().get(0).getId());
        request.setLevel(1);
        request.setMediaType("image");
        request.setSrc("src");
        request.setText("text");
        request.setMultipleOption(false);


        mockMvc.perform(
                post("/api/v1/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
        });
    }


    @Test
    void updateSuccess() throws Exception {
        Question question = new Question();
        question.setTopic(topicRepository.findAll().get(0));
        question.setLevel(1);
        question.setMediaType("image");
        question.setSrc("src");
        question.setText("text");
        questionRepository.save(question);

        UpdateQuestionRequest request = new UpdateQuestionRequest();
        request.setId(questionRepository.findAll().get(0).getId());
        request.setTopicId(topicRepository.findAll().get(0).getId());
        request.setLevel(1);
        request.setMediaType("image");
        request.setSrc("src");
        request.setText("text");
        request.setMultipleOption(false);

        mockMvc.perform(
                patch("/api/v1/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
        });
    }
}