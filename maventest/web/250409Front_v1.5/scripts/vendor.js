function goBack() {
    window.history.back();
  }
  
  // const vendors = [
  //   {
  //     id: "V001",
  //     name: "TechTrend Innovations",
  //     score: "4.8/5",
  //     location: "San Francisco, CA, USA",
  //     products: ["P001", "P002", "P003"]
  //   },
  //   {
  //     id: "V002",
  //     name: "AudioWave Co.",
  //     score: "4.5/5",
  //     location: "London, United Kingdom",
  //     products: ["P001"]
  //   },
  //   {
  //     id: "V003",
  //     name: "GreenLiving Supplies",
  //     score: "4.7/5",
  //     location: "Sydney, NSW, Australia",
  //     products: ["P001", "P002"]
  //   },
  //   {
  //     id: "V004",
  //     name: "GamerTech Solutions",
  //     score: "4.9/5",
  //     location: "Tokyo, Japan",
  //     products: ["P003", "P004"]
  //   },
  //   {
  //     id: "V005",
  //     name: "PureThread Apparel",
  //     score: "4.4/5",
  //     location: "Shanghai, China",
  //     products: ["P004"]
  //   }
  // ];
  
  const container = document.getElementById("vendorList");
// Use full API adress
fetch('http://localhost:5000/api/vendors')
    .then(response => {
      if (!response.ok) {
          throw new Error('Network error: ' + response.status);
      }
      return response.json();
    })
    .then(vendors => {
      vendors.forEach(vendor => {
        const div = document.createElement("div");
        div.className = "vendor-item";
        div.innerHTML = `
            <strong>Vendor ID:</strong> ${vendor.VendorID}<br/>
            <strong>Business Name:</strong> ${vendor.BusinessName}<br/>
            <strong>Customer Feedback Score:</strong> ${vendor.FeedbackScore}<br/>
            <strong>Geographical Presence:</strong> ${vendor.Geography}
        `;
        div.onclick = () => {
            localStorage.setItem("selectedVendor", JSON.stringify(vendor));
            window.location.href = "vendor_detail.html";
        };
        container.appendChild(div);
      });
    })
    .catch(error => {
      console.error('获取供应商数据失败:', error);
      container.innerHTML = '<p>无法加载供应商数据，请稍后重试。</p>';
    });

  
  // vendors.forEach(vendor => {
  //   const div = document.createElement("div");
  //   div.className = "vendor-item";
  //   div.innerHTML = `
  //     <strong>Vendor ID:</strong> ${vendor.id}<br/>
  //     <strong>Business Name:</strong> ${vendor.name}<br/>
  //     <strong>Customer Feedback Score:</strong> ${vendor.score}<br/>
  //     <strong>Geographical Presence:</strong> ${vendor.location}
  //   `;
  //   div.onclick = () => {
  //     // 使用 localStorage 传递数据
  //     localStorage.setItem("selectedVendor", JSON.stringify(vendor));
  //     window.location.href = "vendor_detail.html";
  //   };
  //   container.appendChild(div);
  // });
  