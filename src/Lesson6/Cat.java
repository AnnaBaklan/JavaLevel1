package Lesson6;

public class Cat extends Animal {

    protected static int maxRunDistance = 200;

    public Cat(String name) {
        super(name);
    }

    @Override
    public void swim(int distance) {
        System.out.println("Коты не умеют плавать!");
    }

    @Override
    protected int getMaxRunDistance () {
        return maxRunDistance;
    }
}
