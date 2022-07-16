import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AnnualReport {
    final static byte AVAILABLE_YEARS = 1;
    private static int firstYearOfReadyAnnualReport = 2021;
    ArrayList<AnnualRecord> annualRecords = new ArrayList<>();
    AnnualReport[] annualReports;
    int year;

    public AnnualReport(int year, String path) {
        this.year = year;
        String content = readFileContentsOrNull(path);
        assert content != null;
        String[] lines = content.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            annualRecords.add(new AnnualRecord(month, amount, isExpense));
        }
    }

    public AnnualReport() {
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public void getAnnualReports() {
        annualReports = new AnnualReport[AVAILABLE_YEARS];
        for (int i = 0; i < AVAILABLE_YEARS; i++) {
            annualReports[i] = new AnnualReport(i + firstYearOfReadyAnnualReport, "resources/y.20" + (i + 21) + ".csv");
        }
    }

    public void printResultOfReadingAnnualReports() {
        System.out.println("\nЕжегодные отчёты успешно считаны.\n");
    }

    public void printTable(AnnualReport[] annualReports) {
        for (AnnualReport annualReport : annualReports) {
            System.out.println(annualReport.annualRecords);
        }
    }

    public static int getFirstYearOfReadyAnnualReport() {
        return firstYearOfReadyAnnualReport;
    }

    public static void setFirstYearOfReadyAnnualReport(int firstYearOfReadyAnnualReport) {
        AnnualReport.firstYearOfReadyAnnualReport = firstYearOfReadyAnnualReport;
    }
}
