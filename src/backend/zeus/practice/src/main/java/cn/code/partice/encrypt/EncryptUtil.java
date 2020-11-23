//package cn.code.partice.encrypt;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import java.io.IOException;
//
//public class EncryptUtil {
//
//    public static String jdkBase64(String str) {
//        try {
//            //加密编码
//            BASE64Encoder encoder = new BASE64Encoder();
//            String encode = encoder.encode(str.getBytes());
//            System.out.println("jdk encoder:" + encode);
//            //解码
//            BASE64Decoder decoder = new BASE64Decoder();
//            String decode = new String(decoder.decodeBuffer(encode));
//            System.out.println("jdk decoder:" + decode);
//            return decode;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
