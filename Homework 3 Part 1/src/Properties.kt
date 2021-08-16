import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/** 1. Add a custom setter to PropertyExample.propertyWithCounter so that the counter property
 * is incremented every time a propertyWithCounter is assigned to it.
 * **/
class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value){
            field=value
            counter++
        }
}

/** 2. Add a custom getter to make the val 'lazy' really lazy. It should be initialized by invoking
 * 'initializer()' during the first access.
You can add any additional properties as you need.
Do not use delegated prop
 * **/
class LazyProperty(val initializer: () -> Int) {
    var firstTimeValue: Int? = null
    val lazy: Int
        get() {
            if (firstTimeValue == null) {
                firstTimeValue = initializer()
            }
            return firstTimeValue as Int

        }
}

/** 3. Learn about delegated properties and make the property lazy using delegates.
 * **/
class LazyProperties(val initializer: () -> Int) {
    val lazyValue: Int by lazy(initializer)
}


/** 4. Delegates
You can declare your own delegates. Implement the methods of the class EffectiveDate so you can delegate to it.
Store only the time in milliseconds in the timeInMillis property.
Use the extension functions MyDate.toMillis() and Long.toDate(), defined in MyDate.kt.
 * **/

class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
         return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis=value.toMillis()
    }
}

