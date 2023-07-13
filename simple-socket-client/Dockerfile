# Sử dụng image Java chứa Java Runtime Environment
FROM eclipse-temurin:17-jdk-alpine
COPY . .
# Sao chép file jar của ứng dụng Socket Client và Socket Server vào image
COPY out/artifacts/simple_socket_client_jar/simple-socket-client.jar client.jar

# Lệnh chạy ứng dụng Socket Server khi container được khởi chạy
CMD ["java", "-jar", "client.jar"]