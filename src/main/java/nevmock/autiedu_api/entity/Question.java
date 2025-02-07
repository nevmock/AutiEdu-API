package nevmock.autiedu_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nevmock.autiedu_api.enums.QuestionMediaType;

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

//    @Column(name= "topic_id")
//    private String topicId;

    private Integer level;

    @Column(name= "media_type")
    private String mediaType = "image";

    private String src;

    @Column(name= "is_multiple_option")
    private boolean isMultipleOption;

    private String text;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;
}
