package Persons;

import java.util.Random;

public class Player extends Person {
    private int dorsal;
    private static int numbOfPlayers;
    private boolean transferible;
    private double precio;
    private String position;
    private double motivation;
    private static String [] positions = {"POR", "DEF", "MIG", "DAV"};

    public Player(String name, String surname, int age, int salary, boolean transferible, int dorsal, double quality, double motivation, String position) {
        super(name, surname, age, salary);
        this.transferible = transferible;
        this.dorsal = dorsal;
        this.quality = quality;
        this.precio = 1020000 * this.quality;
        this.motivation = 5;
        this.position = position;
        this.motivation = 5;
    }

    public Player(){

    }

    public int getNumber() {
        return dorsal;
    }

    public void setNumber(int number) {
        this.dorsal = dorsal;
    }

    public static int getNumbOfPlayers() {
        return numbOfPlayers;
    }

    public static void setNumbOfPlayers(int numbOfPlayers) {
        Player.numbOfPlayers = numbOfPlayers;
    }

    public boolean isTransferible() {
        return transferible;
    }

    public void setTransferible(boolean transferible) {
        this.transferible = transferible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static String[] getPositions() {
        return positions;
    }

    public static void setPositions(String[] positions) {
        Player.positions = positions;
    }

    @Override
    public void entrenament() {
        super.entrenament();
        int random = new Random().nextInt(99);
        int rPos = new Random().nextInt(3);

        if (random >= 0 && random <= 4){
            setPosition(this.positions[rPos]);
            System.out.println(this.name + " " + this.surname + " New Position: " + this.position);
        }
    }
}
