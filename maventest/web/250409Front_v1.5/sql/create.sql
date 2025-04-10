-- 创建数据库
DROP DATABASE IF EXISTS VendorPlatform;
CREATE DATABASE VendorPlatform 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

USE VendorPlatform;

-- 供应商表
CREATE TABLE Vendor (
  VendorID INT PRIMARY KEY AUTO_INCREMENT,
  BusinessName VARCHAR(255) NOT NULL,
  FeedbackScore INT DEFAULT 0 COMMENT 'Percentage score (0-100)',
  Geography VARCHAR(500)
);

-- 产品表
CREATE TABLE Product (
  ProductID INT PRIMARY KEY AUTO_INCREMENT,
  VendorID INT NOT NULL,
  ProductName VARCHAR(255) NOT NULL,
  ListPrice INT NOT NULL,
  Tag1 VARCHAR(50),
  Tag2 VARCHAR(50),
  Tag3 VARCHAR(50),
  FOREIGN KEY (VendorID) REFERENCES Vendor(VendorID),
  CONSTRAINT chk_tags CHECK (
    (Tag1 IS NOT NULL AND Tag2 IS NULL AND Tag3 IS NULL) OR
    (Tag1 IS NOT NULL AND Tag2 IS NOT NULL AND Tag3 IS NULL) OR
    (Tag1 IS NOT NULL AND Tag2 IS NOT NULL AND Tag3 IS NOT NULL)
  ),
  CONSTRAINT chk_price CHECK (ListPrice > 0)
);

-- 客户表
CREATE TABLE Customer (
  CustomerID INT PRIMARY KEY AUTO_INCREMENT,
  ContactNumber VARCHAR(20) NOT NULL UNIQUE,
  ShippingAddress VARCHAR(500)
);

-- 订单表 (使用反引号避开保留字)
CREATE TABLE `Order` (
  OrderID INT PRIMARY KEY AUTO_INCREMENT,
  CustomerID INT NOT NULL,
  OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
  TotalAmount INT NOT NULL,
  FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- 订单供应商关联表
CREATE TABLE OrderVendor (
  OrderVendorID INT PRIMARY KEY AUTO_INCREMENT,
  OrderID INT NOT NULL,
  VendorID INT NOT NULL,
  FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID),
  FOREIGN KEY (VendorID) REFERENCES Vendor(VendorID)
);

-- 订单明细表
CREATE TABLE OrderItem (
  OrderItemID INT PRIMARY KEY AUTO_INCREMENT,
  OrderVendorID INT NOT NULL,
  ProductID INT NOT NULL,
  Quantity INT NOT NULL CHECK (Quantity > 0),
  UnitPrice INT NOT NULL COMMENT '下单时单价',
  FOREIGN KEY (OrderVendorID) REFERENCES OrderVendor(OrderVendorID),
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- 插入示例数据
INSERT INTO Vendor (BusinessName, FeedbackScore, Geography) VALUES
('VendorA', 95, 'KLN'),
('VendorB', 92, 'HK');

INSERT INTO Product (VendorID, ProductName, ListPrice, Tag1, Tag2, Tag3) VALUES
(1, 'Wireless Bluetooth Earphones', 59900, 'Electronics', 'Audio', NULL),
(1, 'USB-C Fast Charger', 24900, 'Accessories', 'Charging', NULL),
(2, '32" 4K Monitor', 319900, 'Computer', 'Office', 'Display');

INSERT INTO Customer (ContactNumber, ShippingAddress) VALUES
('61234567', 'Shop 123, Silvercord, Tsim Sha Tsui'),
('61581859', 'Flat B, 15/F, Tower 1, Cyberport');

INSERT INTO `Order` (CustomerID, OrderDate, TotalAmount) VALUES
(1, '2025-03-01 10:00:00', 7498),
(2, '2025-03-02 14:30:00', 29999);

INSERT INTO OrderVendor (OrderID, VendorID) VALUES
(1, 1),
(2, 2);

INSERT INTO OrderItem (OrderVendorID, ProductID, Quantity, UnitPrice) VALUES
(1, 1, 1, 59900),  -- Earphones
(1, 2, 1, 24900),  -- Charger
(2, 3, 1, 319900); -- Monitor