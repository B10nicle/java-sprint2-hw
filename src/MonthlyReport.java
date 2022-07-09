import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class MonthlyReport {
    private static final byte MONTHS = 3;

    void getMonthReports() {
        readMonthReport();
        getTotalIncomePerMonth();
        getTotalExpensesPerMonth();
        System.out.println("\nЕжемесячные отчёты успешно считаны.\n");
    }

    void getYearReports() {
        readYearReports();
        System.out.println("\nЕжегодные отчёты успешно считаны.\n");
    }

    void checkAllReports() {
        printTotalIncomePerMonth();
        printTotalExpensesPerMonth();
        getTotalPerYear(totalIncomePerMonth, totalExpensesPerMonth);
    }

    String[] getFileNames() {
        String[] fileNames = new String[MONTHS];
        for (int i = 0; i < MONTHS; i++) {
            String fileName = "resources/m.20210" + (i + 1) + ".csv";
            fileNames[i] = fileName;
        }
        return fileNames;
    }

    ArrayList<String> monthsData = new ArrayList<>();
    ArrayList<String> yearsData = new ArrayList<>();

    ArrayList<String> readMonthReport() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/m.202101" + ".csv"));
            while ((line = bufferedReader.readLine()) != null) {
                monthsData.add(line);
            }
        } catch (IOException e) {
            System.out.println("\nНевозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.\n");
        }
        return monthsData;
    }


    double totalIncomePerMonth;

    double getTotalIncomePerMonth() {
        totalIncomePerMonth = 0;
        for (String element : monthsData) {
            String[] columns = element.split(",");
            if (columns[1].equalsIgnoreCase("false")) //значит это доходы
                totalIncomePerMonth += Double.parseDouble(columns[2]) * Double.parseDouble(columns[3]);
        }
        return totalIncomePerMonth;
    }

    void printTotalIncomePerMonth() {
        System.out.println("\nОбщие доходы за " + Month.JANUARY.getMonth() + ": " + totalIncomePerMonth);
    }

    double totalExpensesPerMonth;

    double getTotalExpensesPerMonth() {
        totalExpensesPerMonth = 0;
        for (String element : monthsData) {
            String[] columns = element.split(",");
            if (columns[1].equalsIgnoreCase("true"))
                totalExpensesPerMonth += Double.parseDouble(columns[2]) * Double.parseDouble(columns[3]);
        }
        return totalExpensesPerMonth;
    }

    void printTotalExpensesPerMonth() {
        System.out.println("Общие расходы за " + Month.JANUARY.getMonth() + ": " + totalExpensesPerMonth);
    }


    ArrayList<String> readYearReports() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/y.2021" + ".csv"));
            while ((line = bufferedReader.readLine()) != null) {
                yearsData.add(line);
            }
        } catch (IOException e) {
            System.out.println("\nНевозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.\n");
        }
        return yearsData;
    }

    void getTotalPerYear(double totalIncomePerMonth, double totalExpensesPerMonth) {
        for (String element : yearsData) {
            String[] columns = element.split(",");
            if (columns[0].equals("01") && columns[2].equalsIgnoreCase("false")) { //значит это доход за январь
                double totalIncomePerYear = Double.parseDouble(columns[1]);
                if (totalIncomePerYear == totalIncomePerMonth) {
                    System.out.println("Ежемесячные и ежегодные доходы сошлись!");
                } else {
                    System.out.println("Извините, ежемесячная сумма доходов за январь не сходится с суммой дохода, указанной в ежегодном отчёте.");
                }
            } else if (columns[0].equals("01") && columns[2].equalsIgnoreCase("true")) { //значит это расход за январь
                double totalExpensesPerYear = Double.parseDouble(columns[1]);
                if (totalExpensesPerYear == totalExpensesPerMonth) {
                    System.out.println("Ежемесячные и ежегодные расходы сошлись!\n");
                } else {
                    System.out.println("Извините, ежемесячная сумма расходов за январь не сходится с суммой расходов, указанной в ежегодном отчёте.\n");
                }
            }
        }
    }
}
