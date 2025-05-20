# Arkitektura me Mikroservise për Menaxhimin e Universitetit

Ky është implementimi me mikroservise i Sistemit të Menaxhimit të Universitetit, ku secili shërbim (Studentët, Kurset dhe Regjistrimet) është një aplikacion i pavarur Spring Boot.

## Arkitektura

Arkitektura me mikroservise përdor:
- Tre aplikacione të pavarura Spring Boot
- Një databazë MySQL të përbashkët
- Komunikim ndër-shërbimesh duke përdorur Spring Cloud OpenFeign
- Endpoint-et RESTful API
- Monitorim me Prometheus dhe Grafana

## Shërbimet

### Student Service (Port 8081)
- Menaxhon informacionin e studentëve
- Endpoint-et:
  - `GET /students` - Merr të gjithë studentët
  - `POST /students` - Krijo një student të ri
  - `GET /students/{id}` - Merr një student specifik

### Course Service (Port 8082)
- Menaxhon informacionin e kurseve
- Endpoint-et:
  - `GET /courses` - Merr të gjitha kurset
  - `POST /courses` - Krijo një kurs të ri
  - `GET /courses/{id}` - Merr një kurs specifik

### Enrollment Service (Port 8083)
- Menaxhon regjistrimet e studentëve në kurse
- Komunikon me Student Service dhe Course Service
- Endpoint-et:
  - `GET /enrollments` - Merr të gjitha regjistrimet
  - `POST /enrollments` - Krijo një regjistrim të ri
  - `GET /enrollments/{id}` - Merr një regjistrim specifik

## Teknologjitë e Përdorura

- **Spring Boot 3.x**
- **Spring Cloud**
  - OpenFeign për komunikimin ndër-shërbimesh
  - Spring Cloud Config për menaxhimin e konfigurimit
- **Spring Data JPA** për operacionet e databazës
- **MySQL** për ruajtjen e të dhënave
- **Maven** për menaxhimin e varësive
- **Docker** për kontejnerizimin
- **Prometheus** për mbledhjen e metrikave
- **Grafana** për vizualizimin e metrikave

## Monitorimi

### Prometheus
- Mbledh metrikat nga të gjitha shërbimet
- Konfigurimi në `prometheus.yml`
- Targetet:
  - `student-service:8081`
  - `course-service:8082`
  - `enrollment-service:8083`

### Grafana
- Dashboard-et e personalizuara për secilin shërbim
- Metrikat e monitoruara:
  - Konsumi i memories heap
  - Konsumi i memories fizike
  - Shkalla e kërkesave HTTP
  - Koha e përgjigjes
  - Metrikat e komunikimit ndër-shërbimesh

## Si të Filloni

1. **Ndërto të gjitha shërbimet:**
   ```bash
   cd microservices
   mvn clean package
   ```

2. **Starto me Docker Compose:**
   ```bash
   docker-compose up --build
   ```

Shërbimet do të jenë të aksesueshme në:
- Student Service: `http://localhost:8081`
- Course Service: `http://localhost:8082`
- Enrollment Service: `http://localhost:8083`

## Konfigurimi

### Student Service
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://microservices-mysql:3306/microservices_db
spring.datasource.username=user
spring.datasource.password=password
```

### Course Service
```properties
server.port=8082
spring.datasource.url=jdbc:mysql://microservices-mysql:3306/microservices_db
spring.datasource.username=user
spring.datasource.password=password
```

### Enrollment Service
```properties
server.port=8083
spring.datasource.url=jdbc:mysql://microservices-mysql:3306/microservices_db
spring.datasource.username=user
spring.datasource.password=password
student.service.url=http://student-service:8081
course.service.url=http://course-service:8082
```

## Monitorimi

### Prometheus
- URL: `http://localhost:9090`
- Konfigurimi: `prometheus.yml`
- Intervali i mbledhjes: 15 sekonda

### Grafana
- URL: `http://localhost:9000`
- Kredencialet: admin/admin
- Dashboard-et:
  - "Microservices Overview"
  - "Individual Service Metrics"

## Testimi

Projekti përfshin plane testimi JMeter:
- 10 përdorues konkurrentë për shërbim
- Teston operacionet GET dhe POST
- Mat kohën e përgjigjes dhe throughput-in
- Teston komunikimin ndër-shërbimesh

### Konfigurimi i Testit
- Thread Groups: 10 threads për shërbim
- Koha e ngritjes: 1 sekondë
- Numri i përsëritjeve: 1

## Shënime të Rëndësishme

1. Sigurohu që portet e mëposhtme janë të lira:
   - 8081 (Student Service)
   - 8082 (Course Service)
   - 8083 (Enrollment Service)
   - 3306 (MySQL)
   - 9090 (Prometheus)
   - 9000 (Grafana)

2. Kredencialet e paracaktuara:
   - MySQL: user/password
   - Grafana: admin/admin

3. Volumet Docker:
   - mysql-data: për të dhënat e MySQL
   - grafana-storage: për të dhënat e Grafana

## Struktura e Direktoriveve

```
microservices/
├── student-service/        # Shërbimi i studentëve
├── course-service/         # Shërbimi i kurseve
├── enrollment-service/     # Shërbimi i regjistrimeve
├── grafana/               # Dashboard-et e Grafana
│   ├── dashboards/       # Konfigurimi i dashboard-eve
│   └── provisioning/     # Konfigurimi i Grafana
├── prometheus/           # Konfigurimi i Prometheus
├── docker-compose.yml    # Konfigurimi i Docker
└── README.md            # Ky dokument
```

## Komunikimi Ndër-Shërbimesh

Shërbimet komunikojnë duke përdorur Spring Cloud OpenFeign:

### Student Client (në Enrollment Service)
```java
@FeignClient(name = "student-service", url = "${student.service.url}")
public interface StudentClient {
    @GetMapping("/students/{id}")
    StudentDTO getStudent(@PathVariable("id") Long id);
}
```

### Course Client (në Enrollment Service)
```java
@FeignClient(name = "course-service", url = "${course.service.url}")
public interface CourseClient {
    @GetMapping("/courses/{id}")
    CourseDTO getCourse(@PathVariable("id") Long id);
} 