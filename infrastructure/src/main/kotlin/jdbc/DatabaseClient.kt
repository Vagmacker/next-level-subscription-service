package com.nextlevel.subscription.infrastructure.jdbc

import java.sql.ResultSet

interface DatabaseClient {

    fun <T> queryOne(sql: String, params: Map<String, Any>, mapper: (ResultSet) -> T): T?

    fun <T> query(sql: String, mapper: (ResultSet) -> T): List<T>

    fun <T> query(sql: String, params: Map<String, Any>, mapper: (ResultSet) -> T): List<T>

    fun update(sql: String, params: Map<String, Any>): Int

    fun insert(sql: String, params: Map<String, Any>): Number
}