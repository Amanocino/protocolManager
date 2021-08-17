package com.Amanocino.driver.driver;

import com.Amanocino.driver.common.BaseConvertByte;
import com.Amanocino.driver.common.BeanConvertorUtil;
import com.Amanocino.driver.common.ByteObjectConvert;
import com.Amanocino.driver.driver.common.IScadaDriverTag;
import com.Amanocino.driver.driver.message.ModbusTcpEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@IScadaDriverTag(id="modebusTcp")
public class ModbusTcpDriver implements IScadaDriver {

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
                modbusTcpEntity.convertDataFromTag(tag);
//                modbusTcpEntity.setDateList(bytesData);
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

    public byte[] MBAPMake(ModbusTcpEntity tag){
        seqNo += 1;
        byte[] bytes = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes(seqNo), BaseConvertByte.short2Bytes(PROTOCOL)
                , BaseConvertByte.short2Bytes(tag.getMessagelength()), BaseConvertByte.byte2Bytes( tag.getDeviceId()));
        return bytes;
    }

    public byte[] PDUMake(ModbusTcpEntity tag){
        byte[] buf =ByteObjectConvert.ByteConnection(BaseConvertByte.byte2Bytes(tag.getFunctionCode()), tag.getDateList());

        return buf;
    }

    public byte[] encode(ModbusTcpEntity tag){
        byte[] bufMBAP = MBAPMake(tag);
        byte[] bufPDU = PDUMake(tag);
        return ByteObjectConvert.ByteConnection(bufMBAP, bufPDU);
    }

    @Override
    public List<ModbusTcpEntity> decode(byte[] message) {
        List<ModbusTcpEntity> scadaTags = new ArrayList<>();
        for (int i=0;i<message.length;){
            ModbusTcpEntity tag = new ModbusTcpEntity();
            tag.setMessageId(BaseConvertByte.bytes2Short(Arrays.copyOf(message, i+2)));
            tag.setProtocolId(BaseConvertByte.bytes2Short(Arrays.copyOfRange(message, i+2, i+4)));
            tag.setMessagelength(BaseConvertByte.bytes2Short(Arrays.copyOfRange(message, i+4, i+6)));
            tag.setDeviceId(message[i+6]);
            tag.setFunctionCode(message[i+7]);
            tag.setDateList(Arrays.copyOfRange(message, i+8, i+8+tag.getMessagelength()-2));
            i+=6+tag.getMessagelength();
            scadaTags.add(tag);
        }


        return scadaTags;
    }

}
