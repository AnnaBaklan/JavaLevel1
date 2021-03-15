package Lesson6;

public class Dog extends Animal {

    protected int maxRunDistance = 500;
    protected int maxSwimDistance = 10;


    public Dog(String name) {

        super(name);
    }

    @Override
    protected int getMaxSwimDistance () {
        return maxSwimDistance;
    }

    @Override
    protected int getMaxRunDistance () {
        return maxRunDistance;
    }

}
