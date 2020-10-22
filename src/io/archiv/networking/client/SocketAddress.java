/*
 * SocketAddress.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.client;

/**
 *
 * @author Dusko
 */
public class SocketAddress {

    public final String ip;
    public final int port;

    public SocketAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
