package chapter1

import kotlinx.coroutines.*

/**
 * 1-1 스코프빌더(1)
 * */
object `1-1 스코프빌더(1)` {

    /**
     * 예제 1) 간단한 코루틴
     *
     * 코루틴을 만드는 가장 간단한 함수: runBlocking()
     * 코루틴을 만드는 함수를 코루틴 빌더라고 한다.
     * runBlocking()은 코루틴을 만들고 코드 블록의 수행이 끝날 때까지 runBlocking 다음의 코드를 수행하지 못하게 막는다.
     *
     * 스레드 이름이 main @coroutine1 입니다.
     * 메인 스레드에서 수행되는데 뒤에 수식어 @coroutine1 가 붙어 있다.
     * */
    fun example1() = runBlocking {
        println(Thread.currentThread())
    }

    /**
     * 예제 2) 코루틴 빌더의 수신 객체
     *
     * runBlocking 안에서 this 를 수행하면 코루틴이 수신 객체(Receiver)인 것을 알 수 있습니다.
     *
     * 결과: "coroutine#1":BlockingCoroutine{Active}@5f205aa
     *
     * BlockingCoroutine 은 CoroutineScope 의 자식이다.
     * 코틀린 코루틴을 쓰는 모든 곳에는 코루틴 스코프(CoroutineScope)가 있다고 생각하면 된다.
     *
     * "코루틴의 시작은 코루틴 스코프다"
     * */
    fun example2() = runBlocking {
        println(this)
        println(Thread.currentThread())
    }

    /**
     * 예제 3) 코루틴 컨텍스트(CoroutineContext)
     *
     * 코루틴 스코프는 코루틴을 제대로 처리하기 위한 정보, 코루틴 컨텍스트(CoroutineContext)를 가지고 있다.
     * */
    fun example3() = runBlocking {
        println(coroutineContext)
        println(Thread.currentThread())
    }

    /**
     * 예제 4) launch 코루틴 빌더
     *
     * launch 는 "할 수 있다면 다른 코루틴 코드를 같이 수행"시키는 코루틴 빌더입니다.
     *
     * runBlocking()과 launch() 모두 main 스레드를 사용하기 떄문에,
     * runBlocking()의 블록이 끝난 다음 launch() 의 블록이 실행되는 모습이다.
     * */
    fun example4() = runBlocking {
        launch {
            println("launch: ${Thread.currentThread()}")
        }
        println("runBlocking: ${Thread.currentThread()}")
    }

    /**
     * 예제 5) delay 함수
     *
     * delay(): 해당 스레드에서 블록이 잠시 잠들었다가 다시 돌아옴(중단점, Suspension point)
     * Thread.sleep(): 해당 스레드가 멈춤
     * */
    fun example5() = runBlocking {
        launch {
            println("launch: ${Thread.currentThread()}")
        }
        println("runBlocking: ${Thread.currentThread()}")
        delay(500L)
        //Thread.sleep(500L)
        println("runBlocking: after 500ms")
    }
}