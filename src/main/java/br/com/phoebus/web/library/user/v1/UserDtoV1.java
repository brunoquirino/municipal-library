package br.com.phoebus.web.library.user.v1;

import br.com.phoebus.web.library.user.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDtoV1 {

    private Long id;

    private String name;

    private Integer age;

    private String phone;

    public User to() {
        return User.builder()
                .id(id)
                .name(name)
                .age(age)
                .phone(phone)
                .build();
    }

    public static UserDtoV1 from(User user) {
        if (user == null) {
            return null;
        }

        return UserDtoV1.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .phone(user.getPhone())
                .build();
    }
}
