# Phone AI Assistant 🤖📱

An Android AI assistant app controlled via web dashboard for alarm management, messaging, and automation.

## Project Overview

This project consists of three main components:

- **Android App** - AI assistant running on your Android phone
- **Web Dashboard** - Control interface to manage your phone remotely
- **Backend Server** - API server handling communication between web and mobile

## Architecture

```
┌─────────────────────┐
│   Web Dashboard     │
│   (React)           │
└──────────┬──────────┘
           │
           │ HTTP/WebSocket
           │
┌──────────┴──────────┐
│  Backend Server     │
│ (Node.js/Express)   │
└──────────┬──────────┘
           │
           │ HTTP/WebSocket
           │
┌──────────┴──────────┐
│   Android App       │
│   (Kotlin)          │
└─────────────────────┘
```

## Branches

- **main** - Main project documentation and structure
- **backend** - Node.js/Express backend server
- **web-dashboard** - React web dashboard UI
- **android-app** - Kotlin Android application

## Features (Planned)

- ✅ Alarm Management (Create, Edit, Delete alarms)
- 📱 Remote Phone Control via Web Dashboard
- 💬 Messaging Automation
- 🔔 Real-time Notifications
- 🔐 Secure Authentication
- 📊 Activity Logging & Dashboard

## Tech Stack

### Android
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Database**: Room + SQLite
- **Networking**: Socket.io + OkHttp

### Backend
- **Runtime**: Node.js
- **Framework**: Express.js
- **Database**: PostgreSQL or Firebase
- **Authentication**: JWT
- **Real-time**: WebSockets (Socket.io)

### Web Dashboard
- **Framework**: React
- **UI Library**: Material-UI
- **State Management**: React Hooks
- **Real-time**: Socket.io Client

## Getting Started

### Prerequisites
- Node.js v16+ (for backend)
- Android Studio (for Android app)
- npm or yarn (for web dashboard)
- PostgreSQL (optional, for database)

### Quick Start

#### 1. Clone Repository
```bash
git clone https://github.com/harlemnemzzz-png/phone-ai-assistant.git
cd phone-ai-assistant
```

#### 2. Backend Setup
```bash
git checkout backend
cd backend
npm install

# Create .env file
cp .env.example .env
# Edit .env with your settings

# Start backend server
npm run dev
# Server runs at http://localhost:5000
```

#### 3. Web Dashboard Setup
```bash
git checkout web-dashboard
cd web-dashboard
npm install

# Create .env file
cp .env.example .env
# Edit .env with your settings

# Start web dashboard
npm start
# Dashboard opens at http://localhost:3000
```

#### 4. Android App Setup
```bash
git checkout android-app
# Open in Android Studio
File > Open > phone-ai-assistant

# Build and run on emulator or device
# Emulator: Right-click app > Run
# Device: Connect and Run
```

## Project Structure

```
phone-ai-assistant/
├── README.md
├── CONTRIBUTING.md
├── LICENSE
├── .github/
│   └── workflows/
│       └── ci.yml
├── backend/
│   ├── src/
│   │   ├── models/
│   │   ├── routes/
│   │   ├── controllers/
│   │   ├── middleware/
│   │   └── server.js
│   ├── package.json
│   ├── .env.example
│   └── README.md
├── web-dashboard/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── App.jsx
│   ├── package.json
│   ├── .env.example
│   └── README.md
└── android-app/
    ├── app/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/com/phoneai/
    │   │   │   └── res/
    │   │   └── androidTest/
    │   ├── build.gradle
    │   └── AndroidManifest.xml
    ├── build.gradle
    ├── settings.gradle
    └── README.md
```

## API Documentation

### WebSocket Events

#### Client → Server
- `set_alarm` - Set a new alarm
  ```js
  socket.emit('set_alarm', { time: '09:00', label: 'Morning Alarm' })
  ```

- `get_alarms` - Retrieve all alarms
  ```js
  socket.emit('get_alarms', (data) => console.log(data.alarms))
  ```

#### Server → Client
- `alarm_notification` - Alarm triggered on device
  ```js
  socket.on('alarm_notification', (data) => console.log(data))
  ```

### REST Endpoints (Planned)

- `GET /api/health` - Server health check
- `POST /api/alarms` - Create alarm
- `GET /api/alarms` - List alarms
- `PUT /api/alarms/:id` - Update alarm
- `DELETE /api/alarms/:id` - Delete alarm

## Development Workflow

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Follow the project structure
   - Write clean, readable code
   - Add comments where needed

3. **Commit your changes**
   ```bash
   git commit -m "feat: add your feature description"
   ```

4. **Push and create a Pull Request**
   ```bash
   git push origin feature/your-feature-name
   ```

## Roadmap

- [ ] Backend API setup with Express
- [ ] User authentication (JWT)
- [ ] Alarm model and database schema
- [ ] Android app UI with Jetpack Compose
- [ ] Web dashboard UI
- [ ] Real-time synchronization (WebSockets)
- [ ] Messaging automation
- [ ] Push notifications
- [ ] Testing and CI/CD
- [ ] Deployment (Heroku/AWS)

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Troubleshooting

### Backend won't connect
- Make sure the server is running on port 5000
- Check `.env` file configuration
- Verify Node.js version is 16+

### Web dashboard connection issues
- Ensure backend is running
- Check `REACT_APP_API_URL` in `.env`
- Clear browser cache and try again

### Android app emulator connection
- Use `http://10.0.2.2:5000` for emulator (not localhost)
- Check `AndroidManifest.xml` permissions
- Ensure internet permission is granted

## Support

For issues, questions, or suggestions:
1. Check existing GitHub issues
2. Create a new issue with detailed description
3. Include logs and screenshots if applicable

## Acknowledgments

- React team for Compose framework
- Socket.io for real-time communication
- Express.js community

---

**Happy Coding! 🚀**

Made with ❤️ by harlemnemzzz-png
