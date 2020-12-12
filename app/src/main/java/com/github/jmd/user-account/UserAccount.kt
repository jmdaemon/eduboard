package com.github.jmd.user

class User (firstName: String, lastName: String, userName: Int, password: Int) { 

  var firstName: String = ""
  var lastName: String = ""
  var userName: Int = 0
  var password: Int = 0
    get() = field
    set(value) { field = value }
}
