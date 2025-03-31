package com.joses.mali

import android.graphics.*
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class TenantPdfGenerator {
    fun generatePdf(filePath: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(500, 700, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint()

        // 🔹 Title
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 18f
        paint.color = Color.BLACK
        canvas.drawText("Current Tenants Report", 150f, 40f, paint)

        // 🔹 Subtitle (Current Date)
        paint.textSize = 12f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        canvas.drawText("Generated on: $currentDate", 150f, 70f, paint)

        // 🔹 Table Headers Background
        paint.color = Color.LTGRAY
        canvas.drawRect(30f, 100f, 470f, 130f, paint)

        // 🔹 Table Headers
        paint.color = Color.BLACK
        paint.textSize = 12f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText("Tenant Name", 40f, 120f, paint)
        canvas.drawText("Unit", 180f, 120f, paint)
        canvas.drawText("Rent (Ksh)", 280f, 120f, paint)
        canvas.drawText("Status", 380f, 120f, paint)

        // 🔹 Tenant Data
        paint.typeface = Typeface.DEFAULT
        paint.textSize = 10f
        paint.color = Color.BLACK

        val tenants = listOf(
            listOf("John Kamau", "1", "15,000", "Paid"),
            listOf("Jane Mwangi", "2", "18,000", "Pending"),
            listOf("David Ouma", "3", "14,500", "Paid"),
            listOf("Alice Njoroge", "4", "16,000", "Overdue"),
            listOf("Michael Otieno", "5", "17,500", "Paid")
        )

        var y = 150f
        for (row in tenants) {
            canvas.drawText(row[0], 40f, y, paint)  // Name
            canvas.drawText(row[1], 180f, y, paint) // Unit Number
            canvas.drawText(row[2], 280f, y, paint) // Rent Amount
            canvas.drawText(row[3], 380f, y, paint) // Payment Status
            y += 25f
        }

        // 🔹 Footer
        paint.textSize = 10f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
        paint.color = Color.DKGRAY
        canvas.drawText("Generated by MaliApp", 180f, y + 40f, paint)

        pdfDocument.finishPage(page)

        // 🔹 Save the PDF
        val file = File(filePath)
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()
    }
}
