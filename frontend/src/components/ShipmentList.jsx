function ShipmentList({ shipments, setActivePage, setSelectedShipment }) {
  return (
    <section>
      <div className="page-header">
        <div>
          <h1>My Shipments</h1>
          <p>View all shipments linked to your customer account.</p>
        </div>
      </div>

      <div className="panel">
        {shipments.length === 0 ? (
          <div className="empty-state">
            <h3>No shipments found</h3>
            <p>Your shipment records will appear here after they are linked to your account.</p>
          </div>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Tracking ID</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Status</th>
                <th>Expected Date</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {shipments.map((shipment) => (
                <tr key={shipment.shipmentId}>
                  <td>{shipment.trackingId}</td>
                  <td>{shipment.origin}</td>
                  <td>{shipment.destination}</td>
                  <td>
                    <span className={`status-badge ${shipment.status.toLowerCase()}`}>
                      {shipment.status}
                    </span>
                  </td>
                  <td>{shipment.expectedDeliveryDate}</td>
                  <td>
                    <button
                      className="table-btn"
                      onClick={() => {
                        setSelectedShipment(shipment)
                        setActivePage('track')
                      }}
                    >
                      Manage
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </section>
  )
}

export default ShipmentList