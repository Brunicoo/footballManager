package Persons;

import Persons.Person;

public class Trainer extends Person {
    private int torunamentsWins;
    private boolean transferibe;

    public Trainer(String name, String surname, int age, int salary, double quality, double motivation,int torunamentsWins, boolean transferibe) {
        super(name, surname, age, salary);
        this.torunamentsWins = torunamentsWins;
        this.transferibe = transferibe;
        this.motivation = 5;
        this.quality = 50;
    }

    public int getTorunamentsWins() {
        return torunamentsWins;
    }

    public void setTorunamentsWins(int torunamentsWins) {
        this.torunamentsWins = torunamentsWins;
    }

    public boolean isTransferibe() {
        return transferibe;
    }

    public void setTransferibe(boolean transferibe) {
        this.transferibe = transferibe;
    }

    @Override
    public void entrenament() {
        if (torunamentsWins > 0){
            this.motivation += 0.3;
        } else {
            this.motivation += 0.15;

        }
        this.salary = (int) (this.salary + (this.salary * 0.05));
    }
}
