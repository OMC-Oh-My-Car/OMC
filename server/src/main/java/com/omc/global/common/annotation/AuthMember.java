package com.omc.global.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
// @AuthenticationPrincipal todo: Spring Security 적용 후 사용
public @interface AuthMember {
}
