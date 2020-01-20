package com.scs.soft.cloud.api.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
@Data
@Builder
public class PageDto {
    private Integer pageSize;
    private Integer currentPage;
}
