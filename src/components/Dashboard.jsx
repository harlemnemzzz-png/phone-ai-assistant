import React from 'react';
import './Dashboard.css';

function Dashboard() {
  return (
    <div className="dashboard">
      <h2>Dashboard</h2>
      <div className="stats">
        <div className="stat-card">
          <h3>Active Alarms</h3>
          <p className="stat-number">5</p>
        </div>
        <div className="stat-card">
          <h3>Messages Sent</h3>
          <p className="stat-number">24</p>
        </div>
        <div className="stat-card">
          <h3>Device Status</h3>
          <p className="stat-number">Online</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
