fetch('http://localhost:8080/JCustomer.json') // 替换为你的 Java 服务器地址和 JSON 文件路径
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.json(); // 将响应解析为 JSON
  })
  .then(data => {
    console.log('成功获取 JSON 数据:', data);
    // 在这里处理你的 JSON 数据
  })
  .catch(error => {
    console.error('获取 JSON 数据失败:', error);
  });