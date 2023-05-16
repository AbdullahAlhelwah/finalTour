package com.example.finaltour;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Tournament> tournaments = new ArrayList<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        loadTournaments();
        HelloApplication.main(args);
        saveTournaments();
    }

    // a method to save the tournaments objects to a file named "tournaments.bin"
    public static void saveTournaments() {
        //if there are no tournament to save, just exit
        if(tournaments == null)
            return;
        try {
            // Open an output stream to save the tournaments to the file
            FileOutputStream fos = new FileOutputStream("tournaments.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write the number of tournaments to the file
            oos.writeInt(tournaments.size());

            // Write each tournament to the file
            for (Tournament tournament : tournaments) {
                oos.writeObject(tournament);
            }

            // Close the output streams
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // a method to load the tournaments objects from a file named "tournaments.bin"
    public static void loadTournaments() throws IOException, ClassNotFoundException {
        // if file does not exist, just exit
        if(!new File("tournaments.bin").exists())
            return;
        // Open an input stream to read from the file
        FileInputStream fis = new FileInputStream("tournaments.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Read the number of tournaments from the file
        int numTournaments = ois.readInt();


        // Read each tournament from the file and store it in the array
        for (int i = 0; i < numTournaments; i++) {
            Tournament tournament = (Tournament) ois.readObject();
            if (tournament instanceof Elimination elimination) {
                tournaments.add(elimination);
            } else if (tournament instanceof RoundRobin roundRobin) {
                tournaments.add(roundRobin) ;
            } else {
                tournaments.add(tournament);
            }
        }

        // Close the input streams
        ois.close();
        fis.close();

    }



}

