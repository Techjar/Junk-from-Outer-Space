/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacejunk.util;

import java.io.*;

/**
 *
 * @author Techjar
 */
public class StackTrace {
    public static String stackTraceToString(Throwable throwable) {
        final Writer stackTrace = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stackTrace);
        throwable.printStackTrace(printWriter);
        return stackTrace.toString();
    }
}
