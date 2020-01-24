package com.scs.soft.cloud.api.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/24
 * @description TODO
 */
@Data
@Builder
public class BlurQueryDto {
    private Integer id;
    private String field;
    private Integer pageSize;
    private Integer currentPage;
}
