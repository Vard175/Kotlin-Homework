fun main() {
    printTruthTable { a, b -> a.and_(a.or_(b.not_())) }
}
/** 46. Truth tables for logical expressions. **/
fun Boolean.and_(that: Boolean) = this && that
fun Boolean.or_(that: Boolean) = this || that
fun Boolean.nand_(that: Boolean) = !(this.and_(that))
fun Boolean.nor_(that: Boolean) = !this.or_(that)
fun Boolean.xor_(that: Boolean) = this.xor(that)
fun Boolean.impl_(that: Boolean) = !this.or_(that)
fun Boolean.equ_(that: Boolean) = !(this.xor_(that))
fun Boolean.not_()=!this

fun printTruthTable(operator: (Boolean, Boolean) -> Boolean){
    println("a\t\t b\t\t  result")
    println("_______________________")
    println("${true}\t ${true}\t  ${operator(true, true)}")
    println("${true}\t ${false}\t  ${operator(true, false)}")
    println("${false}\t ${true}\t  ${operator(false, true)}")
    println("${false}\t ${false}\t  ${operator(false, false)}")
}

