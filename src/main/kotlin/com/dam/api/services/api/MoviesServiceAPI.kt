package com.dam.api.services.api

import com.dam.api.commons.GenericServiceAPI
import com.dam.api.models.Movie

interface MoviesServiceAPI : GenericServiceAPI <Movie,Long> {
}