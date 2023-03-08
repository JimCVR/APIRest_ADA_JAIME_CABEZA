package com.dam.api.models

import jakarta.persistence.*

@Entity
@Table(name="session")
data class Session(
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,


    @ManyToOne
    @JoinColumn(name = "movie_id")
    var movie_id: Movie,

    @Column(name="room_id")
    var room_id: Long,

    @Column(name="date")
    var date:String,


) {
}