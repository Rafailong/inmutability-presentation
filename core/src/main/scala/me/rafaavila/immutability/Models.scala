package me.rafaavila.immutability

case class Address(number: Int, street: String)
case class Gym(name: String, address: Address)
case class Member(firstName: String, lastName: String, gym: Gym)
