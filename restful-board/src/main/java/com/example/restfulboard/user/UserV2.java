package com.example.restfulboard.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonFilter("UserInfoV2")  //UserInfo라는 이름의 JsonFilter 생성
public class UserV2 extends User{
    private String grade;
}
