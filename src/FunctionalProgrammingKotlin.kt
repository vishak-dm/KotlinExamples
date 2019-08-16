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


    /**
     * COMMON OPERATIONS ON COLLECTIONS INVOLVING LAMBDAS
     */

    /**
     * 1 FILTER OPERATOR : It filters out the elements of the list and keep only that satisfy the given predicate.
     * In the below example we are filtering out items that are only even numbers
     * Please check the filter function and how it works internally
     * filter is an extension function that accepts a lambda as a predicate and then returns a NEW list that satisfy the predicate
     */
    println(listOf(1, 2, 3, 4, 5, 6, 7).filter { it % 2 == 0 })  //output [2,4,6]


    /**
     * 2 MAP OPERATOR : this transforms each element in a collection and stores all the result elements in a new list.
     * In the below example we find the square of each element of the list. the resulting list contains as many elements as the original list
     * map is an extention function that is defined on the collection class which accepts a transform function which is a lambda
     */
    println(listOf(1, 2, 3, 4).map { it * it })// output [1,4,9,16]

    /**
     * 3 ANY operator : Any operator checks that is ATLEAST one element satisfying the given predicate.
     * In the below example we check whether there is atleast one even  number in the list
     * any() function checks if there is atleast one element in the list , where as there is an overlaoded version which takes in a predicate lambda on which you can mention the condition
     */

    println(listOf(2, 3, 5, 6).any { it % 2 == 0 })// output true


    /**
     * 4 ALL operator : All operator checks if all the elements satisfy the given predicate
     * In the below example we check whether all the elements are even  number in the list
     */

    println(listOf(2, 3, 5, 6).all { it % 2 == 0 })// output false


    /**
     * 5 NONE operator : none opertor makes sure that none of the elements satisfy the given predicate. Its the opposite of ALL operator
     * none() function checks if the list contains no elements
     */
    println(listOf(2, 3, 5, 6).none { it % 2 == 0 })// output false
    println(listOf(7, 3, 5, 1).none { it % 2 == 0 })// output true
    println(listOf(7, 3, 5, 1).none())// output false


    /**
     * 6 FIND operator : find operator finds the FIRST element that satisfy the predicate and then returns the element as the result.
     * If there is no required elements then find returns null,
     * find internally calls another function called firstOrNull, we can use this function as well.
     */

    println(listOf(7, 3, 5, 1).find { it % 2 == 0 })// output null
    println(listOf(7, 2, 5, 4).find { it % 2 == 0 })// output 2

    /**
     * 7 First and firstorNull  : First is similar to find with one differnence  : first takes a predicate and throws an exception if no elements satisfying the predicate is found.
     * firstOrNull is same as FIND OPERATOR
     */

    /**
     * 8 PARTITION : Partition divides the collection into two collections. Filter returns only the elements that satisfy the predicate, and in a sense, throws out all the elements that don't satisfy the predicate.
     * If you need to keep both groups of elements that satisfy or do not satisfy the predicate, you can use the partition.
     * It returns two collections, for the good elements and the remaining ones. It returns a Pair<List,List>
     */

    println(listOf(1, 2, 3, 4, 5, 6, 7).partition { it % 2 == 0 })  //output ([2, 4, 6], [1, 3, 5, 7])


    /**
     * 9 GROUP BY : If you need to divide the collection into more than two parts then use groupby operator.
     * As an argument, you provide the way how to group the elements.The result is, map from the given key to a list of elements that satisfy this scheme
     * In the below example we have grouped all elements by their last letter. Output is a map that gives different list
     */


    println(listOf("one", "two", "three").groupBy { it.lastChar() }) //output {e=[one, three], o=[two]}


    /**
     * 10 associateBy Returns a [Map] containing the elements from the given collection indexed by the key
     * returned from [keySelector] function applied to each element.
     *
     * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
     *
     * The returned map preserves the entry iteration order of the original collection.
     */


    println(listOf("one", "two", "three").associateBy { it.lastChar() }) //output {e=three, o=two}


    /**
     * 11 associate : You can use as associate to build a map based on a list.
     * As an argument, you pass allowed to creating the key value pair based on each list element, then associate builds a map by filling in specified keys and values.
     * The first value in a pair becomes key in the map, the second becomes the value.
     * Here, a plus it specifies how to create keys, while 10 multiply, it is the way to create values
     */
    println(listOf(1, 2, 3, 4, 5, 6, 7).associate { 'a' + it to 10 * it })  //output {b=10, c=20, d=30, e=40, f=50, g=60, h=70}


    /**
     * 12 zip : Zip provides you with a way to organize a couple of lists.
     * It zips like a zipper the elements from two lists.
     * It returns you a list of pairs where each pair contains one element from the first list and another element from the second list.
     * If their initial list have different sizes, then the resulting list of pairs will have the length of the shortest list, the remaining elements from the longest list will be ignored
     */

    println(listOf(1, 2, 3, 4, 5, 6, 7).zip(listOf("a","b","c","d"))) //output : [(1, a), (2, b), (3, c), (4, d)]


    /**
     * 13 zipNext : The frequent use case is to zip neighboring elements in the list.
     * That can be done with the help of the zipWithNext function. It returns you a list of pairs where each pair consists of neighboring elements is from the initial list.
     * Note that, each element except the first and the last one will belong two pairs.
     * Like three in this example list is the second element in the second pair and the first element in the third pair.
     * Both zip and zipWithNext have overloaded versions taking a lambda as an argument that specifies how each pair of elements must be transformed
     */

    println(listOf(1, 2, 3, 4, 5, 6, 7).zipWithNext())  //output [(1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7)]


    /**
     * 14 flatten : Flatten is an extension function that must be called on a list of lists.
     * It combines all the elements from the nested list and returns you a list of these elements as the result.
     * We can say, it flattens the list of lists contents
     */

    println(listOf(listOf(1,2,3,4),listOf("a","b","x","z")).flatten()) //output : [1, 2, 3, 4, a, b, x, z]


    /**
     * 15 flatMap : It combines two operations, map and flat. The argument to flatMap must be a lambda that converts each initial element to a list.
     * Here, we first map each string into a list of characters. In the middle layer after applying the first map operations, we have at list of lists.
     * Often, you'd prefer list of elements as a result instead and flatten does that. Here, flatMap returns a list of characters obtained from initial strings.
     */

    println(listOf("abc","efg","hij").flatMap { it.toList() }) //output : [a, b, c, e, f, g, h, i, j]



}

fun calculateSumOfNumbers(i: Int, i1: Int, lambdaExpression: (Int, Int) -> Unit) = lambdaExpression(i, i1)


fun calculateSumOf3and5(lambdaExpression: (Int, Int) -> Unit) = lambdaExpression(3, 5)


fun calculateSquareOfNumber(lambdaExpression: (Int) -> Unit) = lambdaExpression(3)



