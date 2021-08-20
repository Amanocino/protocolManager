package driver.message;


import driver.common.BaseConvertByte;
import driver.common.ByteObjectConvert;
import driver.common.FileName;
import driver.driver.ScadaTag;
import driver.driver.convert.ProtocoConvert;
import driver.driver.message.Message;

/**
 * @author : zhicheng chen
 * @date : 2021/7/22
 * @time : 9:53
 */
public class ModbusTcpEntity implements Message, ProtocoConvert<ModbusTcpEntity> {

    private short messageId;
    @FileName(name = "opCode")
    private short protocolId;
    private short messagelength;
    @FileName(name = "length")
    private short length;
    @FileName(name = "slaveId")
    private byte deviceId;
    @FileName(name = "opCode")
    private byte functionCode;

    private byte[] dateList;

    public short getMessageId() {
        return messageId;
    }

    public void setMessageId(short messageId) {
        this.messageId = messageId;
    }

    public short getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(short protocolId) {
        this.protocolId = protocolId;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte deviceId) {
        this.deviceId = deviceId;
    }

    public byte getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(byte functionCode) {
        this.functionCode = functionCode;
    }

    public byte[] getDateList() {
        return dateList;
    }

    public void setDateList(byte[] dateList) {
        this.dateList = dateList;
    }

    public short getMessagelength() {
        return messagelength;
    }

    public void setMessagelength(short messagelength) {
        this.messagelength = messagelength;
    }

    @Override
    public ModbusTcpEntity convertDataFromTag(ScadaTag scadaTag) {
        byte[] bytesData = null;
        switch (scadaTag.getOpCode()){
            case FuncCodeResource.TCP_OP_READ_COIL_STATUS:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_READ_INPUT_STATUS:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_READ_HOLDING_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_READ_INPUT_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_WRITE_SINGLE_COIL:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_WRITE_SINGLE_REGISTER:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            case FuncCodeResource.TCP_OP_WRITE_MULTIPLE_COIL:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
            default:
                bytesData = ByteObjectConvert.ByteConnection(BaseConvertByte.short2Bytes((short)scadaTag.getAddr()), BaseConvertByte.short2Bytes((short) scadaTag.getLength()));
                break;
        }
        this.dateList = bytesData;
        return this;
    }

    @Override
    public ScadaTag reConvertDataFromTag(ModbusTcpEntity data) {
        ScadaTag scadaTag = new ScadaTag();
        scadaTag.setOpCode(data.getFunctionCode());
        scadaTag.setLength(data.getLength());
        scadaTag.setSlaveId(data.getDeviceId());

        return scadaTag;
    }
}
