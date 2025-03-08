The SOLID principles are a set of design principles that help developers create more maintainable, scalable, and testable software.
These principles were introduced by Robert C. Martin (Uncle Bob) and are widely used in object-oriented programming.



1. Single Responsibility Principle (SRP)

A class should have only one reason to change.
A class should only have one responsibility to ensure code maintainability.


/**
 * Class UserManager is reponsible for add the user and Sending Email which violates SRP(Single responsible Principle)
 */
class UserManager{

    fun addUser(name: String , email:String){
        println("UserName ${name} Email ${email}")
    }

    fun sendEmail(email:String){
        println("Email ${email}")
    }
}



******************************************************************************************************************************************

2. Open / Closed Principle

2. Open/Closed Principle (OCP)
Software entities should be open for extension but closed for modification.


/**
 * Suppose here i want to modify the printCopies and PPT functionality,
 * Developer needs to modify the printCopies method , which is not required.
 *
 * Bad Example
 */
class InvoicePrinter{
    fun printCopies(invoiceType:String){
        if(invoiceType=="PDF"){
            println("Printing PDF Invoice")
        }else if(invoiceType=="Excel"){
            println("Printing Excel Invoice")
        }
    }
}


// Good Example

/**
 * Good Example
Use an interface and implement the interface based on the requirements.

 */

 interface InvoicePrinter {
    fun print()
}

class PdfInvoicePrinter : InvoicePrinter {
    override fun print() {
        println("Printed PDF  InVoice Copy ")
    }
}

class ExceInvoicePrinter : InvoicePrinter {
    override fun print() {
        println("Printed Excel InVoice Copy ")
    }
}


fun main() {
    val pdfInvoicePrinter = PdfInvoicePrinter()
    pdfInvoicePrinter.print()

    val exceInvoicePrinter = ExceInvoicePrinter()
    exceInvoicePrinter.print()
}

******************************************************************************************************************************************

Liskov Substitution Principle (LSP) Definition

Example of LSP

// Parent Class
open class Animal {
    open fun makeSound() {
        println("Animal makes a sound")
    }

    open fun move() {
        println("Animal is moving")
    }
}

// Child Class 1
class Dog : Animal() {
    override fun makeSound() {
        println("Dog barks")
    }

    override fun move() {
        println("Dog runs")
    }
}

// Child Class 2
class Cat : Animal() {
    override fun makeSound() {
        println("Cat meows")
    }

    override fun move() {
        println("Cat jumps")
    }
}

// Main function
fun main() {
    val animal1: Animal = Dog()  // Parent reference holding Dog object
    val animal2: Animal = Cat()  // Parent reference holding Cat object

    animal1.makeSound()  // Output: Dog barks
    animal1.move()       // Output: Dog runs

    animal2.makeSound()  // Output: Cat meows
    animal2.move()       // Output: Cat jumps
}



Example 2:- 

Correct Usage of LSP

// Parent Class
open class Animal {
    open fun makeSound() {
        println("Animal makes a sound")
    }

    open fun move() {
        println("Animal is moving")
    }
}

// Child Class 1
class Dog : Animal() {
    override fun makeSound() {
        println("Dog barks")
    }

    override fun move() {
        println("Dog runs")
    }
}

// Child Class 2
class Cat : Animal() {
    override fun makeSound() {
        println("Cat meows")
    }

    override fun move() {
        println("Cat jumps")
    }
}

// Main function
    fun main() {
        val animals: List<Animal> = listOf(Animal(), Dog(), Cat())

        for (animal in animals) {
            animal.makeSound()  // Correct substitution
            animal.move()       // Correct substitution
        }
    }



Study this link for more scenario
https://chatgpt.com/share/67cc38ba-6f10-8006-87b6-10e07759f48e

Important Point.

✅ Why This Code Technically Follows LSP
Cat is a subclass of Animal, so assigning Cat() to an Animal variable is valid.
There is no immediate error, since Cat overrides all necessary methods of Animal.
However, just assigning an object to a variable does not test whether the substitution works correctly.
 The real test of LSP is whether the program can use the animal reference without knowing the exact type and still behave as expected.
******************************************************************************************************************************************

4. Interface Segregation Principle (ISP)

Clients should not be forced to depend on interfaces they do not use.

Example 1

❌ Bad Example (Violates ISP)

interface Worker {
    fun work()
    fun eat()
}

class Developer : Worker {
    override fun work() {
        println("Writing code")
    }

    override fun eat() {
        println("Eating lunch")
    }
}

class Robot : Worker {
    override fun work() {
        println("Building machines")
    }

    override fun eat() {
        throw UnsupportedOperationException("Robots don't eat")
    }
}

Example 2:- 

✅ Good Example (Follows ISP)

interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}

class Developer : Workable, Eatable {
    override fun work() {
        println("Writing code")
    }

    override fun eat() {
        println("Eating lunch")
    }
}
// Now, Robot only implements Workable and is not forced to implement Eatable.
class Robot : Workable {
    override fun work() {
        println("Building machines")
    }
}

******************************************************************************************************************************************
Dependency Inversion Principle (DIP)

High-level modules should not depend on low-level modules. Both should depend on abstractions.

Example 1

❌ Bad Example (Violates DIP)

class MySQLDatabase {
    fun connect() {
        println("Connecting to MySQL")
    }
}

class Application {
    private val database = MySQLDatabase()

    fun start() {
        database.connect()
    }
}
// Here, Application is tightly coupled with MySQLDatabase. If we switch to PostgreSQL, we need to modify the class.

Example 2

✅ Good Example (Follows DIP)

interface Database {
    fun connect()
}

class MySQLDatabase : Database {
    override fun connect() {
        println("Connecting to MySQL")
    }
}

class PostgreSQLDatabase : Database {
    override fun connect() {
        println("Connecting to PostgreSQL")
    }
}

class Application(private val database: Database) {
    fun start() {
        database.connect()
    }
}

fun main() {
    val app = Application(MySQLDatabase())  // We can easily switch to PostgreSQL
    app.start()
}

// Now, Application depends on an abstraction (Database), making it flexible.
******************************************************************************************************************************************