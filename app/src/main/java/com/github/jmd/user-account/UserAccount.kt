package com.github.jmd.user

//class User (firstName: String, lastName: String, userName: Int, password: Int) { 
class User (fName: String, lName: String, uName: Int, pass: Int) { 

  var firstName: String
  var lastName: String
  var userName: Int
  var password: Int

  init { 
    //firstName = firstName
    //lastName = lastName
    //userName = userName
    //password = password

    firstName = fName
    lastName = lName
    userName = uName
    password = pass
  }
}
