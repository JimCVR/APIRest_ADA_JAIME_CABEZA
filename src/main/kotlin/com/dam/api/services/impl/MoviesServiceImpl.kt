package com.dam.api.services.impl

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Movie
import com.dam.api.repositories.MoviesRepository
import com.dam.api.services.api.MoviesServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class MoviesServiceImpl : MoviesServiceAPI , GenericServiceImpl<Movie, Long>() {
    @Autowired
    lateinit var moviesRepository: MoviesRepository

    override val dao: CrudRepository<Movie, Long>
        get() = moviesRepository
}