package com.group.libraryapp.domain.book

import com.group.libraryapp.enums.book.BookType
import javax.persistence.*

@Entity
class Book(
    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BookType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    // 테스트 코드 수정을 매번 하지 않기 위한 정적 팩토리 메서드 (kotlin 에서는 companion object 가 가장 아래에 들어가는게 코드컨벤션이다.)
    companion object {
        fun fixture(
            name: String = "책 이름",
            type: BookType = BookType.COMPUTER,
            id: Long? = null,
        ): Book {
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }

}