typealias IntToStringConverter =
            (Int) -> String

typealias StringListAppender =
            (String, MutableList<String>) -> Unit

typealias Predicate<T> = (T) -> Boolean
typealias Converter<FROM, TO> = (FROM) -> TO


fun main() {

    /**
     * Lambdas  : For java explanation please refer to  JavaLambdaExamples.class
     * Lambda is an anonymous function that can be used as an expression, for instance, passed as an argument to another function vacation.
     * In older versions of Java, anonymous classes were used for the same purpose. Modern developed languages including Kotlin as well as Java starting from Java 8, support Lambdas which provide more concise syntax for the same functionality.
     * Having Lambdas in a language also makes it possible to work with collections in a functional style. You can use such functions as filter or map which make the overall code more readable
     */


    /**
     * In Kotlin, Lambda always goes in curly braces. To distinguish regular curly braces, for instance, for if expression, from curly braces used in Lambdas, IDE highlights in bold curly braces for Lambdas.
     * When you see highlighted in bold curly braces, that means you see a Lambda. Inside curly braces, first you specify the parameters, then the arrow, then the Lambda body.
     */

    val myLamdaExpression = { x: Int, y: Int -> println(x + y) }

    /**
     * If use see the below function , we can pass the lambda expression as a parameterr to other function.
     * we declare a type of form (Int,Int)->Unit.
     * If the inferred return type of the lambda is not Unit, the last (or possibly single) expression inside the lambda body is treated as the return value.
     * If you decompile this class n check the java version we can see that internally it uses the Function(kotlin.jvm.functions) class for creating the java with the specified number of arguments
     */
    calculateSumOfNumbers(2, 3, myLamdaExpression)
    println(myLamdaExpression)

    //We can pass the whole lambda expression as a parameter as shown below
    calculateSumOfNumbers(2, 3, { x: Int, y: Int -> println(x + y) })

    /**
     *As we can see that the IDE is recommending us to move the lambda out of the parenthesis
     * You can move Lambda out with parentheses, if the Lambda is the last argument, and if the parentheses are empty, you can omit them.
     * This way you have the nicer syntax
     */

    calculateSumOfNumbers(2, 3) { x: Int, y: Int -> println(x + y) }
    calculateSumOf3and5 { x: Int, y: Int -> println(x + y) }


    /**
     * If the type of the argument can be inferred, if it's clear from the context, it can be omitted.
     * If your Lambda has only one argument, then you can replace its name with "it".
     * "it" is an automatically generated name for your Lambda, if it has only one argument, and you don't specify a different argument name.
     * If you want to express some complicated logic and need a multi-line Lambda, then you just write several lines of code inside the parentheses. The last expression of this Lambda is the result
     */

    calculateSquareOfNumber { i: Int -> println(i * i) }

    //OR
    calculateSquareOfNumber { println(it * it) }

    /**
     * In all the previous examples i have use lambda expresison as a parameter and then executed it in another method.
     * We can execute a java in 2 ways - > one by just using the name of the lambda with specified paramters : max(3,5)
     * Second by using the invoke method. as we know that lambdas in kotlin are baccked by Function Interfacce(kotlin.jvm.functions) and these interfaces has only one method invoke which can be used to execute the lambda
     */

    val max = { a: Int, b: Int ->
        if (a > b)
            a
        else
            b
    }

    println(max.invoke(3, 5))


    /**
     * If one of the Lambda parameters is not used, you can replace its name with the underscore. It's more readable and you don't need to invent a name for the parameter if it's not used.
     */
    calculateSumOf3and5 { _, _ -> println(3 + 5) }


    /**
     * If we want to return early from a lambda body, in this case we must follow "return-at-label" syntax:
     * First we tag lambda expression with label lambda@. You may use any name you like but label must ends with @ character (called ‘at’ character).
     * Then inside lambda body to signify return we use return keyword followed by label name. There must be no whitespace between return and label name.
     */

    val doStuff = myLambaName@{ stopEarly: Boolean ->
        println("line 1")
        if (stopEarly) return@myLambaName
        println("line 2")
    }

    doStuff(true)
    doStuff(false)

    var sum = 0
    (1..10).forEach { sum += it }
    println(sum)


    /**
     * Function types
     * Kotlin provides succinct syntax for specifying function types, for example () -> Unit is type of a function that doesn’t take any parameters and returns nothing,
     * and (Int) -> Int is type of a function that takes single parameter of type Int and returns value of type Int.
     * Here are more examples:
     * IF YOU CAN SEE WHILE DECALARING THE TYPES i.e ON LEFT HAND SIDE WE USE THE PARANTHESIS AND NOT IN LAMBDA EXPRESSION, if we use it in lambda expresiion then it becomes destructing lambdas
     */

    val fun1: (Int, Int) -> Int =
        { a, b -> Math.max(a, b) }

    val fun2: (String, MutableList<String>) -> Unit =
        { s, list -> list.add(s) }

    /**
     * fun3 it is a higher level function, it returns an integer and takes two arguments, an integer and a function that takes an integer and returns an integer.
     */
    val fun3: (Int, (Int) -> Int) -> Int =
        { value, func -> func(value) }

    //Most complicated way to add 3 to 4
    println(fun3.invoke(3, { s -> s + 4 }))
    //Over complicating more :P , HINT : lambda is the last expression
    println(fun3.invoke(3) { s -> s + 4 })


    /**
     * TYPE ALIASES IN LAMBDAS
     * Since repeating function types may be tiring and error prone, we should use Kotlin type aliases to give them meaningful names.
     * Type aliases are top-level only. In other words, they can’t be nested inside a class, object, interface, or other code block. If you try to do this, you’ll get this error message from the compiler:
     * Now we can change the above func2 to more redable form as shown below
     */

    val fun4: StringListAppender =
        { s, list -> list.add(s) }


    /**
     * The last cool thing about type aliases is that they can use generic parameters. This allows us to easily create types like:
     * Check at the top of the file as type aliases are top level functions and im lazy to go up there and type now
     * More about lambdas:  https://marcin-chwedczuk.github.io/lambda-expressions-in-kotlin
     * More about typealias : https://typealias.com/guides/all-about-type-aliases/
     */


}

fun calculateSumOfNumbers(i: Int, i1: Int, lambdaExpression: (Int, Int) -> Unit) = lambdaExpression(i, i1)


fun calculateSumOf3and5(lambdaExpression: (Int, Int) -> Unit) = lambdaExpression(3, 5)


fun calculateSquareOfNumber(lambdaExpression: (Int) -> Unit) = lambdaExpression(3)



