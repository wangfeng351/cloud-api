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
public class Resource {
    private Integer id;
    private String name;
    private Integer groupId;
    private Integer experience;
    private String use;
    private String knowledgePoint;
    private String studyRequest;
    private Integer releaseSetting;
    private String title;
    private String url;
    private String thumbnail;
    private Integer publisherId;
    private Integer type;
    private LocalDateTime createTime;
    private Integer viewers;
}
