package nevmock.autiedu_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private String description;

    private String method;

    private Integer level;

    @ManyToOne // banyak topic untuk 1 learning module
    @JoinColumn(name = "learning_module_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private LearningModule learningModule;

    @OneToMany(mappedBy = "topic")
    private List<Question> questions;
}
