package com.Amanocino.driver.driver.modbus;


import com.Amanocino.driver.driver.NetMessage;

public class ModbusResponse extends NetMessage {

    public static int OP_READ_COILS_STATUS = 1;
    public static int OP_READ_INPUT_STATUS = 2;
    public static int OP_READ_HOLDING_REGISTER = 3;
    public static int OP_READ_INPUT_REGISTER = 4;
    public static int OP_WRITE_SINGLE_COIL = 5;
    public static int OP_WRITE_SINGLE_REGISTER = 6;
    public static int OP_WRITE_MULTIPLE_COIL = 15;
    public static int OP_WRITE_MULTIPLE_REGISTER = 16;

    protected ModbusMABPHeader modbusMABPHeader;
    protected ModbusResponseBody modbusResponseBody;

    public ModbusResponse(byte[] msg) {
        super(msg);
    }

    public void decode() {
//        modbusMABPHeader = readMABPHeader();
//
//        if(modbusMABPHeader.getOpCode() == OP_READ_COILS_STATUS) {
//            modbusResponseBody = new ModbusReadCoilStatusResponse();
//        }
//
//        modbusResponseBody.decode();
    }


    public ModbusMABPHeader readMABPHeader() {
        ModbusMABPHeader modbusMABPHeader = new ModbusMABPHeader();

        modbusMABPHeader.setSeqNo(readShort());
        modbusMABPHeader.setProtocol(readShort());
        modbusMABPHeader.setLength(readShort());
        modbusMABPHeader.setId(readByte());
        modbusMABPHeader.setOpCode(readByte());

        return modbusMABPHeader;
    }



}
