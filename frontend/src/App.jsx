import { useState } from 'react'
import './App.css'

import AuthPage from './components/AuthPage'
import PortalLayout from './components/PortalLayout'
import Dashboard from './components/Dashboard'
import ShipmentList from './components/ShipmentList'
import TrackShipment from './components/TrackShipment'
import Notifications from './components/Notifications'

import { shipmentApi, notificationApi } from './api'

function App() {
  const [user, setUser] = useState(null)
  const [activePage, setActivePage] = useState('dashboard')
  const [shipments, setShipments] = useState([])
  const [notifications, setNotifications] = useState([])
  const [selectedShipment, setSelectedShipment] = useState(null)
  const [globalMessage, setGlobalMessage] = useState('')

  const loadShipments = async (customerId) => {
    try {
      const data = await shipmentApi.getCustomerShipments(customerId)
      setShipments(data)
    } catch {
      setShipments([])
    }
  }

  const loadNotifications = async (customerId) => {
    try {
      const data = await notificationApi.getCustomerNotifications(customerId)
      setNotifications(data)
    } catch {
      setNotifications([])
    }
  }

  const handleAuthSuccess = async (customer) => {
    setUser(customer)
    setActivePage('dashboard')
    setGlobalMessage(customer.message)
    await loadShipments(customer.customerId)
    await loadNotifications(customer.customerId)
  }

  const refreshCustomerData = async () => {
    if (!user) return
    await loadShipments(user.customerId)
    await loadNotifications(user.customerId)
  }

  const handleLogout = () => {
    setUser(null)
    setShipments([])
    setNotifications([])
    setSelectedShipment(null)
    setActivePage('dashboard')
    setGlobalMessage('')
  }

  if (!user) {
    return <AuthPage onAuthSuccess={handleAuthSuccess} />
  }

  return (
    <PortalLayout
      user={user}
      activePage={activePage}
      setActivePage={setActivePage}
      onLogout={handleLogout}
    >
      {activePage === 'dashboard' && (
        <Dashboard
          user={user}
          shipments={shipments}
          notifications={notifications}
          globalMessage={globalMessage}
          setActivePage={setActivePage}
          setSelectedShipment={setSelectedShipment}
        />
      )}

      {activePage === 'shipments' && (
        <ShipmentList
          shipments={shipments}
          setActivePage={setActivePage}
          setSelectedShipment={setSelectedShipment}
        />
      )}

      {activePage === 'track' && (
        <TrackShipment
          user={user}
          selectedShipment={selectedShipment}
          setSelectedShipment={setSelectedShipment}
          refreshCustomerData={refreshCustomerData}
        />
      )}

      {activePage === 'notifications' && (
        <Notifications
          notifications={notifications}
          refreshCustomerData={refreshCustomerData}
        />
      )}
    </PortalLayout>
  )
}

export default App