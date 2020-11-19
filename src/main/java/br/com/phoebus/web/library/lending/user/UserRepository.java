package br.com.phoebus.web.library.lending.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("municipal-library-user")
public interface UserRepository {

    @GetMapping(value = "/v1/users/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto findById(@PathVariable("uuid") String userID);
}
