class AnnualRecord {
    private int month;
    private int amount;
    private boolean isExpense;

    //конструктор класса AnnualRecord
    public AnnualRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    //переопределяю метод toString чтобы вывод в консоль был читаемым
    @Override
    public String toString() {
        return month + " " + amount + " " + isExpense;
    }

    //геттер month
    public int getMonth() {
        return month;
    }

    //сеттер month (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setMonth(int month) {
        this.month = month;
    }

    //геттер amount
    public int getAmount() {
        return amount;
    }

    //сеттер amount (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setAmount(int amount) {
        this.amount = amount;
    }

    //геттер expense
    public boolean isExpense() {
        return isExpense;
    }

    //сеттер expense (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
