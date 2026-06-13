function Dashboard({
  user,
  shipments,
  notifications,
  globalMessage,
  setActivePage,
  setSelectedShipment,
}) {
  const inTransit = shipments.filter((s) => s.status === 'IN_TRANSIT').length
  const rescheduled = shipments.filter((s) => s.status === 'RESCHEDULED').length
  const delayed = shipments.filter((s) => s.status === 'DELAYED').length
  const recentShipments = shipments.slice(0, 3)

  return (
    <section className="dashboard-page">
      <div className="hero-panel">
        <div>
          <span className="eyebrow">Customer Delivery Workspace</span>
          <h1>Hello {user.name}, manage your shipments from one place.</h1>
          <p>
            Track delivery progress, update delivery preferences, reschedule suitable dates,
            and review system notifications.
          </p>

          <div className="hero-actions">
            <button className="primary-btn" onClick={() => setActivePage('track')}>
              Track a Shipment
            </button>
            <button className="outline-btn" onClick={() => setActivePage('notifications')}>
              View Alerts
            </button>
          </div>
        </div>

        <div className="hero-metric-card">
          <span>Total Active Shipments</span>
          <strong>{shipments.length}</strong>
          <p>{notifications.length} customer notifications available</p>
        </div>
      </div>

      {globalMessage && <div className="success-box">{globalMessage}</div>}

      <div className="metric-strip">
        <div className="metric-tile">
          <span>In Transit</span>
          <strong>{inTransit}</strong>
        </div>
        <div className="metric-tile amber">
          <span>Rescheduled</span>
          <strong>{rescheduled}</strong>
        </div>
        <div className="metric-tile red">
          <span>Delayed</span>
          <strong>{delayed}</strong>
        </div>
        <div className="metric-tile violet">
          <span>Alerts</span>
          <strong>{notifications.length}</strong>
        </div>
      </div>

      <div className="dashboard-grid">
        <div className="panel large-panel">
          <div className="panel-header">
            <div>
              <h2>Shipment Activity</h2>
              <p>Recent shipment records linked to your customer account.</p>
            </div>
            <button className="outline-btn small" onClick={() => setActivePage('shipments')}>
              View all shipments
            </button>
          </div>

          <div className="shipment-card-list">
            {recentShipments.map((shipment) => (
              <div className="shipment-row-card" key={shipment.shipmentId}>
                <div>
                  <span className="tracking-label">{shipment.trackingId}</span>
                  <h3>{shipment.destination}</h3>
                  <p>From {shipment.origin}</p>
                </div>

                <div>
                  <span className={`status-badge ${shipment.status.toLowerCase()}`}>
                    {shipment.status}
                  </span>
                  <p className="date-text">ETA: {shipment.expectedDeliveryDate}</p>
                </div>

                <button
                  className="table-btn"
                  onClick={() => {
                    setSelectedShipment(shipment)
                    setActivePage('track')
                  }}
                >
                  Manage
                </button>
              </div>
            ))}
          </div>
        </div>

        <div className="panel action-panel">
          <h2>Recommended Actions</h2>
          <div className="action-item">
            <strong>Check delivery preferences</strong>
            <p>Review special instructions before the delivery date.</p>
          </div>
          <div className="action-item">
            <strong>Monitor changes</strong>
            <p>Use alerts to view recent shipment updates.</p>
          </div>
          <button className="primary-btn full" onClick={() => setActivePage('track')}>
            Open Track & Manage
          </button>
        </div>
      </div>
    </section>
  )
}

export default Dashboard