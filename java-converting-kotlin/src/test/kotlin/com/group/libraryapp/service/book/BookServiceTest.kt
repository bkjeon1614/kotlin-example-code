package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다")
    fun saveBookTest() {
        // given
        val request = BookRequest("bkjeon book")

        // when
        bookService.saveBook(request)

        // then
        val bookList = bookRepository.findAll()
        assertThat(bookList).hasSize(1)
        assertThat(bookList[0].name).isEqualTo("bkjeon book")
    }

    @Test
    @DisplayName("책 대출이 정상 동작한다")
    fun loanBookTest() {
        // given
        bookRepository.save(Book("bkjeon book"))
        val savedUser = userRepository.save(User("bkjeon", null))
        val request = BookLoanRequest("bkjeon", "bkjeon book")

        // when
        bookService.loanBook(request)

        // then
        val bookList = userLoanHistoryRepository.findAll()
        assertThat(bookList).hasSize(1)
        assertThat(bookList[0].bookName).isEqualTo("bkjeon book")
        assertThat(bookList[0].user.id).isEqualTo(savedUser.id)
        assertThat(bookList[0].isReturn).isFalse
    }
    
    @Test
    @DisplayName("책이 진작 대출되어 있다면, 신규 대출이 실패한다")
    fun loanBookFailTest() {
        // given
        bookRepository.save(Book("bkjeon book"))
        val savedUser = userRepository.save(User("bkjeon", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "bkjeon book", false))
        val request = BookLoanRequest("bkjeon", "bkjeon book")

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    @DisplayName("책 반납이 정상 동작한다")
    fun returnBookTest() {
        // given
        bookRepository.save(Book("bkjeon book"))
        val savedUser = userRepository.save(User("bkjeon", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "bkjeon book", false))
        val request = BookReturnRequest("bkjeon", "bkjeon book")

        // when
        bookService.returnBook(request)

        // then
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result[0].isReturn).isTrue
    }

}