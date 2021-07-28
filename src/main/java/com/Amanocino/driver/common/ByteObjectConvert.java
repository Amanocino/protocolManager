package com.Amanocino.driver.common;

/**
 * @author : zhicheng chen
 * @date : 2021/7/21
 * @time : 14:33
 */
public class ByteObjectConvert {
    public void ByteSerialize(Object... objects){
        int length = 0;
        for (Object object:objects){
            if (object.getClass() == int.class){
                int value= (int) object;
                byte[] bytes = BaseConvertByte.int2Bytes(value, 4);
                length += 4;
            }else if (object.getClass() == short.class){
                short value= (short) object;
                byte[] bytes = BaseConvertByte.short2Bytes(value);
                length += 4;
            }else if (object.getClass() == byte[].class){
                short value= (short) object;
                byte[] bytes = BaseConvertByte.short2Bytes(value);
                length += 4;
            }
        }
    }

    public static byte[] ByteConnection(byte[]... values){
        int lengthByte = 0;
        for (byte[] value : values) {
            if (null!=value){
                lengthByte += value.length;
            }

        }
        byte[] allBytes = new byte[lengthByte];
        int countLength = 0;
        for (byte[] b : values) {
            if (null!=b){
                System.arraycopy(b, 0, allBytes, countLength, b.length);
                countLength += b.length;
            }

        }
        return allBytes;
    }
}
