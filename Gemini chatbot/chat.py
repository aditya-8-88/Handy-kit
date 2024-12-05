import os
from dotenv import load_dotenv
import google.generativeai as genai

# Load environment variables from .env file
load_dotenv()

# Initialize the generative AI model
genai.configure(api_key=os.getenv('API_KEY'))


# help(genai.GenerativeModel)

model = genai.GenerativeModel(
    model_name='gemini-1.5-pro',
    )

print("Chatbot is ready! Type 'exit' to end the conversation.")

conversation_history = []

while True:
    user_input = input("You: ")
    if user_input.lower() == 'exit':
        print("Chatbot: Goodbye!")
        break

    conversation_history.append(f"You: {user_input}")
    conversation_context = "\n".join(conversation_history)
    
    response = model.generate_content(conversation_context)
    chatbot_response = response.text.strip()
    
    print("Chatbot:", chatbot_response)
    conversation_history.append(f"Chatbot: {chatbot_response}")