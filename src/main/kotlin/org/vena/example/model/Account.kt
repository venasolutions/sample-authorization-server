package org.vena.example.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "account")
data class Account(@Column(name = "email", nullable = false)
                   var email: String,

                   @NaturalId
                   @Column(name = "username", unique = true, nullable = false)
                   @NotNull
                   @Size(min = 3, max = 255)
                   var username: String = email,

                   var firstName: String,
                   var lastName: String) {

    @Id
    @GeneratedValue
    var id: Long? = null
}