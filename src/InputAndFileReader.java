import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

class InputAndFileReader {
    //Уважаемый Эркин, данный метод readFileContentsOrNull был предоставлен в самом ТЗ к спринту и, как я понимаю, должен
    //крашить программу если файл не обнаружен по задумке авторов данного спринта

    //считывание содержимого файла
    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    //считывание ввода пользователя
    public static String readUserInput() {
        String userInput = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }
}
