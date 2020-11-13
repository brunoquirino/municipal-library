package br.com.phoebus.web.library.user.v1;

import br.com.phoebus.web.library.user.User;

import java.util.Objects;

public class UserDTO {

    private Long id;

    private String name;

    private Integer age;

    private String phone;

    public UserDTO() {}

    public UserDTO(Long id) {
        this.id = id;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.phone = user.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserTO{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", idade=" + age +
                ", telefone='" + phone + '\'' +
                '}';
    }
}
