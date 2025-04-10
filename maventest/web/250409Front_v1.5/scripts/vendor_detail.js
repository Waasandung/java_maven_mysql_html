function goBack() {
    window.history.back(); // 回退上一个页面
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
  

  const products = {
    P001: {
      name: "Wireless Bluetooth Earbuds",
      price: "$49.99",
      tags: ["Audio", "Portable", "Wireless"]
    },
    P002: {
      name: "Stainless Steel Water Bottle",
      price: "$19.95",
      tags: ["Eco-Friendly", "Drinkware"]
    },
    P003: {
      name: "Gaming Mouse",
      price: "$35.50",
      tags: ["Electronics", "Gaming", "Precision"]
    },
    P004: {
      name: "Organic Cotton T-Shirt",
      price: "$25.00",
      tags: ["Clothing", "Sustainable", "Casual"]
    }
  };
  
  const vendor = JSON.parse(localStorage.getItem("selectedVendor"));
  
  if (vendor) {
    document.getElementById("vendorName").textContent = vendor.name;
  
    const vendorDetailDiv = document.getElementById("vendorDetail");
    vendorDetailDiv.innerHTML = `
      <strong>Vendor ID:</strong> ${vendor.id}<br/>
      <strong>Customer Feedback Score:</strong> ${vendor.score}<br/>
      <strong>Geographical Presence:</strong> ${vendor.location}
    `;
  
    const productsDiv = document.getElementById("productGrid");
    vendor.products.forEach(pid => {
      const product = products[pid];
      const div = document.createElement("div");
      div.className = "vendor-item";
      div.innerHTML = `
        <strong>Product ID:</strong> ${pid}<br/>
        <strong>Name:</strong> ${product.name}<br/>
        <strong>Price:</strong> ${product.price}<br/>
        <strong>Tags:</strong> [${product.tags.join(", ")}]
      `;

      div.onclick = () => {
        const fullProduct = {
          id: pid,
          name: product.name,
          price: product.price,
          tags: product.tags,
          vendorIds: Object.keys(vendorsMap).filter(vId =>
            vendorsMap[vId].products.includes(pid)
          )
        };
        localStorage.setItem("selectedProduct", JSON.stringify(fullProduct));
        window.location.href = "product_detail.html";
      };

      productsDiv.appendChild(div);
    });
  
  }
  