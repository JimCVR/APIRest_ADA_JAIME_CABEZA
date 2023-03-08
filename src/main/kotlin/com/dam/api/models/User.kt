package com.dam.api.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "userID")
    var id: Long,

    @Column(name = "nick")
    var nick: String,

    @Column(name = "email")
    var email: String?,

    @Column(name = "password")
    var password: String?,

    @Column(name = "profilePicture")
    var profilePicture: String?,

    ) {

}