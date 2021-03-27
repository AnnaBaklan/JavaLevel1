package Lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void info() {
        System.out.println("Food amount: " + food);
    }

    public int getFood() {
        return food;
    }

    public void setFood (int food) {
        this.food = food;
    }

    public boolean decreaseFood(int amount) {
        if (amount > food || amount <= 0) {
            return false;
        }
        food -= amount;
        return true;
    }

    public void addFood (int food) {
        this.food += food;
    }

}
