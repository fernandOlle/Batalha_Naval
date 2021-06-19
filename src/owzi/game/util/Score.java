package owzi.game.util;

import java.io.*;
import java.util.ArrayList;

import owzi.game.bean.Player;

public class Score {

    private static Score instance;
    private ArrayList<Player> players = new ArrayList<Player>();

	public Score(){
	}

	public static synchronized Score getInstance(){
		if (instanc == null) {
			instanc = new Score();
		}
		return instanc;
	}

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void saveRank() {

        ArrayList<Player> archive = loadPlayers();

        if (archive != null && !archive.isEmpty()) {
            try {
                FileOutputStream file = new FileOutputStream("ranking.sav");
                ObjectOutputStream stream = new ObjectOutputStream(file);
                // Concatena as listas de jogadores
                players.addAll(archive);
                stream.writeObject(players);
                stream.close();
                file.close();
            } catch (Exception e) {
                System.out.println("Erro ao acessar o arquivo.");
            }
        }

        try {
            File f = new File("ranking.sav");
            FileOutputStream file = new FileOutputStream(f);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(players);
            stream.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Erro ao acessar o arquivo.");
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Player> loadPlayers() {
        ArrayList<Player> game = null;
        try {
            FileInputStream file = new FileInputStream("/home/ranking.sav");
            ObjectInputStream in = new ObjectInputStream(file);
            game = (ArrayList<Player>) in.readObject();
            in.close();
            file.close();
        } catch (Exception e) {

        }
        return game;
    }
}

