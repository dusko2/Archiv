/*
 * FatalError
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.error;

/**
 *
 * @author Dusko
 */
public class FatalError extends RuntimeException {

    public FatalError() {
        super();
        
        System.exit(0);
    }
    
    public FatalError(String message) {
        super(message);
        
        System.exit(0);
    }
    
    public FatalError(String message, Throwable throwable) {
        super(message, throwable);
        
        System.exit(0);
    }
    
    public FatalError(Throwable throwable) {
        super(throwable);
        
        System.exit(0);
    }
}
