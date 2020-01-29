package com.scs.soft.cloud.api.service.Impl;

import com.scs.soft.cloud.api.mapper.UserMapper;
import com.scs.soft.cloud.api.service.ExportService;
import com.scs.soft.cloud.api.util.ImportDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wf
 * @create 2020/1/29
 * @description TODO
 */
@Service
public class ExportServiceImpl implements ExportService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void ExportInformation(HttpServletResponse response) {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            ImportDataUtil.createExcel1(response, maps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
