package com.joses.mali.pdf

interface PdfGenerator {
    fun generatePdf(reportData: String, filePath: String)
}
