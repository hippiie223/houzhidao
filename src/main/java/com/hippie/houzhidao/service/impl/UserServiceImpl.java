package com.hippie.houzhidao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hippie.houzhidao.domain.FocusAndFans;
import com.hippie.houzhidao.domain.UserInfo;
import com.hippie.houzhidao.domain.example.FocusAndFansExample;
import com.hippie.houzhidao.domain.example.UserInfoExample;
import com.hippie.houzhidao.mapper.ExtMapper;
import com.hippie.houzhidao.mapper.FocusAndFansMapper;
import com.hippie.houzhidao.mapper.UserInfoMapper;
import com.hippie.houzhidao.mapper.UsersMapper;
import com.hippie.houzhidao.request.UpdateUserInfoRequestBody;
import com.hippie.houzhidao.respbody.UserInfoRespBody;
import com.hippie.houzhidao.service.UserService;
import com.hippie.houzhidao.util.CheckSumBuilder;
import com.hippie.houzhidao.util.JwtUtils;
import com.hippie.houzhidao.util.MathUtil;
import com.hippie.houzhidao.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by reckywangbowen_i on 2019/03/04
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private FocusAndFansMapper focusAndFansMapper;

    @Autowired
    private ExtMapper extMapper;

    @Override
    public void insertUser(UserInfo users) {
        users.setCreateTime(TimeUtil.getCurrentTime());
        userInfoMapper.insertSelective(users);
    }

    @Override
    public boolean login(String phone, String password) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andPhoneEqualTo(phone);
        UserInfo users = userInfoMapper.selectByExample(userInfoExample).get(0);
        return CheckSumBuilder.getMD5(password).equals(users.getPassword());
    }

    @Override
    public boolean userFocus(String userName, String focusUserName) {
        FocusAndFans focusAndFans = new FocusAndFans();
        focusAndFans.setFocus(focusUserName);
        focusAndFans.setFans(userName);
        if(focusAndFansMapper.insertSelective(focusAndFans) != 1){
            return false;
        }
        return true;
    }

    @Override
    public boolean isNameExist(String name) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(name);
        if(userInfoMapper.countByExample(userInfoExample) != 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(String userName, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void updateUserInfo(UpdateUserInfoRequestBody requestBody) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(requestBody.getOldUserName());

        UserInfo userInfo = new UserInfo();
        if(requestBody.getUserName() != null){
            userInfo.setUserName(requestBody.getUserName());
            FocusAndFansExample focusAndFansExample = new FocusAndFansExample();
            focusAndFansExample.createCriteria().andFocusEqualTo(requestBody.getOldUserName());
            FocusAndFans focusAndFans = new FocusAndFans();
            focusAndFans.setFocus(requestBody.getUserName());
            focusAndFansMapper.updateByExampleSelective(focusAndFans, focusAndFansExample);

            focusAndFansExample.clear();
            focusAndFansExample.createCriteria().andFansEqualTo(requestBody.getOldUserName());
            focusAndFans.setFocus("");
            focusAndFans.setFans(requestBody.getUserName());
            focusAndFansMapper.updateByExampleSelective(focusAndFans, focusAndFansExample);
        }
        if(requestBody.getAddress() != null){
            userInfo.setAddress(requestBody.getAddress());
        }
        if(requestBody.getEmail() != null){
            userInfo.setEmail(requestBody.getEmail());
        }
        if(requestBody.getGender() != null){
            userInfo.setGender(requestBody.getGender());
        }
        if(requestBody.getJob() != null){
            userInfo.setJob(requestBody.getJob());
        }
        if(requestBody.getOrganize() != null){
            userInfo.setOrganize(requestBody.getOrganize());
        }
        if(requestBody.getSignature() != null){
            userInfo.setSignature(requestBody.getSignature());
        }

        userInfoMapper.updateByExampleSelective(userInfo, userInfoExample);
    }

    @Override
    public List<String> getFocusList(String userName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return focusAndFansMapper.getFocusList(userName);
    }

    @Override
    public List<String> getFansList(String userName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return focusAndFansMapper.getFansList(userName);
    }

    @Override
    public UserInfoRespBody getUser(String userName) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(userName);
        UserInfo userInfo = userInfoMapper.selectByExample(userInfoExample).get(0);

        FocusAndFansExample focusAndFansExample = new FocusAndFansExample();
        focusAndFansExample.createCriteria().andFansEqualTo(userName);
        int focusNum = focusAndFansMapper.countByExample(focusAndFansExample);
        focusAndFansExample.clear();
        focusAndFansExample.createCriteria().andFocusEqualTo(userName);
        int fansNum = focusAndFansMapper.countByExample(focusAndFansExample);



        UserInfoRespBody userInfoRespBody = new UserInfoRespBody();
        userInfoRespBody.setUserName(userInfo.getUserName());
        userInfoRespBody.setPhone(userInfo.getPhone());
        userInfoRespBody.setAddress(userInfo.getAddress());
        userInfoRespBody.setEmail(userInfo.getEmail());
        userInfoRespBody.setGender(userInfo.getGender());
        userInfoRespBody.setJob(userInfo.getJob());
        userInfoRespBody.setOrganize(userInfo.getOrganize());
        userInfoRespBody.setSignature(userInfo.getSignature());
        userInfoRespBody.setFocusNum(focusNum);
        userInfoRespBody.setFansNum(fansNum);

        return userInfoRespBody;
    }

    @Override
    public boolean deleteFocus(String userName, String focusUserName) {
        FocusAndFansExample example = new FocusAndFansExample();
        example.createCriteria().andFansEqualTo(userName).andFocusEqualTo(focusUserName);
        return focusAndFansMapper.deleteByExample(example) == 1;

    }

    @Override
    public boolean deleteFans(String userName, String fansUserName) {
        FocusAndFansExample example = new FocusAndFansExample();
        example.createCriteria().andFocusEqualTo(userName).andFansEqualTo(fansUserName);
        return focusAndFansMapper.deleteByExample(example) == 1;
    }

    @Override
    public UserInfo getUserInfo(String userName) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(userName);
        return userInfoMapper.selectByExample(userInfoExample).get(0);
    }

    @Override
    public List<String> getUserRoles(String userName) {
        return extMapper.getUserRoles(userName);
    }

    @Override
    public String generateJwtToken(String userName) {
        String salt = MathUtil.getRandomCode();

        //将salt存入数据库
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(userName);
        UserInfo userInfo = new UserInfo();
        userInfo.setSalt(salt);
        userInfoMapper.updateByExampleSelective(userInfo, userInfoExample);
        return JwtUtils.sign(userName, salt, 1800);
    }

    @Override
    public UserInfo getJwtTokenInfo(String userName) {
        return getUserInfo(userName);
    }

    @Override
    public void deleteLoginInfo(String userName) {
//        UserInfoExample example = new UserInfoExample();
//        example.createCriteria().andUserNameEqualTo(userName);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setSalt("");
//        userInfoMapper.updateByExampleSelective(userInfo, example);
        extMapper.updateUserSalt(userName);
    }

    @Override
    public String getPasswordByName(String userNanme) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(userNanme);
        return userInfoMapper.selectByExample(userInfoExample).get(0).getPassword();
    }
}
