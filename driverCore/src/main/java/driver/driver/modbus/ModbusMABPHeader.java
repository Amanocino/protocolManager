package driver.driver.modbus;

public class ModbusMABPHeader {

    public int seqNo;

    public int protocol;

    public int length;

    public int id;

    public int opCode;

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public int getSeqNo() {
        return this.seqNo;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getProtocol() {
        return this.protocol;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public int getOpCode() {
        return this.opCode;
    }
}
