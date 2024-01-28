package com.group.coroutine

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
 * 실행순서
 * 1. main 함수가 실행되면 runBlocking 에 의해 코루틴이 생기고 그 안에서 START 가 출력된다.
 * 2. 그 다음 launch 라는 함수가 호출되서 새로운 코루틴을 만들고 yield() 로 넘어간다. (launch 는 만들어진 새로운 코루틴을 바로 실행하지 않는다.)
 * 3. yield() 가 스레드를 양보하므로 runBlocking 의 실행이 중단되고, launch 로 주도권이 넘어간다. 그러면 newRoutine() 가 실행된다.
 * 4. newRoutine() 에서 지역변수 num1, num2 가 초기화되고 yield() 에 의해서 다시 한 번 양보하기 때문에 밖으로 나가진다.
 * 5. 그래서 END 를 출력한다음 최종적으로 ${num1 + num2} 를 출력하고 프로그램이 종료된다.
 * 즉 결과는
 * [main] START
 * [main] END
 * [main] 3
 */
fun main(): Unit = runBlocking {    // runBlocking 중괄호부터 코루틴 영역이라고 보면된다. (즉, 일반루틴과 코루틴과 연결)
    println("START")
    launch { newRoutine() } // launch 는 반환값이 없는 코루틴을 만든다.
    yield()
    println("END")
}

suspend fun newRoutine() {  // suspend fun 은 다른 suspend fun 을 호출할 수 있다. (yield 를 사용하기 위함)
    val num1 = 1
    val num2 = 2
    yield() // 지금 코루틴을 중단하고 다른 코루틴이 실행되도록 한다. (스레드를 양보한다.)
    printWithTread("${num1 + num2}")
}

// VM Options 에 -Dkotlinx.coroutines.debug 을 넣으면 코루틴 생성 순서를 상세하게 확인할 수 있다.
fun printWithTread(str: Any) {
    println("[${Thread.currentThread().name}] $str")
}