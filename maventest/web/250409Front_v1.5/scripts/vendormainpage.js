function goToProducts() {
    window.location.href = "products.html";
  }

function goToCustomer() {
  window.location.href = "customer.html";
}

function goToVendor() {
  window.location.href = "vendor.html";
}

const products = [
  {
    id: "P001",
    name: "Wireless Bluetooth Earbuds",
    price: "$49.99",
    tags: ["Audio", "Portable", "Wireless"],
    vendorIds: ["V001", "V002", "V003"]
  },
  {
    id: "P002",
    name: "Stainless Steel Water Bottle",
    price: "$19.95",
    tags: ["Eco-Friendly", "Drinkware"],
    vendorIds: ["V001", "V003"]
  },
  {
    id: "P003",
    name: "Gaming Mouse",
    price: "$35.50",
    tags: ["Electronics", "Gaming", "Precision"],
    vendorIds: ["V001", "V004"]
  },
  {
    id: "P004",
    name: "Organic Cotton T-Shirt",
    price: "$25.00",
    tags: ["Clothing", "Sustainable", "Casual"],
    vendorIds: ["V004", "V005"]
  }
];


document.getElementById("searchInput").addEventListener("keydown", function (e) {
  if (e.key === "Enter") {
    const query = this.value.trim().toLowerCase();
    if (!query) return;

    const match = products.find(p =>
      p.name.toLowerCase().includes(query) ||
      p.tags.some(tag => tag.toLowerCase().includes(query))
    );

    if (match) {
      localStorage.setItem("selectedProduct", JSON.stringify(match));
      window.location.href = "product_detail.html";
    } else {
      alert("No matching product found.");
    }
  }
});