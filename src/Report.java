abstract class Report {
    abstract String readFileContentsOrNull(String fileName);

    abstract void getAllReports();

    abstract void getLines(StringBuilder stringBuilder);

    abstract void printColumn();
}
