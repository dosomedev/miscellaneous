package com.dosomedev.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.dosomedev.model.Animal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class ApiAccess {
    private static ApiAccess instance;
    private String apiUri;

    private ApiAccess() {
        this.apiUri = "http://localhost:8080/api";
    }

    public static ApiAccess getInstance() {
        if (instance == null) {
            instance = new ApiAccess();
        }
        return instance;
    }

    public Animal[] getAnimals() {
        Animal[] animals = null;

        try {
            // Prepare API URL.
            URI uri = new URI(this.apiUri + "/animals");
            URL url = uri.toURL();

            // Get JSON from web service.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                throw new IOException("Failed to retrieve data from web service.");
            }

            // Convert JSON to object.
            String responseBody = this.loadResponseBody(connection);
            Gson gson = new Gson();
            TypeToken<Animal[]> animalArrayType = new TypeToken<Animal[]>() {};
            animals = gson.fromJson(responseBody, animalArrayType.getType());

        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return animals;
    }

    private String loadResponseBody(HttpURLConnection connection) {
        StringBuilder responseBody = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return responseBody.toString();
    }
}
