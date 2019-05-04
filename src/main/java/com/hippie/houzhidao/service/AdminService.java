package com.hippie.houzhidao.service;

import com.hippie.houzhidao.domain.QiNiu;
import org.springframework.stereotype.Service;

/**
 * @author 39239
 * @Date 2019/4/26 10:46
 * @Package com.hippie.houzhidao.service
 * @Description:
 */
@Service
public interface AdminService {
    public QiNiu getToken();
}
