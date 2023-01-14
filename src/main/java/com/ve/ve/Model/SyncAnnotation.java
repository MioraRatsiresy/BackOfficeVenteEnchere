package com.vehicule.vehicule.Model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SyncAnnotation {
    public boolean sync();
}
