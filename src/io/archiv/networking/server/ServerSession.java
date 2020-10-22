/*
 * ServerSession.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.server;

import io.archiv.error.FatalError;
import io.archiv.networking.client.ClientSession;
import io.archiv.optional.Optional;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusko
 */
public class ServerSession {

    private final ServerSocket serverSocket;
    private final ConnectedClients connectedClients = new ConnectedClients();
    
    private boolean running = false;

    public ServerSession(int port) {
        this.serverSocket = ServerSocketBuilder.create().withPort(port).build();
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
    }
    
    private void listen() {
        while (running) {
            accept().ifLet(socket -> {
                ClientSession session = new ClientSession(socket);
                session.start();
                
                connectedClients.add(session);
            });
        }
    }
    
    private Optional<Socket> accept() {
        try {
            return new Optional<>(serverSocket.accept());
        } catch (IOException ex) {
            Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.nil();
    }
}
