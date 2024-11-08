# Spring Boot Movie Recommendation Demo Chatbot

## Project Overview
This project is a movie recommendation chatbot built with Spring Boot, Ollama, and LLaMA models.
The chatbot is designed to provide movie recommendations based on user inputs such as genre, mood, or actors.
It leverages AI models to offer intelligent and tailored movie suggestions.

## Key Features
1. **Movie Recommendations**: Provide movie suggestions based on user inputs such as genre, mood, or specific actors.
2. **Interactive Chat**: Users can interact with the bot via a REST API to receive recommendations.
3. **AI Model Integration**: Integrates with Ollama’s LLaMA models for intelligent responses.

## Tech Stack
- **Java 21**
- **Spring Boot 3.3.4 (WebFlux, Spring AI)**
- **Ollama AI**
- **LLaMA models**

### Prerequisites
- **Java 21** or higher.
- **Ollama** installed locally (instructions below)
- **Maven**

### Setting up Ollama

To install Ollama, follow these steps:

1. **Download Ollama**: Visit the [Ollama download page](https://ollama.com/download) and download the installer for your operating system.
2. **Install the application**: Follow the instructions on the page to install Ollama on your system.
3. **Download the Mistral Model**: Go to the terminal and run: `ollama pull mistral`
   
Once Ollama is installed, you can use the model directly with the Spring Boot application.

## Project Structure
```plaintext
src/
 ├── main/
 │   ├── java/
 │   │   └── com/tc/chatbot            # Contains application source code
 │   └── resources/
 │       ├── application.yaml          # Main application configuration
 │       ├── prompts/                  # Contains chatbot prompt template
 └── test/
     └── java/                         
         └── com/tc/chatbot            # Unit and integration test cases
```

## How to Run the Application

### Steps to Run

1. **Clone the Repository**
    ```bash
    git clone https://github.com/dfjmax/spring-boot-llama-chatbot.git
    cd spring-boot-llama-chatbot
    ```
2. **Starting the application**: After the model is downloaded simply run:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### API Usage

You can interact with the chatbot by sending POST requests to the /recommendation-blocking or /recommendation-flux endpoints.

**Blocking Endpoint:** This endpoint processes the request and waits for the AI model to generate a full response before returning it to the client. It’s suited for clients that prefer a complete reply in a single request/response cycle.
   ```bash
   curl --location --request POST 'http://localhost:8080/recommendation-blocking' \
   --header 'Content-Type: application/json' \
   --data '{ "question": "I would like to get a recommendation for an action movie, a thriller movie, and a sci-fi movie" }'
   ```
**Reactive (Flux) Endpoint:** This endpoint streams the response incrementally as it is generated by the AI model, allowing clients to receive parts of the response as soon as they are available. It’s ideal for real-time or interactive applications where partial responses can enhance user experience.
   ```bash
   curl --location --request POST 'http://localhost:8080/recommendation-flux' \
   --header 'Content-Type: application/json' \
   --data '{ "question": "I would like to get a recommendation for an action movie, a thriller movie, and a sci-fi movie" }'
   ```
Note: Ensure that the question field is not left empty, as the API performs input validation to guarantee meaningful interaction with the chatbot.
