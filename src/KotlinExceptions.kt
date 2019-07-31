import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main() {

    /**
     * Exceptions in kotlin are very similar to java with one important difference, Kotlin doesnt differentiate between checked and unchecked exceptions.
     * In kotlin, you may or may not handle any exception, and your function doesnot need to specify which exception it can throw.
     */

    /**
     * "throw" is an expression in kotlin, you can assign the result of throw to a variable.Consider the following example
     * In this case, if the number belongs to the range from 0-100, this number becomes the result of expression, otherwise, we throw an exception.
     * You can assign throw to a variable of any type which is very convenient especially to use that in if or when branches.
     * In java, throw is a special construct which you can't assign to a variable
     */
    val number = 10
    val percentage =
        if (number in 0..100) number else throw IllegalArgumentException("A percentage value must be between 0 and 100 : $number")
    println(percentage)
    /**
     * Why throw is made as an expression in kotlin?
     * First consider if throw was not an expression then we should have written the above code like this
     */
    var percentages = 0
    if (number in 0..100) percentages =
        number else throw IllegalArgumentException("A percentage value must be between 0 and 100 : $number")

    /**
     * In the above example we cant directly assign the value from the if statement , if we consider throw as not an expression.
     * But in kotlin since throw is an expression , it allows us to use it directly in if statement and assign the result to the variable if there is no exception.
     * This is the same reason why even "return" is an expression in Kotlin.
     */


    /**
     * "try" can catch an exception if it was thrown inside the block, ocheck it for being of a specific type, and perform the corresponding actions.
     * Like in Java try simpley throws and exception if it's type wasn't listed.
     * Unlike Java, in Kotlin try is an expression. That means we can assign the result of try to variable.
     * Here we assign the result of parseInt function to a number variable if everything works fine.
     * We return from the outer function if the exception was throw.
     * Instead of returing from  the function entirely from the catch block, we can assign null or any other value  to a number variable as a result
     * HERE we have used return as an expression in kotlin
     */

    val num = try {
        Integer.parseInt("asdf")
    } catch (e: NumberFormatException) {
        0 //or just return
    }
    println(num)


    /**
     * There are no checked exceptions in Kotlin so there is no need to specify this function throws at this exception. However, we have the throws annotation in Kotlin.
     * Consider the two functions foo() and bar() that are defined below. bar() function  has the the @Throws annotation and foo doesnt what happens when we call these two functions from a Java class
     * Example Java code  :
     * try{
     *  KotlinExceptionsKt.foo()
     * }catch(IOException e){
     * }
     *
     * try{
     *  KotlinExceptionsKt.bar()
     * }catch(IOException e){
     * }
     */

    /**
     * The code calling their annotated function compiles, but the code calling the function without annotation doesn't compile.
     * Let's look at the first Java example. Here we tried to call foo from Java and catch IOException but the Java compiler complains that exception is never thrown in the corresponding try block.
     * In Java, you cannot catch a checked exception if it wasn't thrown.
     * For this reason, when you throw a checked exception from Java point of view in Kotlin and want to later handle it in Java, you need to add this annotation.
     * Note that if you only use a function throwing an exception in Kotlin, then you don't need the throws annotations at all.
     */


}


fun foo() {
    throw IOException()
}

@Throws(IOException::class)
fun bar() {
    throw IOException()

}