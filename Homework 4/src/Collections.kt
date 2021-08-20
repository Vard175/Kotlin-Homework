fun Shop.getSetOfCustomers(): Set<Customer> =
    customers.toSet()

// Return a list of customers, sorted in the descending by number of orders they have made
fun Shop.getCustomersSortedByOrders(): List<Customer> =
    customers.sortedByDescending { it.orders.size}

// Find all the different cities the customers are from
fun Shop.getCustomerCities(): Set<City> =
    customers.map { it.city }.toSet()

// Find the customers living in a given city
fun Shop.getCustomersFrom(city: City): List<Customer> =
    customers.filter{ it.city==city}

// Return true if all customers are from a given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean =
    customers.all { it.city ==city }

// Return true if there is at least one customer from a given city
fun Shop.hasCustomerFrom(city: City): Boolean =
    customers.any { it.city ==city }

// Return the number of customers from a given city
fun Shop.countCustomersFrom(city: City): Int =
    customers.count { it.city ==city }

// Return a customer who lives in a given city, or null if there is none
fun Shop.findCustomerFrom(city: City): Customer? =
    customers.find { it.city ==city }

// Return a customer who has placed the maximum amount of orders
fun Shop.getCustomerWithMaxOrders(): Customer? =
    customers.maxBy { it.orders.size }

// Return the most expensive product that has been ordered by the given customer
fun getMostExpensiveProductBy(customer: Customer): Product? =
    customer.orders.flatMap{order -> order.products  }
        .maxBy { product -> product.price  }

// Return the sum of prices for all the products ordered by a given customer
fun moneySpentBy(customer: Customer): Double =
    customer.orders.flatMap{order -> order.products  }
        .sumByDouble { product -> product.price  }

// Build a map that stores the customers living in a given city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> =
    customers.groupBy { it.city }

// Build a map from the customer name to the customer
fun Shop.nameToCustomerMap(): Map<String, Customer> =
    customers.associateBy { it.name }

// Build a map from the customer to their city
fun Shop.customerToCityMap(): Map<Customer, City> =
    customers.associateWith { it.city }

// Build a map from the customer name to their city
fun Shop.customerNameToCityMap(): Map<String, City> =
    customers.associate { it.name to it.city }

// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> =
    customers.filter {
        val (isDelivered, isNotDelivered) = it.orders.partition { it.isDelivered }
        isDelivered.size<isNotDelivered.size
    }.toSet()

// Return all products the given customer has ordered
fun Customer.getOrderedProducts(): List<Product> =
    orders.flatMap(Order::products)

// Return all products that were ordered by at least one customer
fun Shop.getOrderedProducts(): Set<Product> =
    customers.flatMap { customer -> customer.orders.flatMap(Order::products) }.toSet()

// Find the most expensive product among all the delivered products
// ordered by the customer. Use `Order.isDelivered` flag.
fun findMostExpensiveProductBy(customer: Customer): Product? =
    customer.orders
        .filter { order ->  order.isDelivered }
        .flatMap { order ->  order.products }
        .maxBy { product ->  product.price }


// Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int =
    customers.flatMap { customer -> customer.getOrderedProducts()}
        .count{p ->  p==product}

fun doSomethingWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection.groupBy { s -> s.length }

    val maximumSizeOfGroup = groupsByLength.values.map { group -> group.size }.max()

    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}

// Find the most expensive product among all the delivered products
// ordered by the customer. Use `Order.isDelivered` flag.
fun findMostExpensiveProductBy2(customer: Customer): Product? =
    customer.orders.asSequence()
        .filter { order ->  order.isDelivered }
        .flatMap { order ->  order.products.asSequence() }
        .maxBy{product ->  product.price}


// Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
fun Shop.getNumberOfTimesProductWasOrdered2(product: Product): Int {
    return customers.asSequence()
        .flatMap { customer ->  customer.getOrderedProducts2()}
        .count{p -> p==product}
}

fun Customer.getOrderedProducts2(): Sequence<Product> =
    orders.flatMap(Order::products).asSequence()

