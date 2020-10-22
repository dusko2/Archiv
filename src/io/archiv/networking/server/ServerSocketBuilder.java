/*
 * ServerSocketBuilder.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.server;

import io.archiv.error.FatalError;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Dusko
 */
public class ServerSocketBuilder {

    private int port = 0;
    
    private ServerSocketBuilder() {
        
    }
    
    public ServerSocketBuilder withPort(int port) {
        this.port = port;
        
        return this;
    }
    
    public ServerSocket build() {
        try {
            return new ServerSocket(port);
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
    
    public static ServerSocketBuilder create() {
        return new ServerSocketBuilder();
    }
}
