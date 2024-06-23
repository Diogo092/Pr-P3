package controller;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String filePath = "/tmp/exchange.json"; 
        Scanner scanner = new Scanner(System.in);

        try {
           
            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            System.out.print("Digite a unidade de moeda: ");
            String unit = scanner.nextLine();

            if (jsonObject.has("rates") && jsonObject.getAsJsonObject("rates").has(unit)) {
                JsonObject unitObject = jsonObject.getAsJsonObject("rates").getAsJsonObject(unit);
                System.out.println("Valores para " + unit + ": " + unitObject.toString());
            } else {
                System.out.println("Unidade de moeda não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
