import Persons.Player;
import Persons.Trainer;
import Teams.Club;
import Teams.Ligue;
import Persons.Person;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static String red = "\u001B[31m";
    static String blue = "\u001B[34m";
    static String green = "\u001B[32m";
    static String yellow = "\u001B[33m";
    static Scanner sc = new Scanner(System.in);
    static String reset = "\u001B[0m";

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.0");
        ArrayList<Player> players = new ArrayList<>();

        int decision = 0;
        int gestio = 0;
        int eliminar = 0;
        int destitucio = 0;
        int fichar = 0;
        char confirmacio = 0;

        String name = null;
        String surname = null;
        double motivacio = 0;
        double precio = 0;
        int salary = 0;
        int age = 0;
        int dorsal = 0;
        int tornejosGuanyats = 0;
        int fundationYear = 0;
        int d = 0;
        int money = 0;
        boolean selected = false;
        boolean transferible = false;
        String nameStadium = null;
        String presidentName = null;
        String position = null;
        double quality = 0;
        String [] positions = {"POR", "DEF", "MIG", "DAV"};

        boolean exit = false;

        Club g = new Club();
        Club f = new Club();
        Club c = new Club();
        Player p = new Player();
        Player t = new Player();

        ArrayList<Club> liga = new ArrayList<>();
        ArrayList<Club> clubs = new ArrayList<>();
        ArrayList<Trainer> entrenadorsTransferibles = new ArrayList<>();
        ArrayList<Player> jugadorsTransferibles = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Integer> dorsales = new ArrayList<>();

        Club clubProba = new Club("FCB", 1990, "Camp Nou", "Bartomeu", players, money, dorsales);
        Club clubProba2 = new Club("RM", 1990, "Bernabeu", "Florentino", players, money, dorsales);
        Club clubProba3 = new Club("Villareal", 1990, "Camp Nou", "Bartomeu", players, money, dorsales);
        Club clubProba4 = new Club("Atletic", 1990, "Bernabeu", "Florentino", players, money, dorsales);
        Club clubProba5 = new Club("RSociedad", 1990, "Camp Nou", "Bartomeu", players, money, dorsales);
        Club clubProba6 = new Club("ATM", 1990, "Bernabeu", "Florentino", players, money, dorsales);
        Club clubProba7 = new Club("Las Palmas", 1990, "Camp Nou", "Bartomeu", players, money, dorsales);
        Club clubProba8 = new Club("Murcia", 1990, "Bernabeu", "Florentino", players, money, dorsales);
        Club clubProba9 = new Club("PSG", 1990, "Camp Nou", "Bartomeu", players, money, dorsales);
        Club clubProba10 = new Club("MC", 1990, "Bernabeu", "Florentino", players, money, dorsales);

        Player playerproba = new Player("Bruno", "Convalia", 19, 15000, true,  10,  87, 5,"MIG");
        Player playerproba2 = new Player("Mohamed", "Boutanghach", 20, 20000, true, 20,  95, 5,"DAV");
        Player playerproba3 = new Player("Guillem", "Ruano", 20, 20000, true, 10,  65, 5, "POR");


        clubs.add(clubProba);
        clubs.add(clubProba2);
        clubs.add(clubProba3);
        clubs.add(clubProba4);
        clubs.add(clubProba5);
        clubs.add(clubProba6);
        clubs.add(clubProba7);
        clubs.add(clubProba8);
        clubs.add(clubProba9);
        clubs.add(clubProba10);
        jugadorsTransferibles.add(playerproba);
        jugadorsTransferibles.add(playerproba2);
        jugadorsTransferibles.add(playerproba3);
        persons.add(playerproba);
        persons.add(playerproba2);
        persons.add(playerproba3);
        System.out.println(yellow + "Welcome to Football Manager!!!" + reset);

        while (!exit) {
            decision = menu(decision);

            switch (decision) {
                case 1:
                    if (liga.isEmpty()) {
                        System.out.println("No se ha creado ningun equipo, primero crea minimo un equipo para que se refleje en la clasificacion");
                    } else {
                        Collections.sort(liga, Comparator.comparingInt(Club::getPunts).reversed().thenComparingInt(Club::getGoals));
                        Ligue.showLigue(liga);
                    }
                    break;

                case 2:
                    if (clubs.isEmpty()) {
                        System.out.println("Crea algun equip per poder gestionar-ho");

                    } else {
                        g = gestionarClub(g, clubs);
                        gestio = gestioEquip(gestio, g, clubs);

                        switch (gestio) {

                            case 1:
                                Ligue.donarBaixa(clubs, g, entrenadorsTransferibles, jugadorsTransferibles);
                                break;

                            case 2:
                                Club.modificarPresident(g);
                                break;

                            case 3:
                                Club.destituirEntrenador(g, entrenadorsTransferibles);
                                break;

                            case 4:
                                fichar = queVolsFichar();
                                switch (fichar) {
                                    case 1:
                                        f = f.contractarEntrenador(entrenadorsTransferibles, clubs, g, f);
                                        break;

                                    case 2:
                                        p = g.contractarJugador(jugadorsTransferibles, clubs, g, p, players);
                                        break;

                                    case 3:
                                        System.out.println("Volviendo al menu...");
                                        break;

                                    default:
                                        System.out.println("No es una opcion valida");
                                        break;
                                }
                                break;

                            case 5:
                                t = g.transferirJugador(jugadorsTransferibles, g, t, players, dorsales);
                                break;

                            case 0:
                                System.out.println("Volviendo al menu");
                                break;

                            default:
                                System.out.println("No es una opcio valida");
                        }
                    }
                    break;

                case 3:
                    crearEquip(clubs, liga, name, fundationYear, nameStadium, presidentName, players, money, dorsales);
                    break;

                case 4:
                    d = crearJugadorOentrenador();
                    switch (d){
                        case 1:
                            crearJugador(jugadorsTransferibles, persons, name, surname, age, motivacio, salary, precio, transferible, dorsal, quality, positions, position);
                            break;

                        case 2:
                            crearEntrenador(name, surname, age, salary, quality, motivacio, tornejosGuanyats, transferible, entrenadorsTransferibles, persons);
                            break;

                        case 3:
                            System.out.println("Volviendo al menu...");
                            break;

                        default:
                            System.out.println("No es una opcion valida");
                    }
                    break;

                case 5:
                    c = showClubDices(c, clubs, players);
                    break;

                case 6:
                    showDicesPlayer(jugadorsTransferibles, clubs, players);
                    break;

                case 7:
                    reboot(clubs);
                  crearLliga(clubs, liga);
                  disputarLliga(liga);
                  break;

                case 8:
                    sesioEntrenament(persons);
                    break;

                case 0:
                    System.out.println("Has salido!");
                    exit = true;
                    break;

                default:
                    System.out.println("No es una opcio valida");
                    break;
            }
        }
    }

    private static int menu(int decision) {

        System.out.println(yellow + "\tChoose an option:" + reset);
        System.out.println(yellow + "\t1-Veure clasificació de la lliga"+ reset);
        System.out.println(yellow + "\t2-Gestionar equip"+ reset);
        System.out.println(yellow + "\t3-Donar alta equip"+ reset);
        System.out.println(yellow + "\t4-Donar alta jugador/a o entrenador/a"+ reset);
        System.out.println(yellow + "\t5-Consultar dades equip"+ reset);
        System.out.println(yellow + "\t6-Consultar dades jugador"+ reset);
        System.out.println(yellow + "\t7-Disputar nova lliga"+ reset);
        System.out.println(yellow + "\t8-Sesio de entrenament"+ reset);
        System.out.println(yellow + "\t0-Exit"+ reset);
        System.out.print("Decision: ");
        decision = sc.nextInt();
        sc.nextLine();

        return decision;
    }

    private static void crearEquip(ArrayList<Club> clubs, ArrayList<Club> liga, String name, int fundationYear, String nameStadium, String presidentName, ArrayList<Player> players, int money, ArrayList<Integer> dorsales) {
        System.out.print("Nom del equip: ");
        name = sc.nextLine();
        System.out.print("Any de fundacio: ");
        fundationYear = sc.nextInt();
        System.out.print("Nom del estadi: ");
        nameStadium = sc.nextLine();
        sc.nextLine();
        System.out.print("Nom del president: ");
        presidentName = sc.nextLine();

        Club club = new Club(name, fundationYear, nameStadium, presidentName, players, money, dorsales);

        clubs.add(club);
    }

    private static int gestioEquip(int gestio, Club g, ArrayList<Club> clubs) {

        System.out.println("Quina gestio vols dur a terme amb l'equip: " + g.getName());
        System.out.println("1.Donar de baixa l'equip");
        System.out.println("2.Modificar president");
        System.out.println("3.Destituir entrenador/a");
        System.out.println("4.Fitxar entrenador/a o jugador/a");
        System.out.println("5.Transferir jugador/a");
        System.out.println("0.Sortir");
        gestio = sc.nextInt();
        sc.nextLine();

        return gestio;
    }

    private static Club gestionarClub(Club g, ArrayList<Club> clubs) {
        int clubGestionat = 0;

        System.out.println("Que equipo quieres gestionar?");
        for (int i = 0; i < clubs.size(); i++) {
            System.out.println((i + 1) + ". " + clubs.get(i).getName() );
        }
        clubGestionat = sc.nextInt();
        sc.nextLine();
        g = clubs.get(clubGestionat - 1);

        return g;
    }

    private static int queVolsFichar() {
        int fichar = 0;

        System.out.println("Que quieres fichar a un jugador o entrenador");
        System.out.println("1. Entrenador");
        System.out.println("2. Jugador");
        System.out.println("3. Volver");
        fichar = sc.nextInt();
        sc.nextLine();

        return fichar;
    }

    private static int crearJugadorOentrenador(){
        int d;
        System.out.println("Que vols crear:");
        System.out.println("1.Jugador");
        System.out.println("2.Entrenador");
        System.out.println("3.Exit");
        d= sc.nextInt();

        return d;
    }

    private static void crearJugador(ArrayList<Player> jugadorsTransferibles, ArrayList<Person> persons, String name, String surname, int age, double motivacio, int salary, double precio, boolean transferible, int dorsal, double quality, String [] positions, String position){
        boolean exit = false;
        boolean correct = false;
        boolean salir = false;

        sc.nextLine();
        Random random = new Random();
        System.out.print("Name: ");
        name = sc.nextLine();
        System.out.print("Surname: ");
        surname = sc.next();
        System.out.print("Age: ");
        age = sc.nextInt();
        while (!exit) {
            System.out.print("Salary: ");
            salary = sc.nextInt();
            if (salary < 12000){
                System.out.println("El salary minim es de 12000$");
            } else {
                exit = true;
            }
        }
        transferible = true;
        System.out.println("La qualitat es posara de forma random: ");
        quality = random.nextDouble() * (30 + (99 - 30));
        String qual = String.format("%.1f", quality);

        System.out.println("Quality: " + qual);
        System.out.print("Dorsal: ");
        dorsal = sc.nextInt();

        while (!salir) {
                System.out.print("Posicion [POR, DEF, MIG, DAV]: ");
                position = sc.next();

                int i = 0;
                while (!correct && i < Player.getPositions().length) {
                    if (position.equalsIgnoreCase(positions[i])) {
                        position = position.toUpperCase();
                        correct = true;
                        salir = true;
                    } else {
                        i++;
                    }
                }
                if (i >= positions.length) {
                    System.out.println("No se ha encontrado la posicion, introduce otra que este dentro de las indicadas");
                }

            }
        Player player = new Player(name, surname, age, salary, transferible, dorsal, quality, motivacio, position);
        System.out.println("El jugador se ha creado correctamente y ha sido añadido al mercado");
        jugadorsTransferibles.add(player);
        persons.add(player);
        Player.setNumbOfPlayers(Player.getNumbOfPlayers() + 1);


        }

    private static void crearEntrenador (String name, String surname, int age, int salary, double quality, double motivation,int torunamentsWins, boolean transferibe, ArrayList<Trainer> trainersTransferibles, ArrayList<Person> persons){
        boolean exit = false;

        System.out.println("Creant un entrenador...");
        System.out.print("Name: ");
        name = sc.next();
        System.out.print("Surname: ");
        surname = sc.next();
        System.out.print("Age: ");
        age = sc.nextInt();

        while (!exit) {
        System.out.print("Salary");
        salary = sc.nextInt();
            if (salary >= 10000){
                exit = true;
            } else {
                System.out.println("No es un salary just, minim 10.000$");
            }
        }

        System.out.print("Tournaments win?: ");
        torunamentsWins = sc.nextInt();
        transferibe = true;

        Trainer trainer = new Trainer(name, surname, age, salary, quality, motivation, torunamentsWins, transferibe);
        trainersTransferibles.add(trainer);
        persons.add(trainer);
        }

    private static Club showClubDices (Club c, ArrayList<Club> clubs, ArrayList<Player> players){
        String name = null;
        boolean exit = false;
        ArrayList<Player> players1;

        System.out.print("De quin equip vols veure les dades: ");
        name = sc.nextLine();
        while (!exit) {
            for (int i = 0; i < clubs.size(); i++) {
                if (name.equalsIgnoreCase(clubs.get(i).getName())) {
                    players1 = clubs.get(i).getPlayers();
                    c = clubs.get(i);
                    System.out.println("Name: " + c.getName());
                    System.out.println("Fundation year: " + c.getFundationYear());
                    System.out.println("Stadium: " + c.getNameStadium());
                    System.out.println("President name: " + c.getPresidentName());
                    if (c.getTrainer() == null) {
                        System.out.println("Trainer: No hi ha entrenador en el " + c.getName());
                    } else {
                        System.out.println("Trainer: " + c.getTrainer().getName());
                    }

                    if (players.isEmpty()) {
                        System.out.println("JUGADORS: El" + " " + c.getName() + " no en te cap jugador");
                    } else {
                        System.out.println("JUGADORS: ");
                        for (int j = 0; j < c.getPlayers().size(); j++) {
                            System.out.println("[ " + players1.get(j).getName() + " POS: " + players1.get(j).getPosition() + " DORS: " + players1.get(j).getDorsal() + " ]");
                        }
                    }
                    exit = true;
                } else if (i >= clubs.size()) {
                    System.out.println("No se ha encontrado el club.");
                    exit = true;
                }

            }
        }
        return c;
    }

    private static void showDicesPlayer (ArrayList<Player> jugadorsTransferibles, ArrayList<Club> clubs, ArrayList<Player> players) {
        String playerName = null;
        String clubName = null;
        int dorsalPlayer = 0;
        int i = 0;
        boolean exit = false;
        boolean found1 = false;
        boolean found2 = false;

        System.out.print("Digam el nom del club: ");
        clubName = sc.nextLine();

        while (!exit && i < clubs.size()) {
            if (clubName.equalsIgnoreCase(clubs.get(i).getName())) {
                players = clubs.get(i).getPlayers();
                found1 = true;
                System.out.print("Digam el nom del jugador: ");
                playerName = sc.next();
                System.out.print("Dorsal: ");
                dorsalPlayer = sc.nextInt();
                for (int j = 0; j <players.size() ; j++) {
                    if (playerName.equalsIgnoreCase(players.get(j).getName()) && dorsalPlayer == players.get(j).getDorsal()){
                        found2 = true;
                        Player p = players.get(j);
                        System.out.println("Nom: " + p.getName() + " " + p.getSurname());
                        System.out.println("Quality: " + p.getQuality());
                        System.out.println("Dorsal: " + p.getDorsal());
                        System.out.println("Pos: " + p.getPosition());
                        exit = true;
                    }
                }
                if (!found2){
                    System.out.println("No s'ha trobat aquest jugador");
                    exit = true;
                }

            } else {
                i++;
            }
        }
        if (!found1){
            System.out.println("No s'ha trobat l'equip");
        }
    }

    private static void sesioEntrenament (ArrayList<Person> persons){
        for (int i = 0; i <persons.size() ; i++) {
            if (persons.get(i) instanceof Player){
                ((Player)persons.get(i)).entrenament();
            } else {
                ((Trainer)persons.get(i)).entrenament();
            }
        }
    }

    private static void crearLliga (ArrayList <Club> clubs, ArrayList<Club> ligue) {
        int p = 0;
        boolean exit = false;

        if (!ligue.isEmpty()){

            ligue.clear();
        }
        do {
            System.out.println("Cuants equips vols que participin? MIN 10");
            p = sc.nextInt();
        } while (p < 10);

        if (clubs.size() < 10) {
            System.out.println("No hi ha suficients clubs creats, crea mes avans de jugar la lliga");
        } else {
            exit = true;
        }

        while (exit) {
            for (int i = 0; i < p; i++) {
                ligue.add(clubs.get(i));
            }
            exit = false;
            }
    }

    private static void disputarLliga (ArrayList<Club> ligue){


        for (int i = 0; i <ligue.size() ; i++) {
            for (int j = 1; j <ligue.size(); j++) {
                int gols1 = new Random().nextInt(3);
                int gols2 = new Random().nextInt(3);
                System.out.println(ligue.get(i).getName() + " vs " + ligue.get(j).getName());
                if (gols1 > gols2){
                    ligue.get(i).setPunts(ligue.get(i).getPunts() + 3);
                    ligue.get(i).setGoals(ligue.get(i).getGoals() + gols1);
                    ligue.get(i).setNoGoals(ligue.get(i).getNoGoals() + gols2);
                    ligue.get(j).setGoals(ligue.get(j).getGoals() + gols2);
                    ligue.get(j).setNoGoals(ligue.get(j).getNoGoals() + gols1);
                    ligue.get(i).setNumVictories(ligue.get(i).getNumVictories() + 1);
                } else if (gols2 > gols1){
                    ligue.get(j).setPunts(ligue.get(j).getPunts() + 3);
                    ligue.get(j).setGoals(ligue.get(j).getGoals() + gols2);
                    ligue.get(j).setNoGoals(ligue.get(j).getNoGoals() + gols1);
                    ligue.get(i).setGoals(ligue.get(i).getGoals() + gols1);
                    ligue.get(i).setNoGoals(ligue.get(i).getNoGoals() + gols2);
                    ligue.get(j).setNumVictories(ligue.get(j).getNumVictories() + 1);
                } else {
                    ligue.get(i).setPunts(ligue.get(i).getPunts() + 1);
                    ligue.get(i).setGoals(ligue.get(i).getGoals() + gols1);
                    ligue.get(i).setNoGoals(ligue.get(i).getNoGoals() + gols2);
                    ligue.get(j).setPunts(ligue.get(j).getPunts() + 1);
                    ligue.get(j).setGoals(ligue.get(j).getGoals() + gols2);
                    ligue.get(j).setNoGoals(ligue.get(j).getNoGoals() + gols1);
                }
                System.out.println(ligue.get(i).getName() + " " + gols1 + " - " + gols2 + " " + ligue.get(j).getName());

                ligue.get(i).setDiferencesGoalsNoGoals(ligue.get(i).getGoals() - ligue.get(i).getNoGoals());
                ligue.get(j).setDiferencesGoalsNoGoals(ligue.get(j).getGoals() - ligue.get(j).getNoGoals());
            }
        }
    }
    private static void reboot(ArrayList <Club> clubs){
        for (int i = 0; i < clubs.size() ; i++) {
            clubs.get(i).setPunts(0);
            clubs.get(i).setGoals(0);
            clubs.get(i).setNoGoals(0);
            clubs.get(i).setNumVictories(0);
        }
    }
}



