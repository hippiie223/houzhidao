package com.hippie.houzhidao.controller;

import com.hippie.houzhidao.domain.UserInfo;
import com.hippie.houzhidao.message.SendCode;
import com.hippie.houzhidao.request.InsertUserRequestBody;
import com.hippie.houzhidao.request.LoginRequestBody;
import com.hippie.houzhidao.request.UpdateUserInfoRequestBody;
import com.hippie.houzhidao.respbody.CodeRespBody;
import com.hippie.houzhidao.respbody.RootRespBody;
import com.hippie.houzhidao.respbody.UserInfoRespBody;
import com.hippie.houzhidao.service.UserService;
import com.hippie.houzhidao.util.CheckSumBuilder;
import com.hippie.houzhidao.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by reckywangbowen_i on 2019/03/04
 */
@Api("用户相关接口")
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(path = "/code")
    @ApiOperation("获取验证码")
    public RootRespBody getCode(@RequestParam String phone){
        String response = null;

        try {
            response = SendCode.send(phone);
        } catch (Exception e) {
            logger.error("发送验证码错误!");
        }
        CodeRespBody codeRespBody = StringUtil.getProperties(response);
        if("200".equals(codeRespBody.getStatus())){
            return RootRespBody.success(codeRespBody.getCode());
        }else {
            return RootRespBody.failure(RootRespBody.Status.INVALID_PARAM,codeRespBody.getMsg());
        }
    }

    @PostMapping(path = "/insert")
    @ApiOperation("新建用户")
    public RootRespBody insert(@RequestBody InsertUserRequestBody insertUserRequestBody){
        if(!insertUserRequestBody.getCode().equals(insertUserRequestBody.getInputCode())){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "验证码输入错误!");
        }

        if("".equals(insertUserRequestBody.getPhone()) || insertUserRequestBody.getPhone() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "手机号不能为空！");
        }

        if("".equals(insertUserRequestBody.getPassword()) || insertUserRequestBody.getPassword() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "密码不能为空！");
        }


        UserInfo users = new UserInfo();
        String password = CheckSumBuilder.getMD5(insertUserRequestBody.getPassword());
        users.setPhone(insertUserRequestBody.getPhone());
        if(insertUserRequestBody.getUsername() != null){
            users.setUserName(insertUserRequestBody.getUsername());
        }
        users.setPassword(password);
        try {
            userService.insertUser(users);
        }catch (Exception e){
            return RootRespBody.failure(RootRespBody.Status.INSERT_RECORD_ERROR,"手机号已注册");
        }
        return RootRespBody.success();
    }

    @PostMapping(path = "/login")
    @ApiOperation("用户登录")
    public RootRespBody<String> login(@RequestBody LoginRequestBody requestBody){
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(requestBody.getUserName(), CheckSumBuilder.getMD5(requestBody.getPassword()));
            subject.login(token);

            UserInfo userInfo = (UserInfo)subject.getPrincipal();
            String newToken = userService.generateJwtToken(userInfo.getUserName());
//            response.setHeader("x-token", newToken);
            return RootRespBody.success(newToken);
        } catch (AuthenticationException e){
            return RootRespBody.failure(RootRespBody.Status.BAD_REQUEST,"密码错误");
        } catch (Exception e){
            return RootRespBody.failure(RootRespBody.Status.PERMISSION_DENIED_ERROR, e.toString());
        }
    }

    @PostMapping(path = "/login/code")
    @ApiOperation("根据验证码登录")
    public RootRespBody<String> loginByCode(@RequestBody LoginRequestBody requestBody){
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(requestBody.getUserName(), userService.getPasswordByName(requestBody.getUserName()));
            subject.login(token);

            UserInfo userInfo = (UserInfo)subject.getPrincipal();
            String newToken = userService.generateJwtToken(userInfo.getUserName());
//            response.setHeader("x-token", newToken);
            return RootRespBody.success(newToken);
        } catch (AuthenticationException e){
            return RootRespBody.failure(RootRespBody.Status.BAD_REQUEST,"密码错误");
        } catch (Exception e){
            return RootRespBody.failure(RootRespBody.Status.PERMISSION_DENIED_ERROR, e.toString());
        }

    }

    @PostMapping(path = "/logout")
    @ApiOperation("登出")
    public RootRespBody<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null){
            UserInfo userInfo = (UserInfo) subject.getPrincipals().getPrimaryPrincipal();
            userService.deleteLoginInfo(userInfo.getUserName());
        }
        SecurityUtils.getSubject().logout();
        return RootRespBody.success();
    }

    @GetMapping(path = "/is/username/exist")
    @ApiOperation("用户名是否存在")
    public RootRespBody isUserNameExist(@RequestParam String userName){
        if(userService.isNameExist(userName)){
            return RootRespBody.success(true);
        }else {
            return RootRespBody.success(false);
        }
    }

    @PostMapping(path = "/focus")
    @ApiOperation("关注用户")
    public RootRespBody focus(@RequestParam String userName, @RequestParam String focusUserName){
        if(userService.userFocus(userName, focusUserName)){
            return RootRespBody.success("关注成功!");
        }else {
            return RootRespBody.success("关注失败!");
        }
    }

    @GetMapping(path = "/get/user/info")
    @ApiOperation("获取用户个人信息")
    public RootRespBody<UserInfoRespBody> getUserInfo(@RequestParam String userName){
        return RootRespBody.success(userService.getUser(userName));
    }

    @PostMapping(path = "edit/user/info")
    @ApiOperation("编辑用户信息")
    public RootRespBody editUserInfo(@RequestBody UpdateUserInfoRequestBody requestBody){
        userService.updateUserInfo(requestBody);
        return RootRespBody.success();
    }

    @GetMapping(path = "/list/focus")
    @ApiOperation("获取关注列表")
    public RootRespBody<List<String>> getFocusList(@RequestParam String userName, @RequestParam int pageNum, @RequestParam int pageSize){
        return RootRespBody.success(userService.getFocusList(userName, pageNum, pageSize));
    }

    @GetMapping(path = "/list/fans")
    @ApiOperation("获取粉丝列表")
    public RootRespBody<List<String>> getFansList(@RequestParam String userName, @RequestParam int pageNum, @RequestParam int pageSize){
        return RootRespBody.success(userService.getFansList(userName, pageNum, pageSize));
    }

    @PostMapping(path = "/delete/focus")
    @ApiOperation("取消关注")
    public RootRespBody deleteFocus(@RequestParam String userName, @RequestParam String focusUserName){
        return RootRespBody.success(userService.deleteFocus(userName, focusUserName));
    }

    @PostMapping(path = "/delete/fans")
    @ApiOperation("移除粉丝")
    public RootRespBody deleteFans(@RequestParam String userName, @RequestParam String fansUserName){
        return RootRespBody.success(userService.deleteFans(userName, fansUserName));
    }



}
