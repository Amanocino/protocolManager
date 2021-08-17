package com.Amanocino.driver.driver;

import com.Amanocino.driver.common.BaseConvertByte;
import com.Amanocino.driver.common.BeanConvertorUtil;
import com.Amanocino.driver.common.ByteObjectConvert;
import com.Amanocino.driver.driver.common.IScadaDriverTag;
import com.Amanocino.driver.driver.message.ModbusTcpEntity;
import com.Amanocino.driver.driver.modbus.CRCVerify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@IScadaDriverTag(id="modebusRtu")
public class ModbusRtuDriver implements IScadaDriver {
    public static final int OP_READ_COIL_STATUS = 1;
    public static final int OP_READ_INPUT_STATUS = 2;
    public static final int OP_READ_HOLDING_REGISTER = 3;
    public static final int OP_READ_INPUT_REGISTER = 4;
    public static final int OP_WRITE_SINGLE_COIL = 5;
    public static final int OP_WRITE_SINGLE_REGISTER = 6;
    public static final int OP_WRITE_MULTIPLE_COIL = 15;
    public static final int OP_WRITE_MULTIPLE_REGISTER = 16;


    private short PROTOCOL = 0;

    private static short seqNo = 0;

    @Override
    public byte[] encode(List<ScadaTag> tags) {
        List<byte[]> bytes = new ArrayList<>();
        int length = 0;
        for (ScadaTag tag:tags){
            ModbusTcpEntity modbusTcpEntity= new ModbusTcpEntity();
            try{
                BeanConvertorUtil.convertor(tag, modbusTcpEntity);
                modbusTcpEntity.setMessagelength((short) 6);
                byte[] bytesData = getByteData(tag);
                modbusTcpEntity.setDateList(bytesData);
                byte[] byteTmp = encode(modbusTcpEntity);
                bytes.add(byteTmp);
                length += byteTmp.length;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        byte[] byteTags = new byte[length];
        int offset = 0;
        for (byte[] byteTmp:bytes){
            int partLen = byteTmp.length;
            System.arraycopy(byteTmp, 0, byteTags, offset, partLen);
            offset += partLen;
        }

        return byteTags;

    }

    private byte[] getByteData(ScadaTag scadaTag){
        byte[] bytesData = null;
        switch (scadaTag.getOpCode()){
            case OP_READ_COIL_STATUS:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_READ_INPUT_STATUS:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_READ_HOLDING_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_READ_INPUT_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_WRITE_SINGLE_COIL:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_WRITE_SINGLE_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case OP_WRITE_MULTIPLE_COIL:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            default:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
        }

        return bytesData;
    }

    public byte[] MBAPMake(ModbusTcpEntity tag){

        seqNo += 1;
        byte[] bytes = ByteObjectConvert.ByteConnection(BaseConvertByte.byte2Bytes( tag.getDeviceId()), BaseConvertByte.byte2Bytes(tag.getFunctionCode()));
        return bytes;
    }

    public byte[] PDUMake(ModbusTcpEntity tag){
        byte[] buf =null;

        buf=ByteObjectConvert.ByteConnection(tag.getDateList());

        return buf;
    }

    public byte[] encode(ModbusTcpEntity tag){
        byte[] bufMBAP = MBAPMake(tag);
        byte[] bufPDU = PDUMake(tag);
        byte[] bufPre = ByteObjectConvert.ByteConnection(bufMBAP, bufPDU);

        return ByteObjectConvert.ByteConnection(bufPre, BaseConvertByte.byte2Bytes((byte)CRCVerify.getCRC(bufPre)));
    }

    @Override
    public List<ModbusTcpEntity> decode(byte[] message) {
        List<ModbusTcpEntity> scadaTags = new ArrayList<>();
        for (int i=0;i<message.length;){
            ModbusTcpEntity tag = new ModbusTcpEntity();
            tag.setDeviceId(Arrays.copyOf(message, i+1)[0]);
            tag.setFunctionCode(Arrays.copyOfRange(message, i+1, i+2)[0]);
            tag.setMessagelength(Arrays.copyOfRange(message, i+2, i+3)[0]);

            tag.setDateList(Arrays.copyOfRange(message, i+3, i+3+tag.getMessagelength()));
            byte[] crcCheck = BaseConvertByte.byte2Bytes((byte)CRCVerify.getCRC(Arrays.copyOfRange(message, i, i+3+tag.getMessagelength())));
            for (int var1=0;var1<crcCheck.length;var1++){
                if (crcCheck[var1]!=message[var1]){

                }
            }
            i+=5+tag.getMessagelength();
            scadaTags.add(tag);
        }


        return scadaTags;
    }

}
