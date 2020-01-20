package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/21
 * @description TODO
 */
@Data
@Builder
public class UserRole {
    private Integer id;
    private Integer roleId;
    private Integer userId;
}
