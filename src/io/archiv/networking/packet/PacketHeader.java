/*
 * PacketHeader.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.packet;

import java.nio.ByteBuffer;

/**
 *
 * @author Dusko
 */
public class PacketHeader {

    public static final int BYTES = Integer.BYTES + Integer.BYTES;
    
    public final int id;
    public final int size;

    public PacketHeader(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public PacketHeader(byte[] headerBytes) {
        ByteBuffer buffer = ByteBuffer.wrap(headerBytes);
        
        id = buffer.getInt();
        size = buffer.getInt();
    }
}
