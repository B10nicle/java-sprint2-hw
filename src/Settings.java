import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Settings {
    static void showMenu() {
        String userInput = "";
        boolean MonthsReportsHaveNotBeenRead = true;
        boolean YearsReportsHaveNotBeenRead = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        printMenu();
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            if (userInput.equals("1")) {
                monthlyReport.getAllReports();
                MonthsReportsHaveNotBeenRead = false;
            } else if (userInput.equals("2")) {
                yearlyReport.getAllReports();
                YearsReportsHaveNotBeenRead = false;
            } else if (userInput.equals("3")) {
                System.out.println(3);
            } else if (userInput.equals("4")) {
                if (MonthsReportsHaveNotBeenRead) {
                    System.out.println("\nИзвините, предварительно необходимо считать все месячные отчёты.\n");
                } else {
                    monthlyReport.printColumn();
                }
            } else if (userInput.equals("5")) {
                if (YearsReportsHaveNotBeenRead) {
                    System.out.println("\nИзвините, предварительно необходимо считать все годовые отчёты.\n");
                } else {
                    yearlyReport.printColumn();
                }
            } else if (userInput.equals("6")) {
                System.out.println("Программа завершена");
                break;
            } else {
                System.out.println("\nИзвините, данная команда на текущий момент не поддерживается. " +
                        "Выберите одну из команд, указанных ниже:\n");
            }
            printMenu();
            try {
                userInput = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printMenu() {
        System.out.println("1 - Считать месячные отчёты");
        System.out.println("2 - Считать годовые отчёты");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о месячных отчётах");
        System.out.println("5 - Вывести информацию о годовых отчётах");
        System.out.println("6 - Выйти из приложения");
    }
}