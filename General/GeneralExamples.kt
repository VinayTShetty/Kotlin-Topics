https://chatgpt.com/share/67ca3a3a-8cb8-8006-84ea-3b8c25b0b9fd

Example 1:- This is the example for interface implementing the class.
But Note that in kotlin its not recomended to write the main fuction() inside the class.


In Kotlin, you cannot define the main function inside a class and expect it to run as the program's entry point.

Why?
Kotlin follows a top-level function structure for main(), meaning:

It must be declared outside any class.
If you put it inside a class, it will not be recognized as the entry point.



/**
 * Good Example
   Use an interface and implement the interface based on the requirements.

 */

 interface  InvoicePrinter{
    fun print()
}

class PdfInvoicePrinter:InvoicePrinter{
    override fun print() {
        println("Printed PDF  InVoice Copy ")
    }
}

class ExceInvoicePrinter:InvoicePrinter{
    override fun print() {
        println("Printed Excel InVoice Copy ")
    }
}

class Print{
    fun main(){
        val pdfInvoicePrinter = PdfInvoicePrinter()
        pdfInvoicePrinter.print()

        val exceInvoicePrinter=ExceInvoicePrinter()
        exceInvoicePrinter.print()
    }

}

*****************************************************************************************************************************