package view;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Principal {
    public static void main(String[] args) {
        String urlString = "https://api.coingecko.com/api/v3/exchange_rates";
        String outputPath = "/tmp/exchange.json"; 

        try {
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
              
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("Arquivo salvo com sucesso em " + outputPath);
            } else {
                System.out.println("Erro na requisição: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}