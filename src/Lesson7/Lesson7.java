package Lesson7;

import java.util.ArrayList;
import java.util.List;

public class Lesson7 {

    private static final int CATS_COUNT = 5;
    public static void main(String[] args) {

        List<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat("Барсик", 15));
        cats.add(new Cat("Рыжик",  25));
        cats.add(new Cat("Тима", 20));
        cats.add(new Cat("Сима", 10));
        cats.add(new Cat("Васька", 30));

        Plate plate = new Plate(50);

        for(Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat.getSatiety());
        }

    }
}
