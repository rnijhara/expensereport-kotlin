package org.nelkinda.training

import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Date
import kotlin.test.assertEquals

class ExpenseReportTest {
    @Test
    fun `it should print expense report`() {
        val expenses = listOf(
            Expense(ExpenseType.DINNER, 5000),
            Expense(ExpenseType.DINNER, 5001),
            Expense(ExpenseType.BREAKFAST, 1000),
            Expense(ExpenseType.BREAKFAST, 1001),
            Expense(ExpenseType.CAR_RENTAL, 2500)
        )
        val out = ByteArrayOutputStream()

        ExpenseReport(PrintStream(out)).printReport(expenses, Date(2020, 2, 21))

        val expectedOutput = "Expenses Sun Mar 21 00:00:00 IST 3920\n" +
                "Dinner\t5000\t \n" +
                "Dinner\t5001\tX\n" +
                "Breakfast\t1000\t \n" +
                "Breakfast\t1001\tX\n" +
                "Car Rental\t2500\t \n" +
                "Meal expenses: 12002\n" +
                "Total expenses: 14502\n"

        assertEquals(expectedOutput, String(out.toByteArray()))
    }
}
