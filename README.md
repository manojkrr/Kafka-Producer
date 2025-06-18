# Simple Kafka Producer Spring Boot Application

## 🧾 Overview

* This Spring Boot application acts as a Kafka **Producer**.
* It sends messages to a Kafka topic as **Strings** using the Apache Kafka client.

---

## ✅ Prerequisites

- Java 11 or later
- Apache Kafka and Zookeeper installed and running
- Maven

---

## 🏗️ Build the Application

```bash
mvn clean install
````

---

## ▶️ Run the Application

```bash
java -jar target/kafka-producer-0.0.1-SNAPSHOT.jar
```

---

## 📬 REST Endpoints

The application exposes two REST APIs to send messages to Kafka:

### 1. Send Asynchronous Message

* **Endpoint:** `POST /kafka/send`
* **Query Params:**

    * `message` (required): The message content

**Example:**

```bash
curl -X POST "http://localhost:8081/kafka/send?message=HelloKafka"
```

---

### 2. Send Synchronous Message

* **Endpoint:** `POST /kafka/send-sync`
* **Query Params:**

    * `message` (required): The message content

**Example:**

```bash
curl -X POST "http://localhost:8081/kafka/send-sync?message=HelloSync"
```

On success:
✅ `Message sent to Kafka topic [asynchronously/synchronously]`
On error:
❌ `Failed to send message: <error-details>`

---

## 🔎 Swagger

Access Swagger UI at:

```
http://localhost:8081/kafka-producer/swagger-ui.html
```

---

## 💬 Some Useful Kafka Commands

### ▶️ Start Zookeeper

```bash
zookeeper-server-start <config-folder>/zookeeper.properties
# or
brew services start zookeeper
```

### ▶️ Start Kafka

```bash
kafka-server-start <config-folder>/server.properties
# or
brew services start kafka
```

### ➕ Create Kafka Topic

```bash
kafka-topics --create --topic test-topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
```

### 📃 List Topics

```bash
kafka-topics --list --bootstrap-server localhost:9092
```

### 👂 Start a Kafka Consumer (console)

```bash
kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:9092
```

### ❌ Delete a Topic

```bash
kafka-topics --delete --topic test-topic --bootstrap-server localhost:9092
```

### ⏹️ Stop Kafka & Zookeeper

```bash
kafka-server-stop
zookeeper-server-stop
# or via Homebrew
brew services stop kafka
brew services stop zookeeper
```

---

## 🧪 Test the Producer

You can test the endpoints via:

* Curl (examples above)
* Postman
* Browser:

  ```
  http://localhost:8081/kafka/send?message=TestFromBrowser
  ```

---

## 🛠️ Tech Stack

* Java 11+
* Spring Boot
* Apache Kafka
* Swagger UI
