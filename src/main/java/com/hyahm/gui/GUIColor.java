package com.hyahm.gui;

import java.nio.ByteBuffer;

public class GUIColor {
    private byte a;
    private byte r;
    private byte g;
    private byte b;

    public GUIColor(int color) {
        this.fromInt(color);
    }

    public GUIColor() {
        a = r = g = b = 0;
    }

    public void fromInt(int color) {
        byte[] ba = ByteBuffer.allocate(4).putInt(color).array();
        a = ba[0];
        r = ba[1];
        g = ba[2];
        b = ba[3];
    }

    public int toInt() {
        return ByteBuffer.wrap(new byte[]{a, r, g, b}).getInt();
    }

    public static int toInt(byte a, byte r ,byte g ,byte b) {
        return ByteBuffer.wrap(new byte[]{a, r, g, b}).getInt();
    }

    public byte getA() {
        return a;
    }

    public byte getB() {
        return b;
    }

    public byte getG() {
        return g;
    }

    public byte getR() {
        return r;
    }

    public void setA(byte a) {
        this.a = a;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public void setG(byte g) {
        this.g = g;
    }

    public void setR(byte r) {
        this.r = r;
    }
}