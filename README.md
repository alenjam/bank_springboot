# Getting Started

### Starting the Application
* Clone the repository:
```bash
git clone https://github.com/alenjam/bank_springboot.git
```
* Start the services using Docker Compose:

```bash
docker-compose up -d
```
* The application will be available at http://localhost:8080

### Database Initialization
The docker-compose.yml spins up a PostgreSQL container with the configured databases and credentials.

### Accessing the Database
The PostgreSQL database can be accessed at the hostname postgres on port 5432 from within the Docker network.
External clients can connect to it via the mapped port 5430.
The credentials, database names etc. are specified in the Compose file.

# Endpoints
The following endpoints are exposed by the application:

## Get Branch Details
Returns the details of a bank branch based on the IFSC code.

Request:
GET /banks/{ifscCode}

Response:

```json
{
  "ifscCode": "ABHY0065001",
  "branch": "RTGS-HO", 
  "address": "ABHYUDAYA BANK BLDG., B.NO.71, NEHRU NAGAR, KURLA (E), MUMBAI-400024",
  "city": "MUMBAI",
  "district": "GREATER MUMBAI",
  "state": "MAHARASHTRA" 
}
```

## Get Branches By City
Returns a list of branches present in the given city.

Request:
GET /banks/city/{city}

Response:

```json
[
    {
        "ifscCode": "CNRB0000826",
        "branch": "THAMARASSERY",
        "address": "LAILA NILAYAM KARADAY BAZAR , TP X 140 MAIN ROAD, , THAMARASSERY 673573 ,",
        "city": "TAMARASSERY",
        "district": "KOZHIKODE",
        "state": "KERALA",
        "bank": "CANARA BANK"
    },
    {
        "ifscCode": "HDFC0003010",
        "branch": "THAMARASSERY",
        "address": "HDFC BANK LTD. V K SHOPPING COMPLEX OPP KSRTC BUS STAND, KARADI, THAMARASSERY KOZHIKODE KERALA 673573",
        "city": "TAMARASSERY",
        "district": "KOZHIKODE",
        "state": "KERALA",
        "bank": "HDFC BANK"
    },
    {
        "ifscCode": "ICIC0002672",
        "branch": "THAMARASSERY",
        "address": "ICICI BANK LTD., CITY CENTRE, KOYILANDY ROAD, NEAR BSNL OFFICE, THAMARASSERY CHUNGAM, THAMARASSERY - 673573, OZHIKODE DIST., KERALA",
        "city": "TAMARASSERY",
        "district": "KOZHIKODE",
        "state": "KERALA",
        "bank": "ICICI BANK LIMITED"
    }
]
```
