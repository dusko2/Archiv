/*
 * Packet.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.packet;

/**
 *
 * @author Dusko
 */
public class Packet {

    public final long timestamp = System.nanoTime();
    
    public final PacketHeader header;
    public final DataBuffer body;

    public Packet(int id, int size) {
        header = new PacketHeader(id, size);
        body = new DataBuffer(size);
    }

    public Packet(PacketHeader header, byte[] bodyBytes) {
        this.header = header;
        this.body = new DataBuffer(bodyBytes);
    }
    
    public final byte[] data() {
        DataBuffer buffer = new DataBuffer(PacketHeader.BYTES + header.size);
        
        buffer.put(header.id);
        buffer.put(header.size);
        buffer.put(body.data());
        
        return buffer.data();
    }
}
