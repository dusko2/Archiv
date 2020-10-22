/*
 * ClientSession.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.client;

import io.archiv.error.FatalError;
import io.archiv.networking.packet.Packet;
import io.archiv.networking.packet.PacketIO;
import io.archiv.optional.Optional;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Dusko
 */
public class ClientSession {

    private final Socket socket;
    private final PacketIO packetIO;
    
    private boolean running = false;
    
    public final Optional<ClientSessionDelegate> delegate = new Optional<>();
    
    public ClientSession(SocketAddress address) {
        socket = SocketBuilder.create().withIP(address.ip).withPort(address.port).build();
        packetIO = PacketIO.createForSocket(socket);
    }

    public ClientSession(Socket socket) {
        this.socket = socket;
        this.packetIO = PacketIO.createForSocket(socket);
    }
    
    public final void start() {
        if (running) {
            throw new FatalError("Already started");
        }
        
        running = true;
        new Thread(this::listen).start();
    }
    
    public final void stop() {
        if (!running) {
            throw new FatalError("Already stopped");
        }
        
        running = false;
        
        try {
            socket.close();
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
    
    private void listen() {
        while (running) {
            Packet packet = packetIO.read();
            delegate.ifLet(value -> value.didReceivePacket(this, packet));
        }
    }
    
    public final void sendPacket(Packet packet) {
        packetIO.write(packet);
    }
    
    public final boolean isRunning() {
        return running;
    }
}
