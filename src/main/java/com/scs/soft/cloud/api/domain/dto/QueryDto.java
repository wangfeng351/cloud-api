package com.scs.soft.cloud.api.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/21
 * @description TODO
 */
@Data
@Builder
public class QueryDto {
    private String field;
    private Integer id;
}
