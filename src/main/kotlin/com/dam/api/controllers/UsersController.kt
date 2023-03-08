package com.dam.api.controllers

import com.dam.api.models.User
import com.dam.api.services.api.UsersServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
class UsersController {

    @Autowired
    lateinit var usersService: UsersServiceAPI


    //GETS
    @GetMapping()
    fun getAll(): ResponseEntity<MutableList<User>> {
        return ResponseEntity(usersService.all, HttpStatus.OK)
    }

    @GetMapping("/{nick}")
    fun getUserByNick(@PathVariable nick: String): ResponseEntity<Any> {
        val usersByNick = usersService.all.filter { it.nick == nick }

        if (usersByNick != null) {
            return ResponseEntity<Any>(usersByNick, HttpStatus.OK)
        }
        return ResponseEntity<Any>(null, HttpStatus.NOT_FOUND)
    }

    //POST
    @PostMapping()
    fun insertUser(@RequestBody user: User): ResponseEntity<Any> {
        val usersByNick = usersService[user.id]
        if (usersByNick != null) {
            return ResponseEntity<Any>("USER ALREADY EXISTS", HttpStatus.NOT_MODIFIED)
        }
        val user = usersService.save(user)
        return ResponseEntity<Any>(user, HttpStatus.OK)
    }

    //PUT
    @PutMapping("/{nick}")
    fun updateUser(@RequestBody user: User, @PathVariable nick: String): ResponseEntity<Any> {
        var usersByNick = usersService.all.filter { it.nick == nick }
        if (usersByNick != null) {
            usersByNick.forEach {
                if (user.nick != null)
                    it.nick = user.nick
                if (user.email != null)
                    it.email = user.email
                if (user.password != null)
                    it.password = user.password
                if (user.profilePicture != null)
                    it.profilePicture = user.profilePicture
                usersService.save(it)
            }
            return ResponseEntity<Any>("USER MODIFIED", HttpStatus.OK)
        }

        return ResponseEntity<Any>("USER NOT FOUND", HttpStatus.NOT_MODIFIED)
    }

    //DELETE
    @DeleteMapping("/{nick}")
    fun deleteUserByNick(@PathVariable nick: String): ResponseEntity<Any> {
        var usersByNick = usersService.all.filter { it.nick == nick }
        if (usersByNick != null) {
            usersByNick.forEach {
                usersService.delete(it.id)
            }
            return ResponseEntity<Any>(usersService.all.filter { it.nick == nick }, HttpStatus.OK)
        }
        return ResponseEntity<Any>(usersByNick, HttpStatus.NOT_MODIFIED)
    }
}