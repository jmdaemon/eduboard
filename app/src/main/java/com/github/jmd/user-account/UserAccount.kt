package com.github.jmd.user


//class User (firstName: String, lastName: String, userName: String, password: String) {
class User (firstName: String, lastName: String, userName: Int, password: Int) { 

  var firstName: String = ""
  var lastName: String = ""
  var userName: Int = 0
  var password: Int = 0
    get() = field
    set(value) { field = value }

  //constructor (
  //var firstName: String = firstName, 
  //var lastName: String = lastName, 
  //var userName: Int = userName, 
  //var password: Int = password,
      //get() = field
      //set(value) { field = value }
    //private set
  //get() = field

  //get() = field
  //set(value) { field = value }

  //get getFirstName(): String {
    //return firstName
  //}
  //get getLastName(): String {
    //return lastName
  //}

  //get getUserName(): Int {
    //return userName
  //}

  //get getPassword(): Int {
    //return password 
  //}

    ////get() = this.firstName

  //val getLastName: String 
    //get() = this.lastName

  //val getUserName: Int
  //get() = this.userName

  //val getPassword: Int
  //get() = this.password


    //val userName: String,
    //val password: String,
    //val firstName: Int,
    //val lastName: Int,

    //fun getFname() : String {
      //return firstName
    //}

    //fun getLname() : String {
      //return lastName
    //}

    //fun getUserName() : Int {
      //return userName
    //}

    //fun getPassword() : Int {
      //return password 
    //}
    
}
