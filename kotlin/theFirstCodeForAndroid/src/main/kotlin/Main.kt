fun main() {
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    if (list has "Apple") {
        print("apple")
    }
}

infix fun <T> Collection<T>.has(element: T) = contains(element)