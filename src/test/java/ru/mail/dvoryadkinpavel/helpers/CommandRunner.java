package ru.mail.dvoryadkinpavel.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CommandRunner {
    /**запуск команды bash
     *
     * @param command команда
     * @return вывод
     */
    public static String Run(String command) throws UnsupportedEncodingException {
        Process process = null;
        var encoding ="";
        try {
            var os =System.getProperty("os.name");
            if(os.contains("Windows")) {
                process = Runtime.getRuntime().exec("cmd.exe /c "+command);
                encoding = "Cp866";
            }
            else {
                process = Runtime.getRuntime().exec(command);
                encoding = "UTF-8";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream(),encoding));
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
