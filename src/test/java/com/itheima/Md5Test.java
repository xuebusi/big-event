package com.itheima;

import com.itheima.utils.Md5Util;
import org.junit.jupiter.api.Test;

public class Md5Test {
    @Test
    public void testGetMD5String() {
        String md5String = Md5Util.getMD5String("123456");
        System.out.println(md5String);
    }
}
