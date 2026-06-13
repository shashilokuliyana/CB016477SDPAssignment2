function PortalLayout({ user, activePage, setActivePage, onLogout, children }) {
  return (
    <div className="app-shell">
      <header className="topbar">
        <div className="topbar-brand">
          <div className="brand-mark">RS</div>
          <div>
            <h2>RouteSphere Control Centre</h2>
            <p>Customer shipment self-service module</p>
          </div>
        </div>

        <nav className="topnav">
          <button
            className={activePage === 'dashboard' ? 'active' : ''}
            onClick={() => setActivePage('dashboard')}
          >
            Overview
          </button>
          <button
            className={activePage === 'shipments' ? 'active' : ''}
            onClick={() => setActivePage('shipments')}
          >
            Shipments
          </button>
          <button
            className={activePage === 'track' ? 'active' : ''}
            onClick={() => setActivePage('track')}
          >
            Track & Manage
          </button>
          <button
            className={activePage === 'notifications' ? 'active' : ''}
            onClick={() => setActivePage('notifications')}
          >
            Alerts
          </button>
        </nav>

        <div className="topbar-user">
          <div className="avatar">{user.name?.charAt(0) || 'C'}</div>
          <div>
            <strong>{user.name}</strong>
            <span>{user.email}</span>
          </div>
          <button className="logout-link" onClick={onLogout}>
            Logout
          </button>
        </div>
      </header>

      <main className="content-area">{children}</main>
    </div>
  )
}

export default PortalLayout