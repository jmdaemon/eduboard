package com.github.jmd.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserAccountTests { 
  @Test 
  fun User_WhenInit_ReturnsUser() { 
    val user = User("Paul", "Allen", 112233, 123456)
    assertEquals("Paul", user.firstName)
    assertEquals("Allen", user.lastName)
    assertEquals(112233, user.userName)
    assertEquals(123456, user.password)
  }
}
