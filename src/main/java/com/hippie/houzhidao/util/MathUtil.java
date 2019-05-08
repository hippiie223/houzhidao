package com.hippie.houzhidao.util;

import java.util.Random;

/**
 * @author 39239
 * @Date 2019/5/7 12:50
 * @Package com.hippie.houzhidaoadmin.util
 * @Description:
 */

public class MathUtil {
    public static String getRandomCode(){
        return String.valueOf(new Random().nextInt(89999) + 10000);
    }
}
