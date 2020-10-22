/*
 * ConnectedClientsScannerService.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.server;

import io.archiv.concurrent.ScheduledService;
import io.archiv.networking.client.ClientSession;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 * @author Dusko
 */
public class ConnectedClientsScannerService extends ScheduledService {

    private class Constants {
        
        public static final int THREAD_COUNT = 1;
        public static final int DELAY = 100;
    }
    
    private final List<ClientSession> connectedClients;
    
    public ConnectedClientsScannerService(List<ClientSession> connectedClients) {
        super(Constants.THREAD_COUNT, Constants.DELAY, TimeUnit.MILLISECONDS);
        
        this.connectedClients = connectedClients;
    }

    @Override
    protected void execute() {
        List<ClientSession> disconnected = connectedClients.stream().filter(ClientSession::isRunning).collect(Collectors.toList());
        connectedClients.removeAll(disconnected);
    }
}
