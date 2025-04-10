import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



public class JsonServer {
    private static String jsonFolder;
    public static void main(String[] args) throws IOException {
        int port = 8080; // 设置服务器监听的端口
        if (args.length != 1) {
            System.out.println("Usage: java JsonServer <folder_path>");
            return;
        }
        jsonFolder = args[0];
        Path folderPath = Paths.get(jsonFolder);
        if (!Files.isDirectory(folderPath)) {
            System.err.println("Error: Folder path is not a valid directory.");
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/files/", new FolderHandler());
        server.createContext("/run-java-program", new RunJavaHandler()); // 设置处理 /files/ 开头请求的 Handler
        server.setExecutor(null); // 使用默认的 Executor
        server.start();
        System.out.println("JSON Server started on port " + port + ", serving files from: " + jsonFolder);
    }

    static class RunJavaHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            StringBuilder output = new StringBuilder();
            int responseCode = 200;
            String contentType = "text/plain";

            try {

                /* String javaHome = System.getProperty("java.home");
                String javaBin = javaHome + java.io.File.separator + "bin" + java.io.File.separator + "java";
                String className = "groupproject.class"; // 替换为你的另一个 Java 程序的类名 (假设已编译)
                String mainClassName = "groupproject.groupproject";
                String classpath = "C:/Users/WST/maventest/demo/target/classes/groupproject"; // 假设你的 .class 文件在当前目录     
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/mysql-connector-j-8.0.33.jar";
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/gson-2.8.7.jar";   
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/junit-4.11.jar"; 
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/protobuf-java-3.21.9.jar";
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/hamcrest-core-1.3.jar";
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/CreateFileUtil.class";
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/JsonFormatTool.class";
                classpath += java.io.File.pathSeparator + "C:/Users/WST/maventest/demo/target/classes/groupproject/groupproject.class"; */
                ProcessBuilder builder = new ProcessBuilder("java", "-jar", "C:/Users/WST/maventest/demo/target/demo-1.0-SNAPSHOT.jar");
                
                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                StringBuilder errorOutput = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorOutput.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                System.out.println("被执行的 Java 程序退出代码: " + exitCode);
                System.out.println("被执行的 Java 程序的标准输出:\n" + output.toString());
                System.err.println("被执行的 Java 程序的标准错误输出:\n" + errorOutput.toString());

                if (exitCode != 0) {
                    responseCode = 500;
                    output.append("\nError executing Java program. Exit Code: ").append(exitCode).append("\n");
                    output.append("Error Output:\n").append(errorOutput);
                }

            } catch (IOException | InterruptedException e) {
                responseCode = 500;
                output.append("Error executing Java program: ").append(e.getMessage());
                e.printStackTrace();
            }

            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            byte[] responseBytes = output.toString().getBytes("UTF-8");
            exchange.sendResponseHeaders(responseCode, responseBytes.length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseBytes);
            responseBody.close();
            exchange.close();
        }
    }


    static class FolderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            try {
                String path = exchange.getRequestURI().getPath();
                // 请求路径应该是 /files/your_file.json
                if (path.startsWith("/files/")) {
                    String filename = path.substring("/files/".length());
                    Path filePath = Paths.get(jsonFolder, filename);

                    if (Files.exists(filePath) && Files.isRegularFile(filePath) && filename.toLowerCase().endsWith(".json")) {
                        byte[] jsonData = Files.readAllBytes(filePath);

                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); // 添加 CORS 头部

                        exchange.sendResponseHeaders(200, jsonData.length);
                        OutputStream responseBody = exchange.getResponseBody();
                        responseBody.write(jsonData);
                        responseBody.close();
                        return;
                    }
                }

                // 文件未找到或路径不正确
                String errorMessage = "404 Not Found";
                byte[] errorBytes = errorMessage.getBytes();
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(404, errorBytes.length);
                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(errorBytes);
                responseBody.close();

            } catch (IOException e) {
                String errorMessage = "Error reading or serving file: " + e.getMessage();
                byte[] errorBytes = errorMessage.getBytes();
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(500, errorBytes.length);
                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(errorBytes);
                responseBody.close();
            } finally {
                exchange.close();
            }
        }
    }
}