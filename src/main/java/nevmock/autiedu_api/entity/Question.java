package nevmock.autiedu_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nevmock.autiedu_api.enums.QuestionMediaType;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Integer level;

    @Column(name= "media_type")
    private String mediaType = "image";

    private String src;

    @Column(name= "is_multiple_option")
    @Nullable
    private Boolean isMultipleOption;

    @Column(name= "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @OneToMany
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private List<Option> options;

    @OneToMany
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private List<Answer> answers;
}
