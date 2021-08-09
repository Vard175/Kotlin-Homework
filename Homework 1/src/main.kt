import java.lang.IllegalArgumentException

/** 1. Make the function start return the string "OK". **/
fun start(): String = "OK"

/** 2. Make the function joinOptions() return the list in a JSON format (e.g., "[a, b, c]")
 by specifying only two arguments. **/
fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix="[" , postfix="]" )

/** 3. Imagine, you have several overloads of 'foo()' in Java:

public String foo(String name, int number, boolean toUpperCase) {
return (toUpperCase ? name.toUpperCase() : name) + number;
}
public String foo(String name, int number) {
return foo(name, number, false);
}
public String foo(String name, boolean toUpperCase) {
return foo(name, 42, toUpperCase);
}
public String foo(String name) {
return foo(name, 42);
}
You can replace all these Java overloads with one function in Kotlin.
Change the declaration of the foo function in a way that makes the code using foo compile.
Use default and named arguments. **/
fun foo(name: String, number: Int=42, toUpperCase: Boolean=false) =
    (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

/** 4. Replace the trimIndent call with the trimMargin call taking # as the prefix value,
  so that the resulting string doesn't contain the prefix character. **/
const val question = "life, the universe, and everything"
const val answer = 42

val tripleQuotedString = """
    #question = "$question"
    #answer = $answer""".trimMargin("#")

fun main() {
    println(tripleQuotedString)
}

/** 5. The following pattern matches a date in the format 13.06.1992 (two digits, a dot, two digits, a dot, four digits):

fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
Using the month variable rewrite this pattern in such a way that it matches
the date in the format 13 JUN 1992 (two digits, a whitespace, a month abbreviation, a whitespace, four digits). **/
val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2} $month \d{4}"""

/** 6. rewrite the following Java code so that it only has one if expression:

public void sendMessageToClient(
@Nullable Client client,
@Nullable String message,
@NotNull Mailer mailer
) {
if (client == null || message == null) return;

PersonalInfo personalInfo = client.getPersonalInfo();
if (personalInfo == null) return;

String email = personalInfo.getEmail();
if (email == null) return;

mailer.sendMessage(email, message);
} **/
fun sendMessageToClient(
    client: Client?, message: String?, mailer: Mailer
) {

    val email: String? = client?.personalInfo?.email

    if (email != null && message != null) {
        mailer.sendMessage(email, message)

    }
}

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}

/** 7. Specify Nothing return type for the failWithWrongAge function. **/

fun failWithWrongAge(age: Int?): Nothing {
    throw IllegalArgumentException("Wrong age: $age")
}

fun checkAge(age: Int?) {
    if (age == null || age !in 0..150) failWithWrongAge(age)
    println("Congrats! Next year you'll be ${age + 1}.")
}

/** 8. Pass a lambda to the any function to check if the collection contains an even number.
 * The any function gets a predicate as an argument and returns true
 * if there is at least one element satisfying the predicate. **/
fun containsEven(collection: Collection<Int>): Boolean =
    collection.any { it%2==0 }
