package com.github.jmd.user

class User (fName: String, lName: String, uName: Int, pass: Int) { 
  var firstName: String
  var lastName: String
  var userName: Int
  var password: Int

  init { 
    firstName = fName
    lastName = lName
    userName = uName
    password = pass
  }
}
