----------------Test Results-----------------
---- Create TestResults first
--INSERT INTO testResults (testResultId, budget) VALUES   (1, 100.0),
--                                                        (2, 150.0);

----------------User-----------------
INSERT INTO users(userId, email, password) VALUES('00001', 'jialuo.chen@mail.utoronto.ca', '1111');
INSERT INTO users(userId, email, password) VALUES('00002', 'lea.kwon@mail.utoronto.ca', '1234');
INSERT INTO users(userId, email, password) VALUES('00003', 'user.testing@mail.utoronto.ca', '2222');

----------------Skintypes-----------------
INSERT INTO skintypes(skintypeId, description) VALUES (1, 'Oily');
INSERT INTO skintypes(skintypeId, description) VALUES (2, 'Dry');
INSERT INTO skintypes(skintypeId, description) VALUES (3, 'Combination');

----------------Concerns-----------------
INSERT INTO concerns(concernId, description) VALUES (1, 'Wrinkles');
INSERT INTO concerns(concernId, description) VALUES (2, 'Acne');
INSERT INTO concerns(concernId, description) VALUES (3, 'Pores');
INSERT INTO concerns(concernId, description) VALUES (4, 'Hyperpigmentation');
INSERT INTO concerns(concernId, description) VALUES (5, 'Irritation');
INSERT INTO concerns(concernId, description) VALUES (6, 'Dull skin');

----------------Categories-----------------
INSERT INTO categories(categoryId, categoryName) VALUES (1, 'Sunscreen');
INSERT INTO categories(categoryId, categoryName) VALUES (2, 'Cleanser');
INSERT INTO categories(categoryId, categoryName) VALUES (3, 'Toner');
INSERT INTO categories(categoryId, categoryName) VALUES (4, 'Moisturizer');
INSERT INTO categories(categoryId, categoryName) VALUES (5, 'Exfoliator');

----------------Products-----------------
INSERT INTO products (productId, name, brand, price, categoryId, imageURL)
VALUES
    (1, 'ultra fluid', 'La Roche-Posay', 30.50, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (2, 'Anthelios Melt-in Milk Sunscreen SPF 60', 'La Roche-Posay', 36.99, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (3, 'Unseen Sunscreen SPF 40', 'Supergoop!', 34.00, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (4, 'Urban Environment Vita-Clear Sunscreen SPF 42', 'Shiseido', 51.50, 1, 'https://www.sephora.com/productimages/sku/s2673382-main-zoom.jpg?imwidth=630'),
    (5, 'Hydro UV Defense Sunscreen - SPF50+', 'Laneige', 40.50, 1, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (6, 'Mini Cicapair Color Correcting Treatment SPF 30', 'Dr. Jart+', 34.00, 1, 'https://www.sephora.com/productimages/sku/s2580801-main-zoom.jpg?imwidth=630'),

    (7, 'Ultra Facial Moisturizer', 'Kiehl', 22.00, 4, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (8, 'Natural Moisturizing Factors + Beta Glucan', 'The Ordinary', 15.90, 4, 'https://www.sephora.com/productimages/sku/s2673317-main-zoom.jpg?imwidth=630'),
    (9, 'Hydro Boost Water Gel', 'Neutrogena', 19.99, 4, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (10, 'AM Facial Moisturizing Lotion SPF 30', 'CeraVe', 25.79, 4, 'https://digital.loblaws.ca/SDM/SDM_3606000537408/en/1/3606000537408_en_01_v1_400.jpeg'),
    (11, 'barrier+ Strengthening and Moisturizing Triple Lipid-Peptide Refillable Cream with B-L3', 'Skinfix', 73.00, 4, 'https://www.sephora.com/productimages/sku/s2742369-main-zoom.jpg?imwidth=3000'),
    (12, 'Omega Repair Deep Hydration Moisturizer with Ceramides and Hyaluronic Acid + Squalane', 'Biossance', 81.00, 4, 'https://www.sephora.com/productimages/sku/s2105856-main-zoom.jpg?imwidth=3000'),

    (13, 'Hydrating Facial Cleanser', 'CeraVe', 14.99, 2, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (14, 'Gentle Cleansing Foam', 'Sulwhasoo', 17.00, 2, 'https://www.sephora.com/productimages/sku/s2708469-main-zoom.jpg?imwidth=3000'),
    (15, 'Effaclar Purifying Foaming Gel', 'La Roche-Posay', 17.50, 2, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (16, 'Glucoside Foaming Cleanser', 'The Ordinary', 14.80, 2, 'https://www.sephora.com/productimages/sku/s2644151-main-zoom.jpg?imwidth=3000'),
    (17, 'Sulfate-Free Green Tea Amino Acid Cleansing Foam', 'innisfree', 19.00, 2, 'https://www.sephora.com/productimages/sku/s2740538-main-zoom.jpg?imwidth=3000'),
    (18, 'Anua - Heartleaf Succinic Moisture Cleansing Foam', 'YESSTYLE', 14.90, 2, 'https://d1flfk77wl2xk4.cloudfront.net/Assets/21/443/XXL_p0191844321.jpg'),

    (19, 'Pyunkang Yul Calming Deep Moisture Toner', 'Amazon', 9.99, 3, 'https://m.media-amazon.com/images/I/413fhlawkhL._AC_SL1000_.jpg'),
    (20, 'glazing milk', 'rhodeskin', 52.00, 3, 'https://www.rhodeskin.com/cdn/shop/files/glazing-milk-11_2480x.jpg?v=1727315221'),
    (21, 'Mini Cream Skin Refillable Toner & Moisturizer with Ceramides and Peptides', 'Laneige', 22.00, 3, 'https://www.sephora.com/productimages/sku/s2671873-main-zoom.jpg?imwidth=3000'),
    (22, 'Glycolic Acid 7% Toning Solution', 'The Ordinary', 10.80, 3, 'https://www.sephora.com/productimages/sku/s2768083-main-zoom.jpg?imwidth=315'),
    (23, 'Anua - Heartleaf 77% Soothing Toner Mini', 'YESSTYLE', 10.28, 3, 'https://d1flfk77wl2xk4.cloudfront.net/Assets/88/722/XXL_p0130772288.jpg'),
    (24, 'Cetaphil Healthy Radiance Hydrating Toner', 'Cetaphil', 18.78, 3, 'https://m.media-amazon.com/images/I/616I5BUxXjL._AC_SX522_.jpg'),

    (25, 'Salicylic Acid 2% Exfoliating Solution', 'Paula''s Choice', 25.00, 5, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (26, 'Daily Microfoliant Exfoliator', 'Dermalogica', 59.00, 5, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1');

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

----------------Product Alternatives-----------------
//INSERT INTO product_alternatives (productID, altProdId) VALUES (1,2), (2,1);
//INSERT INTO product_alternatives (productID, altProdId) VALUES (3,4), (4,3);
//INSERT INTO product_alternatives (productID, altProdId) VALUES (5,6), (6,5);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (7,8), (8,7);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (9,10), (10,9);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (11,12), (12,11);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (13,14), (14,13);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (15,16), (16,15);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (17,18), (18,17);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (19,20), (20,19);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (21,22), (22,21);
//INSERT INTO product_alternatives (productId, altProdId) VALUES (23,24), (24,23);
//INSERT INTO product_alternatives (productId, altProdId)VALUES (25,26), (26,25);

----------------Product Concerns-----------------
INSERT INTO ProductConcerns (productId, concernId) VALUES (7,1),(11,1), (12,1), (19,1), (20,1);
INSERT INTO ProductConcerns (productId, concernId) VALUES (15,2),(16,2), (17,2), (22,2), (25,2);
INSERT INTO ProductConcerns (productId, concernId) VALUES (15,3),(17,3), (22,3), (26,3);
INSERT INTO ProductConcerns (productId, concernId) VALUES (4, 4), (5, 4), (6, 4), (22, 4), (25, 4);
INSERT INTO ProductConcerns (productId, concernId) VALUES(11, 5), (12, 5), (19, 5), (23, 5), (6, 5);
INSERT INTO ProductConcerns (productId, concernId) VALUES (22, 6), (24, 6), (26, 6), (4, 6);

----------------Product Ingredients-----------------

 INSERT INTO ProductIngredients (productId, ingredientId) VALUES (1, 6), (1, 7), (1, 5), -- Ultra Fluid
                                                                  (2, 6), (2, 7), (2, 5), -- Anthelios Melt-in Milk Sunscreen SPF 60
                                                                  (3, 6), (3, 8), (3, 5), -- Unseen Sunscreen SPF 40
                                                                  (4, 6), (4, 4), (4, 5), -- Urban Environment Vita-Clear Sunscreen SPF 42
                                                                  (5, 6), (5, 2), (5, 5), -- Hydro UV Defense Sunscreen - SPF50+
                                                                  (6, 6), (6, 3), (6, 7); -- Mini Cicapair Color Correcting Treatment SPF 30

INSERT INTO ProductIngredients (productId, ingredientId) VALUES (7, 1), (7, 5), (7, 7), -- Ultra Facial Moisturizer
                                                                 (8, 1), (8, 5), (8, 2), -- Natural Moisturizing Factors + Beta Glucan
                                                                 (9, 1), (9, 5), (9, 7), -- Hydro Boost Water Gel
                                                                 (10, 6), (10, 5), (10, 7), -- AM Facial Moisturizing Lotion SPF 30
                                                                 (11, 1), (11, 3), (11, 5), -- Barrier+ Strengthening and Moisturizing Triple Lipid-Peptide Refillable Cream
                                                                 (12, 1), (12, 4), (12, 5); -- Omega Repair Deep Hydration Moisturizer

INSERT INTO ProductIngredients (productId, ingredientId) VALUES (13, 1), (13, 5), (13, 7), -- Hydrating Facial Cleanser
                                                                 (14, 1), (14, 2), (14, 7), -- Gentle Cleansing Foam
                                                                 (15, 1), (15, 4), (15, 7), -- Effaclar Purifying Foaming Gel
                                                                 (16, 1), (16, 8), (16, 7), -- Glucoside Foaming Cleanser
                                                                 (17, 1), (17, 2), (17, 7), -- Sulfate-Free Green Tea Amino Acid Cleansing Foam
                                                                 (18, 1), (18, 8), (18, 7); -- Anua - Heartleaf Succinic Moisture Cleansing Foam

-- Toners
INSERT INTO ProductIngredients (productId, ingredientId) VALUES (19, 1), (19, 2), (19, 5), -- Pyunkang Yul Calming Deep Moisture Toner
                                                                 (20, 1), (20, 8), (20, 5), -- Glazing Milk
                                                                 (21, 1), (21, 5), (21, 7), -- Mini Cream Skin Refillable Toner & Moisturizer
                                                                 (22, 1), (22, 4), (22, 7), -- Glycolic Acid 7% Toning Solution
                                                                 (23, 1), (23, 2), (23, 5), -- Anua - Heartleaf 77% Soothing Toner Mini
                                                                 (24, 1), (24, 8), (24, 5); -- Cetaphil Healthy Radiance Hydrating Toner

INSERT INTO ProductIngredients (productId, ingredientId) VALUES (25, 1), (25, 3), (25, 5), -- Salicylic Acid 2% Exfoliating Solution
                                                                 (26, 1), (26, 4), (26, 5); -- Daily Microfoliant Exfoliator


----------------Product Type-----------------
INSERT INTO userSkintype (productId, skintypeId) VALUES (1,1), (3,1), (4,1), (9,1), (10,1);
INSERT INTO userSkintype (productId, skintypeId) VALUES (2,2), (7,2), (8,2), (13,2), (14,2), (19,2), (20,2);
INSERT INTO userSkintype (productId, skintypeId) VALUES (5,3), (6,3), (11,3), (12,3), (17,3), (18,3), (23,3), (24,3), (25,3), (26,3);
INSERT INTO userSkintype (productId, skintypeId) VALUES (15,1), (16,1), (21,1), (22,1);

----------------Reviews-----------------
INSERT INTO reviews (userId, productId, reviewBody, score, date)
VALUES
    ('00001', 1, 'This product was perfect for my skin', 5, '2025-Jan-05'),
    ('00002', 1, 'This product did not work for me', 2, '2025-Jan-03'),
    ('00001', 2, 'This product was okay', 3, '2024-Feb-19'),
    ('00002', 2, 'no thanks', 1, '2023-April-01');

