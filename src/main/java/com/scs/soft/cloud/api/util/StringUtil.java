package com.scs.soft.cloud.api.util;

import java.util.Random;

/**
 * @author wf
 * @create 20202020/1/19
 * @description TODO
 */
public class StringUtil {

    public static int getRoleCode() {
        Random random = new Random();
        StringBuilder roleCode = new StringBuilder();
        for(int i = 0; i < 6; i++){
            roleCode.append(random.nextInt(9));
        }
        return Integer.parseInt(roleCode.toString());
    }
}
