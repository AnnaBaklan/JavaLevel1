package Lesson7;

public class Cat {

    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
        if (p.decreaseFood(appetite)) {
            satiety = true;
        }
    }

    public int getAppetite() {
        return appetite;
    }

    public String getSatiety() {
        return (name + "\t\t" + (satiety ? "сыт" : "голоден"));
    }
}