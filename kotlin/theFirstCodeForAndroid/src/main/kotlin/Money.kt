class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }

}

fun main() {
    val money1 = Money(10)
    val money2 = Money(20)
    val sum = money1 + money2
    print(sum.value)
}