package com.dam.api.services.impl

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Session
import com.dam.api.repositories.SessionsRepository
import com.dam.api.services.api.SessionsServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class SessionsServiceImpl : SessionsServiceAPI, GenericServiceImpl <Session,Long>(){
    @Autowired
    lateinit var sessionsRepository: SessionsRepository

    override val dao: CrudRepository<Session,Long>
        get() = sessionsRepository
}