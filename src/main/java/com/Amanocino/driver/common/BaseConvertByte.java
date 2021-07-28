package com.Amanocino.driver.common;

/**
 * @author : zhicheng chen
 * @date : 2021/7/21
 * @time : 16:02
 */
public class BaseConvertByte {
    public static byte[] byte2Bytes(byte n) {
        byte[] bytes = new byte[1];
        bytes[0] = n;
        return bytes;
    }

    public static byte[] int2Bytes(int n, int byteLength) {
        //自己数组的长度就是我们的int在内存中占用的大小==
        int len = Integer.SIZE/8;
        byte[] bytes = new byte[len>byteLength?byteLength:len];
        for(int i=0; i<len && i<byteLength; i++ ) {
            //这里特别重要,主要目的是将我们的int数字的高位一次移动到字节数组中==
            //大家都知道int总共占用4个字节，转化到这里，就是将int的二进制码分段存储==
            //这里有个技巧 n*8 = n<<3
            bytes[i] = (byte) (n >> ((len-1-i)<<3));
        }
        return bytes;
    }

    public static byte[] short2Bytes(short n) {
        int len = Short.SIZE/8;
        byte[] bytes = new byte[len];
        for(int i=0; i<len; i++ ) {
            bytes[i] = (byte) (n >>> ((len-1-i)<<3));
        }
        return bytes;
    }
    public static byte[] char2Bytes(char n) {
        int len = Character.SIZE/8;
        byte[] bytes = new byte[len];
        for(int i=0; i<len; i++ ) {
            bytes[i] = (byte) (n >>> ((len-1-i)<<3));
        }
        return bytes;
    }

    public static byte[]  long2Bytes(long n) {
        int len = Long.SIZE/8;
        byte[] bytes = new byte[len];
        for(int i=0; i<len; i++ ) {
            bytes[i] = (byte) (n >>> ((len-1-i)<<3));
        }
        return bytes;
    }


    public static int bytes2Int(byte[] bytes) {
        int result=0;
        int len = bytes.length;
        //result = ((bytes[3] & 0xff)) | ((bytes[2] & 0xff) << 8)| ((bytes[1] & 0xff) << 16) | ((bytes[0])<<24);
        for(int i= len-1;i>=0; i--){
            result |= (i==0 ? bytes[i]:(bytes[i] & 0xff)) << ((len-1-i)<<3);
        }
        return result;
    }

    public static short bytes2Short(byte[] bytes) {
        short result=0;
        int len = bytes.length;
        for(int i=len-1;i>=0; i--){
            result |= (short)(i==0 ? bytes[i]:(bytes[i] & 0xff)) << ((len-1-i)<<3);
        }
        return result;
    }

    public static char bytes2Char(byte[] bytes) {
        char result=0;
        int len = bytes.length;
        for(int i=bytes.length-1;i>=0; i--){
            result |= (i==0 ? bytes[i]:(bytes[i] & 0xff)) << ((len-1-i)<<3);
        }
        return result;
    }
    public static long bytes2Long(byte[] bytes) {
        long result=0;
        int len = bytes.length;
        for(int i=len-1;i>=0; i--){
            result |= (long)(i==0 ? bytes[i]:(bytes[i] & 0xff)) << ((len-1-i)<<3);
        }
        return result;
    }
}
