const express = require('express');
const http = require('http');
const socketIO = require('socket.io');
const cors = require('cors');
require('dotenv').config();

const app = express();
const server = http.createServer(app);
const io = socketIO(server, {
  cors: {
    origin: process.env.CLIENT_URL || 'http://localhost:3000',
    methods: ['GET', 'POST']
  }
});

const PORT = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(express.json());

// Routes
app.get('/api/health', (req, res) => {
  res.json({ status: 'Server is running' });
});

// WebSocket Events
io.on('connection', (socket) => {
  console.log('User connected:', socket.id);

  socket.on('set_alarm', (data) => {
    console.log('Alarm set:', data);
    // Broadcast to Android app
    io.emit('alarm_notification', data);
  });

  socket.on('get_alarms', (callback) => {
    // Fetch alarms from database
    callback({ alarms: [] });
  });

  socket.on('disconnect', () => {
    console.log('User disconnected:', socket.id);
  });
});

server.listen(PORT, () => {
  console.log(`✓ Server running on port ${PORT}`);
});
