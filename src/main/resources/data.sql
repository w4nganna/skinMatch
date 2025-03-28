----------------Test Results-----------------
---- Create TestResults first
--INSERT INTO testResults (testResultId, budget) VALUES   (1, 100.0),
--                                                        (2, 150.0);

----------------User-----------------
INSERT INTO users(userId, email, password) VALUES('00001', 'jialuo.chen@mail.utoronto.ca', '1111');
INSERT INTO users(userId, email, password) VALUES('00002', 'lea.kwon@mail.utoronto.ca', '1234');
INSERT INTO users(userId, email, password) VALUES('00003', 'karuna.adhikari@mail.utoronto.ca', '3333');
INSERT INTO users(userId, email, password) VALUES('00004', 'pragya.prakash@mail.utoronto.ca', '4444');
INSERT INTO users(userId, email, password) VALUES('00005', 'anoushka.paul@mail.utoronto.ca', '5555');
INSERT INTO users(userId, email, password) VALUES('00006', 'anna.wang@mail.utoronto.ca', '6666');
INSERT INTO users(userId, email, password) VALUES('00007', 'naeer.khan@mail.utoronto.ca', '7777');

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
INSERT INTO ingredients (ingredientId, ingredientName, body) VALUES (1, 'Water', 'As the label says H20'),
                                                                        (2, 'Aloe Vera', 'Used to topically treat sunburn'),
                                                                        (3, 'Retinol','increases cell turnover rate' ),
                                                                        (4, 'Vitamin C', 'boosts collagen production'),
                                                                        (5, 'Hyaluronic Acid', 'Hydrates the skin'),
                                                                        (6, 'SPF50+', 'protects from UV rays'),
                                                                        (7, 'Salicyclic acid', 'clears sebum from skin'),
                                                                        (8, 'Niacinamide','helps to retain moisture, by boosting the production of ceramides ' ),
                                                                        (9, 'Parfum', 'scented');

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
INSERT INTO reviews (userId, productId, reviewBody, score, date) VALUES
    ('00001', 1, 'Didn''t work for my skin.', 3, '2025-01-01'),
    ('00002', 1, 'Amazing product, will buy again!', 4, '2025-01-02'),
    ('00003', 1, 'Too harsh for me.', 4, '2025-01-03'),
    ('00004', 1, 'Great value for the price.', 3, '2025-01-04'),
    ('00005', 1, 'Too harsh for me.', 1, '2025-01-05'),
    ('00006', 1, 'A bit greasy but works.', 2, '2025-01-06'),
    ('00007', 1, 'A bit greasy but works.', 3, '2025-01-07'),
    ('00001', 2, 'Smells nice and feels fresh.', 3, '2025-01-01'),
    ('00002', 2, 'Hydrating and smooth.', 4, '2025-01-02'),
    ('00003', 2, 'Perfect under makeup!', 2, '2025-01-03'),
    ('00004', 2, 'Great value for the price.', 3, '2025-01-04'),
    ('00005', 2, 'Perfect under makeup!', 1, '2025-01-05'),
    ('00006', 2, 'Hydrating and smooth.', 4, '2025-01-06'),
    ('00007', 2, 'Very lightweight and effective.', 1, '2025-01-07'),
    ('00001', 3, 'Perfect under makeup!', 2, '2025-01-01'),
    ('00002', 3, 'Didn''t work for my skin.', 3, '2025-01-02'),
    ('00003', 3, 'Amazing product, will buy again!', 1, '2025-01-03'),
    ('00004', 3, 'A bit greasy but works.', 1, '2025-01-04'),
    ('00005', 3, 'Perfect under makeup!', 1, '2025-01-05'),
    ('00006', 3, 'Great value for the price.', 1, '2025-01-06'),
    ('00007', 3, 'Great value for the price.', 5, '2025-01-07'),
    ('00001', 4, 'Very lightweight and effective.', 4, '2025-01-01'),
    ('00002', 4, 'Didn''t work for my skin.', 5, '2025-01-02'),
    ('00003', 4, 'Smells nice and feels fresh.', 5, '2025-01-03'),
    ('00004', 4, 'Great value for the price.', 3, '2025-01-04'),
    ('00005', 4, 'Smells nice and feels fresh.', 1, '2025-01-05'),
    ('00006', 4, 'Left a white cast.', 3, '2025-01-06'),
    ('00007', 4, 'Hydrating and smooth.', 1, '2025-01-07'),
    ('00001', 5, 'Very lightweight and effective.', 1, '2025-01-01'),
    ('00002', 5, 'Didn''t work for my skin.', 5, '2025-01-02'),
    ('00003', 5, 'Hydrating and smooth.', 3, '2025-01-03'),
    ('00004', 5, 'Didn''t work for my skin.', 2, '2025-01-04'),
    ('00005', 5, 'Amazing product, will buy again!', 2, '2025-01-05'),
    ('00006', 5, 'A bit greasy but works.', 4, '2025-01-06'),
    ('00007', 5, 'Too harsh for me.', 4, '2025-01-07'),
    ('00001', 6, 'Hydrating and smooth.', 3, '2025-01-01'),
    ('00002', 6, 'Left a white cast.', 4, '2025-01-02'),
    ('00003', 6, 'Great value for the price.', 2, '2025-01-03'),
    ('00004', 6, 'Very lightweight and effective.', 5, '2025-01-04'),
    ('00005', 6, 'Hydrating and smooth.', 1, '2025-01-05'),
    ('00006', 6, 'Smells nice and feels fresh.', 1, '2025-01-06'),
    ('00007', 6, 'Great value for the price.', 5, '2025-01-07'),
    ('00001', 7, 'Hydrating and smooth.', 1, '2025-01-01'),
    ('00002', 7, 'Too harsh for me.', 1, '2025-01-02'),
    ('00003', 7, 'Too harsh for me.', 5, '2025-01-03'),
    ('00004', 7, 'Great value for the price.', 5, '2025-01-04'),
    ('00005', 7, 'A bit greasy but works.', 5, '2025-01-05'),
    ('00006', 7, 'Perfect under makeup!', 5, '2025-01-06'),
    ('00007', 7, 'Very lightweight and effective.', 4, '2025-01-07'),
    ('00001', 8, 'Great value for the price.', 2, '2025-01-01'),
    ('00002', 8, 'Hydrating and smooth.', 4, '2025-01-02'),
    ('00003', 8, 'A bit greasy but works.', 4, '2025-01-03'),
    ('00004', 8, 'Didn''t work for my skin.', 2, '2025-01-04'),
    ('00005', 8, 'Very lightweight and effective.', 3, '2025-01-05'),
    ('00006', 8, 'Too harsh for me.', 5, '2025-01-06'),
    ('00007', 8, 'Amazing product, will buy again!', 2, '2025-01-07'),
    ('00001', 9, 'Perfect under makeup!', 5, '2025-01-01'),
    ('00002', 9, 'Perfect under makeup!', 4, '2025-01-02'),
    ('00003', 9, 'Too harsh for me.', 3, '2025-01-03'),
    ('00004', 9, 'Amazing product, will buy again!', 5, '2025-01-04'),
    ('00005', 9, 'Amazing product, will buy again!', 4, '2025-01-05'),
    ('00006', 9, 'A bit greasy but works.', 5, '2025-01-06'),
    ('00007', 9, 'Too harsh for me.', 1, '2025-01-07'),
    ('00001', 10, 'Didn''t work for my skin.', 4, '2025-01-01'),
    ('00002', 10, 'Smells nice and feels fresh.', 2, '2025-01-02'),
    ('00003', 10, 'Great value for the price.', 5, '2025-01-03'),
    ('00004', 10, 'Left a white cast.', 2, '2025-01-04'),
    ('00005', 10, 'Great value for the price.', 1, '2025-01-05'),
    ('00006', 10, 'Very lightweight and effective.', 4, '2025-01-06'),
    ('00007', 10, 'Smells nice and feels fresh.', 5, '2025-01-07'),
    ('00001', 11, 'Smells nice and feels fresh.', 4, '2025-01-01'),
    ('00002', 11, 'A bit greasy but works.', 4, '2025-01-02'),
    ('00003', 11, 'A bit greasy but works.', 1, '2025-01-03'),
    ('00004', 11, 'Very lightweight and effective.', 4, '2025-01-04'),
    ('00005', 11, 'Great value for the price.', 3, '2025-01-05'),
    ('00006', 11, 'Smells nice and feels fresh.', 5, '2025-01-06'),
    ('00007', 11, 'Very lightweight and effective.', 3, '2025-01-07'),
    ('00001', 12, 'Smells nice and feels fresh.', 5, '2025-01-01'),
    ('00002', 12, 'Too harsh for me.', 5, '2025-01-02'),
    ('00003', 12, 'Perfect under makeup!', 1, '2025-01-03'),
    ('00004', 12, 'Very lightweight and effective.', 4, '2025-01-04'),
    ('00005', 12, 'Too harsh for me.', 1, '2025-01-05'),
    ('00006', 12, 'Smells nice and feels fresh.', 1, '2025-01-06'),
    ('00007', 12, 'Left a white cast.', 3, '2025-01-07'),
    ('00001', 13, 'Very lightweight and effective.', 3, '2025-01-01'),
    ('00002', 13, 'Amazing product, will buy again!', 1, '2025-01-02'),
    ('00003', 13, 'Didn''t work for my skin.', 5, '2025-01-03'),
    ('00004', 13, 'Hydrating and smooth.', 2, '2025-01-04'),
    ('00005', 13, 'Amazing product, will buy again!', 4, '2025-01-05'),
    ('00006', 13, 'Hydrating and smooth.', 5, '2025-01-06'),
    ('00007', 13, 'Too harsh for me.', 2, '2025-01-07'),
    ('00001', 14, 'Amazing product, will buy again!', 2, '2025-01-01'),
    ('00002', 14, 'Too harsh for me.', 2, '2025-01-02'),
    ('00003', 14, 'A bit greasy but works.', 3, '2025-01-03'),
    ('00004', 14, 'Perfect under makeup!', 5, '2025-01-04'),
    ('00005', 14, 'A bit greasy but works.', 4, '2025-01-05'),
    ('00006', 14, 'Didn''t work for my skin.', 5, '2025-01-06'),
    ('00007', 14, 'Great value for the price.', 1, '2025-01-07'),
    ('00001', 15, 'Perfect under makeup!', 4, '2025-01-01'),
    ('00002', 15, 'Left a white cast.', 5, '2025-01-02'),
    ('00003', 15, 'Perfect under makeup!', 2, '2025-01-03'),
    ('00004', 15, 'Perfect under makeup!', 1, '2025-01-04'),
    ('00005', 15, 'Great value for the price.', 5, '2025-01-05'),
    ('00006', 15, 'Smells nice and feels fresh.', 5, '2025-01-06'),
    ('00007', 15, 'Great value for the price.', 3, '2025-01-07'),
    ('00001', 16, 'Left a white cast.', 4, '2025-01-01'),
    ('00002', 16, 'Left a white cast.', 1, '2025-01-02'),
    ('00003', 16, 'Smells nice and feels fresh.', 1, '2025-01-03'),
    ('00004', 16, 'Very lightweight and effective.', 5, '2025-01-04'),
    ('00005', 16, 'Smells nice and feels fresh.', 3, '2025-01-05'),
    ('00006', 16, 'A bit greasy but works.', 3, '2025-01-06'),
    ('00007', 16, 'Great value for the price.', 2, '2025-01-07'),
    ('00001', 17, 'Hydrating and smooth.', 2, '2025-01-01'),
    ('00002', 17, 'Didn''t work for my skin.', 1, '2025-01-02'),
    ('00003', 17, 'Too harsh for me.', 1, '2025-01-03'),
    ('00004', 17, 'Very lightweight and effective.', 1, '2025-01-04'),
    ('00005', 17, 'Amazing product, will buy again!', 4, '2025-01-05'),
    ('00006', 17, 'Too harsh for me.', 4, '2025-01-06'),
    ('00007', 17, 'Perfect under makeup!', 2, '2025-01-07'),
    ('00001', 18, 'Perfect under makeup!', 2, '2025-01-01'),
    ('00002', 18, 'Left a white cast.', 1, '2025-01-02'),
    ('00003', 18, 'Smells nice and feels fresh.', 1, '2025-01-03'),
    ('00004', 18, 'Left a white cast.', 3, '2025-01-04'),
    ('00005', 18, 'Didn''t work for my skin.', 1, '2025-01-05'),
    ('00006', 18, 'Very lightweight and effective.', 3, '2025-01-06'),
    ('00007', 18, 'A bit greasy but works.', 4, '2025-01-07'),
    ('00001', 19, 'Left a white cast.', 5, '2025-01-01'),
    ('00002', 19, 'Hydrating and smooth.', 3, '2025-01-02'),
    ('00003', 19, 'Amazing product, will buy again!', 2, '2025-01-03'),
    ('00004', 19, 'Smells nice and feels fresh.', 2, '2025-01-04'),
    ('00005', 19, 'Too harsh for me.', 4, '2025-01-05'),
    ('00006', 19, 'Perfect under makeup!', 4, '2025-01-06'),
    ('00007', 19, 'Hydrating and smooth.', 1, '2025-01-07'),
    ('00001', 20, 'Perfect under makeup!', 3, '2025-01-01'),
    ('00002', 20, 'Didn''t work for my skin.', 4, '2025-01-02'),
    ('00003', 20, 'Hydrating and smooth.', 4, '2025-01-03'),
    ('00004', 20, 'Perfect under makeup!', 5, '2025-01-04'),
    ('00005', 20, 'Didn''t work for my skin.', 3, '2025-01-05'),
    ('00006', 20, 'Smells nice and feels fresh.', 2, '2025-01-06'),
    ('00007', 20, 'Great value for the price.', 4, '2025-01-07'),
    ('00001', 21, 'Hydrating and smooth.', 3, '2025-01-01'),
    ('00002', 21, 'Very lightweight and effective.', 1, '2025-01-02'),
    ('00003', 21, 'Too harsh for me.', 2, '2025-01-03'),
    ('00004', 21, 'Perfect under makeup!', 4, '2025-01-04'),
    ('00005', 21, 'Hydrating and smooth.', 1, '2025-01-05'),
    ('00006', 21, 'Didn''t work for my skin.', 5, '2025-01-06'),
    ('00007', 21, 'Smells nice and feels fresh.', 1, '2025-01-07'),
    ('00001', 22, 'Perfect under makeup!', 5, '2025-01-01'),
    ('00002', 22, 'Very lightweight and effective.', 3, '2025-01-02'),
    ('00003', 22, 'Left a white cast.', 4, '2025-01-03'),
    ('00004', 22, 'Amazing product, will buy again!', 3, '2025-01-04'),
    ('00005', 22, 'A bit greasy but works.', 5, '2025-01-05'),
    ('00006', 22, 'Too harsh for me.', 3, '2025-01-06'),
    ('00007', 22, 'Hydrating and smooth.', 4, '2025-01-07'),
    ('00001', 23, 'Didn''t work for my skin.', 4, '2025-01-01'),
    ('00002', 23, 'Hydrating and smooth.', 5, '2025-01-02'),
    ('00003', 23, 'Didn''t work for my skin.', 5, '2025-01-03'),
    ('00004', 23, 'Great value for the price.', 5, '2025-01-04'),
    ('00005', 23, 'Perfect under makeup!', 5, '2025-01-05'),
    ('00006', 23, 'Great value for the price.', 3, '2025-01-06'),
    ('00007', 23, 'Smells nice and feels fresh.', 5, '2025-01-07'),
    ('00001', 24, 'Smells nice and feels fresh.', 5, '2025-01-01'),
    ('00002', 24, 'Too harsh for me.', 3, '2025-01-02'),
    ('00003', 24, 'Hydrating and smooth.', 2, '2025-01-03'),
    ('00004', 24, 'Left a white cast.', 4, '2025-01-04'),
    ('00005', 24, 'Hydrating and smooth.', 4, '2025-01-05'),
    ('00006', 24, 'Hydrating and smooth.', 3, '2025-01-06'),
    ('00007', 24, 'A bit greasy but works.', 2, '2025-01-07'),
    ('00001', 25, 'Hydrating and smooth.', 4, '2025-01-01'),
    ('00002', 25, 'Very lightweight and effective.', 5, '2025-01-02'),
    ('00003', 25, 'Hydrating and smooth.', 2, '2025-01-03'),
    ('00004', 25, 'Perfect under makeup!', 3, '2025-01-04'),
    ('00005', 25, 'Smells nice and feels fresh.', 3, '2025-01-05'),
    ('00006', 25, 'Didn''t work for my skin.', 2, '2025-01-06'),
    ('00007', 25, 'Perfect under makeup!', 4, '2025-01-07'),
    ('00001', 26, 'Great value for the price.', 5, '2025-01-01'),
    ('00002', 26, 'Smells nice and feels fresh.', 2, '2025-01-02'),
    ('00003', 26, 'Didn''t work for my skin.', 3, '2025-01-03'),
    ('00004', 26, 'Too harsh for me.', 3, '2025-01-04'),
    ('00005', 26, 'Great value for the price.', 1, '2025-01-05'),
    ('00006', 26, 'Too harsh for me.', 5, '2025-01-06'),
    ('00007', 26, 'Perfect under makeup!', 1, '2025-01-07');