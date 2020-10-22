/*
 * ClientSessionDelegate.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.networking.client;

import io.archiv.networking.packet.Packet;

/**
 *
 * @author Dusko
 */
public interface ClientSessionDelegate {

    public void didReceivePacket(ClientSession session, Packet packet);
}
