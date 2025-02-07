package nevmock.autiedu_api.service;

import jakarta.transaction.Transactional;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.model.LoginUserRequest;
import nevmock.autiedu_api.model.TokenResponse;
import nevmock.autiedu_api.repository.UserRepository;
import nevmock.autiedu_api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;


    @Transactional
    public TokenResponse login(LoginUserRequest request) {
       validationService.validate(request);

       User user = userRepository.findByEmail(request.getEmail())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

       if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());

            userRepository.save(user);

            return TokenResponse
                    .builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
       } else {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
       }
   }

   private Long next30Days() {
       return System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000;
   }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }
}
