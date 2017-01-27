package com.ices.yangengzhe.util.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.SocketUser;

/**
 * @date 2017年1月24日 上午8:46:58
 * @author yangengzhe
 */
public class Security {

    private static final String key = "ices-yangengzhe";

    public static String getPassword(Integer uid) {
        String password = uid + key;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            password = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static boolean authentication(Integer uid, String password) {
        String _password = uid + key;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(_password.getBytes());
            _password = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!_password.equals(password)) return false;
        else return true;
    }

    public static boolean isLogin(Integer uid) {
        SocketUser user = WebChatFactory.createManager().getUser(uid);
        if (user.getSession() != null) return true;
        else return false;
    }

    public static void main(String args[]) {
        System.out.println(getPassword(10001));
    }
}
