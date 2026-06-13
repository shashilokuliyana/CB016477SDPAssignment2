import { useEffect, useState } from 'react'
import { shipmentApi } from '../api'

function TrackShipment({ user, selectedShipment, setSelectedShipment, refreshCustomerData }) {
  const [trackingId, setTrackingId] = useState('')
  const [rescheduleDate, setRescheduleDate] = useState('')
  const [rescheduleReason, setRescheduleReason] = useState('')
  const [instructionText, setInstructionText] = useState('')
  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  useEffect(() => {
    if (selectedShipment) {
      setTrackingId(selectedShipment.trackingId)
      setInstructionText(selectedShipment.specialInstructions || '')
      setRescheduleDate('')
      setRescheduleReason('')
    }
  }, [selectedShipment])

  const handleTrack = async (event) => {
    event.preventDefault()
    setMessage('')
    setError('')
    setLoading(true)

    try {
      const data = await shipmentApi.trackShipment(trackingId, user.customerId)
      setSelectedShipment(data)
      setInstructionText(data.specialInstructions || '')
      setMessage('Shipment details loaded successfully')
    } catch (err) {
      setSelectedShipment(null)
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const handleReschedule = async (event) => {
    event.preventDefault()
    if (!selectedShipment) return

    setMessage('')
    setError('')
    setLoading(true)

    try {
      await shipmentApi.rescheduleShipment(selectedShipment.shipmentId, user.customerId, {
        newDeliveryDate: rescheduleDate,
        reason: rescheduleReason,
      })

      const updatedShipment = await shipmentApi.trackShipment(
        selectedShipment.trackingId,
        user.customerId
      )

      setSelectedShipment(updatedShipment)
      await refreshCustomerData()
      setMessage('Delivery rescheduled successfully')
      setRescheduleDate('')
      setRescheduleReason('')
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const handleInstructionUpdate = async (event) => {
    event.preventDefault()
    if (!selectedShipment) return

    setMessage('')
    setError('')
    setLoading(true)

    try {
      await shipmentApi.updateInstruction(selectedShipment.shipmentId, user.customerId, {
        instructionText,
      })

      const updatedShipment = await shipmentApi.trackShipment(
        selectedShipment.trackingId,
        user.customerId
      )

      setSelectedShipment(updatedShipment)
      await refreshCustomerData()
      setMessage('Delivery instruction saved successfully')
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <section>
      <div className="page-header">
        <div>
          <h1>Track Shipment</h1>
          <p>Search by tracking ID and manage delivery preferences.</p>
        </div>
      </div>

      {message && <div className="success-box">{message}</div>}
      {error && <div className="error-box">{error}</div>}

      <div className="panel">
        <form className="track-form" onSubmit={handleTrack}>
          <div>
            <label>Tracking ID</label>
            <input
              value={trackingId}
              onChange={(e) => setTrackingId(e.target.value)}
              placeholder="Example: TRK1001"
              required
            />
          </div>
          <button className="primary-btn" type="submit" disabled={loading}>
            {loading ? 'Searching...' : 'Track Shipment'}
          </button>
        </form>
      </div>

      {selectedShipment && (
        <div className="details-grid">
          <div className="panel">
            <div className="panel-header">
              <div>
                <h2>Shipment Details</h2>
                <p>{selectedShipment.trackingId}</p>
              </div>
              <span className={`status-badge ${selectedShipment.status.toLowerCase()}`}>
                {selectedShipment.status}
              </span>
            </div>

            <div className="detail-list">
              <div>
                <span>Origin</span>
                <strong>{selectedShipment.origin}</strong>
              </div>
              <div>
                <span>Destination</span>
                <strong>{selectedShipment.destination}</strong>
              </div>
              <div>
                <span>Expected Delivery Date</span>
                <strong>{selectedShipment.expectedDeliveryDate}</strong>
              </div>
              <div>
                <span>Driver</span>
                <strong>{selectedShipment.driverName || 'Not assigned'}</strong>
              </div>
              <div>
                <span>Last Updated</span>
                <strong>{selectedShipment.lastUpdated}</strong>
              </div>
              <div>
                <span>Special Instructions</span>
                <strong>{selectedShipment.specialInstructions || 'No instructions added'}</strong>
              </div>
            </div>

            <div className="status-timeline">
              <div className="timeline-step done">Order Created</div>
              <div className="timeline-step done">In Transit</div>
              <div className="timeline-step active">{selectedShipment.status}</div>
              <div className="timeline-step">Delivered</div>
            </div>
          </div>

          <div className="panel">
            <h2>Reschedule Delivery</h2>
            <p>Choose a new delivery date if the current delivery date is not suitable.</p>

            <form className="form" onSubmit={handleReschedule}>
              <label>New Delivery Date</label>
              <input
                type="date"
                value={rescheduleDate}
                onChange={(e) => setRescheduleDate(e.target.value)}
                required
              />

              <label>Reason</label>
              <textarea
                value={rescheduleReason}
                onChange={(e) => setRescheduleReason(e.target.value)}
                placeholder="Example: Customer is not available on original date"
                rows="4"
              />

              <button className="primary-btn" type="submit" disabled={loading}>
                {loading ? 'Saving...' : 'Reschedule Delivery'}
              </button>
            </form>
          </div>

          <div className="panel full-width">
            <h2>Special Delivery Instructions</h2>
            <p>Add instructions for the delivery driver.</p>

            <form className="form" onSubmit={handleInstructionUpdate}>
              <label>Instruction</label>
              <textarea
                value={instructionText}
                onChange={(e) => setInstructionText(e.target.value)}
                placeholder="Example: Leave parcel at front door and call before delivery"
                rows="4"
                required
              />

              <button className="primary-btn" type="submit" disabled={loading}>
                {loading ? 'Saving...' : 'Save Instruction'}
              </button>
            </form>
          </div>
        </div>
      )}
    </section>
  )
}

export default TrackShipment