class MonthlyRecord {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    //конструктор класса MonthlyRecord
    public MonthlyRecord(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    //переопределяю метод toString чтобы вывод в консоль был читаемым
    @Override
    public String toString() {
        return itemName + " " + isExpense + " " + quantity + " " + sumOfOne;
    }

    //геттер itemName
    public String getItemName() {
        return itemName;
    }

    //сеттер itemName (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    //геттер expense
    public boolean isExpense() {
        return isExpense;
    }

    //сеттер expense (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setExpense(boolean expense) {
        isExpense = expense;
    }

    //геттер quantity
    public int getQuantity() {
        return quantity;
    }

    //сеттер quantity (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //геттер sumOfOne
    public int getSumOfOne() {
        return sumOfOne;
    }

    //сеттер sumOfOne (ввожу на случай расширения программы и необходимости внесения изменений)
    public void setSumOfOne(int sumOfOne) {
        this.sumOfOne = sumOfOne;
    }
}
