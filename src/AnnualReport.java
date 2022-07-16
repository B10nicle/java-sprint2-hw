import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class AnnualReport {
    final static byte AVAILABLE_YEARS = 1;
    private static int firstYearOfReadyAnnualReport = 2021;
    ArrayList<AnnualRecord> annualRecords = new ArrayList<>();
    HashMap<Integer, Integer> totalIncomePerYear = new HashMap<>();
    HashMap<Integer, Integer> totalExpensesPerYear = new HashMap<>();
    MonthlyReport monthlyReport = new MonthlyReport();
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
            annualReports[i] = new AnnualReport(firstYearOfReadyAnnualReport + i, "resources/y." + (firstYearOfReadyAnnualReport + i) + ".csv");
        }
    }

    public void printResultOfReadingAnnualReports() {
        System.out.println("\nЕжегодные отчёты успешно считаны.\n");
    }

    private HashMap<Integer, Integer> getTotalIncomePerYear() {
        for (int i = 0; i < annualReports.length; i++) {
            for (int j = 0; j < annualReports[i].annualRecords.size(); j++) {
                if (!annualReports[i].annualRecords.get(j).isExpense()) {
                    int monthNumber = annualReports[i].annualRecords.get(j).getMonthNumber();
                    int amount = annualReports[i].annualRecords.get(j).getAmount();
                    totalIncomePerYear.put(monthNumber, amount);
                }
            }
        }
        return totalIncomePerYear;
    }

    public void compareMonthlyAndAnnualIncome(MonthlyReport[] monthlyReports) {
        HashMap<Integer, Integer> totalIncomePerYear = getTotalIncomePerYear();
        int[] totalIncomePerMonth = monthlyReport.totalIncomePerMonth(monthlyReports);
        for (int i = 0; i < totalIncomePerYear.values().size(); i++) {
            if (!(totalIncomePerMonth[i] == totalIncomePerYear.get(i + 1))) {
                System.out.println("Обнаружено расхождение доходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + "): " +
                        totalIncomePerMonth[i] + " не равно " + totalIncomePerYear.get(i + 1) + ".");
            } else {
                System.out.println("Операция завершена успешно. Расхождений в доходах в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + ") не обнаружено.\n");
            }
        }
    }

    private HashMap<Integer, Integer> getTotalExpensesPerYear() {
        for (int i = 0; i < annualReports.length; i++) {
            for (int j = 0; j < annualReports[i].annualRecords.size(); j++) {
                if (annualReports[i].annualRecords.get(j).isExpense()) {
                    int monthNumber = annualReports[i].annualRecords.get(j).getMonthNumber();
                    int amount = annualReports[i].annualRecords.get(j).getAmount();
                    totalExpensesPerYear.put(monthNumber, amount);
                }
            }
        }
        return totalExpensesPerYear;
    }

    public void compareMonthlyAndAnnualExpenses(MonthlyReport[] monthlyReports) {
        HashMap<Integer, Integer> totalExpensesPerYear = getTotalExpensesPerYear();
        int[] totalExpensesPerMonth = monthlyReport.totalExpensesPerMonth(monthlyReports);
        for (int i = 0; i < totalExpensesPerYear.values().size(); i++) {
            if (!(totalExpensesPerMonth[i] == totalExpensesPerYear.get(i + 1))) {
                System.out.println("Обнаружено расхождение расходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + "): " +
                        totalExpensesPerMonth[i] + " не равно " + totalExpensesPerYear.get(i + 1) + ".");
            } else {
                System.out.println("Операция завершена успешно. Расхождений в расходах в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + ") не обнаружено.\n");
            }
        }
    }

    public void printAnnualInfo(AnnualReport[] annualReports) {
        getTotalIncomePerYear();
        getTotalExpensesPerYear();
        for (int i = 0; i < annualReports.length; i++) {
            int averageIncome = 0;
            int averageExpenses = 0;
            System.out.println("\nРассматриваемый год: " + (firstYearOfReadyAnnualReport + i));
            for (int j = 0; j < annualReports[i].annualRecords.size(); j++) {
                if (totalIncomePerYear.get(j + 1) != null || totalExpensesPerYear.get(j + 1) != null) {
                    System.out.println("Прибыль за " + MonthlyReport.monthsOfYear[j] + ": " +
                            (totalIncomePerYear.get(j + 1) - totalExpensesPerYear.get(j + 1)));
                    averageIncome += totalIncomePerYear.get(j + 1);
                    averageExpenses += totalExpensesPerYear.get(j + 1);
                }
            }
            System.out.println("Средний доход за все месяцы в году: " + averageIncome / MonthlyReport.AVAILABLE_MONTHS);
            System.out.println("Средний расход за все месяцы в году: " + averageExpenses / MonthlyReport.AVAILABLE_MONTHS + "\n");
        }
    }

    public static int getFirstYearOfReadyAnnualReport() {
        return firstYearOfReadyAnnualReport;
    }

    public static void setFirstYearOfReadyAnnualReport(int firstYearOfReadyAnnualReport) {
        AnnualReport.firstYearOfReadyAnnualReport = firstYearOfReadyAnnualReport;
    }
}
