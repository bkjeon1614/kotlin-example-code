package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    fun findByName(nam: String): User?

    // JPQL 사용 (이것보다 QueryDSL 을 사용하는게 더 편하다)
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN u.userLoanHistories")
    fun findAllWithHistories(): List<User>

}