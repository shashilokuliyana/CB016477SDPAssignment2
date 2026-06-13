function Notifications({ notifications, refreshCustomerData }) {
  return (
    <section>
      <div className="page-header">
        <div>
          <h1>Notifications</h1>
          <p>View shipment updates, delivery changes, and customer messages.</p>
        </div>

        <button className="secondary-btn" onClick={refreshCustomerData}>
          Refresh
        </button>
      </div>

      <div className="panel">
        {notifications.length === 0 ? (
          <div className="empty-state">
            <h3>No notifications found</h3>
            <p>Shipment updates and delivery messages will appear here.</p>
          </div>
        ) : (
          <div className="notification-list">
            {notifications.map((notification) => (
              <div className="notification-card" key={notification.notificationId}>
                <div>
                  <span className="notification-type">{notification.notificationType}</span>
                  <h3>{notification.message}</h3>
                  <p>Tracking ID: {notification.trackingId}</p>
                </div>
                <small>{notification.createdAt}</small>
              </div>
            ))}
          </div>
        )}
      </div>
    </section>
  )
}

export default Notifications