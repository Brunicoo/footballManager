package Teams;

import Persons.Player;
import Persons.Trainer;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Club implements Transferible {
    protected String name;
    protected int money;
    protected int fundationYear;
    protected String nameStadium;
    protected int numVictories;
    protected int liguesWin;
    protected int punts;
    protected int goals;
    protected int noGoals;
    protected String presidentName;
    protected int diferencesGoalsNoGoals;
    protected int partits;
    protected static Trainer trainer;
    protected Player player;

    ArrayList<Integer> dorsales;

    ArrayList<Player> players;
    static Scanner sc = new Scanner(System.in);

    public Club(String name, int fundationYear, String nameStadium, String presidentName, ArrayList<Player> players, int money, ArrayList<Integer> dorsales) {
        this.name = name;
        this.fundationYear = fundationYear;
        this.nameStadium = nameStadium;
        this.presidentName = presidentName;
        this.players = players;
        this.money = 500000000;
        this.dorsales = dorsales;
    }

    public Club(int goals, int noGoals, int diferencesGoalsNoGoals){
        this.diferencesGoalsNoGoals = this.goals - this.diferencesGoalsNoGoals;
    }

    public Club() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public int getFundationYear() {
        return fundationYear;
    }

    public void setFundationYear(int fundationYear) {
        this.fundationYear = fundationYear;
    }

    public String getNameStadium() {
        return nameStadium;
    }

    public void setNameStadium(String nameStadium) {
        this.nameStadium = nameStadium;
    }

    public int getNumVictories() {
        return numVictories;
    }

    public void setNumVictories(int numVictories) {
        this.numVictories = numVictories;
    }

    public int getLiguesWin() {
        return liguesWin;
    }

    public void setLiguesWin(int liguesWin) {
        this.liguesWin = liguesWin;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getNoGoals() {
        return noGoals;
    }

    public void setNoGoals(int noGoals) {
        this.noGoals = noGoals;
    }

    public int getDiferencesGoalsNoGoals() {
        return diferencesGoalsNoGoals;
    }

    public void setDiferencesGoalsNoGoals(int diferencesGoalsNoGoals) {
        this.diferencesGoalsNoGoals = diferencesGoalsNoGoals;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPartits() {
        return partits;
    }

    public void setPartits(int partits) {
        this.partits = partits;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Integer> getDorsales() {
        return dorsales;
    }

    public void setDorsales(ArrayList<Integer> dorsales) {
        this.dorsales = dorsales;
    }

    public static void modificarPresident(Club g) {

        System.out.print("Digam el nom del nou president: ");
        g.setPresidentName(sc.next());
        System.out.println("El presiden s'ha modificat correctament, nom: " + g.getPresidentName());
    }

    public static void destituirEntrenador(Club g, ArrayList<Trainer> entrenadorsTransferibles) {

        if (g.getTrainer() == null) {
            System.out.println(g.getName() + " no tiene entrenador para poder ser sustituido");

        } else {
            char sust;
            boolean exit = false;
            while (!exit) {
                System.out.println("Estas segur que vols destituir a" + g.getTrainer().getName() + " Y/N");
                sust = sc.next().charAt(0);
                if (sust == 'Y' || sust == 'y') {
                    entrenadorsTransferibles.add(g.getTrainer());
                    g.getTrainer().setTransferibe(true);
                    g.setTrainer(null);
                    exit = true;
                } else if (sust == 'N' || sust == 'n') {
                    System.out.println("No se ha eliminado a " + g.getTrainer().getName());
                    exit = true;
                } else {
                    System.out.println("No es una opcion valida");
                }
            }
        }

    }

    @Override
    public Club contractarEntrenador(ArrayList<Trainer> entrenadorsTransferibles, ArrayList<Club> clubs, Club g, Club f) {
        int decision = 0, fichar = 0;
        Trainer t;

        System.out.println("Quieres ver el mercado de transferibles, o fichar de un equipo?");
        System.out.println("1.Mercado");
        System.out.println("2.Fichar de un equipo");
        System.out.println("3.Volver");
        decision = sc.nextInt();

        switch (decision) {

            case 1:
                if (entrenadorsTransferibles.isEmpty()) {
                    System.out.println("No hay entrenadores actualmente en el mercado");
                } else {
                    System.out.println("Elije que entrenador quieres fichar");
                    for (int i = 0; i < entrenadorsTransferibles.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadorsTransferibles.get(i).getName());
                    }
                    fichar = sc.nextInt();
                    t = entrenadorsTransferibles.get(fichar - 1);

                    if (g.getTrainer() == null) {
                        g.setTrainer(t);
                        t.setTransferibe(false);
                        entrenadorsTransferibles.remove(t);
                        g.setMoney(this.money -= 1000000);

                    } else {
                        System.out.println("Ya tienes un entrenador. Despidele antes de fichar a otro");
                    }
                }
                break;

            case 2:
                boolean exit = false;
                if (g.getTrainer() == null) {
                    if (g.getMoney() >= 1000000) {
                        for (int i = 0; i < clubs.size(); i++) {
                            if (clubs.get(i).getTrainer() == null){

                            } else {
                                System.out.println(clubs.get(i).getTrainer().getName() + " " + clubs.get(i).getName());
                                exit = true;
                            }
                        }
                        if (!exit){
                            System.out.println("No hay ningun equipo que tenga entrenador");

                        } else {
                            System.out.print("Indica el numero del entrenador que quieres fichar: ");
                            fichar = sc.nextInt();
                            sc.nextLine();
                            f = clubs.get(fichar - 1);
                            t = clubs.get(fichar - 1).getTrainer();

                            t.setTransferibe(false);
                            g.setTrainer(t);
                            f.setTrainer(null);
                            f.setMoney(this.money += 1000000);
                            g.setMoney(this.money -= 1000000);
                            System.out.println("Se ha realizado el traspaso correctamente del entrtenador " + t.getName() + " del club " + f.getName() + " a " + g.getName());
                        }

                    } else {
                        System.out.println("El equipo " + g.getName() + " no tiene dinero suficiente para realizar la transaccion");
                    }

                } else {
                    System.out.println("Ya tienes un entrenador. Despidele antes de fichar a otro");
                }
                break;

            case 3:
                System.out.println("Volviendo al menu");
                break;
        }

        return f;
    }

    @Override
    public Player contractarJugador(ArrayList<Player> jugadorsTransferibles, ArrayList<Club> clubs, Club g, Player p, ArrayList<Player> players) {

            if (jugadorsTransferibles.isEmpty()) {
                System.out.println("No hi ha cap jugador al mercat");
            } else {
                int fichar = 0;

                System.out.println("Presupost de " + g.getName() + ": " + g.getMoney() + "$");

                System.out.println("Escull el jugador que vols fichar (El seu preu dependra de la qualitat");
                for (int i = 0; i < jugadorsTransferibles.size(); i++) {

                    BigDecimal b = new BigDecimal(jugadorsTransferibles.get(i).getPrecio());
                    String qual = String.format("%.1f", jugadorsTransferibles.get(i).getQuality());

                            System.out.println((i + 1) + " " + jugadorsTransferibles.get(i).getName() + " Quality: " + qual + " Precio: " + b.setScale(2, BigDecimal.ROUND_HALF_UP) + "$ " + "Posicion: " + jugadorsTransferibles.get(i).getPosition());
                }
                fichar = sc.nextInt();
                p = jugadorsTransferibles.get(fichar - 1);

                if (g.players.size() >= 11){
                    System.out.println("L'equip ya en té 11 jugadors");
                } else {

                    if (g.getMoney() >= p.getPrecio()) {

                        p = validateDorsal(jugadorsTransferibles, g, p, fichar);

                        BigDecimal bd = new BigDecimal(p.getPrecio());
                        p.setTransferible(false);
                        g.players.add(p);
                        jugadorsTransferibles.remove(fichar - 1);
                        g.setMoney(g.money -= p.getPrecio());
                        System.out.println("Se ha realizado el traspaso correctamente");
                        System.out.println(p.getName() + " ha sido traspado al " + g.getName() + " por una cantidad de " + bd.setScale(2, BigDecimal.ROUND_HALF_UP) + "$");
                        this.dorsales.add(p.getDorsal());

                    } else {
                        System.out.println("No tienes suficiente dinero para fichar a este jugador");
                        System.out.println("Te faltan: " + (p.getPrecio() - g.money) + "$");
                    }
                }
            }


        return p;
    }

    private Player validateDorsal(ArrayList<Player> jugadorsTransferibles, Club g, Player p, int fichar) {
        boolean exit = false;

        while (this.dorsales.contains(p.getDorsal()) && !exit){
            if (this.dorsales.contains(p.getDorsal())) {
                System.out.println("Cambiale el dorsal al jugador, debido a que ya tienes un jugador con el mismo dorsal");
                System.out.print("[ ");
                for (int dorsal : dorsales) {
                    System.out.print(dorsal + ", ");
                }
                System.out.print(" ]");
                System.out.println();
                p.setDorsal(sc.nextInt());
                validateDorsal(jugadorsTransferibles, g, p, fichar);

            } else {
                exit = true;
            }
        }
        return p;
    }

    @Override
    public Player transferirJugador(ArrayList<Player> jugadorsTransferibles, Club g, Player t, ArrayList<Player> players, ArrayList<Integer> dorsales) {
        int transfer = 0;

        if (g.players.isEmpty()) {
            System.out.println("No tienes ningun jugador, ficha o crea uno antes de todo");

        } else {
            for (int i = 0; i < g.players.size(); i++) {
                System.out.println((i + 1) + "." + " Name: " + g.players.get(i).getName() + " Quality: " + g.players.get(i).getQuality() + g.players.get(i).getPrecio());
            }
            transfer = sc.nextInt();
            t = g.players.get(transfer - 1);
            g.players.remove(t);
            jugadorsTransferibles.add(t);
            t.setTransferible(true);
            g.setMoney(g.money += t.getPrecio());
            g.dorsales.remove(t.getDorsal());
            System.out.println("El jugador " + t.getName() + " ha sido traspado al mercado.");
        }

        return t;
    }
}
