package com.example.restfulboard.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("/admin")  선언하면 /admin 앞에 안붙여도됨
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/admin/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                //아래의 필드들을 가져온다. JsonIgnore 되어있는 경우 가져오지 않음.
                .filterOutAllExcept("id", "name", "joinDate", "ssn", "password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/admin/v1/users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                //아래의 필드들을 가져온다. JsonIgnore 되어있는 경우 가져오지 않음.
                .filterOutAllExcept("id", "name", "joinDate", "ssn", "password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        /*
        만약 특정 필터가 여러 군데에서 사용된다면
        @Configuration을 선언한 Filter 클래스를 하나 만든 후에
        @Bean 빈으로 등록해서 사용하면 되지 않을까??
        */
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/admin/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        UserV2 userV2 = new UserV2("VIP");
        BeanUtils.copyProperties(user, userV2);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                //아래의 필드들을 가져온다. JsonIgnore 되어있는 경우 가져오지 않음.
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        /*
        만약 특정 필터가 여러 군데에서 사용된다면
        @Configuration을 선언한 Filter 클래스를 하나 만든 후에
        @Bean 빈으로 등록해서 사용하면 되지 않을까??
        */
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }
}
