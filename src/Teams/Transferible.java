package Teams;

import Persons.Player;
import Persons.Trainer;

import java.util.ArrayList;

public interface Transferible {
    Club contractarEntrenador(ArrayList<Trainer> entrenadorsTransferibles, ArrayList<Club> clubs, Club c, Club f);

    Player contractarJugador(ArrayList<Player> jugadorsTransferibles, ArrayList<Club> clubs, Club g, Player p, ArrayList<Player> players);

    Player transferirJugador (ArrayList<Player> jugadorsTransferibles, Club g, Player t, ArrayList<Player> players, ArrayList<Integer> dorsales);


}
