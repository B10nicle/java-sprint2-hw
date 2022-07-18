public class Main {
    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        AnnualReport annualReport = new AnnualReport();
        boolean MonthsReportsHaveNotBeenRead = true;
        boolean YearsReportsHaveNotBeenRead = true;
        printMenu();
        String userInput = InputAndFileReader.readUserInput();
        label:
        while (true) {
            switch (userInput) {
                case "1":
                    monthlyReport.readMonthlyReports();
                    monthlyReport.printResultOfReadingMonthlyReports();
                    MonthsReportsHaveNotBeenRead = false;
                    break;
                case "2":
                    annualReport.readAnnualReports();
                    annualReport.printResultOfReadingAnnualReports();
                    YearsReportsHaveNotBeenRead = false;
                    break;
                case "3":
                    if (MonthsReportsHaveNotBeenRead || YearsReportsHaveNotBeenRead) {
                        System.out.println("\nИзвините, предварительно необходимо считать все месячные и " +
                                "ежегодные отчёты.\n");
                    } else {
                        annualReport.compareMonthlyAndAnnualIncome(monthlyReport.monthlyReports);
                        annualReport.compareMonthlyAndAnnualExpenses(monthlyReport.monthlyReports);
                    }
                    break;
                case "4":
                    if (MonthsReportsHaveNotBeenRead) {
                        System.out.println("\nИзвините, предварительно необходимо считать все месячные отчёты.\n");
                    } else {
                        monthlyReport.printMonthlyInfo(monthlyReport.monthlyReports);
                    }
                    break;
                case "5":
                    if (YearsReportsHaveNotBeenRead) {
                        System.out.println("\nИзвините, предварительно необходимо считать все годовые отчёты.\n");
                    } else {
                        annualReport.printAnnualInfo(annualReport.annualReports);
                    }
                    break;
                case "6":
                    System.out.println("Программа завершена");
                    break label;
                default:
                    System.out.println("\nИзвините, данная команда на текущий момент не поддерживается. " +
                            "Выберите одну из команд, указанных ниже:\n");
                    break;
            }
            printMenu();
            userInput = InputAndFileReader.readUserInput();
        }
    }

    private static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать все годовые отчёты");
        System.out.println("3 - Сверить все отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о всех годовых отчётах");
        System.out.println("6 - Выйти из приложения");
    }
}
