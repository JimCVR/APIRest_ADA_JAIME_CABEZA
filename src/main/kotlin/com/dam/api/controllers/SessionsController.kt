package com.dam.api.controllers

import com.dam.api.models.Session
import com.dam.api.services.api.SessionsServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/api/v1/sessions")
@CrossOrigin("*")
class SessionsController {

    @Autowired
    lateinit var sessionsService: SessionsServiceAPI

    //GETS
    @GetMapping()
    fun getAll(): ResponseEntity<MutableList<Session>> {
        return ResponseEntity(sessionsService.all, HttpStatus.OK)
    }

    @GetMapping("/todayonwards")
    fun getSessionTodayOnwards(): ResponseEntity<Any> {
        val currentDate = LocalDate.now()
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val todayOnwardsSessions = sessionsService.all.filter {
            currentDate <= LocalDate.parse(it.date,dateFormat)
        }
        if (todayOnwardsSessions != null) {
            return ResponseEntity<Any>(todayOnwardsSessions, HttpStatus.OK)
        }
        return ResponseEntity<Any>("SESSION NOT FOUND", HttpStatus.NOT_FOUND)
    }


    @GetMapping("/today")
    fun getSessionsToday(): ResponseEntity<Any> {
        val currentDate = LocalDate.now()
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val todayOnwardsSessions = sessionsService.all.filter {
            currentDate ==  LocalDate.parse(it.date,dateFormat)
        }

        if (todayOnwardsSessions != null) {
            return ResponseEntity<Any>(todayOnwardsSessions, HttpStatus.OK)
        }
        return ResponseEntity<Any>("SESSION NOT FOUND", HttpStatus.NOT_FOUND)
    }


    @PostMapping()
    fun insertSession(@RequestBody session: Session): ResponseEntity<Any> {
        sessionsService.save(session)
        return ResponseEntity<Any>(sessionsService[session.id], HttpStatus.OK)
    }

}