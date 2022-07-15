public class AnnualRecord {
    private int monthNumber;
    private int amount;
    private boolean isExpense;

    public AnnualRecord(int monthNumber, int amount, boolean isExpense) {
        this.monthNumber = monthNumber;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        return monthNumber + " " + amount + " " + isExpense;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
