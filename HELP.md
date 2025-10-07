# Chat Application - Help Guide

## Service Ports

The Chat Application consists of multiple microservices, each running on its own port:

| Service | Port | Description | URL |
|---------|------|-------------|-----|
| Discovery Service (Eureka) | 8761 | Service registry for microservices | http://localhost:8761 |
| API Gateway | 8080 | Main entry point for all client requests | http://localhost:8080 |
| Auth Service | 8081 | Authentication and authorization service | http://localhost:8081 |

## Getting Started

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose
- Gradle

### Running with Docker Compose

1. **Start all services:**
   ```bash
   docker-compose up
   ```

2. **Start services in detached mode:**
   ```bash
   docker-compose up -d
   ```

3. **Stop all services:**
   ```bash
   docker-compose down
   ```

4. **View logs:**
   ```bash
   docker-compose logs -f [service-name]
   ```

### Running Locally (Development)

Each service can be run independently for development:

1. **Discovery Service:**
   ```bash
   cd discovery-service
   ./gradlew bootRun
   ```
   Access at: http://localhost:8761

2. **API Gateway:**
   ```bash
   cd api-gateway
   ./gradlew bootRun
   ```
   Access at: http://localhost:8080

3. **Auth Service:**
   ```bash
   cd auth-service
   ./gradlew bootRun
   ```
   Access at: http://localhost:8081

## Service Discovery

All services register with the Discovery Service (Eureka) at startup. You can view registered services at:
http://localhost:8761

## API Gateway Routes

The API Gateway routes requests to the appropriate services:

- `/auth/**` → Auth Service (with prefix stripped)

## Troubleshooting

### Port Already in Use
If you see an error about a port already in use:
```bash
# Find the process using the port
lsof -i :8080

# Kill the process
kill -9 <PID>
```

### Service Not Registering with Eureka
1. Check that Discovery Service is running
2. Verify the `eureka.client.service-url.defaultZone` in application.yml
3. Check network connectivity between containers

### Docker Issues
```bash
# Rebuild containers
docker-compose up --build

# Remove all containers and volumes
docker-compose down -v

# View container logs
docker-compose logs -f [service-name]
```

## Health Checks

Each service exposes actuator endpoints for health monitoring:

- Discovery Service: http://localhost:8761/actuator/health
- API Gateway: http://localhost:8080/actuator/health
- Auth Service: http://localhost:8081/actuator/health

## Additional Resources

For more information, refer to the individual service HELP.md files:
- [Discovery Service Help](discovery-service/HELP.md)
- [API Gateway Help](api-gateway/HELP.md)
- [Auth Service Help](auth-service/HELP.md)