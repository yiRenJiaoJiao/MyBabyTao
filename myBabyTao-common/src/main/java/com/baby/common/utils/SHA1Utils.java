package com.baby.common.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017-06-02.
 */
public class SHA1Utils {

    /**
     *
     * @param pwd 密码
     * @return 返回SHA1加密后的密码
     */
    public static String pwdSHADispose(String pwd) {
        byte[] encodedPwdByte = null;
        String encodedPwd = null;
        try {
            MessageDigest alg = MessageDigest.getInstance("SHA-1");
            alg.update(pwd.getBytes());
            encodedPwdByte = alg.digest();
            encodedPwd = bytes2Hex(encodedPwdByte);
            return encodedPwd.toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
