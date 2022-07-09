public enum MonthOfYear {
    JANUARY("январь"),
    FEBRUARY("февраль"),
    MARCH("март"),
    APRIL("апрель"),
    MAY("май"),
    JUNE("июнь"),
    JULY("июль"),
    AUGUST("август"),
    SEPTEMBER("сентябрь"),
    OCTOBER("октябрь"),
    NOVEMBER("ноябрь"),
    DECEMBER("декабрь");

    private final String month;

    MonthOfYear(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }
}
