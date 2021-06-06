package ru.mail.dvoryadkinpavel;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExampleTest {
    //Пример настройки логгера
    static Logger Log;
    private static final java.util.logging.LogManager LogManager = null;

    static {
        try(FileInputStream ins = new FileInputStream("log.config")){ //полный путь до файла с конфигами
            LogManager.getLogManager().readConfiguration(ins);
            Log = Logger.getLogger(ExampleTest.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    //------------------------

    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", "/home/pasha/Chromedriver/chromedriver");
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void exampleTest() throws IOException, URISyntaxException, InterruptedException {
        driver.get("http://google.ru");
        Assert.assertTrue("Ошибка Selenium driver",driver.getCurrentUrl().contains("google"));

        var currentDirectoryContent = CommandRunner.Run("ls");
        Assert.assertTrue("Не удалось выполнить команду консоли",currentDirectoryContent != "");
        Log.info("Содержимое текущей директории:");
        Log.info(currentDirectoryContent);


        String apikey = FileReader.ReadToString("./src/test/Data/apikey.txt");
        Assert.assertTrue("Не удалось прочитать файл",apikey != "");

        RestClientExample.GetActualWeatherJsonString(apikey);
        var weather = RestClientExample.ParseJsonExample();
        Assert.assertTrue("Не удалось распарсить JSON",weather!="");
        Log.info(weather);

        Log.info("test passed");

    }
    /**
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterClass
    public static void tearDown() {
        driver.quit();
        System.out.println("Test passed");
    }
}
