package com.example.orderreception.infrastructure.query

import com.example.orderreception.infrastructure.entity.generated.UsersBase
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.delete
import com.example.orderreception.infrastructure.mapper.generated.insert
import com.example.orderreception.infrastructure.mapper.generated.insertMultiple
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class UserQueryImplTest(
    @Autowired private val sut: UserQueryImpl,
    @Autowired private val usersBaseMapper: UsersBaseMapper
) {
    companion object {
        val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
    }

    @BeforeEach
    fun clear() {
        usersBaseMapper.delete { allRows() }
    }

    @Test
    fun 渡されたユーザIDのユーザ情報が取得できること() {
        // given
        val expected = getUserBase(userId = 1)
        val notSelected = getUserBase( // 一意制約を回避するため電話番号およびメールアドレスを変更
            userId = 2,
            phoneNumber = "111-1111-1111",
            email = "huga@example.com"
        )
        usersBaseMapper.insertMultiple(listOf(expected, notSelected))

        // when
        val actual = sut.findById(expected.userId!!)

        // then
        assertThat(actual?.id).isEqualTo(expected.userId)
        assertThat(actual?.blackLevel).isEqualTo(expected.blackLevel)
    }

    @Test
    fun 存在しないユーザIDが渡された場合nullを返すこと() {
        // given
        val usersBase = getUserBase(userId = 1)
        usersBaseMapper.insert(usersBase)

        // when
        val actual = sut.findById(userId = 2)

        // then
        assertThat(actual).isNull()
    }

    private fun getUserBase(
        userId: Long = 1,
        firstName: String = "太郎",
        lastName: String = "佐藤",
        phoneNumber: String = "000-0000-0000",
        email: String = "hoge@example.com",
        password: String = "password",
        birthday: LocalDate = LocalDate.of(2000, 1, 2),
        rankId: Long = 1,
        blackLevel: Int = 0,
        createdAt: LocalDateTime = now,
        updatedAt: LocalDateTime = now
    ): UsersBase {
        return UsersBase(
            userId = userId,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email,
            password = password,
            birthday = birthday,
            rankId = rankId,
            blackLevel = blackLevel,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}