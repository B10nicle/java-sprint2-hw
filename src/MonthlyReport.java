import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class MonthlyReport extends Report {
    private static final byte MONTHS = 3;
    StringBuilder stringBuilder = new StringBuilder();
    String[] lineContents;
    String[] lines;

    @Override
    protected String readFileContentsOrNull(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            System.out.println("\nНевозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.\n");
            return "";
        }
    }

    @Override
    void getAllReports() {
        String fileName;
        String line;
        for (int i = 1; i <= MONTHS; i++) {
            fileName = "resources/m.20210" + i + ".csv";
            line = readFileContentsOrNull(fileName);
            stringBuilder.append(line);
        }
        System.out.println("\nВсе данные по месячным отчётам считаны.\n");
    }

    @Override
    void getLines(StringBuilder stringBuilder) {
        lines = stringBuilder.toString().split(System.lineSeparator());
    }

    @Override
    void printColumn() {
        getLines(stringBuilder);
        for (int i = 1; i < lines.length; i++) {
            lineContents = lines[i].split(",");
            System.out.println(lineContents[1]);
        }
    }
}
