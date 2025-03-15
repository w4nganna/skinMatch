----------------User-----------------
INSERT INTO users(userId, email, password) VALUES('00001', 'jialuo.chen@mail.utoronto.ca', '1111');
INSERT INTO users(userId, email, password) VALUES('00002', 'lea.kwon@mail.utoronto.ca', '1234');

----------------Skintypes-----------------
INSERT INTO skintypes(skintypeId, description) VALUES (1, 'oily');
INSERT INTO skintypes(skintypeId, description) VALUES (2, 'dry');
INSERT INTO skintypes(skintypeId, description) VALUES (3, 'combination');

----------------Concerns-----------------
INSERT INTO concerns(concernId, description) VALUES (1, 'wrinkles');
INSERT INTO concerns(concernId, description) VALUES (2, 'acne');
INSERT INTO concerns(concernId, description) VALUES (3, 'pores');
INSERT INTO concerns(concernId, description) VALUES (4, 'hyperpigmentation');
INSERT INTO concerns(concernId, description) VALUES (5, 'irritation');
INSERT INTO concerns(concernId, description) VALUES (6, 'dull skin');

----------------Products-----------------
INSERT INTO products (productId, name, brand, price, category, type, imageURL)
VALUES
    (1, 'ultra fluid', 'La Roche-Posay', 30.50, 'Sunscreen', 'normal', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (2, 'Anthelios Melt-in Milk Sunscreen SPF 60', 'La Roche-Posay', 36.99, 'Sunscreen', 'dry', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (3, 'Unseen Sunscreen SPF 40', 'Supergoop!', 34.00, 'Sunscreen', 'oily', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (4, 'Urban Environment Vita-Clear Sunscreen SPF 42', 'Shiseido', 51.50, 'Sunscreen', 'oily', 'https://www.sephora.com/productimages/sku/s2673382-main-zoom.jpg?imwidth=630'),
    (5, 'Hydro UV Defense Sunscreen - SPF50+', 'Laneige', 40.50, 'Sunscreen', 'combo', 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (6, 'Mini Cicapair Color Correcting Treatment SPF 30', 'Dr. Jart+ ', 34.00, 'Sunscreen', 'combo', 'https://www.sephora.com/productimages/sku/s2580801-main-zoom.jpg?imwidth=630'),


    (7, 'Ultra Facial Moisturizer', 'Kiehl', 22.00, 'Moisturizer', 'dry', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (8, 'Natural Moisturizing Factors + Beta Glucan ', 'The Ordinary', 15.90, 'Moisturizer', 'dry', 'https://www.sephora.com/productimages/sku/s2673317-main-zoom.jpg?imwidth=630'),
    (9, 'Hydro Boost Water Gel', 'Neutrogena', 19.99, 'Moisturizer', 'oily', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (10, 'AM Facial Moisturizing Lotion SPF 30', 'CeraVe', 25.79, 'Moisturizer', 'oily', 'https://digital.loblaws.ca/SDM/SDM_3606000537408/en/1/3606000537408_en_01_v1_400.jpeg'),
    (11, 'barrier+ Strengthening and Moisturizing Triple Lipid-Peptide Refillable Cream with B-L3', 'Skinfix', 73.00, 'Moisturizer', 'combo', 'https://www.sephora.com/productimages/sku/s2742369-main-zoom.jpg?imwidth=3000'),
    (12, 'Omega Repair Deep Hydration Moisturizer with Ceramides and Hyaluronic Acid + Squalane', 'Biossance ', 81.00, 'Moisturizer', 'combo', 'https://www.sephora.com/productimages/sku/s2105856-main-zoom.jpg?imwidth=3000'),




    (13, 'Hydrating Facial Cleanser', 'CeraVe', 14.99, 'Cleanser', 'dry', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (14, 'Gentle Cleansing Foam', 'Sulwhasoo', 17.00, 'Cleanser', 'dry', 'https://www.sephora.com/productimages/sku/s2708469-main-zoom.jpg?imwidth=3000'),
    (15, 'Effaclar Purifying Foaming Gel', 'La Roche-Posay', 17.50, 'Cleanser', 'oily', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (16, 'Glucoside Foaming Cleanser', 'The Ordinary', 14.80, 'Cleanser', 'oily', 'https://www.sephora.com/productimages/sku/s2644151-main-zoom.jpg?imwidth=3000'),
    (17, 'Sulfate-Free Green Tea Amino Acid Cleansing Foam', 'innisfree', 19.00, 'Cleanser', 'combo', 'https://www.sephora.com/productimages/sku/s2740538-main-zoom.jpg?imwidth=3000'),
    (18, 'Anua - Heartleaf Succinic Moisture Cleansing Foam', 'YesStyle', 14.90, 'Cleanser', 'combo', 'https://d1flfk77wl2xk4.cloudfront.net/Assets/21/443/XXL_p0191844321.jpg'),

    (19, 'Pyunkang Yul Calming Deep Moisture Toner', 'amazon', 9.99, 'Toner', 'dry', 'https://m.media-amazon.com/images/I/413fhlawkhL._AC_SL1000_.jpg'),
    (20, 'glazing milk', 'rhodeskin', 52.00, 'Toner', 'dry', 'https://www.rhodeskin.com/cdn/shop/files/glazing-milk-11_2480x.jpg?v=1727315221'),
    (21, 'Mini Cream Skin Refillable Toner & Moisturizer with Ceramides and Peptides', 'LANEIGE', 22.00, 'Toner', 'oily', 'https://www.sephora.com/productimages/sku/s2671873-main-zoom.jpg?imwidth=3000'),
    (22, 'Glycolic Acid 7% Toning Solution', 'The Ordinary', 10.80, 'Toner', 'oily', 'https://www.sephora.com/productimages/sku/s2768083-main-zoom.jpg?imwidth=315'),
    (23, 'Anua - Heartleaf 77% Soothing Toner Mini', 'YESSTYLE', 10.28, 'Toner', 'combo', 'https://d1flfk77wl2xk4.cloudfront.net/Assets/88/722/XXL_p0130772288.jpg'),
    (24, 'Cetaphil Healthy Radiance Hydrating Toner', 'Cetaphil', 18.78, 'Toner', 'combo', 'https://m.media-amazon.com/images/I/616I5BUxXjL._AC_SX522_.jpg'),


    (25, 'Salicylic Acid 2% Exfoliating Solution', 'Paula’s Choice', 25.00, 'Exfoliator', 'combo', 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (26, 'Daily Microfoliant Exfoliator', 'Dermalogica', 59.00, 'Exfoliator', 'combo', 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1');

----------------Ingredients-----------------
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (1, 'Water');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (2, 'Aloe Vera Juice');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (3, 'Retinol');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (4, 'Vitamin C');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (5, 'Hyaluronic Acid');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (6, 'SPF50+');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (7, 'Glycerin');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (8, 'Oil Extracts');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (9, 'Parfum');

----------------Reviews-----------------
INSERT INTO reviews (userId, productId, reviewBody, score, date)
VALUES
    ('00001', 1, 'This product was perfect for my skin', 5, '2025-Jan-05'),
    ('00002', 1, 'This product did not work for me ', 2, '2025-Jan-03'),
    ('00001', 2, 'This product was okay', 3, '2024-Feb-19'),
    ('00002', 2, 'no thanks', 1, '2023-April-01');
