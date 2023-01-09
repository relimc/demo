import jdk.swing.interop.SwingInterOpUtils
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        println(result)
    }
}

