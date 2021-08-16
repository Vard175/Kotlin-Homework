import java.lang.RuntimeException

fun main() {
    /** 38.  Compare the two methods of calculating Euler's totient function.**/
    println(10.totient())
    println(10.totient2())
    println(45.totient())
    println(45.totient2())
}

/** 31. Determine whether a given integer number is prime **/
fun Int.isPrime(): Boolean {
    if (this == 1) {
        return false;
    }
    for (i in 2..Math.sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) {
            return false;
        }
    }
    return true;
}

/** 32. Determine the greatest common divisor of two positive integer numbers **/
fun gcd(a: Int, b: Int): Int {
    return if (a == 0) b else gcd(b % a, a)
}

/** 33. Determine whether two positive integer numbers are coPrime.**/
fun areCoPrime(a: Int, b: Int) = gcd(a, b) == 1

/** 34. Calculate Euler's totient function phi(m) **/
fun Int.totient(): Int {
    var count = 0
    for (i in 1..this) {
        if (areCoPrime(this, i)) {
            count++
        }
    }
    return count
}

/** 35. Determine prime factors of a given positive integer **/
// NOTE: I didn't use functions above cause this is more efficient
fun Int.getPrimeFactors(): List<Int> {
    var num = this
    val list = mutableListOf<Int>()

    //this cycle is for evens
    while (num % 2 == 0) {
        list.add(2)
        num /= 2
    }
    //at this point we have odd num
    for (i in 3..Math.sqrt(num.toDouble()).toInt() step 2) {
        while (num % i == 0) {
            list.add(i)
            num /= i
        }
    }
    // this is for the case when we got prime even
    if (num > 2) {
        list.add(num)
    }
    return list
}

/** 36. Construct a list containing prime factors and their multiplicity **/
fun Int.getPrimeFactorsMultiplicity() =
    this.getPrimeFactors()
        .groupBy { it }
        .map { Pair(it.key, it.value.size) }

/** 37. Calculate Euler's totient function phi(m) using (2) **/
fun Int.totient2(): Int {
    var phi = 1
    val listOfPrimeMultiplicity = getPrimeFactorsMultiplicity()

    for (i in listOfPrimeMultiplicity) {
        phi *= (i.first - 1) * Math.pow(i.first.toDouble(), (i.second - 1).toDouble()).toInt()
    }
    return phi
}

/** 39. Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range.**/
fun IntRange.getPrimesInRange() =
    this.filter { it.isPrime() }

/** 40. Write a function to find the two prime numbers that sum up to a given even integer **/
fun Int.getGoldbachConjecture(): Pair<Int, Int> {
    if (this % 2 == 1) {
        throw RuntimeException("$this is not even number")
    }

    var list = (1..this).getPrimesInRange()
    var r = 0

    for (i in list) {
        if ((this - i).isPrime()) {
            r = this - i
        }
    }
    return Pair(r, this - r)
}

/** 41. Given a range of integers by its lower and upper limit, print a list of all even numbers and their Goldbach composition**/
fun IntRange.printGoldbachConjecturesInRange() {
    for (n in this) {
        if (n % 2 == 0) {
            var pair = n.getGoldbachConjecture()
            println("$n = ${pair.first} + ${pair.second}")
        }
    }
}