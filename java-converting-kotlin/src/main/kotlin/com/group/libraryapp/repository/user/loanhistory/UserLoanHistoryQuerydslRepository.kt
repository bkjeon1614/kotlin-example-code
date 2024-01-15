package com.group.libraryapp.repository.user.loanhistory

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.enums.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {
    /**
     * 1. status: UserLoanStatus? = null
     *    - default parameter 에 null 을 넣어 외부에서는 bookName 만 쓸 수도, status 까지 같이 쓸 수도 있다.
     * 2. status?.let { userLoanHistory.status.eq(status) }
     *    - status 가 null 이 아닌 경우에만 User_loan_history.status = ? 가 들어간다.
     */
    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
                // where 에 여러 조건에 들어오면 각 조건은 AND 로 연결된다. 또한 where 조건에 null 이 들어오면 무시하게 된다.
            .where(userLoanHistory.bookName.eq(bookName), status?.let { userLoanHistory.status.eq(status) })
            .limit(1)
            .fetchOne() // List<Entity> 로 조회 결과를 가져오는 대신 Entity 하나만 가져온다.
    }

    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.count()) // SQL 의 count(id)로 변경된다.
            .from(userLoanHistory)
            .where(userLoanHistory.status.eq(status))
            .fetchOne() ?: 0L   // Count 의 결과는 숫자 1개 이므로 fetchOne() 을 사용
    }

}