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
public class Role {
    private Integer id;
    private String name;
    private Integer code;
}
