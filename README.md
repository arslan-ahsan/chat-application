# Chat Application - Microservices Backend

A scalable microservices-based chat application built with Spring Boot and Spring Cloud.

## Architecture

This application follows a microservices architecture with the following components:

```
┌─────────────────┐
│   API Gateway   │  Port: 8080
│   (Entry Point) │
└────────┬────────┘
         │
         ├──────────────┬──────────────┐
         │              │              │
    ┌────▼────┐    ┌────▼────┐   ┌────▼────┐
    │  Auth   │    │  Chat   │   │  User   │
    │ Service │    │ Service │   │ Service │
    │  :8081  │    │  :8082  │   │  :8083  │
    └────┬────┘    └────┬────┘   └────┬────┘
         │              │              │
         └──────────────┴──────────────┘
                        │
                 ┌──────▼──────┐
                 │  Discovery  │
                 │   Service   │
                 │    :8761    │
                 └─────────────┘
```

## Services

| Service | Port | Status | Description |
|---------|------|--------|-------------|
| **Discovery Service** | 8761 | ✅ Active | Eureka server for service registry and discovery |
| **API Gateway** | 8080 | ✅ Active | Entry point routing requests to microservices |
| **Auth Service** | 8081 | ✅ Active | Handles authentication and authorization |
| **Chat Service** | 8082 | 🚧 Planned | Manages chat messages and conversations |
| **User Service** | 8083 | 🚧 Planned | User profile and management |

## Quick Start

### Prerequisites

- **Java 17** or higher
- **Docker** and **Docker Compose**
- **Gradle** (wrapper included)

### Run with Docker Compose

```bash
# Start all services
docker-compose up

# Start in detached mode
docker-compose up -d

# Stop all services
docker-compose down

# Rebuild and start
docker-compose up --build
```

### Access Services

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Auth Service**: http://localhost:8081

## Development

### Running Services Locally

Each service can be run independently for development:

```bash
# Discovery Service
cd discovery-service && ./gradlew bootRun

# API Gateway
cd api-gateway && ./gradlew bootRun

# Auth Service
cd auth-service && ./gradlew bootRun
```

### Building Services

```bash
# Build all services
./gradlew build

# Build specific service
cd [service-name] && ./gradlew build

# Skip tests
./gradlew build -x test
```

## Technology Stack

- **Spring Boot 3.5.6** - Application framework
- **Spring Cloud 2025.0.0** - Microservices framework
- **Netflix Eureka** - Service discovery
- **Spring Cloud Gateway** - API gateway and routing
- **Docker** - Containerization
- **Gradle** - Build tool

## Project Structure

```
chat-application/
├── discovery-service/      # Eureka service registry
├── api-gateway/           # API gateway and routing
├── auth-service/          # Authentication service
├── docker-compose.yml     # Docker orchestration
├── HELP.md               # Detailed help guide
└── README.md             # This file
```

## Port Configuration

| Port | Service | Purpose |
|------|---------|---------|
| 8761 | Discovery Service | Service registry (Eureka) |
| 8080 | API Gateway | Main entry point |
| 8081 | Auth Service | Authentication |
| 8082 | Chat Service | Chat functionality (planned) |
| 8083 | User Service | User management (planned) |

## API Gateway Routes

Current routing configuration:

- `GET /auth/**` → Auth Service

## Health Monitoring

All services expose Spring Actuator endpoints:

```bash
# Check service health
curl http://localhost:8761/actuator/health  # Discovery
curl http://localhost:8080/actuator/health  # Gateway
curl http://localhost:8081/actuator/health  # Auth
```

## Contributing

1. Create a feature branch
2. Make your changes
3. Write/update tests
4. Submit a pull request

## Troubleshooting

See [HELP.md](HELP.md) for detailed troubleshooting guide.

### Common Issues

**Port already in use:**
```bash
lsof -i :[port]
kill -9 [PID]
```

**Service not registering:**
- Ensure Discovery Service is running
- Check `eureka.client.service-url.defaultZone` configuration
- Verify network connectivity

**Docker issues:**
```bash
docker-compose down -v  # Remove containers and volumes
docker-compose up --build  # Rebuild and start
```

## License

[Your License Here]

## Contact

[Your Contact Information]