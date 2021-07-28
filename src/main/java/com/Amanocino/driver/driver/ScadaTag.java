package com.Amanocino.driver.driver;

public class ScadaTag {

    protected int slaveId;

    protected int opCode;

    protected int name;

    protected int type;

    protected int addr;

    protected int length;


    protected Object value;

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public int getOpCode() {
        return opCode;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public int getAddr() {
        return addr;
    }



    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
