
public class JvmOverloadsExample {
    public static void main(String[] args) {
        /**
         * In Kotlin if we need to have overloaded methods then we can achieve it using default and named arguments
         * But we cant use the default parameters that are defined in the kotlin function in java.This is because Kotlin internally knows how to use default parameters but java doesnt
         * So we need to specify the @JvnOverloads annotation
         * Adding this annotation forces it to generate multiple overloads for the function or constructor.
         * This annotaion instructs the Kotlin compiler to generate overloads for this function that substitute default parameter values.
         * If a method has N parameters and M of which have default values, M overloads are generated: the first one takes N-1 parameters (all but the last one that takes a default value),
         * the second takes N-2 parameters, and so on.
         * In the DefaultAndNamedArguments class we have added annotation to sum method but not for arithmeticSeriesSum Smethod so we cant call overloaded function of the latter
         */

        DefaultAndNamedArgumentsKt.sum();
        DefaultAndNamedArgumentsKt.sum(3);
        DefaultAndNamedArgumentsKt.sum(3, 4);
        DefaultAndNamedArgumentsKt.sum(3, 4, 5);
        DefaultAndNamedArgumentsKt.sum(3, 4, 5, 6);

        DefaultAndNamedArgumentsKt.arithmeticSeriesSum(3, 4, 5);
        //DefaultAndNamedArgumentsKt.arithmeticSeriesSum(3); Error: There are no overloaded functions generated for this


        /**
         * If we use the @JvmOverloads on a function which doesnt have any default arguments then there are no overloaded functions that are created
         */

        DefaultAndNamedArgumentsKt.newFunc(3, 4);
        // DefaultAndNamedArgumentsKt.newFunc(3); ror: There are no overloaded functions generated for this function


        //EXTENTION FUNCTIONS EXAMPLE
        /**
         * As we've already discussed, when you call a top-level function from Java, it's simply a static function.
         * For extensions, it works in the same way. When you call an extension function from Java, you call it as a regular static function.
         * You can add a static input and call it without specification, under the hood it's just a regular static function.
         * Here we need to pass the receiver as an argument in java.
         * So if extension functions has 2 parameters then calling from java will add one extra i.e the receiver
         */
        System.out.println(KotlinExtFunctionsKt.lastChar("abc"));

    }
}
