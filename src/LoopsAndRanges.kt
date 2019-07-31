import java.util.*

fun main() {
    /**
     * In Kotlin we have the same loops as we do in JAVA i.e for, while and do while loop.
     * For loop looks a bit different in Kotlin.First, a different keyword is used to express iteration over somethings i.e "in". Here in is nothing but an iterator
     * Second, we can omit the variable type if needed.Most of the times we omit the types , if needed we can explictly add it as shown below.
     */

    val list = listOf("a", "b", "c")
    for (s in list) print(s)
    println()

    //or we can define the type
    for (s: String in list) print(s)
    println()


    /**
     * We can iterate a contents over the map using the for loop as shown below.
     * Here we assign key and value separately at each iteration loop, then printed the result. This syntax works not only for maps, it can be used to iterate over a collection with index
     */

    val numberMap = mapOf(1 to "one", 2 to "two", 3 to "three")
    for ((key, value) in numberMap) println("$key = $value")

    /**
     * We can use the same above iteration with "withIndex()" function.
     * withIndex() function returns an iterator wrapping each value produced by this iterator with the IndexedValue, containing index and value.
     * IndexedValue : Data class representing a value from a collection or sequence, along with its index in that collection or sequence. IndexedValue(index: Int, value: T)
     */

    val listOfAlphabets = listOf("a", "b", "c")
    for ((index, value) in listOfAlphabets.withIndex()) println("$index : $value")


    /**
     * If we need to work with indexes directly then we can use ranges.There is no full form of for loop as in Java, we can use ranges for this.
     * Here we iterate over range of number from 1 to 9
     */
    for (i in 1..9) println(i)
    println("*************************************")

    /**
     * You can also built over range using until function. The last number then will be excluded.
     * The first range includes the upper bound and using until function excludes the upper bound.
     */

    for (i in 1 until 9) println(i)
    println("*************************************")


    /**
     * It is also possible to iterate over numbers with an arbitrary step (not necessarily 1). This is done via the step function.
     */

    for (i in 1 until 10 step 2) println(i)
    println("*************************************")


    /**
     * To iterate numbers in the reverse order we can use the downTo function instead of ..
     */

    for (i in 4 downTo -4) println(i)

    /**
     * in operator has two different usecases in kotlin
     * First, in acts a iteration as discussed above
     * Second, it can check for belonging as shown below
     * Under the hood compiler generates this code for "in" check
     * 'a'<=c &&  c<='z'
     */

    println("Is b a character ${isChar('a')}")

    /**
     *You can use not in (!in), rechecks whether an element is not in the range
     */

    println("Is x not a digit ${isNotDigit('x')}")

    /**
     * You can use in, as one condition like in this example. Here, we check whether the character is in the range of character numbers or is a letter
     */
    println(recognizeChar('%'))
    println(recognizeChar('a'))
    println(recognizeChar('9'))

    /**
     * ou can create ranges of different elements. It's clear what range of integer number or a range of character means, but what about the range of strings?
     * In fact, you can create range of any comparable elements like range of your custom  date class.
     * You can store a range in a variable of the corresponding range type, such range is regular object
     * Ranges can be used for all the object typees that have compareable interface
     * ClosedRange : takes in the paremeter which SHOULD implement the comprable interface
     */
    val intRange: IntRange = 1..9
    val anotherIntRange: IntRange = 1 until 9
    val charRange: CharRange = 'a'..'z'
    val stringRange: ClosedRange<String> = "abc".."xyz"
    val DateRange: ClosedRange<Date> = Date()..Date()


    /**
     * In Kotlin, we can compare strings like numbers by this nice syntax.--- its replaced by <= and >=
     * Under the hood, the comparative function is called, like in Java.
     * Strings are comparative lexicographically by default or in other words by the alphabetical order
     * String Ranges is mostly used for checking bounds
     */
    println("Kotlin" in "Java".."Scala")

    /**
     * If we see the decompiled code for the below statement we see that the kotlin uses the contains method to check if kotlin is presnet in the set
     * we also compare the strings by their alphabetical order and get true as a result. K goes between j and s in the alphabet
     */
    println("Kotlin" in setOf("Java", "Scala"))


}


fun isChar(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognizeChar(c: Char) = when (c) {
    in '0'..'9' -> "Its a digit"
    in 'a'..'z', in 'A'..'Z' -> "Its a letter"
    else -> "I dont no this character"
}