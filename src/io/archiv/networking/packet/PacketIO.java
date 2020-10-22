/*
 * PacketIO.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.packet;

import io.archiv.error.FatalError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Dusko
 */
public class PacketIO {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public PacketIO(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
    
    public final Packet read() {
        byte[] headerBytes = new byte[PacketHeader.BYTES];
        read(headerBytes);
        
        PacketHeader header = new PacketHeader(headerBytes);
        
        byte[] bodyBytes = new byte[header.size];
        read(bodyBytes);
        
        return new Packet(header, bodyBytes);
    }
    
    private void read(byte[] bytes) {
        try {
            inputStream.read(bytes);
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
    
    public final void write(Packet packet) {
        try {
            outputStream.write(packet.data());
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
    
    public static PacketIO createForSocket(Socket socket) {
        try {
            return new PacketIO(socket.getInputStream(), socket.getOutputStream());
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
}
