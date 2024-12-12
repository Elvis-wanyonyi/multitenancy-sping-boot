package com.wolfcode.multitenancy.multitenancy;

import org.springframework.lang.Nullable;

@FunctionalInterface
public interface TenantResolver<T> {


    @Nullable
    String resolveTenantIdentifier(T source);

}

