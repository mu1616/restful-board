package com.example.restfulboard.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")  //UserInfo라는 이름의 JsonFilter 생성
@ApiModel(description = "사용자 상제 정보를 위한 도메인 객체")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 두글자 이상, 에러 메시지
    @Size(min=2, message ="Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해주세요")
    private Date joinDate;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //@JsonIgnore는 읽기 쓰기 둘다 안되는듯?
    @ApiModelProperty(notes = "사용자 패스워드를 입력해주세요")
    private String password;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(notes = "사용자의 주민등록번호를 입력해주세요")
    private String ssn;
}
