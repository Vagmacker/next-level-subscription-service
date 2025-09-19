package com.nextlevel.subscription.infrastructure.jdbc

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.jdbc.support.GeneratedKeyHolder
import java.sql.ResultSet

class JdbcClientAdapter(private val jdbcClient: JdbcClient) : DatabaseClient {
    override fun <T> queryOne(
        sql: String,
        params: Map<String, Any>,
        mapper: (ResultSet) -> T
    ): T? = jdbcClient.sql(sql).params(params).query(mapper)

    override fun <T> query(sql: String, mapper: (ResultSet) -> T): List<T> = listOf(jdbcClient.sql(sql).query(mapper))

    override fun <T> query(
        sql: String,
        params: Map<String, Any>,
        mapper: (ResultSet) -> T
    ): List<T> = listOf(jdbcClient.sql(sql).params(params).query(mapper))

    override fun update(sql: String, params: Map<String, Any>): Int {
        try {
            return jdbcClient.sql(sql).params(params).update()
        } catch (e: DataIntegrityViolationException) {
            throw e
        }
    }

    override fun insert(sql: String, params: Map<String, Any>): Number {
        try {
            val holder = GeneratedKeyHolder()
            jdbcClient.sql(sql).params(params).update(holder)
            return holder.key
        } catch (e: DataIntegrityViolationException) {
            throw e
        }
    }
}