function goBack() {
    window.history.back();
  }
  
  const vendorsMap = {
    V001: "TechTrend Innovations",
    V002: "AudioWave Co.",
    V003: "GreenLiving Supplies",
    V004: "GamerTech Solutions",
    V005: "PureThread Apparel"
  };
  
  const product = JSON.parse(localStorage.getItem("selectedProduct"));
  
  if (product) {
    document.getElementById("productName").textContent = product.name;
  
    document.getElementById("productInfo").innerHTML = `
      <strong>Product ID:</strong> ${product.id}<br/>
      <strong>Price:</strong> ${product.price}<br/>
      <strong>Tags:</strong> [${product.tags.join(", ")}]
    `;
  
    const vendorSelect = document.getElementById("vendorSelect");
    product.vendorIds.forEach(vendorId => {
      const option = document.createElement("option");
      option.value = vendorId;
      option.text = vendorsMap[vendorId];
      vendorSelect.appendChild(option);
    });
  }
  
  function purchase() {
    const vendor = document.getElementById("vendorSelect").value;
    const customer = document.getElementById("customerSelect").value;
    alert(`Purchased ${product.name} from ${vendorsMap[vendor]} for customer ${customer}`);
  }
  