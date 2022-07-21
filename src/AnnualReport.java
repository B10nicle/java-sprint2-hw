import java.util.ArrayList;
import java.util.HashMap;

class AnnualReport {
    final static byte AVAILABLE_YEARS = 1;
    private static int firstYearOfReadyAnnualReport = 2021;
    ArrayList<AnnualRecord> annualRecords = new ArrayList<>();
    HashMap<Integer, Integer> totalIncomePerYear = new HashMap<>();
    HashMap<Integer, Integer> totalExpensesPerYear = new HashMap<>();
    private static boolean yearReportsHaveNotBeenRead = true;
    MonthlyReport monthlyReport = new MonthlyReport();
    AnnualReport[] annualReports;
    int year;

    //данный фрагмент кода(конструктор) был предоставлен Филиппом Ворониным на вебинаре к этому спринту
    public AnnualReport(int year, String path) {
        this.year = year;
        String content = InputAndFileReader.readFileContentsOrNull(path);
        if (!content.equals("")) {
            System.out.println("Ежегодный отчёт успешно считан.\n");
        }
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

    //базовый(пустой) конструктор, так как переопределяю конструктор класса по умолчанию
    public AnnualReport() {
    }

    //считываю ежегодные отчеты из папки resources
    public void readAnnualReports() {
        annualReports = new AnnualReport[AVAILABLE_YEARS];
        for (int i = 0; i < AVAILABLE_YEARS; i++) {
            annualReports[i] = new AnnualReport(firstYearOfReadyAnnualReport + i,
                    "resources/y." + (firstYearOfReadyAnnualReport + i) + ".csv");
        }
    }

    //создаю маппу с данными по доходам за год (ключ - номер месяца, значение - размер дохода)
    private HashMap<Integer, Integer> getTotalIncomePerYear() {
        for (int i = 0; i < annualReports.length; i++) {
            for (int j = 0; j < annualReports[i].annualRecords.size(); j++) {
                if (!annualReports[i].annualRecords.get(j).isExpense()) {
                    int month = annualReports[i].annualRecords.get(j).getMonth();
                    int amount = annualReports[i].annualRecords.get(j).getAmount();
                    totalIncomePerYear.put(month, amount);
                }
            }
        }
        return totalIncomePerYear;
    }

    //сравниваю ежемесячные и ежегодные доходы + печатаю результат по итогу сравнения
    public void compareMonthlyAndAnnualIncome(MonthlyReport[] monthlyReports) {
        HashMap<Integer, Integer> totalIncomePerYear = getTotalIncomePerYear();
        int[] totalIncomePerMonth = monthlyReport.getTotalIncomePerMonth(monthlyReports);
        for (int i = 0; i < totalIncomePerYear.values().size(); i++) {
            if (!(totalIncomePerMonth[i] == totalIncomePerYear.get(i + 1))) {
                System.out.println("Обнаружено расхождение доходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + "): " +
                        totalIncomePerMonth[i] + " не равно " + totalIncomePerYear.get(i + 1) + ".");
            } else {
                System.out.println("Расхождений доходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + ") не обнаружено.\n");
            }
        }
    }

    //создаю маппу с данными по расходам за год (ключ - номер месяца, значение - размер расходов)
    private HashMap<Integer, Integer> getTotalExpensesPerYear() {
        for (int i = 0; i < annualReports.length; i++) {
            for (int j = 0; j < annualReports[i].annualRecords.size(); j++) {
                if (annualReports[i].annualRecords.get(j).isExpense()) {
                    int month = annualReports[i].annualRecords.get(j).getMonth();
                    int amount = annualReports[i].annualRecords.get(j).getAmount();
                    totalExpensesPerYear.put(month, amount);
                }
            }
        }
        return totalExpensesPerYear;
    }

    //сравниваю ежемесячные и ежегодные расходы + печатаю результат по итогу сравнения
    public void compareMonthlyAndAnnualExpenses(MonthlyReport[] monthlyReports) {
        HashMap<Integer, Integer> totalExpensesPerYear = getTotalExpensesPerYear();
        int[] totalExpensesPerMonth = monthlyReport.getTotalExpensesPerMonth(monthlyReports);
        for (int i = 0; i < totalExpensesPerYear.values().size(); i++) {
            if (!(totalExpensesPerMonth[i] == totalExpensesPerYear.get(i + 1))) {
                System.out.println("Обнаружено расхождение расходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + "): " +
                        totalExpensesPerMonth[i] + " не равно " + totalExpensesPerYear.get(i + 1) + ".");
            } else {
                System.out.println("Расхождений расходов в месяце №" +
                        (i + 1) + "(" + MonthlyReport.monthsOfYear[i] + ") не обнаружено.\n");
            }
        }
    }

    //печатаю ежегодный отчет для пункта меню №5
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

    //проверка были ли считаны все ежегодные отчеты
    public void printAnnualInfoIfAnnualReportsAreOK() {
        if (yearReportsHaveNotBeenRead) {
            System.out.println("\nИзвините, предварительно необходимо считать все годовые отчёты.\n");
        } else {
            printAnnualInfo(annualReports);
        }
    }

    //проверка были ли считаны все ежегодные и месячные отчеты
    public void printComparisonIfReportsAreOK(MonthlyReport[] monthlyReports) {
        if (monthlyReport.isMonthReportsHaveNotBeenRead() || isYearReportsHaveNotBeenRead()) {
            System.out.println("\nИзвините, предварительно необходимо считать все месячные и " +
                    "ежегодные отчёты.\n");
        } else {
            compareMonthlyAndAnnualIncome(monthlyReports);
            compareMonthlyAndAnnualExpenses(monthlyReports);
        }
    }

    //считывание всех ежегодных отчетов + флаг
    public void getAnnualReports() {
        readAnnualReports();
        yearReportsHaveNotBeenRead = false;
    }

    //геттер для firstYearOfReadyAnnualReport, первого года, с которого будет начинаться программа, сейчас это 2021 год
    public static int getFirstYearOfReadyAnnualReport() {
        return firstYearOfReadyAnnualReport;
    }

    //сеттер для firstYearOfReadyAnnualReport
    public static void setFirstYearOfReadyAnnualReport(int firstYearOfReadyAnnualReport) {
        AnnualReport.firstYearOfReadyAnnualReport = firstYearOfReadyAnnualReport;
    }

    //геттер для yearsReportsHaveNotBeenRead
    public static boolean isYearReportsHaveNotBeenRead() {
        return yearReportsHaveNotBeenRead;
    }
}
