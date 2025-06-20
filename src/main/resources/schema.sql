DROP TABLE IF EXISTS product;

CREATE TABLE product(
id INT AUTO_INCREMENT PRIMARY KEY,
productCode INT,
productBrand VARCHAR(20) NOT NULL,
productQuantity INT,
productPrice DECIMAL(7,2)
);
