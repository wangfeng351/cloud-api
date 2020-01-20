package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wf
 * @create 20202020/1/20
 * @description TODO
 */
@Data
@Builder
public class User {
    private Integer id;
    private String mobile;
    private String nickname;
    private String email;
    private String name;
    private String gender;
    private String school;
    private String faculty;
    private String jobNumber;
    private Integer experience;
    private Integer charisma;
    private Integer joinClassNumber;
    private Integer createClassNumber;
    private Integer resourceNumber;
    private Integer activityNumber;
    private LocalDateTime createTime;
    private String avatar;
    private String profession;
    private Date birthday;
}
