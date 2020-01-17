package com.scs.soft.cloud.api.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@Data
@Builder
public class RolePermissionQueryDto {
    private Integer id;
    private Integer roleId;
}
