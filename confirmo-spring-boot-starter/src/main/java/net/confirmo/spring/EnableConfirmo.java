package net.confirmo.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ConfirmoConfiguration.class)
public @interface EnableConfirmo {
}
