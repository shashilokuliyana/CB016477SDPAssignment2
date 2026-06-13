const API_BASE_URL = 'http://localhost:8080/api'

async function request(endpoint, options = {}) {
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  })

  let data = null
  try {
    data = await response.json()
  } catch {
    data = null
  }

  if (!response.ok) {
    let message = 'Request failed'

    if (data?.message) {
      message = data.message
    }

    if (data?.messages) {
      message = Object.values(data.messages).join(', ')
    }

    throw new Error(message)
  }

  return data
}

export const authApi = {
  register: (payload) =>
    request('/auth/register', {
      method: 'POST',
      body: JSON.stringify(payload),
    }),

  login: (payload) =>
    request('/auth/login', {
      method: 'POST',
      body: JSON.stringify(payload),
    }),
}

export const shipmentApi = {
  getCustomerShipments: (customerId) =>
    request(`/shipments/customer/${customerId}`),

  trackShipment: (trackingId, customerId) =>
    request(`/shipments/track/${trackingId}?customerId=${customerId}`),

  rescheduleShipment: (shipmentId, customerId, payload) =>
    request(`/shipments/${shipmentId}/reschedule?customerId=${customerId}`, {
      method: 'PUT',
      body: JSON.stringify(payload),
    }),

  updateInstruction: (shipmentId, customerId, payload) =>
    request(`/shipments/${shipmentId}/instructions?customerId=${customerId}`, {
      method: 'PUT',
      body: JSON.stringify(payload),
    }),
}

export const notificationApi = {
  getCustomerNotifications: (customerId) =>
    request(`/notifications/customer/${customerId}`),
}