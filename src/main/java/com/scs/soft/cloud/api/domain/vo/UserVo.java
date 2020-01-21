package com.scs.soft.cloud.api.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/21
 * @description TODO
 */
@Data
@Builder
public class UserVo {
    private Integer id;
    private String email;
    private String name;
    private String mobile;
    private String gender;
    private String school;
    private String faculty;
    private String jobNumber;
    private Boolean status;
    private Integer roleId;
}
