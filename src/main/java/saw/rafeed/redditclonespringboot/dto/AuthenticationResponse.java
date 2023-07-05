package saw.rafeed.redditclonespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
}
