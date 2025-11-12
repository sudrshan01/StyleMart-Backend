INSERT INTO products (id, name, category, subcategory, sku, short_description, description, fabric, price, discount, stock, main_image, tags, occasion, gender) VALUES
(1, 'Classic Crew Neck T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-001', 'Soft cotton crew neck tee', 'Premium cotton crew neck t-shirt, breathable and comfortable for daily wear.', 'Cotton', 799.0, 10.0, 50, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/1758818560959_t1.jpeg', 'casual,summer,basic', 'Casual', 'Men'),
(2, 'Slim Fit Polo T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-002', 'Slim fit polo with buttoned collar', 'Stylish polo with slim fit design, perfect for casual outings and semi-formal looks.', 'Cotton Blend', 999.0, 15.0, 40, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t4.jpeg', 'polo,casual,slimfit', 'Casual', 'Men'),
(3, 'Graphic Print T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-003', 'Trendy graphic printed t-shirt', 'Bold graphic print design for men, ideal for streetwear and casual wear.', 'Polyester', 699.0, 5.0, 60, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t7.jpeg', 'streetwear,graphic,casual', 'Casual', 'Men'),
(4, 'Henley Neck T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-004', 'Henley neck with button placket', 'Soft cotton Henley t-shirt with stylish button placket, great for casual outings.', 'Cotton', 899.0, 20.0, 30, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t10.jpeg', 'henley,casual,stylish', 'Casual', 'Men'),
(5, 'Full Sleeve Solid T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-005', 'Solid color full sleeve t-shirt', 'Classic full sleeve t-shirt in solid color, suitable for layering or standalone wear.', 'Cotton Lycra', 849.0, 0.0, 35, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t13.jpeg', 'solid,fullsleeve,casual', 'Casual', 'Men'),
(6, 'Sports Dry-Fit T-Shirt', 'Clothing', 'T-Shirts', 'MEN-TSHIRT-006', 'Dry-fit sports t-shirt for men', 'Lightweight sports t-shirt with moisture wicking fabric for gym and outdoor activities.', 'Polyester Dry-Fit', 1099.0, 10.0, 45, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/t16.jpeg', 'sports,activewear,gym', 'Sports', 'Men'),
(7, 'Round Neck Printed T-Shirt', 'Clothing', 'T-Shirts', 'WOMEN-TSHIRT-001', 'Printed casual t-shirt for women', 'Trendy round neck t-shirt with floral print, perfect for casual wear.', 'Cotton', 749.0, 5.0, 40, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w1.jpeg', 'printed,casual,women', 'Casual', 'Women'),
(8, 'Crop Top T-Shirt', 'Clothing', 'T-Shirts', 'WOMEN-TSHIRT-002', 'Stylish crop top style t-shirt', 'Fashionable crop top style t-shirt, designed for trendy casual wear.', 'Cotton Lycra', 899.0, 10.0, 25, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w4.jpeg', 'crop,cute,stylish', 'Casual', 'Women'),
(9, 'Oversized Boyfriend T-Shirt', 'Clothing', 'T-Shirts', 'WOMEN-TSHIRT-003', 'Oversized comfy boyfriend tee', 'Oversized fit t-shirt for women, designed for ultimate comfort and trendy looks.', 'Cotton Blend', 999.0, 12.0, 20, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/w7.jpeg', 'oversized,casual,streetwear', 'Casual', 'Women'),
(10, 'Classic Leather Handbag', 'Accessories', 'Bags', 'UNISEX-BAG-001', 'Premium unisex leather handbag', 'Durable unisex leather handbag with spacious compartments, suitable for office or casual use.', 'Leather', 2499.0, 18.0, 15, 'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/h1.jpeg', 'handbag,leather,unisex', 'Formal', 'Unisex');

INSERT INTO product_colors (product_id, color) VALUES
(1,'Black'),(1,'White'),(1,'Navy Blue'),
(2,'Grey'),(2,'Maroon'),(2,'Blue'),
(3,'Black'),(3,'White'),
(4,'Olive'),(4,'Brown'),
(5,'Black'),(5,'White'),(5,'Grey'),
(6,'Blue'),(6,'Red'),(6,'Neon Green'),
(7,'Pink'),(7,'White'),
(8,'Black'),(8,'White'),(8,'Lavender'),
(9,'Beige'),(9,'Light Blue'),
(10,'Black'),(10,'Brown');


 INSERT INTO product_sizes (product_id, size) VALUES
 (1,'S'),(1,'M'),(1,'L'),(1,'XL'),
 (2,'M'),(2,'L'),(2,'XL'),
 (3,'S'),(3,'M'),(3,'L'),
 (4,'M'),(4,'L'),(4,'XL'),
 (5,'S'),(5,'M'),(5,'L'),(5,'XL'),
 (6,'M'),(6,'L'),(6,'XL'),
 (7,'S'),(7,'M'),(7,'L'),
 (8,'S'),(8,'M'),
 (9,'M'),(9,'L'),(9,'XL'),
 (10,'One Size');


INSERT INTO product_gallery (product_id, image_path) VALUES
(1,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t2.jpeg'),
(1,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/1758818562135_t3.jpeg'),
(2,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t5.jpeg'),
(2,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t6.jpeg'),
(3,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t8.jpeg'),
(3,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t9.jpeg'),
(4,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t11.jpeg'),
(4,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t12.jpeg'),
(5,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t14.jpeg'),
(5,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t15.jpeg'),
(6,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t17.jpeg'),
(6,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/t18.jpeg'),
(7,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w2.jpeg'),
(7,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w3.jpeg'),
(8,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w5.jpeg'),
(8,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w6.jpeg'),
(9,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w8.jpeg'),
(9,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/w9.jpeg'),
(10,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/h2.jpeg'),
(10,'https://ecomimages-dev.s3.ap-south-1.amazonaws.com/products/gallery/h3.jpeg');
