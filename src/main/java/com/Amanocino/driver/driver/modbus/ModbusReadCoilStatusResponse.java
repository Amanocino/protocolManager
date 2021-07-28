package com.Amanocino.driver.driver.modbus;

public class ModbusReadCoilStatusResponse extends ModbusResponse {

    private int opCode = 0;
    private int payloadLength = 0;
    private byte[] body;

    public ModbusReadCoilStatusResponse(byte[] msg) {
        super(msg);
    }

    public void decode() {
        super.decode();

        opCode = readByte();
        payloadLength = readShort();
        body = readBytes(getLength()-9);

        System.out.println("ModbusReadCoilStatusResponse decode, payloadLength:" + payloadLength + ", actual length:" + body.length);
    }

    public int getOpCode() {
        return opCode;
    }

    public byte[] getBody() {
        return body;
    }
}
