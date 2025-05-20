# Sistemi i Menaxhimit të Universitetit - Krahasimi i Arkitekturës

Ky projekt demonstron një krahasim të detajuar midis një arkitekture monolitike dhe një arkitekture me mikroservise për një sistem menaxhimi të universitetit. Sistemi menaxhon regjistrimin e studentëve, menaxhimin e kurseve dhe informacionin e studentëve.

## Përmbajtja e Projektit

Projekti përbëhet nga dy implementime kryesore:

1. **Aplikacioni Monolitik** (`/monolith`)
   - Një aplikacion i vetëm Spring Boot
   - Të gjitha shërbimet (Studentët, Kurset, Regjistrimet) në një kodbazë
   - Akses i drejtpërdrejtë në databazë
   - Monitorim me Prometheus dhe Grafana

2. **Arkitektura me Mikroservise** (`/microservices`)
   - Tre aplikacione të pavarura Spring Boot
   - Çdo shërbim ka databazën e vet
   - Komunikim ndër-shërbimesh duke përdorur Spring Cloud
   - Shërbimet:
     - Student Service (Port 8081)
     - Course Service (Port 8082)
     - Enrollment Service (Port 8083)

## Teknologjitë e Përdorura

- **Spring Boot 3.x**
- **Spring Cloud**
  - OpenFeign për komunikimin ndër-shërbimesh
  - Spring Cloud Config për menaxhimin e konfigurimit
- **MySQL** për ruajtjen e të dhënave
- **Docker** për kontejnerizimin
- **Prometheus** për mbledhjen e metrikave
- **Grafana** për vizualizimin e metrikave
- **JMeter** për testimin e performancës

## Si të Filloni

1. Klono repozitorinë
2. Ndiq udhëzimet e instalimit në secilën arkitekturë:
   - [Instalimi i Monolitit](monolith/README.md)
   - [Instalimi i Mikroserviseve](microservices/README.md)

## Krahasimi i Performancës

Projektet përfshijnë:
- Monitorim të detajuar të memories
- Krahasim të kohës së përgjigjes
- Analizë të konsumit të burimeve
- Testim të ngarkesës me JMeter

## Struktura e Direktoriveve

```
.
├── monolith/                 # Aplikacioni monolitik
│   ├── src/                 # Kodi burim
│   ├── grafana/            # Dashboard-et e Grafana
│   └── prometheus/         # Konfigurimi i Prometheus
│
├── microservices/          # Implementimi me mikroservise
│   ├── student-service/    # Shërbimi i studentëve
│   ├── course-service/     # Shërbimi i kurseve
│   ├── enrollment-service/ # Shërbimi i regjistrimeve
│   ├── grafana/           # Dashboard-et e Grafana
│   └── prometheus/        # Konfigurimi i Prometheus
│
└── README.md              # Ky dokument
```

## Monitorimi

Të dyja arkitekturat përfshijnë monitorim të detajuar:

### Prometheus
- Mbledh metrikat nga të gjitha shërbimet
- Ruajtja e historikut të metrikave
- Konfigurim i personalizuar për secilën arkitekturë

### Grafana
- Dashboard-et e personalizuara për secilën arkitekturë
- Vizualizim i konsumit të memories
- Monitorim i performancës në kohë reale

## Testimi

Projektet përfshijnë plane testimi JMeter për:
- Simulimin e ngarkesës
- Matjen e performancës
- Krahasimin e kohës së përgjigjes
- Analizën e konsumit të burimeve

## Shënime të Rëndësishme

1. Sigurohu që ke Docker dhe Docker Compose të instaluar
2. Portet e përdorura:
   - Monolith: 8080
   - Student Service: 8081
   - Course Service: 8082
   - Enrollment Service: 8083
   - Prometheus: 9090
   - Grafana: 9000
   - MySQL: 3306

3. Kredencialet e paracaktuara:
   - Grafana: admin/admin
   - MySQL: root/root
