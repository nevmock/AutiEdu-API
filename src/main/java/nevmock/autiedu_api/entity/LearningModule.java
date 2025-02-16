package nevmock.autiedu_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "learning_module")
@Builder
public class LearningModule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private String description;

    private String method;

    @OneToMany(mappedBy = "learningModule")
    @JsonManagedReference
    private List<Topic> topics;
}
