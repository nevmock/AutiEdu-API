package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nevmock.autiedu_api.entity.LearningModule;
import nevmock.autiedu_api.entity.Topic;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserTopic;
import nevmock.autiedu_api.repository.LearningModuleRepository;
import nevmock.autiedu_api.repository.TopicRepository;
import nevmock.autiedu_api.repository.UserTopicRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import nevmock.autiedu_api.model.*;
import nevmock.autiedu_api.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LearningModuleRepository learningModuleRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserTopicRepository userTopicRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        learningModuleRepository.deleteAll();
        topicRepository.deleteAll();
        userTopicRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("kevin@autiedu.test");
        request.setPassword("rahasia");
        request.setName("Kevin");

        mockMvc.perform(
                post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("OK", response.getData());
        });
    }


    @Test
    void testRegisterBadRequest() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("");
        request.setPassword("");
        request.setName("Kevin");

        mockMvc.perform(
                post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testRegisterDuplicate() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);

        userRepository.save(user);


        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("kevin@autiedu.test");
        request.setPassword("rahasia");
        request.setName("Kevin");

        mockMvc.perform(
                post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getUserUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/v1/users/current")
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
    void getUserUnauthorizedTokenNotSend() throws Exception {
        mockMvc.perform(
                get("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getUserSuccess() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setAge(6);
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000 * 60 * 60);

        userRepository.save(user);

        mockMvc.perform(
                get("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals("kevin@autiedu.test", response.getData().getEmail());
            assertEquals("user", response.getData().getRole());
            assertEquals("Kevin", response.getData().getName());
            assertEquals("test", response.getData().getClassName());
            assertEquals("08511111111111", response.getData().getPhoneNumber());
            assertEquals(6, response.getData().getAge());
            assertTrue(response.getData().isEnabledMusic());
        });
    }

    @Test
    void getUserTokenExpired() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() - 10000);

        userRepository.save(user);

        mockMvc.perform(
                get("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateUserUnauthorized() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();

        mockMvc.perform(
                patch("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateUserSuccess() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000);

        userRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest();
        request.setName("Kevin Updated");
        request.setPassword("rahasia");

        mockMvc.perform(
                patch("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals("Kevin Updated", response.getData().getName());

            User updatedUser = userRepository.findById(user.getId()).orElse(null);
            assert updatedUser != null;
            assertTrue(BCrypt.checkpw("rahasia", updatedUser.getPassword()));
        });
    }

    @Test
    void createUserTopicBadRequest() throws Exception {
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
        learningModule.setDescription("Desc interaksi sosisal");
        learningModule.setMethod("video");
        learningModuleRepository.save(learningModule);

        CreateTopicRequest request = new CreateTopicRequest();
        request.setName("Cuci tangan");
        request.setDescription("Desc cuci tangan");
        request.setMethod("");
        request.setLevel(0);
        request.setLearningModuleId(learningModule.getId());

        mockMvc.perform(
                post("/api/v1/users/topic")
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
    void getUserTopicsUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/v1/users/topic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "notfound")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
        });
    }

    @Test
    void getUserTopicsSuccess() throws Exception {
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
        learningModule.setName("Interaksi Sosial TEST");
        learningModule.setDescription("Desc interaksi sosisal TEST");
        learningModule.setMethod("video");
        learningModuleRepository.save(learningModule);

        Topic topic = new Topic();
        topic.setName("Cuci tangan TEST");
        topic.setDescription("Desc cuci tangan");
        topic.setMethod("video");
        topic.setLevel(0);
        topic.setLearningModule(learningModule);
        topicRepository.save(topic);

        UserTopic userTopic = new UserTopic();
        userTopic.setTopic(topic);
        userTopic.setUser(user);
        userTopicRepository.save(userTopic);


        mockMvc.perform(
                get("/api/v1/users/topic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<TopicResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals(1, response.getData().size());
            assertEquals("Cuci tangan TEST", response.getData().get(0).getName());
        });
    }


    @Test
    void updateUserTopicSuccess() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000 * 60 * 60);
        userRepository.save(user);

        LearningModule learningModule = new LearningModule();
        learningModule.setName("Interaksi Sosial TEST");
        learningModule.setDescription("Desc interaksi sosisal TEST");
        learningModule.setMethod("video");
        learningModuleRepository.save(learningModule);

        Topic topic = new Topic();
        topic.setName("Cuci tangan TEST");
        topic.setDescription("Desc cuci tangan");
        topic.setMethod("video");
        topic.setLevel(0);
        topic.setLearningModule(learningModule);
        topicRepository.save(topic);

        UserTopic userTopic = new UserTopic();
        userTopic.setTopic(topic);
        userTopic.setUser(user);
        userTopicRepository.save(userTopic);


        UpdateUserTopicRequest request = new UpdateUserTopicRequest();
        request.setTopicId(topic.getId());
        request.setUnlocked(true);

        mockMvc.perform(
                patch("/api/v1/users/topic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserTopicResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals(true, response.getData().isUnlocked());

            UserTopic updatedUserTopic = userTopicRepository.findByUserIdAndTopicId(user.getId(), topic.getId()).orElse(null);
            assert updatedUserTopic != null;
            assertEquals(true, updatedUserTopic.isUnlocked());
        });
    }

    @Test
    void updateEnabledMusic() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000);

        userRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest();
        request.setEnabledMusic(false);


        mockMvc.perform(
                patch("/api/v1/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("AUTIEDU-API-TOKEN", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals(false, response.getData().isEnabledMusic());

            User updatedUser = userRepository.findById(user.getId()).orElse(null);
            assert updatedUser != null;
            assertEquals(false, response.getData().isEnabledMusic());
        });
    }
}