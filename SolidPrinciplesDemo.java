package DesignPrinciples;
// Interface Segregation & Dependency Inversion
interface ReportFormatter {
    String format(String data);
}

// A class that formats report in PDF format (Single Responsibility)
class PDFFormatter implements ReportFormatter {
    @Override
    public String format(String data) {
        return "PDF Format: " + data;
    }
}

// A class that formats report in HTML format (Open/Closed - new class doesn't change existing code)
class HTMLFormatter implements ReportFormatter {
    @Override
    public String format(String data) {
        return "HTML Format: " + data;
    }
}

// Report class has only one responsibility: hold report data (Single Responsibility Principle)
class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
}

// High-level module (ReportPrinter) depends on abstraction (ReportFormatter), not concrete classes (Dependency Inversion)
class ReportPrinter {
    private ReportFormatter formatter;

    // Inject formatter dependency via constructor
    public ReportPrinter(ReportFormatter formatter) {
        this.formatter = formatter;
    }

    public void print(Report report) {
        String data = "Title: " + report.getTitle() + "\nContent: " + report.getContent();
        System.out.println(formatter.format(data));
    }
}

// Main class to test all principles
public class SolidPrinciplesDemo {
    public static void main(String[] args) {
        // Create a sample report
        Report report = new Report("Sales Report", "Sales increased by 20% this quarter.");

        // Use PDF Formatter
        ReportPrinter pdfPrinter = new ReportPrinter(new PDFFormatter());
        pdfPrinter.print(report); // Liskov Substitution: You can substitute PDFFormatter or HTMLFormatter freely

        // Use HTML Formatter without modifying existing classes
        ReportPrinter htmlPrinter = new ReportPrinter(new HTMLFormatter());
        htmlPrinter.print(report);
    }
}

