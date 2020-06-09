package com.example.restfulboard.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonFilter("UserInfo")  //UserInfo라는 이름의 JsonFilter 생성
public class User {
    private Integer id;
    // 두글자 이상, 에러 메시지
    @Size(min=2, message ="Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //@JsonIgnore는 읽기 쓰기 둘다 안되는듯?
    private String password;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ssn;
}
