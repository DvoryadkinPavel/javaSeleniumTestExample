package ru.mail.dvoryadkinpavel.helpers;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class RestClientExample {
    private static String responseJsonString = "";
    /**
     * Получение JSON-строки информации о погоде
     * @param apikey
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public static String GetActualWeatherJsonString(String apikey) throws IOException, InterruptedException, URISyntaxException {
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var url = "https://api.openweathermap.org";
        var endpoint ="/data/2.5/weather?q=tomsk&appid="+apikey+"&units=metric&&lang=ru";
        var request = newBuilder(new URI(url+endpoint))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // the response:
        responseJsonString = response.body();
        return responseJsonString;
    }

    /**
     * Пример парсинга JSON-строки
     * @return
     */
    public static String ParseJsonExample(){
        //parsing example
        var res = "";
        if(responseJsonString != "") {
            JSONObject responseObj = new JSONObject(responseJsonString);
            var weatherArr = responseObj.getJSONArray("weather");
            var weatherObj = (JSONObject) weatherArr.get(0);
            var description = weatherObj.getString("description");

            res += "Сейчас ";
            res += description + "\r\n";
            var temp = responseObj.getJSONObject("main").getBigDecimal("temp").toString();
            res += "Температура : " + temp + "С\r\n";
            var wind = responseObj.getJSONObject("wind").getBigDecimal("speed").toString();
            res += "Ветер : " + wind + " м/с\r\n";
        }
        return res;
    }
}
