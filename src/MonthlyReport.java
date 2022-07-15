import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class MonthlyReport {
    ArrayList<MonthlyRecord> data = new ArrayList<>();

    public MonthlyReport(int numberOfMonth, String path) {
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
            data.add(new MonthlyRecord(itemName, isExpense, quantity, sumOfOne));
        }
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}





/*
    void printTotalIncomePerMonth() {
        System.out.println("\nОбщие доходы за " + MonthOfYear.JANUARY.getMonth() + ": " + totalIncomePerMonth);
    }

    void printTotalExpensesPerMonth() {
        System.out.println("Общие расходы за " + MonthOfYear.JANUARY.getMonth() + ": " + totalExpensesPerMonth);
    }

    void getTotalPerYear(double totalIncomePerMonth, double totalExpensesPerMonth) {
        for (String element : yearsData) {
            String[] columns = element.split(",");
            if (columns[0].equals("01") && columns[2].equalsIgnoreCase("false")) { //значит это доход за январь
                double totalIncomePerYear = Double.parseDouble(columns[1]);
                if (totalIncomePerYear == totalIncomePerMonth) {
                    System.out.println("Ежемесячные и ежегодные доходы сошлись!");
                } else {
                    System.out.println("Извините, ежемесячная сумма доходов за " + MonthOfYear.JANUARY.getMonth() + " не сходится с суммой дохода, указанной в ежегодном отчёте.");
                }
            } else if (columns[0].equals("01") && columns[2].equalsIgnoreCase("true")) { //значит это расход за январь
                double totalExpensesPerYear = Double.parseDouble(columns[1]);
                if (totalExpensesPerYear == totalExpensesPerMonth) {
                    System.out.println("Ежемесячные и ежегодные расходы сошлись!\n");
                } else {
                    System.out.println("Извините, ежемесячная сумма расходов за " + MonthOfYear.JANUARY.getMonth() + " не сходится с суммой расходов, указанной в ежегодном отчёте.\n");
                }
            }
        }
    }

    ArrayList<MonthlyRecord> january = new ArrayList<>();

    void createLineOfStatement() {
        try {
            String data = new String(Files.readAllBytes(Paths.get("resources/m.202101.csv")));
            String[] lines = data.split(System.lineSeparator());
            String[] clean = new String[lines.length - 1];
            for (int i = 1; i < lines.length; i++) {
                clean[i - 1] = lines[i];
            }

            for (String line : clean) {
                String[] columns = line.split(",");
                january.add(new MonthlyRecord(columns[0], Boolean.parseBoolean(columns[1]), Integer.parseInt(columns[2]), Integer.parseInt(columns[3])));
            }

            for (MonthlyRecord record : january) {
                System.out.println(record.getQuantity() * record.getSumOfOne());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/