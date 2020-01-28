package com.scs.soft.cloud.api.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/28
 * @description TODO
 */
@Data
@Builder
public class ClassVo {
    private Integer id;
    private Integer creatorId;
    private String classType;
    private String name;
    private Integer invitationCode;
    private Boolean status;
    private String school;
    private String faculty;
}
