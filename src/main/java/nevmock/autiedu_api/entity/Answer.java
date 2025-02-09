package nevmock.autiedu_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id")
    private Option option;
}
