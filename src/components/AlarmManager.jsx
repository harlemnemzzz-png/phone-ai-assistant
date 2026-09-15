import React, { useState } from 'react';
import './AlarmManager.css';

function AlarmManager({ alarms, onSetAlarm, onGetAlarms }) {
  const [time, setTime] = useState('');
  const [label, setLabel] = useState('');

  const handleSetAlarm = (e) => {
    e.preventDefault();
    if (time && label) {
      onSetAlarm({ time, label, id: Date.now() });
      setTime('');
      setLabel('');
    }
  };

  return (
    <div className="alarm-manager">
      <h2>⏰ Alarm Manager</h2>
      
      <form onSubmit={handleSetAlarm} className="alarm-form">
        <input
          type="time"
          value={time}
          onChange={(e) => setTime(e.target.value)}
          placeholder="Select time"
          required
        />
        <input
          type="text"
          value={label}
          onChange={(e) => setLabel(e.target.value)}
          placeholder="Alarm label"
          required
        />
        <button type="submit">Set Alarm</button>
      </form>

      <button className="refresh-btn" onClick={onGetAlarms}>🔄 Refresh Alarms</button>

      <div className="alarms-list">
        <h3>Active Alarms</h3>
        {alarms.length === 0 ? (
          <p>No alarms set</p>
        ) : (
          <ul>
            {alarms.map((alarm) => (
              <li key={alarm.id}>
                <strong>{alarm.time}</strong> - {alarm.label}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default AlarmManager;
