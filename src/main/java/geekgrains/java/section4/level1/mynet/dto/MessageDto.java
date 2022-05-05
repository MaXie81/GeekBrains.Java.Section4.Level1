package geekgrains.java.section4.level1.mynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String authorUserLogin;
    private String recipientUserLogin;
    private String body;
    private String datetime;
}
