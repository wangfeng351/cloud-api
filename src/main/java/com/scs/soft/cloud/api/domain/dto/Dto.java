package com.scs.soft.cloud.api.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wf
 * @create 2020/1/31
 * @description TODO
 */
@Data
@Builder
public class Dto {
    private LocalDateTime start;
    private LocalDateTime end;
}
