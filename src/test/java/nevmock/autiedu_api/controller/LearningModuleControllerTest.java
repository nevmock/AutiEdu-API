package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.CreateLearningModuleRequest;
import nevmock.autiedu_api.model.LearningModuleResponse;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.repository.LearningModuleRepository;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LearningModuleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LearningModuleRepository learningModuleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        learningModuleRepository.deleteAll();
        userRepository.deleteAll();

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
    }

    @Test
    void createBadRequest() throws Exception {
        CreateLearningModuleRequest request = new CreateLearningModuleRequest();
        request.setName("");
        request.setDescription("Belajar interaksi sosial");
        request.setMethod("Video tutorial, permainan mencocokkan, dan mengurutkan aktivitas.");

        mockMvc.perform(
                post("/api/v1/learning-modules")
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
        CreateLearningModuleRequest request = new CreateLearningModuleRequest();
        request.setName("Interaksi Sosial");
        request.setDescription("Belajar interaksi sosial");
        request.setMethod("Video tutorial, permainan mencocokkan, dan mengurutkan aktivitas.");

        mockMvc.perform(
                post("/api/v1/learning-modules")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<LearningModuleResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals("Interaksi Sosial", response.getData().getName());
            assertEquals("Belajar interaksi sosial", response.getData().getDescription());
            assertEquals("Video tutorial, permainan mencocokkan, dan mengurutkan aktivitas.", response.getData().getMethod());

//            learningModuleRepository.existsById(response.getData().getId());
        });
    }

    @Test
    void getUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/v1/learning-modules")
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
                get("/api/v1/learning-modules")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<LearningModuleResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
        });
    }
}