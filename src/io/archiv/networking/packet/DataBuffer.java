/*
 * DataBuffer.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.packet;

import io.archiv.error.FatalError;
import java.util.Arrays;

/**
 *
 * @author Dusko
 */
public class DataBuffer {

    private final byte[] buffer;
    private int position = 0;

    public DataBuffer(int size) {
        buffer = new byte[size];
    }

    public DataBuffer(byte[] buffer) {
        this.buffer = buffer;
    }
    
    public void put(byte value) {
        if (position >= buffer.length) {
            throw new FatalError("Packet body: buffer overflow");
        }
        
        buffer[position] = value;
        
        position++;
    }
    
    public void put(byte[] values) {
        for (byte value : values) {
            put(value);
        }
    }
    
    public void put(char value) {
        put((byte)value);
    }
    
    public void put(short value) {
        put((byte)(value >> 8));
        put((byte)(value));
    }
    
    public void put(int value) {
        put((byte)(value >> 24));
        put((byte)(value >> 16));
        put((byte)(value >> 8));
        put((byte)(value));
    }
    
    public void put(long value) {
        put((byte)(value >> 56));
        put((byte)(value >> 48));
        put((byte)(value >> 40));
        put((byte)(value >> 32));
        put((byte)(value >> 24));
        put((byte)(value >> 16));
        put((byte)(value >> 8));
        put((byte)(value));
    }
    
    public void put(String value) {
        put(value.length());
        put(value.getBytes());
    }
    
    public byte getByte() {
        if (position >= buffer.length) {
            throw new FatalError("Packet body: buffer underflow");
        }
        
        byte value = buffer[position];
        position++;
        
        return value;
    }
    
    public void getBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = getByte();
        }
    }
    
    public char getChar() {
        return (char)getByte();
    }
    
    public short getShort() {
        return (short)((getByte() & 0xFF) << 8 |
                       (getByte() & 0xFF));
    }
    
    public int getInt() {
        return (int)(getByte() & 0xFF) << 24 |
                    (getByte() & 0xFF) << 16 |
                    (getByte() & 0xFF) << 8  |
                    (getByte() & 0xFF);
    }
    
    public long getLong() {
        return (long)(getByte() & 0xFF) << 56 |
               (long)(getByte() & 0xFF) << 48 |
               (long)(getByte() & 0xFF) << 40 |
               (long)(getByte() & 0xFF) << 32 |
                     (getByte() & 0xFF) << 24 |
                     (getByte() & 0xFF) << 16 |
                     (getByte() & 0xFF) << 8  |
                     (getByte() & 0xFF);
    }
    
    public String getString() {
        int length = getInt();
        
        byte[] stringBytes = new byte[length];
        getBytes(stringBytes);
        
        return new String(stringBytes);
    }
    
    public void reset() {
        position = 0;
    }
    
    public byte[] data() {
        return Arrays.copyOf(buffer, buffer.length);
    }
}
