package com.security.message_digest.sha;/**
 * Created by wanggang on 2017/7/28.
 */

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA算法
 * 应用场景：
 * 1，消息判断，先把要发送的消息进行SHA加密发送，然后再发送原始消息，接收到以后对消息进行SHA加密并与刚开始接收的进行判断
 * @author wanggang
 * @version 1.0
 * @create 2017-07-28 11:30
 **/
public class SHADemo {

    public static String src = "Hello,你好";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        jdkSHA1();
        System.out.println("=================");
        commonsCodecSHA1();
    }

    /**
     * JDK实现SHA1
     * @throws NoSuchAlgorithmException
     */
    public static void jdkSHA1() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        byte[] digest = messageDigest.digest(src.getBytes());
        String encodeHexString = Hex.encodeHexString(digest);
        System.out.println("SHA1=>"+encodeHexString);
    }

    /**
     * commons-codec方式实现SHA1
     */
    public static void commonsCodecSHA1(){
        String sha1Hex = DigestUtils.sha1Hex(src.getBytes());
        System.out.println("SHA1=>"+sha1Hex);
    }
}
