package com.leaf.base;

public abstract class Person {

    public void eat() {
        // TODO Auto-generated method stub
        System.out.println("person eat something");
        eatmeat();
    }

    private void eatmeat() {
        // TODO Auto-generated method stub
        System.out.println("person eat meat");
        smoke();
    }

    public void smoke() {
        // TODO Auto-generated method stub
        System.out.println("person smoke");
    }

    public static void main(String[] args) {
        // Person p = new Person();
        Man m = new Man();

        m.eat();

    }

}

class Man extends Person {

    @Override
    public void eat() {
        // TODO Auto-generated method stub
        super.eat();
    }

    public void eatmeat() {
        // TODO Auto-generated method stub
        System.out.println("man eat meat");
    }

    public void smoke() {
        // TODO Auto-generated method stub
        System.out.println("man often smoke");
    }
}
