# Gemini Chatbot

This is a simple chatbot application using Google's Generative AI model.

## Setup

1. Clone the repository:
    ```sh
    git clone git@github.com:aditya-8-88/Handy-kit.git
    cd Gemini-chatbot
    ```

2. Create a `.env` file in the `Gemini chatbot` directory and add your API key:
    ```
    API_KEY=your_api_key_here
    ```

3. Install the required dependencies:
    ```sh
    python -m venv env 
    source env/bin/activate
    pip install -r requirements.txt
    ```

## Usage

1. Run the chatbot:
    ```sh
    python chat.py
    ```

2. Type your messages to the chatbot. Type `exit` to end the conversation.

## Files

- `Gemini chatbot/chat.py`: The main script for the chatbot.
- `Gemini chatbot/.env`: Environment variables file containing the API key.
- `README.md`: This file.

## Dependencies

- `python-dotenv`: For loading environment variables from a `.env` file.
- `google-generativeai`: For interacting with Google's Generative AI model.

## License

This project is licensed under the MIT License.