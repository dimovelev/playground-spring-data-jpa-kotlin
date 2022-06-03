package com.prime157.test

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.testcontainers.containers.MSSQLServerContainer
import org.testcontainers.utility.DockerImageName
import java.util.*


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.prime157.test"])
@EntityScan("com.prime157.test")
open class JpaTestConfiguration {
    val SQL_SERVER_IMG = DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest")

    @Bean(initMethod = "start", destroyMethod = "stop")
    open fun mssql(): MSSQLServerContainer<out MSSQLServerContainer<*>> {
        val res = MSSQLServerContainer(SQL_SERVER_IMG)
        res.acceptLicense()
        return res
    }

    @Bean(destroyMethod = "close")
    open fun datasource(mssql: MSSQLServerContainer<*>): HikariDataSource {
        val props = Properties()
        val config = HikariConfig(props)
        config.jdbcUrl = mssql.jdbcUrl
        config.username = mssql.username
        config.password = mssql.password
        return HikariDataSource(config)
    }
}