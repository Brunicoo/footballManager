package Teams;

import Persons.Player;
import Persons.Trainer;

import java.util.ArrayList;
import java.util.Scanner;

public class Ligue {

    static Scanner sc = new Scanner(System.in);

    public static void showLigue(ArrayList<Club> liga) {

        for (int i = 0; i < liga.size(); i++) {

            System.out.println((i + 1) + ". " + liga.get(i).getName() + " " + liga.get(i).getPunts() + " Punts " + liga.get(i).getGoals() + " Gols a favor " + liga.get(i).getNoGoals() + " Gols en contra " + liga.get(i).getNumVictories() + " Victories ");
        }
    }

    public static void donarBaixa(ArrayList<Club> clubs, Club g, ArrayList<Trainer> entrenadorsTransferibles, ArrayList<Player> jugadorsTransferibles) {
        char confirmar;
        boolean exit = false;
        while (!exit) {
            System.out.println("Estas segur que vols donar de baixa aquest equip? Y/N");
            confirmar = sc.next().charAt(0);

            if (confirmar == 'Y' || confirmar == 'y') {

                if (g.getTrainer() == null){


                } else {
                    entrenadorsTransferibles.add(g.getTrainer());
                    g.getTrainer().setTransferibe(true);
                }
                clubs.remove(g);
                System.out.println("Se ha eliminado el equio " + g.getName() + " con exito");
                exit = true;
            } else if (confirmar == 'N' || confirmar == 'n') {
                System.out.println("No s'ha donat de baixa l'equip");
                exit = true;
            } else {
                System.out.println("No es una opcio valida");
            }
        }
    }
}
