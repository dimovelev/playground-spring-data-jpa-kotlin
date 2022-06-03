package com.prime157.test

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@JpaTest
open class TaskRepositoryTest {
    @Autowired
    lateinit var testee: TaskRepository

    @Test
    fun save() {
        val task = Task(name = "hello")
        val saved = testee.saveAndFlush(task)
        println(saved.id)
    }
}