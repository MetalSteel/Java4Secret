package com.security.base64;/**
 * Created by wanggang on 2017/7/27.
 */

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Base64加解密
 * 应用场景：Base64加解密可以多次进行加解密来完成实际的加解密操作
 * @author wanggang
 * @version 1.0
 * @create 2017-07-27 22:45
 **/
public class Base64Demo {

    public static String src = "你好,Security";

    public static void main(String[] args) throws IOException {
        jdkBase64();
        System.out.println("==============================");
        commonsCodec();
    }

    /**
     * JDK方式实现Base64加解密
     * @throws IOException
     */
    public static void jdkBase64() throws IOException {
        // 编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode=>"+encode);
        // 解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(encode);
        String decode = new String(bytes);
        System.out.println("decode=>"+decode);
    }

    /**
     * commons-codec方式实现Base64加解密
     */
    public static void commonsCodec(){
        // 编码
        byte[] encodeBase64 = Base64.encodeBase64(src.getBytes());
        System.out.println("encode=>"+new String(encodeBase64));
        // 解码
        byte[] decodeBase64 = Base64.decodeBase64(encodeBase64);
        System.out.println("decode=>"+new String(decodeBase64));
    }
}
