function goBack() {
  window.history.back();;
}

const customers = {
  C001: {
    contact: "+1-555-123-4567",
    shipping: "123 Maple Street, Apt 4B, New York, NY 10001, USA",
    orders: [
      "Order #1001: Wireless Bluetooth Earbuds (P001), 2025-03-15",
      "Order #1005: Stainless Steel Water Bottle (P002), 2025-03-20"
    ]
  },
  C002: {
    contact: "+44-207-555-7890",
    shipping: "45 Baker Street, London, NW1 6XE, United Kingdom",
    orders: [
      "Order #1002: Gaming Mouse (P003), 2025-03-18"
    ]
  },
  C003: {
    contact: "+61-2-9876-5432",
    shipping: "78 Ocean Drive, Sydney, NSW 2000, Australia",
    orders: [
      "Order #1003: Organic Cotton T-Shirt (P004), 2025-03-22",
      "Order #1006: Wireless Bluetooth Earbuds (P001), 2025-03-25"
    ]
  },
  C004: {
    contact: "+86-138-5555-6677",
    shipping: "456 Zhongshan Road, Apt 12C, Shanghai, 200001, China",
    orders: [
      "Order #1004: Stainless Steel Water Bottle (P002), 2025-03-19"
    ]
  }
};

function renderCustomer() {
  const id = document.getElementById("customerSelect").value;
  const info = customers[id];
  const container = document.getElementById("customerInfo");
  container.innerHTML = `
    <strong>Customer ID:</strong> ${id}<br/>
    <strong>Contact Number:</strong> ${info.contact}<br/>
    <strong>Shipping Details:</strong> ${info.shipping}<br/>
    <strong>Order History:</strong><br/>
    <ul>${info.orders.map(order => `<li>${order}</li>`).join("")}</ul>
  `;
}

window.onload = renderCustomer;
