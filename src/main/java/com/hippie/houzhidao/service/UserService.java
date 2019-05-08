package com.hippie.houzhidao.service;

import com.hippie.houzhidao.domain.UserInfo;
import com.hippie.houzhidao.request.UpdateUserInfoRequestBody;
import com.hippie.houzhidao.respbody.UserInfoRespBody;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by reckywangbowen_i on 2019/03/04
 */
@Service
public interface UserService {
    void insertUser(UserInfo users);
    boolean login(String phone, String password);
    boolean updatePassword(String userName, String oldPassword, String newPassword);
    boolean isNameExist(String name);
    boolean userFocus(String userName, String focusUserName);
    UserInfoRespBody getUser(String userName);
    void updateUserInfo(UpdateUserInfoRequestBody requestBody);
    List<String> getFocusList(String userName, int pageNum, int pageSize);
    List<String> getFansList(String userName, int pageNum, int pageSize);
    boolean deleteFocus(String userName, String focusUserName);
    boolean deleteFans(String userName, String fansUserName);
    UserInfo getUserInfo(String userName);
    List<String> getUserRoles(String userName);
    String generateJwtToken(String userName);
    UserInfo getJwtTokenInfo(String userName);
    void deleteLoginInfo(String userName);

}
