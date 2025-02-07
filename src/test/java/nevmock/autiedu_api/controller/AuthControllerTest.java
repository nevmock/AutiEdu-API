package nevmock.autiedu_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.LoginUserRequest;
import nevmock.autiedu_api.model.TokenResponse;
import nevmock.autiedu_api.model.WebResponse;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void loginNotFound() throws Exception {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("kevin@autiedu.test");
        request.setPassword("rahasia");

        mockMvc.perform(
                post("/api/v1/auth/login")
                        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginFailedWrongPassword() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);

        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("kevin@autiedu.test");
        request.setPassword("rahasia");

        mockMvc.perform(
                post("/api/v1/auth/login")
                        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertNotNull(response.getData().getToken());
            assertNotNull(response.getData().getExpiredAt());

            User userDB = userRepository.findByEmail("kevin@autiedu.test").orElse(null);

            assertNotNull(userDB);
            assertEquals(userDB.getToken(), response.getData().getToken());
            assertEquals(userDB.getTokenExpiredAt(), response.getData().getExpiredAt());
        });
    }

    @Test
    void logoutFailed() throws Exception {
        mockMvc.perform(
                delete("/api/v1/auth/logout")
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
    void logoutSuccess() throws Exception {
        User user = new User();
        user.setEmail("kevin@autiedu.test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setRole("user");
        user.setName("Kevin");
        user.setClassName("test");
        user.setPhoneNumber("08511111111111");
        user.setEnabledMusic(true);
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);

        userRepository.save(user);

        mockMvc.perform(
                delete("/api/v1/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("AUTIEDU-API-TOKEN", "token")

        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals("OK", response.getData());

            User userDB = userRepository.findByEmail("kevin@autiedu.test").orElse(null);
            assertNotNull(userDB);
            assertNull(userDB.getToken());
            assertNull(userDB.getTokenExpiredAt());
        });
    }
}