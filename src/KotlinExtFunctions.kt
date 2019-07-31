fun main() {

    /**
     * Extension functions extends the class(not in as inheritance). It is defined outside the class but can be called as regular members of this class.
     * Consider the following extension function called lastChar , and we can use it as a member function.
     * Its visible in completion dialog , so it can be easily discoverable.
     * Its like  aregular utility function defined outside of the class.
     */
    println("abcd".lastChar())

    /**
     * An imporatnt thing is that we cannot define an extension and use it everywhere.
     * You have to import it explicitly.If you define an extension function lastChar somewhere and need to use it, you have to import it either as one function, or inside the whole contents of the package.
     * By default, an extension function is not accessible in the whole project. It's visible in completion but to use it you have to put the explicit input
     */

    /**
     * CHECK CALLING EXTENSION FUNCTIONS IN JAVA IN JvmOverloadsExample class
     */


    /**
     * We know that we can access the member variables of the reciever class in case of extension functions.
     * But is it possible to call the private member of receiver class?
     * No , As in java , its only possible to call a private member inside the same class.
     * Kotlin extension functions are most of the time top-level functions defined in a special extra file which content is compiled to the corresponding extra class.
     * Therefore, it's not possible to call a private member of a class from an extension to this class.
     * Extension functions don't give you any additional access rights to the class contents.
     * The main benefit of extensions, is that you can call them in a nicer and more convenient way
     */

    // fun String.getPrivateHashOfString() = this.get(this.hash) ... Cant acess private member variable


    /**
     * Extension functions from the Standard Library
     * Kotlin standard library is just Java standard library and a bunch of extensions that provides very smooth interoperability between Java code and Kotlin code
     */

    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "One", 2 to "Two", 3 to "Three")

    /**
     * Lets check the classes that are created for the above methods.
     * To check the class, we call javaClass property. It's analogous to Java's set.getClass.
     * You can see that Java standard collection classes from java.util package are used under the hood.
     * When you create a collection using the functions from the Kotlin standard library, instances of Java standard classes are created
     */

    println(set.javaClass) //prints java.util.HashSet
    println(list.javaClass)//prints java.util.ArrayList
    println(map.javaClass)//prints java.util.HashMap

    /**
     * The Kotlin library simply provides extensions for regular Java collections.
     * There is no such thing as Kotlin SDK. It's just our standard library plus a bunch of extensions.
     * That gives us a couple of important benefits. :-
     * At first, the size of the runtime jar that you have to add to your application when it starts using Kotlin is relatively small.
     * The Kotlin doesn't duplicate the standard implementations from Java. It tries to reuse them. The Kotlin library simply adds a bunch of extensions.
     * If your application doesn't use some of these extensions, they can be eliminated so that the added jar can get even smaller.
     * The second important benefit is that using standard Java collections provides very easy interoperability with Java.
     * You don't have to convert one collection type to another, they are the same types under the hood. If you call Java API from Kotlin, or vice versa, you call Kotlin from Java, that's very easy.
     * You don't have to introduce any additional conversions for collections
     */

    //EXAMPLES

    /**
     * joinToString
     * The joinToString function allows you to get a nice string representation of a collection.
     * In Java, the square brackets are added by default, which is not always what you need.
     * joinToString allows you to change the default behavior by specifying the values for some of the algorithms
     * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
     * elements will be appended, followed by the [truncated] string (which defaults to "...").
     * PLEASE CHECK THE SOURCE CODE FOR IMPLEMENTAITON
     */
    println(listOf('a', 'b', 'c').joinToString(separator = "", prefix = "(", postfix = ")"))


    /**
     * getOrNull
     * We can call this extension function with array or list
     * Under the hood, Kotlin array is the same array as in Java, but Java array lacks this getOrNull method.
     * In Kotlin, getOrNull is defined as an extension, and that's why we can call it on a regular Java array.
     */
    val myarray = arrayOf(1, 2, 3)
    println(myarray.getOrNull(1))
    println(myarray.getOrNull(4))

    /**
     * until
     * Until is also an extension. The until function is defined as infix.
     * You can either call it in a regular way, as in the first line, or you can call it in an infix form by omitting dot and parentheses.
     * until looks like a built-in syntax, but it's not. It's just an extension function called in an infix form.
     */

    println(4 until 1)
    println(4.until(2))

    /**
     * to
     * To in Kotlin simply returns a pair of values.
     * Again, you can use it in a regular form, but no one does that.Everybody uses nicer infix form.
     * Why it's called to? When you create a map of values, it's very convenient to enumerate key to value pairs using to.
     * And it turned out that it can be used in other use cases as well. Whenever you need a pair of values, you can create it with to
     */

    println(1 to "One")
    val numberMap = hashMapOf(1 to "One", 2 to "Two", 3 to "Three")

    /**
     * Triple-quoted string literals is another interesting feature in Kotlin.An alternative way to call them is the multiline strings.
     * You can't use any special characters there, but you can use strings to place inside them.
     * If you try to form a triple-quoted strings in your code, you'll probably want to add the indent.
     * But by default, this indent becomes the part of the string.
     * There are some ways to crop this indent, and one of these ways is using the function trimMargin.
     * Here the default margin is used(|), but you can specify another marginPrefix if needed. The marginPrefix is automatically cut out together with indent.
     * if we dont specify the margin then indent is not ommited
     */
    val myString = """To code
        |or not to code
    """.trimMargin()
    println(myString)

    val anotherString = """To code
        *or not to code
    """.trimMargin(marginPrefix = "*")
    println(myString)
    println(anotherString)

    //if we dont specify the margin then indent is NOT ommited
    val someOtherString = """To code
        or not to code
    """.trimMargin()
    println(someOtherString)


    /**
     * You can call the trimIndent function directly.
     * If there is the same indent for all the lines in your string, it will be automatically removed
     * Both trimIndent and trimMargin functions are defined as extensions.
     */
    val a = """
        Keep Calm
        and learn kotlin
    """.trimIndent()
    println(a)


    /**
     * If we change the indent in one of the lines then trimIndent doesnt work , It shud be same for all the lines
     */
    val b = """
   Keep Calm
        and learn kotlin
    """.trimIndent()
    println(b)

    val c = """Keep Calm
        and learn kotlin
        and try to learn something else
    """.trimIndent()
    println(c)


    /**
     * Another useful extension is the toRegex function.
     * You can convert a string to the regex clause to present in regular expressions.
     * After that, you can call, for instance, matches method on this regular expression clause. Note that for regular expressions, it's very convenient to use triple-quoted strings.
     * You don't need to skip special characters there, including the backslash. The regular expression often becomes shorter
     */
    val regex = "\\d{2}\\.\\d{2}\\.\\d{4}".toRegex()
    println(regex.matches("15.02.2016"))
    println(regex.matches("15.02.16"))

    val anotherRegex = """\d{2}\.\d{2}\.\d{4}""".toRegex()
    println(anotherRegex.matches("15.02.2016"))
    println(anotherRegex.matches("15.02.16"))


    /**
     * Another useful group of extensions allows you to convert strings to numbers.
     * toInt and toDouble extension functions will try to convert string representations to Int and Double accordingly.
     * Note, however, that if you try to convert something that doesn't make sense to an integer, you'll get NumberFormatException.
     * But there is another extension available, toIntOrNull, which returns null if the string cannot be converted to an integer
     */

    println("123".toInt())
    println("1e-10".toDouble())
    //println("s".toInt()) ... causes numberformatException, so we need to use toIntOrNull
    println("s".toIntOrNull())


    /**
     * Extension functions with inheritance :
     * Lets say we have two classes, parent and child and child extends from parent.
     * We can define two similar extension function for both the class. Consider the following example
     */
    val parent: Parent = Child()
    parent.foo()

    /**
     * In the above example, The resolution of which function should be called works in a similar manner as for Java static functions.
     * Under the hood, these extension functions are compiled to Java static functions.
     * When extension functions are compiled, the type of the receiver is transformed to the type of the first additional parameter.
     * Java resolves static function statically. It finds the right function to be called during the compilation.
     * It only uses the type of the argument to choose the right function, thus the parent function is chosen here since the parent variable has the parent type.
     * In general case, we don't know what is stored in the parent variable. It can be an instance of child or parent or another child and this wider works this way.
     * The actual stored object at runtime doesn't change anything because the function to be called is already chosen during compilation. In Kotlin, it works in the same way as for static function
     */


    /**
     * Because extensions are static functions under the hood, there is no override for extensions, they cannot be overridden in Kotlin.
     * When the compiler chooses the right function to be called, it only uses the type of the receiver expression, not the actual stored value.
     * Now, the interesting question is what happens if we try to define an extension which duplicates a member?
     */
    println("abc".get(1))
    /**
     * In the above example, the string class has a member function get, which simply returns a character by its index. We tried to define an extension with the same signature
     * If you try to define extension with the same signature as a member, then you get a warning that an extension is shadowed, so the member will always be chosen instead.
     * However, you can overload a member. If you define an extension with the different signature, different parameter types or the different number of parameters, your new function will be called if it fits better
     */
    println("abc".get(1, 0))


}

/**
 * Here lastChar is the extended function of String class.
 * Here String is called the receiver and we can access the members of the receiver using the "this" operator
 * For readbility we can omitt the this keyword and compiler knows that we are accessing the members of the receiver.
 * If you define an extension function lastChar somewhere and need to use it, you have to import it either as one function, or inside the whole contents of the package. By default, an extension function is not accessible in the whole project. It's visible in completion but to use it you have to put the explicit input
 */
fun String.lastChar() = this.get(this.length - 1)


open class Parent {

}

class Child : Parent() {

}

fun Parent.foo() = println("Parent foo is printed")
fun Child.foo() = println("child foo is printed")
fun String.get(index: Int) = '*'
fun String.get(index: Int, param: Int) = '*'

