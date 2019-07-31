fun main() {
    /**
     * If statement can be used as an expression as shown below.Here If returns a value which can be assigned to a variable
     */
    val a = if (3 > 2) 3 else 2

    /**
     * There are no ternary operators in Kotlin , instead we need to use if as an expression as shown above
     */


    /**
     * When statement is similar to switch statment in java but not extactly same.Consider the following example where it returns a specific expression depending on the enum constants.
     * In Java you have to use switch to acheive the same and you need to use break or return if you want to stop operating inside the branch.
     * Otherwise we will get unexpected results like all the colors will be printed as blue.
     * In Kotlin, you no longer need to use break to say that the operation should stop here. If the rank condition is satisfied, the result of their corresponding branch is returned.
     * NOTE: Its mandatory that the while statement has a else when you are using it as expression in return statement.
     */

    println(getDescription(Color.BLUE))

    /**
     * You can check whether when argument equal to one of the values.You list several values seperated by comma as shown in the method agreeWithMe function
     */

    println(agreeWithMe("y"))
    println(agreeWithMe("no"))
    println(agreeWithMe("nope"))

    /**
     * You can use any expression, not only constants as branch conditions.Here we create a set of two colors and compared with three other set of predefined colors.
     * When Kotlin compares the objects  by equality under the hood it calls the set equals method, which compares the content.
     * We use sets here to ignore the order of the colors. If one of the branch condition is satisfied, return the corresponding result, the color after mix
     * Please see the colorMix function
     */
    colorMix(Color.BLUE, Color.RED)

    /**
     * setOf() - Returns a new read-only set with the given elements.
     * Elements of the set are iterated in the order they were specified. The returned set is serializable (JVM).
     */
    val set1 = setOf(1, 2, 3)
    val set2 = setOf(3, 2, 1)

    // setOf preserves the iteration order of elements
    println(set1) // [1, 2, 3]
    println(set2) // [3, 2, 1]

    // but the sets with the same elements are equal no matter of order
    println("set1 == set2 is ${set1 == set2}")


    /**
     * Sometimes you have a type hierarchy, and need to check whether it's the sub-type or that subtype, and do actions accordingly.
     * Consider the following example where we have a parent class called Pet and child classes Cat and Dog  which extends from Pet class
     */
    val pet: Pet = Cat()
    when (pet) {
        is Cat -> pet.meow()
        is Dog -> pet.woof()
    }

    /**
     * "IS" cast is analogous to Javs instanceof method.If we can see in the above example we DONT need to cast explicitly
     * The variable after you checked its type, its automatically smart cast to the right type.
     * In java you cast the variable to the type after checking instaceOf but in Kotlin we simply access its memebers as it was of the right type
     * For convenience, smart cast expressions are highlighted by the colored background in the ide as shown in the above example.
     */

    /**
     * We can declare a variable inside the whhen statement as shown below.The scope of the variable is just inside the when block
     */

    when (val favPet = Pet()) {
        is Cat -> favPet.meow()
        is Dog -> favPet.woof()
    }


}

enum class Color {
    BLUE, ORANGE, RED
}

fun getDescription(color: Color): String =
    when (color) {
        Color.BLUE -> "cold"
        Color.ORANGE -> "mild"
        Color.RED -> "hot"
    }

fun agreeWithMe(input: String): String =
    when (input) {
        "y", "yes" -> "Thank god you agreed with me"
        "n", "no" -> "It's okay, you'll agree with me eventually"
        else -> "Sorry I cant understand you"
    }

fun colorMix(c1: Color, c2: Color): Color =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.ORANGE) -> Color.BLUE
        setOf(Color.BLUE, Color.RED) -> Color.ORANGE
        else -> Color.BLUE
    }


open class Pet {
    fun doSomeWork() {
        println("Doing nothing")
    }
}

class Dog : Pet() {
    fun woof() {
        println("Woof Woof!")
    }
}


class Cat : Pet() {
    fun meow() {
        println("Meow ,meow!!")
    }
}

