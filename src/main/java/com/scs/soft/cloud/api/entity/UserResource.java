package com.scs.soft.cloud.api.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@Data
@Builder
public class UserResource {
    private Integer id;
    private Integer userId;
    private Integer resourceId;
    private LocalDateTime viewTime;
    private Integer acquisitionExperience;
    private Boolean viewStatus;
}
