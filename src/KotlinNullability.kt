import java.util.*
import kotlin.test.fail

fun main() {
    /**
     * Kotlin  distinguishes nullable and non-nullable types.
     * f you use a regular type like  string, you can only store non-nullable objects there, you can't store null references.
     * If you try to initialize a variable of a non-nullable type with null, the Kotlin compiler will complain.
     */
    var s1 = "abcd"
    // myString = null  ... Does not compile, because we cannot assign null to non-nullable variable

    /**
     * If you want to store null, you need to declare a variable of a nullable type.
     * To make a type nullable add a question mark and at end of his name.
     * In this example, after adding a question mark to string, you can store either null or non-null string value there.
     */

    var s2: String? = "abcd"
    println(s2)


    /**
     * If you try to reference a variable of a non-nullable type namely access its members or call extensions everything is fine.
     * The Kotlin compiler doesn't complain because it's sure that the exact value is not null.
     * However, if you try to reference a variable of a nullable type, the Kotlin compiler says it's not possible. The value of the nullable type can be null under the hood, and such reference operation will then throw null pointer exception
     */

    s1.length // noproblemo as the compiler knows that the s1 is not null
    //s2.length //prroblemo : as s2 may be null and cannot reference the string

    /**
     * SAFE ACCESS OPERATOR
     * How do we access the string s2 : One way is to use the explicit if condition and check if its not null and then access the variable as shown below.
     * However, IDE,  will suggest you have a better way to express the same logic. You can replace the if expression with a safe access expression.
     * Safe access, or in other words, a safe call, consists of two characters. The question mark and the dot.
     * It allows you to dereference a value in a safe manner. This operator first checks whether the receiver is not null.
     * If it is the case, then the safe access operator calls the required member and returns the result. If the receiver is null, then null becomes the result
     */

//    if (s2!=null)
//        s2.length

    s2?.length


    /**
     * Example question Whic types does length variable below have
     * Its of type Int? or we can call ite as int nullable. it holds the value null and is of the TYPE int nullable.
     * safe access operator calls the required member and returns the result. If the receiver is null, then null becomes the result
     */

    var s: String? = null
    val length = s?.length

    /**
     * In the above use case what if we dont want  to assign a null value for the length varaible and instead what if we want to just give it default value.
     * One way is to use a simple if else as show below
     * val length = if(s!=null) s.length else 0
     * But what if we need to use the safe operator instead of if else and achieve the above use case.
     * The following scheme illustrates how Elvis operator works. The result is either the left expression if it's not null, or the right expression if the left expression is null.
     * if s.length is null then 0 is returned or else the value of length of string s is returned
     */

    val len = s?.length ?: 0


    /**
     * Safe access control flow analaysis
     * If you explicitly check that the reference is null and call  a fail function which throws an exception or simply return, then afterward you can access without safe access operator.
     * The compiler knows that s verbal is smart cast to a non-null nullable type
     */

    if (s == null) return
    s.length // Here is s is smart cast to a non nullable type and we don't need safe access operator.

    //The smart casting can a happen in many ways.If the compiler knows that the variable is not null or if its reassigned a non null type or we check explictiy and return from control
    var s3: String? = null
    //Do some operation
    //s3.length... gives me error because it still null, now assign some value and compiler noes its non null
    s3 = "abcd"
    s3.length //Its smart casted as compiler knows its not null

    /**
     * If you want you can explicitly throw  the null pointer exception.
     * For this, you use this note null assertion(!!), an operator consisting of two exclamation marks.
     * It throws a null pointer exception if it's operand is null and returns the operand if it's not null.
     * Note that after a not-null assertion, the value smart cost to a non-nullable type. And you can dereference it safely without the need to repeat this assertion.
     * It's worth to warn against not-null assertion operator. By default, try to avoid it. We have it in the language, especially for other use cases where the Kotlin compiler isn't smart enough to infer the right type.
     * There are situations when you have an assumption that the expression is not null but the kotlin compiler cannot infer that for you. And you would rather prefer an exception if for some reasons the assumption is not correct.
     * The logic of assumption must then be localized so that it was hard to break it, or it can depend on external frameworks.
     */

    var s4: String? = null
    println(s4!!) //results in null pointer exception

    s4.length //After null assertion its smart cast to non-nullable type..This is beacuse if s was null then it wud have resulted in a NPE and the next line would have not been executed
    //Since the above  null assertion succeeded , compiler knows that the s4 holds a non nullable value and therefore its smart cast

    /**
     * !! is great to throw NPE when you dnot know about the incoming variable type and it can help you to see directly what might be the cause.
     * That means it doesn't make sense to put two or more not-null assertion operators in one line. As you won't to be able to say which one cause the exception.
     * In general prefer using safe operators. Safe access. Elvis separator or explicit checks to cope with nullability. Use not null assertion only with care.
     */


    /**
     * Example question : what will be the result of below snippet of code? 1 or 2 or 3
     * The right answer is one.
     * Operator precedence plays an important role here. If you need the parentheses, some operators take higher precedence than the other operators.
     * That means that by default, the parenthesis surround plus, not Elvis separator. If you want to call Elvis separator first, then you put the parentheses explicitly.
     * If you are not sure about the right amount of precedence and to make the code more readable, prefer the parentheses in all such confusing use cases
     */

    val x: Int? = 1
    val y: Int = 2
    val sum = x ?: 0 + y
    println(sum)


    /**
     * Under the hood, nullable types are implemented using annotations.
     * The Kotlin compilers simply adds Nullable and NotNullable annotations to the corresponding types usages.
     * Which gives us no performance overheads when using Nullable types
     */

    /**
     * There is another solution to the same Nullability problem which is called Optional or Option types.
     * They are special library classes that store value or absence of of the value and you can check whether the value is available or no.
     * That solves the same problem as nullable types because optional allow you to say explicitly whether the variable can have no value option similar to null or not.
     * Despite nullable types and optional solve the same problem, they are very different in terms of the performance.
     * Optional type is a wrapper that stores the reference to the initial object. For each optional value an extra object is created.
     * At the same time, nullable types don't create any wrappers.They are implemented by annotations
     * Optional is mainly used in Java where there is chances of NPE , but Kotlin handles this with the safe ooperator
     */

    val optionElement = Optional.ofNullable("3")
    println(optionElement.get())

    /**
     * When you use nullable types under the hood, the Kotlin compiler adds additional annotations which are only checked at the compilation time.
     * At run time, nullable string is the same string as our Java string. Both types, string and nullable string corresponds to Java like string type , but with different annotations
     */

    /**
     * A SAFE WAY TO CAST AN EXPRESSION TO TYPE
     * In Java, you use a common pattern to do something with the variable only if it is of specific type.
     * First, you use "instanceof" to check whether the variable is of the required type, then you cast it to this type of restoring the result in a new variable.
     * Eventually, you call members of this type on this new variable.
     * n Kotlin, you can do absolutely the same. "is" is the analogus of instanceof and s is the typecast. However, the explicit cast s is not really needed.
     * You can simply use the initial variable afterward, because it's smart cast to the right type. In this case, you can call String members on the any variable directly.
     * This SMART CAST WORKS ONLY FOR IMMUTABLE VALUES
     */
    val any: Any = 0L

    if (any is String) {
        val d = any as String
        d.toUpperCase()
    }

    //we can see that we dont need to use "as" because variable d is smartcasted to string when we use the "is" operator.This can be modified as shown below

    if (any is String) {
        any.toUpperCase()
    }

    /**
     * We can check whether an object conforms to a given type at runtime by using the is operator or its negated form !is:
     */
    val obj: Any = "asdf"
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) { // same as !(obj is String)
        print("Not a String")
    } else {
        print(obj.length)
    }


    /**
     * Note that smart casts do not work when the compiler cannot guarantee that the variable cannot change between the check and the usage. More specifically, smart casts are applicable according to the following rules:
     * 1 val local variables - always except for local delegated properties;
     * 2 val properties - if the property is private or internal or the check is performed in the same module where the property is declared. Smart casts aren't applicable to open properties or properties that have custom getters;
     * 3 var local variables - if the variable is not modified between the check and the usage, is not captured in a lambda that modifies it, and is not a local delegated property;
     * 4 var properties - never (because the variable can be modified at any time by other code).
     */


    /**
     * Type cast "as", throws an exception if the expression can't be cast, while safe cast as question mark returns null in this case.
     * The safe cast returns either the smart cast value or null as the result. If the value can be cast to the required type, then it is returned.
     * Another way to express the same logic is to use if expression explicitly returning either the same expression or null
     */

    val s5: String? = any as? String
    // under the hood its nothing but
    val s6 = if (any is String) any else null


    val z: Any? = null
    val o: String = z as String
    /**
     * Consider the above example:
     * Note that null cannot be cast to String as this type is not nullable, i.e. if z is null, the code above throws an exception.
     * In order to match Java cast semantics we have to have nullable type at cast right hand side, like
     */
    val o1: String? = z as String?

}