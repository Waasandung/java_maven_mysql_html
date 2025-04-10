function goBack() {
  window.history.back();;
}

const vendorsMap = {
  V001: {
    id: "V001",
    name: "TechTrend Innovations",
    score: "4.8/5",
    location: "San Francisco, CA, USA",
    products: ["P001", "P002", "P003"]
  },
  V002: {
    id: "V002",
    name: "AudioWave Co.",
    score: "4.5/5",
    location: "London, United Kingdom",
    products: ["P001"]
  },
  V003: {
    id: "V003",
    name: "GreenLiving Supplies",
    score: "4.7/5",
    location: "Sydney, NSW, Australia",
    products: ["P001", "P002"]
  },
  V004: {
    id: "V004",
    name: "GamerTech Solutions",
    score: "4.9/5",
    location: "Tokyo, Japan",
    products: ["P003", "P004"]
  },
  V005: {
    id: "V005",
    name: "PureThread Apparel",
    score: "4.4/5",
    location: "Shanghai, China",
    products: ["P004"]
  }
};

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

const container = document.getElementById("productList");

products.forEach((product) => {
  const div = document.createElement("div");
  div.className = "product-item";
  const vendorLinks = product.vendorIds.map(vid => {
    const vendor = vendorsMap[vid];
    return `<a href="#" onclick="goToVendor(event, '${vid}')" class="vendor-link">${vendor.name}</a>`;
  }).join(", ");
  

  div.innerHTML = `
  <div class="product-content">
    <strong>Product ID:</strong> ${product.id}<br/>
    <strong>Name:</strong> ${product.name}<br/>
    <strong>Price:</strong> ${product.price}<br/>
    <strong>Tags:</strong> [${product.tags.join(", ")}]<br/>
    <strong>Provided by:</strong> ${vendorLinks}
  </div>
`;

div.querySelector(".product-content").onclick = () => {
  localStorage.setItem("selectedProduct", JSON.stringify(product));
  window.location.href = "product_detail.html";
};
  container.appendChild(div);
});

function goToVendor(event, vendorId) {
  event.stopPropagation(); 
  const vendor = vendorsMap[vendorId];
  localStorage.setItem("selectedVendor", JSON.stringify(vendor));
  window.location.href = "vendor_detail.html";
}

