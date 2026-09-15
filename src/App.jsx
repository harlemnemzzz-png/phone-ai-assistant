import React, { useState, useEffect } from 'react';
import io from 'socket.io-client';
import './App.css';
import AlarmManager from './components/AlarmManager';
import Dashboard from './components/Dashboard';

const socket = io(process.env.REACT_APP_API_URL || 'http://localhost:5000');

function App() {
  const [isConnected, setIsConnected] = useState(false);
  const [alarms, setAlarms] = useState([]);

  useEffect(() => {
    socket.on('connect', () => {
      console.log('Connected to server');
      setIsConnected(true);
    });

    socket.on('disconnect', () => {
      console.log('Disconnected from server');
      setIsConnected(false);
    });

    socket.on('alarm_notification', (data) => {
      console.log('Alarm notification:', data);
      setAlarms([...alarms, data]);
    });

    return () => {
      socket.off('connect');
      socket.off('disconnect');
      socket.off('alarm_notification');
    };
  }, [alarms]);

  const setAlarm = (alarmData) => {
    socket.emit('set_alarm', alarmData);
  };

  const getAlarms = () => {
    socket.emit('get_alarms', (data) => {
      setAlarms(data.alarms);
    });
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>📱 Phone AI Assistant Dashboard</h1>
        <p>Status: {isConnected ? '🟢 Connected' : '🔴 Disconnected'}</p>
      </header>
      <main>
        <Dashboard />
        <AlarmManager alarms={alarms} onSetAlarm={setAlarm} onGetAlarms={getAlarms} />
      </main>
    </div>
  );
}

export default App;
