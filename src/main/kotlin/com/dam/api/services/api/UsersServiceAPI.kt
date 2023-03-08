package com.dam.api.services.api

import com.dam.api.commons.GenericServiceAPI
import com.dam.api.models.User

interface UsersServiceAPI: GenericServiceAPI <User,Long> {
}