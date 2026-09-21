package tr.com.huseyinaydin.presentation;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;
import tr.com.huseyinaydin.domain.exception.InsufficientBalanceException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TransferHandler implements HttpHandler {
    private final TransferMoneyUseCase transferMoneyUseCase;
    private final Gson gson;

    public TransferHandler(TransferMoneyUseCase transferMoneyUseCase) {
        this.transferMoneyUseCase = transferMoneyUseCase;
        this.gson = new Gson();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }

        try (InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8)) {
            TransferRequest request = gson.fromJson(reader, TransferRequest.class);
            transferMoneyUseCase.transfer(request);

            String response = "{\"status\":\"SUCCESS\"}";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } catch (InsufficientBalanceException e) {
            String error = "{\"error\":\"INSUFFICIENT_BALANCE\"}";
            exchange.sendResponseHeaders(400, error.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(error.getBytes());
            }
        } catch (Exception e) {
            String error = "{\"error\":\"INTERNAL_ERROR\"}";
            exchange.sendResponseHeaders(500, error.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(error.getBytes());
            }
        }
    }
}
