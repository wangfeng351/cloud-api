package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@Data
@Builder
public class UserClass {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Integer classId;
    private Integer experience;
}
