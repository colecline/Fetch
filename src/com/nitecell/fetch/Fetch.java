package com.nitecell.fetch;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Fetch {

    public static String fetchUUID(String username, String time) {
        StringBuilder output = new StringBuilder();
        StringBuilder uuid = new StringBuilder();
        HttpURLConnection connection = null;
        String api = "https://api.mojang.com/users/profiles/minecraft/" + username + "?at=" + time;
        String line;
        BufferedReader reader;

        try {
            TimeUnit.MILLISECONDS.sleep(1100);
            URL url = new URL(api);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                reader.close();
            }
            if (output.length() == 0) { return (""); }

            for (int i = 7; i <= 38; i++) {
                uuid.append(output.toString().charAt(i));
                if (i == 14 || i == 18 || i == 22 || i == 26) uuid.append("-");
            }
            System.out.println("Fetched the UUID of " + username + " which is: " + uuid);
            return (uuid.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return (uuid.toString());
    }

    public static void getUUIDS(List<String> usernames, String time, String outputname) {

        try {
            FileWriter file = new FileWriter(outputname + ".txt");
            PrintWriter print = new PrintWriter(file);

            for (String username : usernames) {
                String uuid = fetchUUID(username, time);
                print.println(uuid);
            }
            print.flush();
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Username List:");
        List<String> usernames = Arrays.asList(input.nextLine().split(","));
        System.out.println(usernames.size() + " usernames have been loaded.");

        System.out.println("Enter UNIX Timestamp:");
        String time = input.nextLine();

        System.out.println("Enter Output File Name:");
        String outputname = input.nextLine();

        getUUIDS(usernames, time, outputname);
        System.out.println("Finished.");



    }

}
