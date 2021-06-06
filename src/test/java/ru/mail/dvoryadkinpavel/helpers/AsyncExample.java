package ru.mail.dvoryadkinpavel.helpers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncExample {
    public static AtomicReference<String> weather = new AtomicReference<>("");
    public static Thread WeatherTask(String apikey){
        return new Thread(() -> {
            try {
                RestClientExample.GetActualWeatherJsonString(apikey);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            weather.set(RestClientExample.ParseJsonExample());
        });
    }
}
