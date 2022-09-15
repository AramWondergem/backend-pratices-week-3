package nl.novi.opdrachten.lijsten;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VersleutelNamenLijst {

    /**
     * Bijgevoegd is verzetsleden.txt
     * Maak een programma dat verzetsleden.txt uitleest.
     * Versleutel de namen op dezelfde manier als in GeheimeCode.java
     * En sla de versleutelde namen op in secret.txt
     */
    public static void main(String[] args) {

        File verzetsleden = new File("C:\\Users\\aramw\\Documents\\novi\\backend\\les3\\SD-BE-JP-oefenopdrachten\\src\\nl\\novi\\opdrachten\\lijsten\\verzetsleden.txt");
        List<String> encryptedNames = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            Scanner fileReader = new Scanner(verzetsleden);

            while (fileReader.hasNextLine()) {

                encryptedNames.add(encryption(fileReader.nextLine()));

            }
        } catch (FileNotFoundException e) {
            System.out.println("document does not exist");
        }


        try {
            File encryptednames = new File("C:\\Users\\aramw\\Documents\\novi\\backend\\les3\\SD-BE-JP-oefenopdrachten\\src\\nl\\novi\\opdrachten\\lijsten\\encryptednames.txt");
            FileWriter pen = new FileWriter(encryptednames, true);
            BufferedWriter printer = new BufferedWriter(pen);

            printer.newLine();
            printer.write(dtf.format(now));
            for (String placeHolder : encryptedNames) {
                printer.newLine();
                printer.write(placeHolder);
            }
            printer.close();
        } catch (IOException e) {
            System.out.println("U heeft geen toegang tot de locatie waar u het bestand op wilt slaan. Succes ermee");
        }


    }

    public static String encryption(String name) {
        String encryptedName = "";
        for (int i = 0; i < name.length(); i++) {

            if ((int) name.charAt(i) < 91) {

                int j = (int) name.charAt(i) + 35;
                encryptedName = encryptedName + j;

                System.out.println(encryptedName);
            } else {
                int j = (int) name.charAt(i) - 96;
                encryptedName = encryptedName + j;

                System.out.println(encryptedName);
            }

            if (i == (name.length() - 1)) {
                continue;
            }

            encryptedName = encryptedName + '-';

        }

        return encryptedName;
    }


    //for loop zo lang als het woord
    //index 1 methode om te kijken wat er is op index. --> door switch halen met acht add to end of string new string.
    //met toevoegen - en bij laatste continue toevoegen

}


// Opdracht 2: La Resistance wil niet dat de lijst met namen zo in handen komt te vallen van de bezetter. Versleutel
//         Maak een methode die de lijst op de volgende manier versleuteld:
//         a) Verander elke letter naar het nummer in het alfabet. Voeg na elke veranderde letter een - toe
//         (behalve de laatste). Dus a wordt 1, b wordt 2 et cetera.
//         Wanneer een letter een hoofdletter is, moet je beginnen met tellen bij 100. Dus A wordt 101, B wordt 102.
//         Voorbeeld: Alex wordt versleuteld naar: 101-12-5-24
//         b) Als de positie in de lijst een even getal is, dan moet de cijfercombinatie omgedraaid worden.
//         */