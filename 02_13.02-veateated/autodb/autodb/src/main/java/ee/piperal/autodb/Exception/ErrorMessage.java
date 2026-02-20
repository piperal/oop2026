package ee.piperal.autodb.Exception;

import lombok.Data;

import java.util.Date;

@Data //@Getter @Setter @NoArgsConstructir

public class ErrorMessage {
    private String message;
    private Date timestamp;
    private int status;
}
