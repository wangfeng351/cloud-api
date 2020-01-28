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
public class Class {
    private Integer id;
    private Integer creatorId;
    private String classType;
    private String thumbnail;
    private String name;
    private Integer invitationCode;
    private Short status;
    private Integer resourceNumber;
    private Integer activityNumber;
    private Integer messageNumber;
    private Integer memberNumber;
    private String semester;
    private Short joinPermission;
    private String school;
    private String faculty;
    private String studyRequirement;
    private String teachingProgress;
    private String examArrangement;
}
