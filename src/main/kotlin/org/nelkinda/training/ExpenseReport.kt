package org.nelkinda.training

import java.io.PrintStream
import java.util.Date

enum class ExpenseType(val typeName: String, val limit: Int, val isMeal: Boolean) {
    DINNER("Dinner", 5000, true),
    BREAKFAST("Breakfast", 1001, true),
    CAR_RENTAL("Car Rental", Int.MAX_VALUE, false)
}

class Expense(val type: ExpenseType, val amount: Int) {
    fun isMeal() = type.isMeal

    fun getName(): String {
        return type.typeName
    }

    fun isOverLimit() = amount > type.limit
}

class ExpenseReport(private val out: PrintStream) {
    fun printReport(expenses: List<Expense>, date: Date) {
        printHeader(date)
        printBody(expenses)
        printFooter(expenses)
    }

    private fun printHeader(date: Date) {
        out.println("Expenses $date")
    }

    private fun printBody(expenses: List<Expense>) {
        for (expense in expenses) {
            val mealOverExpensesMarker = if (expense.isOverLimit()) "X" else " "
            out.println("${expense.getName()}	${expense.amount}	$mealOverExpensesMarker")
        }
    }

    private fun printFooter(expenses: List<Expense>) {
        out.println("Meal expenses: ${sumExpenses(expenses) { it.isMeal() }}")
        out.println("Total expenses: ${sumExpenses(expenses) { true }}")
    }

    private fun sumExpenses(expenses: List<Expense>, filterBy: (Expense) -> Boolean): Int {
        return expenses
            .filter { filterBy(it) }
            .map { it.amount }
            .sum()
    }
}
