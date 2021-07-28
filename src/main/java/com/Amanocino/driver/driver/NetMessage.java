package com.Amanocino.driver.driver;

public class NetMessage {

    protected byte[] message;
    protected int msgLen;

    protected int offset = 0;

    public NetMessage(byte[] msg) {
        this.message = msg;
        this.msgLen = msg.length;
    }

    public int getLength() {
        return this.msgLen;
    }

    public int readByte() {
        return message[offset++];
    }

    public int readShort() {
        int H = readByte();
        int L = readByte();

        return (L<<8) | H;
    }

    public int readInt() {
        int c1 = readByte();
        int c2 = readByte();
        int c3 = readByte();
        int c4 = readByte();

        return (c4 << 24) | (c3 << 16) | (c2 << 8) | c1;
    }

    public byte[] readBytes(int length) {
        byte[] v = new byte[length];

        for(int i=0; i<length; i++) {
            v[i] = message[offset++];
        }

        return v;
    }

    public int getByte(int position) {
        return message[position];
    }

    public int getShort(int position) {
        int H = getByte(position);
        int L = getByte(position+1);

        return (L<<8) | H;
    }

    public int getInt(int position) {
        int c1 = getByte(position);
        int c2 = getByte(position+1);
        int c3 = getByte(position+2);
        int c4 = getByte(position+3);

        return (c4<<24) | (c3<<16) | (c2<<8) | c1;
    }

    public byte[] getBytes(int position, int length) {
        byte[] v = new byte[length];

        for(int i=0; i<length; i++) {
            v[i] = message[position+i];
        }

        return v;
    }
}
