package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@Data
@Builder
public class UserLogin {
    private Integer id;
    private String mobile;
    private String password;
    private String code;
    private Boolean status;
}
