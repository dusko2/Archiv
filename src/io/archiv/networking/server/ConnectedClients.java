/*
 * ConnectedClients.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.server;

import io.archiv.networking.client.ClientSession;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Dusko
 */
public class ConnectedClients {

    private final List<ClientSession> list = new LinkedList<>();
    
    private final ConnectedClientsScannerService scannerService = new ConnectedClientsScannerService(list);
    
    protected void add(ClientSession session) {
        list.add(session);
    }
    
    public final void forEach(Consumer<ClientSession> consumer) {
        list.forEach(consumer);
    }
    
    protected void onServerStart() {
        scannerService.start();
    }
    
    protected void onServerStop() {
        scannerService.stop();
    }
}
