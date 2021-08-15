/**Generic functions
Learn about generic functions. Make the code compile by implementing a partitionTo function that splits
a collection into two collections according to the predicate.

There is a partition() function in the standard library that always returns two newly created lists.
You should write a function that splits the collection into two collections given as arguments.
The signature of the toCollection() function from the standard library might help you.
 * **/
import java.util.*

fun <T, C : MutableCollection<T>> Collection<T>.partitionTo(t1: C, t2: C, lambda: (T)->Boolean) : Pair<C,C> {
    for(element in this){
        if(lambda(element)){
            t1.add(element)
        }
        else
            t2.add(element)
    }

    return Pair(t1,t2)
}


fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e")
        .partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
    check(words == listOf("a", "c"))
    check(lines == listOf("a b", "d e"))
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}')
        .partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
    check(letters == setOf('a', 'r'))
    check(other == setOf('%', '}'))
}
//inline fun <T> Iterable<T>.partition(
//    predicate: (T) -> Boolean
//): Pair<List<T>, List<T>>

//data class Person(val name: String, val age: Int) {
//    override fun toString(): String {
//        return "$name - $age"
//    }
//}
//
//val list = listOf(Person("Tom", 18), Person("Andy", 32), Person("Sarah", 22))
//val result = list.partition { it.age < 30 }
//println(result) // ([Tom - 18, Sarah - 22], [Andy - 32])