package nevmock.autiedu_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String email;

    private String password;

    @Column(columnDefinition = "enum('admin', 'user') default 'user'")
    private String role;

    private String name;

    @Column(name = "class_name")
    private String className;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_enabled_music")
    private boolean isEnabledMusic;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<UserTopic> userTopics;

    @OneToMany(mappedBy = "user")
    private List<UserQuestion> userQuestions;
}
