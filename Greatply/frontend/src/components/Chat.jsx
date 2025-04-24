import React, { useState } from 'react';
import './Chat.css';

function Chat() {
  const [message, setMessage] = useState('');
  const [response, setResponse] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!message.trim()) return;

    setIsLoading(true);
    try {        // Google Cloud: https://greatply-backend-374044169385.us-central1.run.app
                  // Local: http://localhost:8080/api/chat    
      const res = await fetch('https://greatply-backend-374044169385.us-central1.run.app', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ message }),
      });

      if (!res.ok) {
        throw new Error('Failed to get response');
      }

      const data = await res.json();
      setResponse(data.response);
    } catch (error) {
      console.error('Error:', error);
      setResponse('Error: Could not get a response. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="chat-container">
      <h1>How can I respond?</h1>
      <div className="chat-box">
        {response && (
          <div className="response-section">
            <h3>Best way to respond:</h3>
            <div className="response-content">{response}</div>
          </div>
        )}
      </div>
      <form onSubmit={handleSubmit} className="chat-form">
        <textarea
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          placeholder="Copy and paste the message you have no idea how to respond to..."
          rows={4}
          className="chat-input"
        />
        <button 
          type="submit" 
          className="send-button" 
          disabled={isLoading || !message.trim()}
        >
          {isLoading ? 'Sending...' : 'Ask ChatGPT'}
        </button>
      </form>
    </div>
  );
}

export default Chat;