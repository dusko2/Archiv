/*
 * SocketBuilder.java
 * Archiv
 *
 * Created on Oct 10, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.client;

import io.archiv.error.FatalError;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Dusko Mirkovic
 */
public class SocketBuilder {

    private String ip = "";
    private int port = 0;
    
    private SocketBuilder() {
        
    }
    
    public SocketBuilder withIP(String ip) {
        this.ip = ip;
        
        return this;
    }
    
    public SocketBuilder withPort(int port) {
        this.port = port;
        
        return this;
    }
    
    public Socket build() {
        try {
            return new Socket(ip, port);
        } catch (IOException ex) {
            throw new FatalError(ex);
        }
    }
    
    public static SocketBuilder create() {
        return new SocketBuilder();
    }
}
