import { useState } from 'react'
import { authApi } from '../api'

function AuthPage({ onAuthSuccess }) {
  const [mode, setMode] = useState('login')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const [activeFeature, setActiveFeature] = useState('Shipment Tracking')

const featureDetails = {
  'Shipment Tracking': 'Customers can search by tracking ID and view current delivery status.',
  Reschedule: 'Customers can request a new delivery date when the original date is not suitable.',
  Notifications: 'Customers can view shipment updates, delivery changes, and system messages.',
  'Secure Login': 'Customer access is protected using login validation and password hashing.',
}

  const [loginForm, setLoginForm] = useState({
    email: 'customer@example.com',
    password: 'password123',
  })

  const [registerForm, setRegisterForm] = useState({
    name: '',
    email: '',
    password: '',
    phone: '',
  })

  const handleLogin = async (event) => {
    event.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await authApi.login(loginForm)
      onAuthSuccess(response)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const handleRegister = async (event) => {
    event.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await authApi.register(registerForm)
      onAuthSuccess(response)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-redesign">
      <div className="auth-info-panel">
        <span className="eyebrow light">RouteSphere Logistics Management System</span>
        <h1>Manage shipment updates through one smart delivery portal</h1>
        <p>
           RouteSphere allows customers to track shipments, request delivery changes,
           update delivery instructions, and view notification messages through a secure self-service module.
        </p>

        <div className="feature-pills interactive-pills">
  {Object.keys(featureDetails).map((feature) => (
    <button
      key={feature}
      type="button"
      className={activeFeature === feature ? 'feature-pill active' : 'feature-pill'}
      onClick={() => setActiveFeature(feature)}
      title={featureDetails[feature]}
    >
      {feature}
    </button>
  ))}
</div>

<div className="feature-preview">
  <strong>{activeFeature}</strong>
  <p>{featureDetails[activeFeature]}</p>
</div>
      </div>

      <div className="auth-form-panel">
        <div className="brand-row">
          <div className="brand-mark">RS</div>
          <div>
            <h2>RouteSphere Customer Portal</h2>
            <p>Customer Shipment Self-Service</p>
          </div>
        </div>

        <div className="switcher">
          <button
            className={mode === 'login' ? 'active' : ''}
            onClick={() => {
              setMode('login')
              setError('')
            }}
          >
            Login
          </button>
          <button
            className={mode === 'register' ? 'active' : ''}
            onClick={() => {
              setMode('register')
              setError('')
            }}
          >
            Register
          </button>
        </div>

        {error && <div className="error-box">{error}</div>}

        {mode === 'login' ? (
          <form onSubmit={handleLogin} className="form">
            <label>Email address</label>
            <input
              type="email"
              value={loginForm.email}
              onChange={(e) => setLoginForm({ ...loginForm, email: e.target.value })}
              required
            />

            <label>Password</label>
            <input
              type="password"
              value={loginForm.password}
              onChange={(e) => setLoginForm({ ...loginForm, password: e.target.value })}
              required
            />

            <button className="primary-btn" type="submit" disabled={loading}>
              {loading ? 'Checking account...' : 'Login to Portal'}
            </button>

            <p className="hint">Demo account: customer@example.com / password123</p>
          </form>
        ) : (
          <form onSubmit={handleRegister} className="form">
            <label>Full name</label>
            <input
              value={registerForm.name}
              onChange={(e) => setRegisterForm({ ...registerForm, name: e.target.value })}
              required
            />

            <label>Email address</label>
            <input
              type="email"
              value={registerForm.email}
              onChange={(e) => setRegisterForm({ ...registerForm, email: e.target.value })}
              required
            />

            <label>Password</label>
            <input
              type="password"
              value={registerForm.password}
              onChange={(e) => setRegisterForm({ ...registerForm, password: e.target.value })}
              required
              minLength="8"
            />

            <label>Phone</label>
            <input
              value={registerForm.phone}
              onChange={(e) => setRegisterForm({ ...registerForm, phone: e.target.value })}
            />

            <button className="primary-btn" type="submit" disabled={loading}>
              {loading ? 'Creating account...' : 'Create Customer Account'}
            </button>
          </form>
        )}
      </div>
    </div>
  )
}

export default AuthPage