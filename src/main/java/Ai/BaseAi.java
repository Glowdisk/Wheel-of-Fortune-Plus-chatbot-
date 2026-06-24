package Ai;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class BaseAi {

        Properties prop = new Properties();
        // Instantiate the client. The client by default uses the Gemini API. It
        //  gets the API key from the environment variable `GOOGLE_API_KEY`.
        Client davidAi = new Client();

        public BaseAi() {


        try (InputStream input = BaseAi.class.getResourceAsStream("/ai.properties")) {

            prop.load(input);

            // Make sure the key here matches the left side of your equal sign inside ai.properties
            String apiKey = prop.getProperty("GEMINI_API_KEY");

            this.davidAi = Client.builder().apiKey(apiKey).build();

        } catch (Exception e) {
            System.out.println("Error: Unable to load properties file");
        }

        }
        public String messageBack(String prompt){
            GenerateContentConfig davidAiConfig = GenerateContentConfig.builder()
                    .systemInstruction(
                            Content.fromParts(Part.fromText(AiDirections.setInstructions()))
                    )
                    .build();

            Chat chatWithDavidAi = davidAi.chats.create("gemini-3.1-flash-lite", davidAiConfig);
                                        // Or could use "gemini-3.1-flash-lite" or "gemini-3.5-flash", "gemini-2.5-flash-lite"

            try {
                GenerateContentResponse response =
                        chatWithDavidAi.sendMessage(prompt);

                // Gets the text string from the response by the quick accessor method `text()`.
                String text = response.text();


                response
                .sdkHttpResponse()
                .ifPresent(
                        httpResponse ->
                                System.out.println("Response headers: " + httpResponse.headers().orElse(null)));

                return "AI: " + text + "\n" + "\n";
            } catch (Exception e) {
                return "ERROR: " + e.getMessage();
            }

        }
    }

