package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @Test
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("bkjeon", null)

        // when
        userService.saveUser(request)

        // then
        val result = userRepository.findAll()
        assertThat(result).hasSize(1)

        // 값을 가져올 때 플랫폼 타입일 경우 NPE 가 발생하므로 해당 User class (Return Class) 의 getter 에 jetbrains.annotations 의 @NotNull, @Nullable 을 선언하자.
        assertThat(result[0].name).isEqualTo("bkjeon")
        assertThat(result[0].age).isNull()
    }

}