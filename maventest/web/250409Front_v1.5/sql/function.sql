-- Vendor Administration
-- 1) display a listing of all vendors
SELECT * FROM Vendor;
-- 2) onboard new vendors onto the marketplace
INSERT INTO Vendor (BusinessName, FeedbackScore, geography) VALUES (?, ?, ?);

-- Product Catalog Management
-- 1) browse all products offered by a specific vendor
SELECT * FROM Product WHERE Vendorid = (SELECT Vendorid FROM Vendor WHERE Vendorname = ?);
-- 2) introduce new products to a vendor's catalog
INSERT INTO Product (VendorID, ProductName, ListPrice, Tag1, Tag2, Tag3) VALUES (?, ?, ?, ?, ?, ?);

-- Product Discovery
SELECT *
FROM Product
WHERE ProductName LIKE CONCAT('%', ?, '%')
   OR Tag1 LIKE CONCAT('%', ?, '%')
   OR Tag2 LIKE CONCAT('%', ?, '%')
   OR Tag3 LIKE CONCAT('%', ?, '%');

-- Product Purchase
INSERT INTO `Order` (CustomerID, TotalAmount) VALUES (?, ?);
INSERT INTO OrderVendor (OrderID, VendorID) VALUES (LAST_INSERT_ID(), ?)
INSERT INTO OrderItem (OrderVendorID, ProductID, Quantity, UnitPrice) VALUES (?, ?, ?, ?);

-- Order Modification
-- update
UPDATE `Order` SET CustomerID = ?, TotalAmount = ? WHERE OrderID = ?;
UPDATE OrderItem SET OrderVendorID = ?, ProductID = ?, Quantity = ?, UnitPrice = ? WHERE OrderItemID = ?;
-- delete
DELETE FROM `Order` WHERE OrderID = ?;
DELETE FROM OrderVendor WHERE OrderID = ?;
DELETE FROM OrderItem WHERE OrderItemID = ?;