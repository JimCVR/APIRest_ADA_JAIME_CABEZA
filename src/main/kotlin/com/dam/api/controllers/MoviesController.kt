package com.dam.api.controllers

import com.dam.api.models.Movie
import com.dam.api.services.api.MoviesServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin("*")
class MoviesController {

    @Autowired
    lateinit var moviesService: MoviesServiceAPI

    //GETS
    @GetMapping()
    fun getAll(): ResponseEntity<MutableList<Movie>> {
        return ResponseEntity(moviesService.all, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: String): ResponseEntity<Any> {
        val movieID = id.toLongOrNull()
        if (movieID != null) {
            val movie = moviesService[movieID]
            if (movie != null) {
                return ResponseEntity<Any>(movie, HttpStatus.OK)
            }
        }
        return ResponseEntity<Any>("MOVIE NOT FOUND", HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun insertMovie(@RequestBody movie: Movie): ResponseEntity<Any> {
        moviesService.save(movie)
        return ResponseEntity<Any>(moviesService[movie.id], HttpStatus.OK)
    }
}