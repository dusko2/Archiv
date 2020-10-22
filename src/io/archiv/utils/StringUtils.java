/*
 * StringUtils
 * Archiv
 *
 * Created on Oct 22, 2020
 * Copyright(c) Dusko Mirkovic, All Rights Reserved.
 *
 */

package io.archiv.utils;

/**
 *
 * @author Dusko
 */
public class StringUtils {

    public static int size(String string) {
        return Integer.BYTES + string.length();
    }
}
