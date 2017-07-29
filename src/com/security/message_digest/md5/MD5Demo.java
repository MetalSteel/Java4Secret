package com.security.message_digest.md5;/**
 * Created by wanggang on 2017/7/27.
 */

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * 应用场景：
 * 1，账户注册时，密码加密，用户登录时加密后与登录密码比较。
 * 2，校验信息完整，如文件的完整性
 * 3，游戏patch包的校验，病毒文件确认，app提审校验
 * @author wanggang
 * @version 1.0
 * @create 2017-07-27 23:12
 **/
public class MD5Demo {

    /**
     * 要加密字符串
     */
    public static String src = "123456";

    public static void main(String[] args) throws NoSuchAlgorithmException {

    }

    /**
     * JDK-MD5消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static void jdkMD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md.digest(src.getBytes());
        String encodeHexString = Hex.encodeHexString(md5Bytes);
        System.out.println("MD5=>"+encodeHexString);
    }

    /**
     * commons-codec MD5消息摘要算法
     */
    public static void commonsCodecMD5(){
        String md5Hex = DigestUtils.md5Hex(src.getBytes());
        System.out.println("MD5=>"+md5Hex);
    }

    /**
     * 对文件进行MD5
     * @param file
     */
    public static void fileMD5(File file){
        // 开始计算耗时
        long begin = System.currentTimeMillis();
        System.out.println("<===开始进行计算===>");
        // 缓冲区大小
        int bufferSize = 1024 * 1024;
        // 文件流
        FileInputStream fileInputStream = null;
        // 消息摘要流
        DigestInputStream digestInputStream = null;
        try {
            // 创建MD5摘要算法
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 初始化文件流
            fileInputStream = new FileInputStream(file);
            // 初始化消息摘要流
            digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
            // 创建缓冲区
            byte[] buffer = new byte[bufferSize];
            // 边读取流边对文件流进行MD5加密
            while (digestInputStream.read(buffer)>0);
            // 获取加密完成后新的消息摘要
            messageDigest = digestInputStream.getMessageDigest();
            // 获取结果
            byte[] digest = messageDigest.digest();
            // 把结果转换成字符串
            String result = Hex.encodeHexString(digest);
            // 结束计时
            long end = System.currentTimeMillis();
            // 计算耗时结果
            long sec = (end-begin)/1000;
            // 输出结果
            System.out.println("计算耗时=>"+sec+"秒,"+"result=>"+result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
