package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.LearningModule;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateTopicRequest;
import nevmock.autiedu_api.model.TopicResponse;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.repository.LearningModuleRepository;
import nevmock.autiedu_api.repository.TopicRepository;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class TopicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LearningModuleRepository learningModuleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        topicRepository.deleteAll();
        userRepository.deleteAll();
        learningModuleRepository.deleteAll();
        topicRepository.deleteAll();


        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000 * 60 * 60);
        userRepository.save(user);

        LearningModule learningModule = new LearningModule();
        learningModule.setName("Interaksi Sosial");
        learningModule.setDescription("Belajar interaksi sosial");
        learningModule.setMethod("Video");
        learningModuleRepository.save(learningModule);
    }

    private LearningModule getLearningModule() {
        return learningModuleRepository.findAll().get(0);
    }

    @Test
    void createUnauthorized() throws Exception {
        CreateTopicRequest request = new CreateTopicRequest();
        request.setName("");
        request.setDescription("Cara berkomunikasi");
        request.setLearningModuleId(getLearningModule().getId());
        request.setMethod("Video");
        request.setLevel(1);

        mockMvc.perform(
                post("/api/v1/topics")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
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
    void createBadRequest() throws Exception {
        CreateTopicRequest request = new CreateTopicRequest();
        request.setName("");
        request.setDescription("Cara berkomunikasi");
        request.setLearningModuleId(getLearningModule().getId());
        request.setMethod("Video");
        request.setLevel(1);

        mockMvc.perform(
                post("/api/v1/topics")
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
        CreateTopicRequest request = new CreateTopicRequest();
        request.setName("Cara berkomunikasi");
        request.setDescription("Cara berkomunikasi dengan baik");
        request.setLearningModuleId(getLearningModule().getId());
        request.setMethod("Video");
        request.setLevel(1);

        log.info("Learning module: {}", getLearningModule());

        mockMvc.perform(
                post("/api/v1/topics")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TopicResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
        });
    }
}