package ee.piperal.veebipood.dto;

import lombok.Data;

@Data
public class PersonLoginDto {
    public String email;
    private String password;
}
