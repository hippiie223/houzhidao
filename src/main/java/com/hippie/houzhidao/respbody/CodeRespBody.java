package com.hippie.houzhidao.respbody;

/**
 * @author 39239
 * @Date 2019/5/8 22:36
 * @Package com.hippie.houzhidao.respbody
 * @Description:
 */

public class CodeRespBody {
    private String status;
    private String msg;
    private String code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
