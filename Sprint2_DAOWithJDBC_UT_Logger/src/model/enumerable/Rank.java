package model.enumerable;

public enum Rank {
    TRAINEE,
    JUNIOR,
    SENIOR,
    INSPECTOR,
    CHIEF_INSPECTOR;

    private int code;

    Rank() {

    }

    Rank(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
