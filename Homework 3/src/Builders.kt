import java.util.HashMap
import javax.swing.UIManager.put

/** 1. Function literals with receiver
Learn about function literals with receiver.
You can declare isEven and isOdd as values, that can be called as extension functions.
Complete the declarations in the code.**/

fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { this%2==0 }
    val isOdd: Int.() -> Boolean = { this%2==1 }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}

/** 2. String and map builders
Function literals with receiver are very useful for creating builders, for example:

fun buildString(build: StringBuilder.() -> Unit): String {
val stringBuilder = StringBuilder()
stringBuilder.build()
return stringBuilder.toString()
}

val s = buildString {
this.append("Numbers: ")
for (i in 1..3) {
// 'this' can be omitted
append(i)
}
}

s == "Numbers: 123"
Implement the function 'buildMap' that takes a parameter (of extension function type) creates a new HashMap, builds it, and returns it as a result.
 * **/

fun buildMap(function: HashMap<Int, String>.() -> Unit): Map<Int, String> {
    var map=HashMap<Int,String>()
    map.function()
    return map
}

fun usage(): Map<Int, String> {
    return buildMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

/** 3. The function apply
The previous examples can be rewritten using the library function apply.
Write your own implementation of this function named 'myApply'.
Learn about the other scope functions and how to use them.
 * **/
fun <T> T.myApply(f: T.() -> Unit): T {
    f()
    return this
}

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

/** 5. Builders: how they work
Answer the questions below

1. In the Kotlin code

tr {
td {
text("Product")
}
td {
text("Popularity")
}
}
'td' is:

a. a special built-in syntactic construct

b. a function declaration

c. a function invocation

2. In the Kotlin code

tr (color = "yellow") {
td {
text("Product")
}
td {
text("Popularity")
}
}
'color' is:

a. a new variable declaration

b. an argument name

c. an argument value

3. The block

{
text("Product")
}
from the previous question is:

a. a block inside built-in syntax construction td

b. a function literal (or "lambda")

c. something mysterious

4. For the code

tr (color = "yellow") {
this.td {
text("Product")
}
td {
text("Popularity")
}
}
which of the following is true:

a. this code doesn't compile

b. this refers to an instance of an outer class

c. this refers to a receiver parameter TR of the function literal:

tr (color = "yellow") {
this@tr.td {
text("Product")
}
}
 * **/
//c b b c