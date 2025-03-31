# Vehicle_Rental_System


vehicle-rental-system/
 ├── api-gateway/            # API Gateway (Spring Cloud Gateway/Zuul)
 ├── eureka-server/          # Service Discovery (Spring Eureka)
 ├── config-server/          # Centralized Configuration (Spring Cloud Config)
 ├── user-service/           # Manages users and authentication
 ├── vehicle-service/        # Manages vehicle inventory
 ├── booking-service/        # Handles reservations
 ├── payment-service/        # Processes payments
 ├── notification-service/   # Sends emails & SMS alerts
 ├── common-library/         # Shared models, DTOs, and utilities
 ├── docker-compose.yml      # Defines services for local development
 ├── k8s/                    # Kubernetes deployment files
 │   ├── api-gateway.yaml
 │   ├── user-service.yaml
 │   ├── vehicle-service.yaml
 │   ├── booking-service.yaml
 ├── postman-collection/     # Postman collection for API testing
 ├── README.md               # Project documentation

