package martns.gymsysproject.dto;

import lombok.Getter;

@Getter
public class MemberDto {
    
    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
}
