package nevmock.autiedu_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nevmock.autiedu_api.entity.Question;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;
}
