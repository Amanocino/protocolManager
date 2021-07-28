package com.Amanocino.driver.common;

/**
 * @author : zhicheng chen
 * @date : 2021/7/21
 * @time : 16:02
 */
public class BaseConvertByteBack {
    public static byte[] byte2Bytes(byte n) {
        byte[] bytes = new byte[1];
        bytes[0] = n;
        return bytes;
    }

    public static byte[] int2Bytes(int n, boolean isBig) {
        byte[] b = new byte[4];
        if (isBig){
            b[3] = (byte) (n & 0xff);
            b[2] = (byte) (n >> 8 & 0xff);
            b[1] = (byte) (n >> 16 & 0xff);
            b[0] = (byte) (n >> 24 & 0xff);
        }else {
            b[0] = (byte) (n & 0xff);
            b[1] = (byte) (n >> 8 & 0xff);
            b[2] = (byte) (n >> 16 & 0xff);
            b[3] = (byte) (n >> 24 & 0xff);
        }

        return b;
    }

    public static byte[] short2Bytes(short n, boolean isBig) {
        byte[] b = new byte[2];
        if (isBig){
            b[1] = (byte) (n & 0xff);
            b[0] = (byte) (n >> 8 & 0xff);
        }else {
            b[0] = (byte) (n & 0xff);
            b[1] = (byte) (n >> 8 & 0xff);
        }

        return b;
    }
    public static byte[] char2Bytes(char n, boolean isBig) {
        byte[] b = new byte[2];
        if (isBig){
            b[1] = (byte) (n & 0xff);
            b[0] = (byte) (n >> 8 & 0xff);
        }else {
            b[0] = (byte) (n & 0xff);
            b[1] = (byte) (n >> 8 & 0xff);
        }

        return b;
    }

    public static byte[]  long2Bytes(long n, boolean isBig) {
        byte[] b = new byte[8];
        if (isBig){
            b[7] = (byte) (n & 0xff);
            b[6] = (byte) (n >> 8  & 0xff);
            b[5] = (byte) (n >> 16 & 0xff);
            b[4] = (byte) (n >> 24 & 0xff);
            b[3] = (byte) (n >> 32 & 0xff);
            b[2] = (byte) (n >> 40 & 0xff);
            b[1] = (byte) (n >> 48 & 0xff);
            b[0] = (byte) (n >> 56 & 0xff);
        }else {
            b[0] = (byte) (n & 0xff);
            b[1] = (byte) (n >> 8  & 0xff);
            b[2] = (byte) (n >> 16 & 0xff);
            b[3] = (byte) (n >> 24 & 0xff);
            b[4] = (byte) (n >> 32 & 0xff);
            b[5] = (byte) (n >> 40 & 0xff);
            b[6] = (byte) (n >> 48 & 0xff);
            b[7] = (byte) (n >> 56 & 0xff);
        }
        return b;
    }


    public static int bytes2Int(byte[] bytes, boolean isBig) {
        if (isBig){
            int int1=bytes[3]&0xff;
            int int2=(bytes[2]&0xff)<<8;
            int int3=(bytes[1]&0xff)<<16;
            int int4=(bytes[0]&0xff)<<24;
            return int1|int2|int3|int4;
        }else {
            int int1=bytes[0]&0xff;
            int int2=(bytes[1]&0xff)<<8;
            int int3=(bytes[2]&0xff)<<16;
            int int4=(bytes[3]&0xff)<<24;
            return int1|int2|int3|int4;
        }

    }

    public static short bytes2Short(byte[] bytes, boolean isBig) {
        if (isBig){
            return (short) (((bytes[0] << 8) | bytes[1] & 0xff));
        }else {
            return (short) (((bytes[1] << 8) | bytes[0] & 0xff));
        }
    }

    public static char bytes2Char(byte[] bytes, boolean isBig) {
        if (isBig){
            return (char) (((bytes[0] << 8) | bytes[1] & 0xff));
        }else {
            return (char) (((bytes[1] << 8) | bytes[0] & 0xff));
        }
    }
    public static long bytes2Long(byte[] bytes, boolean isBig) {
        if (isBig){
            return ((((long) bytes[ 0] & 0xff) << 56)
                    | (((long) bytes[ 1] & 0xff) << 48)
                    | (((long) bytes[ 2] & 0xff) << 40)
                    | (((long) bytes[ 3] & 0xff) << 32)
                    | (((long) bytes[ 4] & 0xff) << 24)
                    | (((long) bytes[ 5] & 0xff) << 16)
                    | (((long) bytes[ 6] & 0xff) << 8)
                    | (((long) bytes[ 7] & 0xff) << 0));
        }else {
            return ((((long) bytes[ 0] & 0xff) << 0)
                    | (((long) bytes[ 1] & 0xff) << 8)
                    | (((long) bytes[ 2] & 0xff) << 16)
                    | (((long) bytes[ 3] & 0xff) << 24)
                    | (((long) bytes[ 4] & 0xff) << 32)
                    | (((long) bytes[ 5] & 0xff) << 40)
                    | (((long) bytes[ 6] & 0xff) << 48)
                    | (((long) bytes[ 7] & 0xff) << 56));
        }
    }
}
