Project Description: Llama API
Overview
The Llama API is a web service built with Spring Boot that allows users to generate text responses using the Llama 3.1 language model. By sending prompts to the API, users can receive dynamic and contextually relevant text output.

Capabilities
Conversational Agents: Create intelligent chatbots that can engage users in natural dialogue.
Content Creation: Automatically generate articles, summaries, or responses based on user input.
Creative Assistance: Help writers brainstorm ideas or expand on concepts through AI-generated suggestions.
Interactive Learning: Develop educational tools that respond to user inquiries with accurate information.
Conclusion
The Llama API provides a powerful interface for leveraging advanced AI language capabilities, enabling innovative applications across various domains.
Prerequisites
Windows Subsystem for Linux (WSL) installed on your Windows machine.
A terminal or command prompt to execute commands.

Step 1: Install Ollama on WSL

Open your WSL terminal.

Run the following command to install Ollama:
curl -fsSL https://ollama.com/install.sh | sh

Verify the installation by checking the version:
ollama --version

Step 2: Install Llama 3.1
Use the following command to pull and install Llama 3.1:
ollama pull llama3.1

Step 3: Determine the IP Address and Listening Port

Find your WSL instance's IP address:
hostname -I

Confirm the port on which Ollama is listening (default is 11434).

Step 4:SSH Configuration for Traffic Forwarding

To enable traffic forwarding every time you start WSL, you can create a script.

Create a script named enable-ssh-forwarding.sh:
nano ~/enable-ssh-forwarding.sh

Add the following content (replace user and remote-server-ip with your details):

#!/bin/bash
ssh -N -L 11434:localhost:11434 user@remote-server-ip

Make the script executable:
chmod +x ~/enable-ssh-forwarding.sh

Run the script when you want to enable SSH forwarding:
~/enable-ssh-forwarding.sh
