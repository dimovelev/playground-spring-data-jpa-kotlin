package com.prime157.test

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@ContextConfiguration(classes = [JpaTestConfiguration::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(
    properties = [ //
        "spring.main.banner-mode=off",  //
        "spring.jpa.hibernate.connection.characterEncoding=UTF-8",  //
        "spring.jpa.hibernate.connection.charSet=UTF-8",  //
        "spring.jpa.hibernate.connection.useUnicode=true" //
    ]
)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
annotation class JpaTest()
