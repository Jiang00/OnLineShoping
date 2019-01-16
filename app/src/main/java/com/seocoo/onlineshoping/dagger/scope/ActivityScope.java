package com.seocoo.onlineshoping.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Documented
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
