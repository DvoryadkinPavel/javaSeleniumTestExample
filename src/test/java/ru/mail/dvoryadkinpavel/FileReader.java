package ru.mail.dvoryadkinpavel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    /**
     * Чтение содержимого файла
     * @param filePath Путь к файлу
     * @return содержимое
     */
  public static String ReadToString(String filePath){
      String content = "";
      try {
          content = new String(Files.readAllBytes(Path.of(filePath)));
      } catch (IOException e) {
          e.printStackTrace();
      }
      return content;
  }
}
