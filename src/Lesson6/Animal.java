package Lesson6;

public class Animal {
    protected static int maxSwimDistance;
    protected static int maxRunDistance;
    private static int counter;

    private String name;

    public Animal (String name) {
        this.name = name;
        ++counter;
    }

    public void run (int distance) {
        if(checkDistance(distance, getMaxRunDistance ())) {
            System.out.println(name + " пробежал " + distance + " м.");
        }
    }

    public void swim (int distance) {
        if(checkDistance(distance, getMaxSwimDistance ())) {
            System.out.println(name + " проплыл " + distance + " м.");
        }
    }

    protected boolean checkDistance (int distance, int maxDistance)  {
        if (distance > maxDistance) {
            System.out.println("Для " + name + " максимальная дистанция " + maxDistance + " метров.");
            return false;
        } else if (distance < 0) {
            System.out.println("Для инверсии вызовете Нолана");
            return false;
        } else if (distance == 0) {
            System.out.println("На такой дистанции можно лежать, сидеть и стоять");
            return false;
        }
        return true;
    }

    protected int getMaxSwimDistance () {
        return this.maxSwimDistance;
    }

    protected int getMaxRunDistance () {
        return this.maxRunDistance;
    }

    public static int getInstansesNumber () {
        return counter;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        --counter;
    }


}
