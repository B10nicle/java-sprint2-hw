public class LineOfStatement {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private double sumOfOne;

    public LineOfStatement(String itemName, boolean isExpense, int quantity, double sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    @Override
    public String toString() {
        return itemName + " " + isExpense + " " + quantity + " " + sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumOfOne() {
        return sumOfOne;
    }

    public void setSumOfOne(double sumOfOne) {
        this.sumOfOne = sumOfOne;
    }
}
