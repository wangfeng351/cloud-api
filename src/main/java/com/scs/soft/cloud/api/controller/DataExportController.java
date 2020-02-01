package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.service.ExportService;
import com.scs.soft.cloud.api.util.ImportDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/2/1
 * @description TODO
 */
@RestController
@RequestMapping("/api/download")
@Api(value = "DataExportController", tags = "数据导入导出接口")
public class DataExportController {
    @Resource
    private ExportService exportService;

    @GetMapping(value = "/resource")
    @ApiOperation(value = "导出资源数据")
    public Object exportResource() throws IOException {
        List<Map<String, Object>> maps = exportService.getResourceInformation();
        return ImportDataUtil.createResourcesExcel(maps);
    }

    @GetMapping(value = "/users")
    @ApiOperation(value = "导出用户数据")
    public Object exportUsers() throws IOException {
        List<Map<String, Object>> maps = exportService.getUserInformation();
        return ImportDataUtil.createUsersExcel(maps);
    }
}
