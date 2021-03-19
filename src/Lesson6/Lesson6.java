package Lesson6;

public class Lesson6 {
    public static void main(String[] args) {

        Cat jeck = new Cat ("Jeck");
        jeck.swim(300);
        jeck.run(300);
        Dog bobik = new Dog ("Bobik");
        bobik.swim(-10);
        bobik.run(600);
        Dog sten = new Dog ("Sten");
        sten.swim(20);
        sten.run(350);

        System.out.println();
        System.out.println("Количество экземпляров класса Animal и производных равно " + Animal.getInstansesNumber());

    }
}
