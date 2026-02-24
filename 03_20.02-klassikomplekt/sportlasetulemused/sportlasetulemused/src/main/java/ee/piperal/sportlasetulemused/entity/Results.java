package ee.piperal.sportlasetulemused.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String fieldName;
    int result;
}
