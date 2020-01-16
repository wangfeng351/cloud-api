package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020
 * @description TODO
 */
@Data
@Builder
public class Permission {
    private Integer id;
    private String name;
    private Short type;
    private String permissionCode;
    private Integer parentId;
    private String routerUrl;
    private String icon;
    private Integer authorization;
    private Boolean status;
    private Short sort;
}
