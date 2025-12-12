<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("user");
    if (username == null) {
        response.sendRedirect("login.html"); // Redirect if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>UniConnect - Chat</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    </head>
    <body class="bg-dark">
        <h1 class="text-center mb-5 mt-2 text-white">Chat</h1>

        <!-- Chat display and message input -->
        <div class="container py-4 w-50">
            <!-- Chat messages container -->
            <div class="border p-3 mb-3 bg-light" style="height: 300px; overflow-y: auto;">
                <ul id="chatBox" class="list-unstyled mb-0"></ul>
            </div>
            <!-- Message input + send button -->
            <div class="d-flex">
                <input 
                    type="text" 
                    name="message" 
                    id="messageInput" 
                    class="form-control me-2" 
                    placeholder="Type a message..." 
                    required
                    >
                <button onclick="sendMessage()" class="btn btn-light">Send</button>
            </div>
        </div>
        
        <div class="text-center mt-4">
            <a href="HomeServlet" class="btn btn-outline-light">Back to Home</a>
        </div>


        <script>
            // GLOBAL VARIABLES 
            let username = "<%= username %>"; // Will store the current user's name
            const senderColors = {}; // Store unique color per sender
            
            //Create a WebSocket connection to the server endpoint
            const socket = new WebSocket("ws://localhost:8080/UniConnect-LiveChat/chatEndpoint");
            
            // Pick a random color from a preset palette
            function getRandomColor() {
                const colors = ["#e74c3c", "#8e44ad", "#2980b9", "#27ae60", "#f39c12", "#d35400", "#2c3e50"];
                return colors[Math.floor(Math.random() * colors.length)];
            }
            
            // Handle incoming messages from the WebSocket server
            socket.onmessage = function (event) {
                const chatBox = document.getElementById("chatBox");

                // Full message from server (format: "username: message text")
                const fullMessage = event.data;
                
                // Separate the sender name and the actual messaege text
                const separatorIndex = fullMessage.indexOf(":");
                let sender = "Unknown";
                let messageText = fullMessage;

                if (separatorIndex !== -1) {
                    sender = fullMessage.substring(0, separatorIndex).trim();
                    messageText = fullMessage.substring(separatorIndex + 1).trim();
                }
                
                // Create a list item for the message
                const messageItem = document.createElement("li");
                messageItem.classList.add("d-flex", "mb-1");

                // Align right if message is from me, else align left
                if (sender === username) {
                    messageItem.classList.add("justify-content-end");  // Align right
                } else {
                    messageItem.classList.add("justify-content-start"); // Align left
                }

                // Assign a random color to the sender if they don't have one yet
                if (!senderColors[sender]) {
                    senderColors[sender] = getRandomColor();
                }

                // Create the sender name element (above message bubble)
                const senderName = document.createElement("strong");
                senderName.textContent = sender;
                senderName.style.color = senderColors[sender];
                senderName.style.fontSize = "0.85em";
                senderName.style.marginBottom = "3px";

                // Create the message bubble container
                const messageBubble = document.createElement("div");
                messageBubble.style.maxWidth = "70%";
                messageBubble.style.padding = "8px 12px";
                messageBubble.style.borderRadius = "15px";
                messageBubble.style.wordBreak = "break-word";

                // Different background/colors for sender and others
                if (sender === username) {
                    messageBubble.style.backgroundColor = "#DCF8C6"; // Light green for own messages
                    messageBubble.style.color = "#000";
                    messageBubble.style.textAlign = "right";
                } else {
                    messageBubble.style.backgroundColor = "#ECECEC"; // Light gray for others
                    messageBubble.style.color = "#000";
                    messageBubble.style.textAlign = "left";
                }

                // Add message text
                messageBubble.textContent = messageText;

                // Add Timestamp
                const timeStamp = document.createElement("small");
                timeStamp.classList.add("text-muted");
                timeStamp.style.display = "block";
                timeStamp.style.fontSize = "0.75em";
                timeStamp.style.marginTop = "4px";
                const now = new Date();
                timeStamp.textContent = now.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit', hour12: false});

                // Append all
                messageBubble.appendChild(timeStamp);
                messageItem.appendChild(senderName);
                messageItem.appendChild(messageBubble);
                chatBox.appendChild(messageItem);

                // Scroll to bottom
                chatBox.parentElement.scrollTop = chatBox.parentElement.scrollHeight;
            };
            
            // Send message from input field to WebSocket server
            function sendMessage() {
                const input = document.getElementById("messageInput");
                const message = input.value.trim();

                if (message !== "") {
                    socket.send(message); //Send to server
                    input.value = ""; // Clear input
                } else {
                    alert("Please type a message before sending.");
                }
            }

            // Utility function to get current date/time 
            function getCurrentDateTime() {
                const now = new Date();
                return now.toLocaleString('en-US', {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false,
                    month: 'short',
                    day: 'numeric'
                });
            }

            document.getElementById('currentDateTime').textContent = getCurrentDateTime();


        </script>
    </body>
</html>

