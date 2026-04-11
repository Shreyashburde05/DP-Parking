import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;

/**
 * Lightweight web server using Java's built-in HttpServer.
 * No external frameworks needed — just compile and run.
 *
 * Endpoints:
 *   GET  /           -> serves index.html
 *   GET  /api/status -> returns parking lot status as JSON
 *   POST /api/park   -> parks a vehicle (expects: type & plate query params)
 *   POST /api/release -> releases a vehicle (expects: plate & hours query params)
 */
public class ParkingServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Serve the frontend
        server.createContext("/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/") || path.equals("/index.html")) {
                File file = new File("index.html");
                if (!file.exists()) {
                    sendResponse(exchange, 404, "text/plain", "index.html not found");
                    return;
                }
                byte[] bytes = Files.readAllBytes(file.toPath());
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.getResponseBody().close();
            } else {
                sendResponse(exchange, 404, "text/plain", "Not Found");
            }
        });

        // API: Get parking status
        server.createContext("/api/status", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "text/plain", "Method Not Allowed");
                return;
            }
            String json = ParkingManager.getInstance().getStatusJson();
            sendResponse(exchange, 200, "application/json", json);
        });

        // API: Park a vehicle
        server.createContext("/api/park", exchange -> {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "text/plain", "Method Not Allowed");
                return;
            }
            String body = new String(exchange.getRequestBody().readAllBytes());
            String type = extractParam(body, "type");
            String plate = extractParam(body, "plate");

            String result = ParkingManager.getInstance().parkVehicle(type, plate);
            sendResponse(exchange, 200, "application/json", result);
        });

        // API: Release a vehicle
        server.createContext("/api/release", exchange -> {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "text/plain", "Method Not Allowed");
                return;
            }
            String body = new String(exchange.getRequestBody().readAllBytes());
            String plate = extractParam(body, "plate");
            String hoursStr = extractParam(body, "hours");
            int hours = 1;
            try { hours = Integer.parseInt(hoursStr); } catch (Exception ignored) {}

            String result = ParkingManager.getInstance().releaseVehicle(plate, hours);
            sendResponse(exchange, 200, "application/json", result);
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Parking System running at http://localhost:" + port);
    }

    private static void sendResponse(HttpExchange exchange, int code, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    // Simple JSON body param extractor (expects {"key":"value"} format)
    private static String extractParam(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) {
            // try number value
            search = "\"" + key + "\":";
            start = json.indexOf(search);
            if (start == -1) return "";
            start += search.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            if (end == -1) return "";
            return json.substring(start, end).trim();
        }
        start += search.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return "";
        return json.substring(start, end);
    }
}
