package lesson1.thirdTask;

public class BigTask {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println(appleBox.compare(orangeBox));
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());

        orangeBox.dropTo(orangeBox2);
        System.out.println(orangeBox2.getBox().size());
        System.out.println(orangeBox.getBox().size());
    }
}
