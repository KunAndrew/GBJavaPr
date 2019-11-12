package lesson1.thirdTask;

import java.util.ArrayList;

public class Box<E extends Fruit> {
    private ArrayList<E> box = new ArrayList<>();

    public ArrayList<E> getBox() {
        return box;
    }

    public void setBox(ArrayList<E> box) {
        this.box = box;
    }

    public void add(E element) {
        box.add(element);
    }

    public float getWeight() {
        if (!getBox().isEmpty()) {
            return getBox().size() * getBox().get(0).getElementWeight();
        } else
            return 0f;
    }

    public boolean compare(Box anotherBox) {
        return ((this.getWeight() - anotherBox.getWeight()) < 0.01);
    }

    public void dropTo(Box<E> anotherBox) {
        while (!this.getBox().isEmpty()) {
            anotherBox.add(this.getBox().get(0));
            this.getBox().remove(0);
        }
    }
}
