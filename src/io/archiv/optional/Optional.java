/*
 * Optional.java
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.optional;

/**
 *
 * @author Dusko
 * @param <Type> The type of the object which is nullable
 */
public class Optional<Type extends Object> {

    public static interface DidSet<Type extends Object> {
        
        public void run(Optional<Type> value);
    }
    
    public static interface IfLet<Type extends Object> {
        
        public void ifLet(Type value);
    }
    
    public static interface ForceUnwrap<Type extends Object> {
        
        public void forceUnwrap(Type value);
    }
    
    private Type value;
    
    private DidSet<Type> didSet;

    public Optional() {
        value = null;
    }

    public Optional(Type value) {
        this.value = value;
    }

    public void setValue(Type value) {
        this.value = value;
        
        if (didSet != null) {
            didSet.run(this);
        }
    }
    
    public void didSet(DidSet<Type> didSet) {
        this.didSet = didSet;
    }
    
    public void ifLet(IfLet<Type> ifLet) {
        if (value != null) {
            ifLet.ifLet(value);
        }
    }
    
    public void forceUnwrap(ForceUnwrap<Type> forceUnwrap) {
        forceUnwrap.forceUnwrap(value);
    }
    
    public static <T> Optional<T> nil() {
        return new Optional<>();
    }
}
