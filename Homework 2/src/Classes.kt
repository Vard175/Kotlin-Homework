import kotlin.random.Random as KRandom
import java.util.Random as JRandom

/** 1. Learn about classes, properties and data classes and then rewrite the following Java code to Kotlin:

public class Person {
private final String name;
private final int age;

public Person(String name, int age) {
this.name = name;
this.age = age;
}

public String getName() {
return name;
}

public int getAge() {
return age;
}
}
 * **/
data class Person(val name:String,val age: Int)

/** 2. Rewrite the following Java code using smart casts and the when expression:

public int eval(Expr expr) {
if (expr instanceof Num) {
return ((Num) expr).getValue();
}
if (expr instanceof Sum) {
Sum sum = (Sum) expr;
return eval(sum.getLeft()) + eval(sum.getRight());
}
throw new IllegalArgumentException("Unknown expression");
}
 **/
fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left)+eval(expr.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

/** 3. Reuse your solution from the previous task, but replace the interface with the sealed class.
 *  Then you no longer need the else branch in when.
 **/
/* NOTE: I put it into comment cause it conflicts with  task 2
fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left)+eval(expr.right)
    }

sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

 */

/** 4. Uncomment the code and make it compile.
 *  Rename Random from the kotlin package to KRandom, and Random from the java package to JRandom. **/
/* NOTE: I put imports in the beginning of the file
import kotlin.random.Random as KRandom
import java.util.Random as JRandom
*/
fun useDifferentRandomClasses(): String {
    return "Kotlin random: " +
            KRandom.nextInt(2) +
            " Java random:" +
            JRandom().nextInt(2) +
            "."
}

/** 5. Learn about extension functions. Then implement the extension functions Int.r() and Pair.r()
 * and make them convert Int and Pair to a RationalNumber.
Pair is a class defined in the standard library:

data class Pair(
val first: A,
val second: B
) **/

fun Int.r(): RationalNumber = RationalNumber(this, 1)

fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first,this.second)

data class RationalNumber(val numerator: Int, val denominator: Int)
