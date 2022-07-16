import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

class MonthlyReport {
    final static byte AVAILABLE_MONTHS = 3;
    ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();
    MonthlyReport[] monthlyReports;
    int numberOfMonth;

    public MonthlyReport(int numberOfMonth, String path) {
        this.numberOfMonth = numberOfMonth;
        String content = readFileContentsOrNull(path);
        assert content != null;
        String[] lines = content.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            monthlyRecords.add(new MonthlyRecord(itemName, isExpense, quantity, sumOfOne));
        }
    }

    public MonthlyReport() {
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public void getMonthlyReports() {
        monthlyReports = new MonthlyReport[AVAILABLE_MONTHS];
        for (int i = 0; i < AVAILABLE_MONTHS; i++) {
            if (i < 9) {
                monthlyReports[i] = new MonthlyReport(i + 1, "resources/m.20210" + (i + 1) + ".csv");
            } else {
                monthlyReports[i] = new MonthlyReport(i + 1, "resources/m.2021" + (i + 1) + ".csv");
            }
        }
    }

    public void printResultOfReadingMonthlyReports() {
        System.out.println("\nЕжемесячные отчёты успешно считаны.\n");
    }

    public int[] totalIncomePerMonth(MonthlyReport[] monthlyReports) {
        int[] totalIncome = new int[AVAILABLE_MONTHS];
        for (int i = 0; i < monthlyReports.length; i++) {
            for (int j = 0; j < monthlyReports[i].monthlyRecords.size(); j++) {
                if (!monthlyReports[i].monthlyRecords.get(j).isExpense()) {
                    totalIncome[i] += monthlyReports[i].monthlyRecords.get(j).getQuantity() * monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                }
            }
            System.out.println("Общие доходы за месяц №" + monthlyReports[i].numberOfMonth + ": " + totalIncome[i]);
        }
        System.out.println(Arrays.toString(totalIncome));
        return totalIncome;
    }

    public int[] totalExpensesPerMonth(MonthlyReport[] monthlyReports) {
        int[] totalExpenses = new int[AVAILABLE_MONTHS];
        for (int i = 0; i < monthlyReports.length; i++) {
            for (int j = 0; j < monthlyReports[i].monthlyRecords.size(); j++) {
                if (monthlyReports[i].monthlyRecords.get(j).isExpense()) {
                    totalExpenses[i] += monthlyReports[i].monthlyRecords.get(j).getQuantity() * monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                }
            }
            System.out.println("Общие расходы за месяц №" + monthlyReports[i].numberOfMonth + ": " + totalExpenses[i]);
        }
        System.out.println(Arrays.toString(totalExpenses));
        return totalExpenses;
    }
}
