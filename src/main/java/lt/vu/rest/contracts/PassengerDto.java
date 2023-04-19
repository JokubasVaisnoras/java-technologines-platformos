package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDto {
    private String name;
    private String last_name;
    private Integer age;
    private String plane;
}
