/*
 * ScheduledService.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Dusko
 */
public abstract class ScheduledService {

    private final ScheduledExecutorService scheduler;
    private final long delay;
    private final TimeUnit timeUnit;
    
    private boolean running = false;

    public ScheduledService(int threadCount, long delay, TimeUnit timeUnit) {
        scheduler = Executors.newScheduledThreadPool(threadCount);
        
        this.delay = delay;
        this.timeUnit = timeUnit;
    }
    
    public final void start() {
        if (running) {
            throw new RuntimeException("Already started");
        }
        
        running = true;
        scheduler.scheduleAtFixedRate(this::execute, 0, delay, timeUnit);
    }
    
    public final void stop() {
        if (!running) {
            throw new RuntimeException("Already stopped");
        }
        
        running = false;
        scheduler.shutdown();
    }
    
    protected abstract void execute();
}
