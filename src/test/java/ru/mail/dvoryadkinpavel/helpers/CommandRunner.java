package ru.mail.dvoryadkinpavel.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRunner {
    /**запуск команды bash
     *
     * @param command команда
     * @return вывод
     */
    public static String Run(String command){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String s = null;
        String y = "";
        while (true) {
            try {
                if (!((s = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            y += (s+"\n");
        }
        return y;
    }
}
