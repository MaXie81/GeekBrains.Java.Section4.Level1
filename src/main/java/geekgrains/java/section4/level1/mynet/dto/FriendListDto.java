package geekgrains.java.section4.level1.mynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendListDto {
    private String userLogin;
    private List<String> listFriendLogin;
}
