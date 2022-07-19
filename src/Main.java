public class Main {
    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        AnnualReport annualReport = new AnnualReport();
        printMenu();
        String userInput = InputAndFileReader.readUserInput();
        label:
        while (true) {
            switch (userInput) {
                case "1":
                    monthlyReport.getMonthlyReports();
                    break;
                case "2":
                    annualReport.getAnnualReports();
                    break;
                case "3":
                    annualReport.printComparisonIfReportsAreOK();
                    break;
                case "4":
                    monthlyReport.printMonthlyInfoIfMonthlyReportsAreOK(monthlyReport.readMonthlyReports());
                    break;
                case "5":
                    annualReport.printAnnualInfoIfAnnualReportsAreOK();
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
