package com.scs.soft.cloud.api.controller;

import com.scs.soft.cloud.api.service.ExportService;
import com.scs.soft.cloud.api.util.ExcelsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/2/1
 * @description TODO
 */
@RestController
@RequestMapping("/api/download")
@Api(value = "ExcelsExportController", tags = "数据导入导出接口")
public class ExcelsExportController {
    @Resource
    private ExportService exportService;

    @PostMapping(value = "/resource")
    @ApiOperation(value = "导出资源数据")
    public void exportResource(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> maps = exportService.getResourceInformation();
        ExcelsUtil.exportResourceExcel(response, maps);
    }

    @PostMapping(value = "/users")
    @ApiOperation(value = "导出用户数据")
    public void exportUsers(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> maps = exportService.getUserInformation();
        ExcelsUtil.exportUserExcel(response,maps );
    }
}
