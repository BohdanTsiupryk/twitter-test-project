package bts.twitter.service;

import bts.twitter.model.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.key}")
    private String key;

    private ObjectMapper mapper = new ObjectMapper();

    public WeatherData getWeather(String cityName) {
        String forObject = restTemplate.getForObject("https://api.weatherbit.io/v2.0/current?" +
                        "city=" + cityName +
                        "&lang=en" +
                        "&key=" + key,
                String.class);

        try {
            JsonNode jsonNode = mapper.readTree(forObject);
            JsonNode data = jsonNode.get("data").get(0);
            return mapper.readValue(data.toString(), WeatherData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new WeatherData();
    }
}
