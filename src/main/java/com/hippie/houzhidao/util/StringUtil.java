package com.hippie.houzhidao.util;

import com.hippie.houzhidao.respbody.CodeRespBody;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * @author 39239
 * @Date 2019/5/8 22:22
 * @Package com.hippie.houzhidao.util
 * @Description:
 */

public class StringUtil {
    public static CodeRespBody getProperties(String respose){
        String[] str = respose.split(",");
        CodeRespBody codeRespBody = new CodeRespBody();
        for(String s : str){
            String[] properties = s.split(":");
            if("\"code\"".equals(properties[0])){
                codeRespBody.setStatus(properties[1]);
            }

            if("\"msg\"".equals(properties[0])){
                codeRespBody.setMsg(StringUtils.delete(properties[1],"\""));
            }

            if("\"obj\"".equals(properties[0])){
                codeRespBody.setCode(properties[1]);
            }
        }
        return codeRespBody;

    }
}
