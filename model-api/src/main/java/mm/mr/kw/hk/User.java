package mm.mr.kw.hk;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private final String mail;
    private final String username;
    private final String address;
    private final String country;
}
