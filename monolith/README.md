# Aplikacioni Monolitik i Menaxhimit të Universitetit

Ky është implementimi monolitik i Sistemit të Menaxhimit të Universitetit, ku të gjitha shërbimet (Studentët, Kurset dhe Regjistrimet) janë pjesë e një aplikacioni të vetëm Spring Boot.

## Arkitektura

Aplikacioni monolitik përdor:
- Një aplikacion të vetëm Spring Boot
- Një databazë të vetme MySQL
- Akses të drejtpërdrejtë në databazë për të gjitha operacionet
- Endpoint-et RESTful API
- Monitorim me Prometheus dhe Grafana

## Teknologjitë e Përdorura

- **Spring Boot 3.x**
- **Spring Data JPA** për operacionet e databazës
- **MySQL** për ruajtjen e të dhënave
- **Maven** për menaxhimin e varësive
- **Docker** për kontejnerizimin
- **Prometheus** për mbledhjen e metrikave
- **Grafana** për vizualizimin e metrikave

## Endpoint-et e API

### Endpoint-et e Studentëve
- `GET /students` - Merr të gjithë studentët
- `POST /students` - Krijo një student të ri
  ```json
  {
    "name": "Emri i Studentit"
  }
  ```

### Endpoint-et e Kurseve
- `GET /courses` - Merr të gjitha kurset
- `POST /courses` - Krijo një kurs të ri
  ```json
  {
    "title": "Titulli i Kursit"
  }
  ```

### Endpoint-et e Regjistrimeve
- `GET /enrollments` - Merr të gjitha regjistrimet
- `POST /enrollments` - Krijo një regjistrim të ri
  ```json
  {
    "studentId": 1,
    "courseId": 1
  }
  ```

## Monitorimi

Aplikacioni përfshin monitorim të detajuar me Prometheus dhe Grafana:

### Prometheus
- Mbledh metrikat nga aplikacioni
- Konfigurimi në `prometheus.yml`
- Target: `monolith:8080`

### Grafana
- Dashboard i personalizuar për monolith
- Metrikat e monitoruara:
  - Konsumi i memories heap
  - Konsumi i memories fizike
  - Shkalla e kërkesave HTTP
  - Koha e përgjigjes

## Si të Filloni

1. **Ndërto jar-in:**
   ```bash
   cd monolith
   mvn clean package
   ```

2. **Starto me Docker Compose:**
   ```bash
   docker-compose up --build
   ```

3. **Për të ndaluar aplikacionin dhe fshirë të gjitha të dhënat (volumet):**
   ```bash
   docker-compose down -v
   ```
   Ky komandë do të:
   - Ndalojë të gjitha kontejnerët
   - Fshijë të gjitha volumet (përfshirë të dhënat e databazës)
   - Fshijë të gjitha imazhet e krijuara
   - Fshijë të gjitha rrjetet e krijuara

4. **Për të ndaluar aplikacionin pa fshirë të dhënat:**
   ```bash
   docker-compose down
   ```

Aplikacioni do të jetë i aksesueshëm në `http://localhost:8080`

## Konfigurimi

Aplikacioni përdor konfigurimin e mëposhtëm në `application.properties`:
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://mysql:3306/monolith_db
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Monitorimi

### Prometheus
- URL: `http://localhost:9090`
- Konfigurimi: `prometheus.yml`
- Intervali i mbledhjes: 15 sekonda

### Grafana
- URL: `http://localhost:9000`
- Kredencialet: admin/admin
- Dashboard: "Monolith Dashboard"

## Testimi

Aplikacioni përfshin plane testimi JMeter:
- 10 përdorues konkurrentë
- Teston operacionet GET dhe POST
- Mat kohën e përgjigjes dhe throughput-in

### Konfigurimi i Testit
- Thread Groups: 10 threads
- Koha e ngritjes: 1 sekondë
- Numri i përsëritjeve: 1

## Shënime të Rëndësishme

1. Sigurohu që portet e mëposhtme janë të lira:
   - 8080 (Aplikacioni)
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
monolith/
├── src/                    # Kodi burim
├── grafana/               # Dashboard-et e Grafana
│   ├── dashboards/       # Konfigurimi i dashboard-eve
│   └── provisioning/     # Konfigurimi i Grafana
├── prometheus/           # Konfigurimi i Prometheus
├── docker-compose.yml    # Konfigurimi i Docker
└── README.md            # Ky dokument
```

## Database Schema

### Student Entity
```java
@Entity
public class Student {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
```

### Course Entity
```java
@Entity
public class Course {
    @Id @GeneratedValue
    private Long id;
    private String title;
}
```

### Enrollment Entity
```java
@Entity
public class Enrollment {
    @Id @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Student student;
    
    @ManyToOne
    private Course course;
}
``` 