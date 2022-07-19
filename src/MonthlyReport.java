import java.util.ArrayList;

class MonthlyReport {
    final static byte AVAILABLE_MONTHS = 3;
    ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();
    private static boolean monthReportsHaveNotBeenRead = true;
    int numberOfMonth;
    final static String[] monthsOfYear = {"январь", "февраль", "март",
            "апрель", "май", "июнь", "июль", "август",
            "сентябрь", "октябрь", "ноябрь", "декабрь"};

    //тоже самое что и с AnnualReport, сделал по аналогии после вебинара с Филиппом Ворониным
    public MonthlyReport(int numberOfMonth, String path) {
        this.numberOfMonth = numberOfMonth;
        String content = InputAndFileReader.readFileContentsOrNull(path);
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

    //базовый(пустой) конструктор, так как переопределяю конструктор класса по умолчанию
    public MonthlyReport() {
    }

    //считываю ежемесячные отчеты из папки resources
    public MonthlyReport[] readMonthlyReports() {
        MonthlyReport[] monthlyReports = new MonthlyReport[AVAILABLE_MONTHS];
        for (int i = 0; i < AVAILABLE_MONTHS; i++) {
            if (i < 9) {
                monthlyReports[i] = new MonthlyReport(i + 1,
                        "resources/m.20210" + (i + 1) + ".csv");
            } else {
                monthlyReports[i] = new MonthlyReport(i + 1,
                        "resources/m.2021" + (i + 1) + ".csv");
            }
        }
        return monthlyReports;
    }

    //печатаю в консоль результат если ежемесячные отчеты из папки resources загружены успешно
    private void printResultOfReadingMonthlyReports() {
        System.out.println("\nЕжемесячные отчёты успешно считаны.\n");
    }

    //создаю массив только с доходами за месяц
    public int[] getTotalIncomePerMonth(MonthlyReport[] monthlyReports) {
        int[] totalIncome = new int[AVAILABLE_MONTHS];
        for (int i = 0; i < monthlyReports.length; i++) {
            for (int j = 0; j < monthlyReports[i].monthlyRecords.size(); j++) {
                if (!monthlyReports[i].monthlyRecords.get(j).isExpense()) {
                    totalIncome[i] += monthlyReports[i].monthlyRecords.get(j).getQuantity() *
                            monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                }
            }
        }
        return totalIncome;
    }

    //создаю массив только с расходами за месяц
    public int[] getTotalExpensesPerMonth(MonthlyReport[] monthlyReports) {
        int[] totalExpenses = new int[AVAILABLE_MONTHS];
        for (int i = 0; i < monthlyReports.length; i++) {
            for (int j = 0; j < monthlyReports[i].monthlyRecords.size(); j++) {
                if (monthlyReports[i].monthlyRecords.get(j).isExpense()) {
                    totalExpenses[i] += monthlyReports[i].monthlyRecords.get(j).getQuantity() *
                            monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                }
            }
        }
        return totalExpenses;
    }

    //печатаю ежемесячный отчет для пункта меню №4
    public void printMonthlyInfo(MonthlyReport[] monthlyReports) {
        for (int i = 0; i < monthlyReports.length; i++) {
            int maxIncome = 0;
            int maxExpenses = 0;
            String nameOfMaxIncome = "";
            String nameOfMaxExpenses = "";
            for (int j = 0; j < monthlyReports[i].monthlyRecords.size(); j++) {
                if (!monthlyReports[i].monthlyRecords.get(j).isExpense()) {
                    int income = monthlyReports[i].monthlyRecords.get(j).getQuantity() *
                            monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                    if (income > maxIncome) {
                        maxIncome = income;
                        nameOfMaxIncome = monthlyReports[i].monthlyRecords.get(j).getItemName();
                    }
                } else {
                    int expenses = monthlyReports[i].monthlyRecords.get(j).getQuantity() *
                            monthlyReports[i].monthlyRecords.get(j).getSumOfOne();
                    if (expenses > maxExpenses) {
                        maxExpenses = expenses;
                        nameOfMaxExpenses = monthlyReports[i].monthlyRecords.get(j).getItemName();
                    }
                }
            }
            System.out.println("Самый прибыльный товар за " + monthsOfYear[i] + ": " +
                    nameOfMaxIncome + ". " + "Сумма прибыли: " + maxIncome);
            System.out.println("Самая большая трата за " + monthsOfYear[i] + ": " +
                    nameOfMaxExpenses + ". " + "Сумма траты: " + maxExpenses + "\n");
        }
    }

    //проверка были ли считаны все месячные отчеты
    public void printMonthlyInfoIfMonthlyReportsAreOK(MonthlyReport[] monthlyReports) {
        if (monthReportsHaveNotBeenRead) {
            System.out.println("\nИзвините, предварительно необходимо считать все месячные отчёты.\n");
        } else {
            printMonthlyInfo(monthlyReports);
        }
    }

    //считывание всех месячных отчетов + печать результата + флаг
    public void getMonthlyReports() {
        readMonthlyReports();
        printResultOfReadingMonthlyReports();
        monthReportsHaveNotBeenRead = false;
    }

    //геттер для monthsReportsHaveNotBeenRead
    public boolean isMonthReportsHaveNotBeenRead() {
        return monthReportsHaveNotBeenRead;
    }
}
