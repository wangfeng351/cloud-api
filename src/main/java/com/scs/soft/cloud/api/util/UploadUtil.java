package com.scs.soft.cloud.api.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author wf
 * @create 2020/1/20
 * @description TODO
 */
public class UploadUtil {

    /*MultipartFile与File之间的转换*/
    public static File fileConversion(MultipartFile file){
        int n;
        File file1 = new File(file.getOriginalFilename());
        try {
            InputStream in = file.getInputStream();
            OutputStream os = new FileOutputStream(file1);
            byte[] bytes = new byte[4096];
            while ((n = in.read(bytes,0,4096)) != -1){
                os.write(bytes,0,n);
                File file2 = new File(file1.toURI());
                file2.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file1;
    }
}
