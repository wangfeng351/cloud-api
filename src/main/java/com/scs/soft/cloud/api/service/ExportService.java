package com.scs.soft.cloud.api.service;

import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
public interface ExportService {

    List<Map<String, Object>> getResourceInformation();

    List<Map<String, Object>> getUserInformation();
}
