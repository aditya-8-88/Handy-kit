import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class HttpPostRequest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter text to detect language: ");
        String h1 = "Detect the language of the following text :";
        String userInput = scanner.nextLine();

        try {
            // Define the URL and request body with the user's input
            String apiKey=""; // Enter your API key here
            String urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="+apiKey;
            String jsonInputString = "{\n" +
                "  \"contents\": [{\n" +
                "    \"parts\":[{\"text\": \"" + h1 + userInput + "\"}]\n" +
                "  }]\n" +
                "}";

            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("POST");

            // Set the request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // To send data in the body

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int statusCode = connection.getResponseCode();
            System.out.println("Response Code: " + statusCode);

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Pretty-print the JSON response
                String formattedResponse = formatJson(response.toString());
                System.out.println("Formatted Response: ");
                System.out.println(formattedResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Method to format JSON response manually
    private static String formatJson(String json) {
        StringBuilder formattedJson = new StringBuilder();
        int indentLevel = 0;
        boolean inQuotes = false;

        for (int i = 0; i < json.length(); i++) {
            char ch = json.charAt(i);

            // Handle quotes to ignore formatting inside strings
            if (ch == '"') {
                inQuotes = !inQuotes;
                formattedJson.append(ch);
                continue;
            }

            // Handle formatting outside quotes
            if (!inQuotes) {
                if (ch == '{' || ch == '[') {
                    formattedJson.append(ch).append("\n");
                    indentLevel++;
                    addIndentation(formattedJson, indentLevel);
                } else if (ch == '}' || ch == ']') {
                    formattedJson.append("\n");
                    indentLevel--;
                    addIndentation(formattedJson, indentLevel);
                    formattedJson.append(ch);
                } else if (ch == ',') {
                    formattedJson.append(ch).append("\n");
                    addIndentation(formattedJson, indentLevel);
                } else {
                    formattedJson.append(ch);
                }
            } else {
                formattedJson.append(ch);
            }
        }

        return formattedJson.toString();
    }

    // Helper method to add indentation
    private static void addIndentation(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  ");
        }
    }
}
