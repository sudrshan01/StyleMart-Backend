# 🛍️ StyleMart - Backend Microservices

The **StyleMart Backend** is a microservices-based architecture designed for the StyleMart e-commerce platform.  
It provides modular services for managing products, users, orders, payments, and authentication.  

Each service is containerized using **Docker** and communicates through **REST APIs**.

---
# You must have AWS Account
## 🚀 Project Overview

This project contains multiple microservices:
- **ProductService** – Handles product creation, updates, and retrieval.
- **OrderService** – Manages customer orders.
- **UserService** – Manages user .
- **CartService** – Manages cart .
- **RewiewService** – Manages product review.
- **NotificationService** – Manages Orders notifications .
- **API Gateway (Optional)** – Routes requests to respective microservices.

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/sudrshan01/StyleMart-Backend.git
cd StyleMart-Backend

🧩 Configure ProductService
Before building the Docker images, update your AWS credentials inside:

📂 ProductService/src/main/resources/application.yml

yaml

aws:
  accessKey: YOUR_AWS_ACCESS_KEY -- your access key
  secretKey: YOUR_AWS_SECRET_KEY --- your secret key
  region: ap-south-1

2️⃣ Build and Run Containers
docker-compose up -d --build

 run this cmd for check conteainer  are run or not docker ps


👨‍💻 Author

Sudrshan Genure
Bachelor of Computer Science (B.Sc. - ECS)
🎓 Dr. Ganpatrao Deshmukh Mahavidyalaya, Sangola
🔗 GitHub Profile
