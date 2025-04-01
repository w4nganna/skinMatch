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
INSERT INTO users(userId, email, password) VALUES('00008', 'aditi.ponemone@mail.utoronto.ca', '8888');

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
    ---------Sunscreen-----------
    (1, 'Light as Air SPF 50', 'Banana Boat', 2.47, 1, 'https://www.bananaboat.com/cdn/shop/products/Light_as_Air_Face_Lotion_FRONT_1400x.jpg?v=1624373442'),
    (2, 'Anthelios Melt-in Milk Sunscreen SPF 60', 'La Roche-Posay', 10.99, 1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (3, 'Unseen Sunscreen SPF 40', 'Supergoop!', 34.00, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (4, 'Urban Environment Vita-Clear Sunscreen SPF 42', 'Shiseido', 51.50, 1, 'https://www.sephora.com/productimages/sku/s2673382-main-zoom.jpg?imwidth=630'),
    (5, 'Hydro UV Defense Sunscreen - SPF50+', 'Laneige', 40.50, 1, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (6, 'Mini Cicapair Color Correcting Treatment SPF 30', 'Dr. Jart+', 34.00, 1, 'https://www.sephora.com/productimages/sku/s2580801-main-zoom.jpg?imwidth=630'),
    ---------Moisturizer-----------
    (7, 'Ultra Facial Moisturizer', 'Kiehl', 22.00, 4, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (8, 'Natural Moisturizing Factors + Beta Glucan', 'The Ordinary', 5.90, 4, 'https://www.sephora.com/productimages/sku/s2673317-main-zoom.jpg?imwidth=630'),
    (9, 'Hydro Boost Water Gel', 'Neutrogena', 8.99, 4, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (10, 'AM Facial Moisturizing Lotion SPF 30', 'CeraVe', 25.79, 4, 'https://digital.loblaws.ca/SDM/SDM_3606000537408/en/1/3606000537408_en_01_v1_400.jpeg'),
    (11, 'barrier+ Strengthening and Moisturizing Triple Lipid-Peptide Refillable Cream with B-L3', 'Skinfix', 73.00, 4, 'https://www.sephora.com/productimages/sku/s2742369-main-zoom.jpg?imwidth=3000'),
    (12, 'Omega Repair Deep Hydration Moisturizer with Ceramides and Hyaluronic Acid + Squalane', 'Biossance', 81.00, 4, 'https://www.sephora.com/productimages/sku/s2105856-main-zoom.jpg?imwidth=3000'),
    ---------Cleansers-----------
    (13, 'Hydrating Facial Cleanser', 'CeraVe', 14.99, 2, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (14, 'Gentle Cleansing Foam', 'Sulwhasoo', 17.00, 2, 'https://www.sephora.com/productimages/sku/s2708469-main-zoom.jpg?imwidth=3000'),
    (15, 'Effaclar Purifying Foaming Gel', 'La Roche-Posay', 17.50, 2, 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (16, 'Glucoside Foaming Cleanser', 'The Ordinary', 4.80, 2, 'https://www.sephora.com/productimages/sku/s2644151-main-zoom.jpg?imwidth=3000'),
    (17, 'Sulfate-Free Green Tea Amino Acid Cleansing Foam', 'innisfree', 19.00, 2, 'https://www.sephora.com/productimages/sku/s2740538-main-zoom.jpg?imwidth=3000'),
    (18, 'Anua - Heartleaf Succinic Moisture Cleansing Foam', 'YESSTYLE', 4.90, 2, 'https://d1flfk77wl2xk4.cloudfront.net/Assets/21/443/XXL_p0191844321.jpg'),
    ---------Toner-----------
    (19, 'Pyunkang Yul Calming Deep Moisture Toner', 'Amazon', 9.99, 3, 'https://m.media-amazon.com/images/I/413fhlawkhL._AC_SL1000_.jpg'),
    (20, 'glazing milk', 'rhodeskin', 52.00, 3, 'https://www.rhodeskin.com/cdn/shop/files/glazing-milk-11_2480x.jpg?v=1727315221'),
    (21, 'Mini Cream Skin Refillable Toner & Moisturizer with Ceramides and Peptides', 'Laneige', 22.00, 3, 'https://www.sephora.com/productimages/sku/s2671873-main-zoom.jpg?imwidth=3000'),
    (22, 'Glycolic Acid 7% Toning Solution', 'The Ordinary', 5.80, 3, 'https://www.sephora.com/productimages/sku/s2768083-main-zoom.jpg?imwidth=315'),
    (23, 'Anua - Heartleaf 77% Soothing Toner Mini', 'YESSTYLE', 10.28, 3, 'https://d1flfk77wl2xk4.cloudfront.net/Assets/88/722/XXL_p0130772288.jpg'),
    (24, 'Cetaphil Healthy Radiance Hydrating Toner', 'Cetaphil', 18.78, 3, 'https://m.media-amazon.com/images/I/616I5BUxXjL._AC_SX522_.jpg'),
    ---------Exfoliator-----------
    (25, 'Salicylic Acid 2% Exfoliating Solution', 'Paula''s Choice', 5.00, 5, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (26, 'Daily Microfoliant Exfoliator', 'Dermalogica', 2.50, 5, 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1');

----------------Ingredients-----------------
INSERT INTO ingredients (ingredientId, ingredientName, body) VALUES (1, 'Aloe Vera', 'supports skin hydration'),
                                                                    (2, 'Alpha-Arbutin', 'Skin Brightening Agent, reduce uneven skin tone, and dark spots'),
                                                                    (3, 'Vitamin C', 'targets uneven texture and signs of aging'),
                                                                    (4, 'Copper Peptides', 'targets signs of aging such as fine lines'),
                                                                    (5, 'Vitamin E', 'moisturizes the skin and offers antioxidant properties'),
                                                                    (6, 'Glycerine','moisturizing and emollient properties' ),
                                                                    (7, 'Glycolic Acid','skin exfoliation, brighter tone and even texture' ),
                                                                    (8, 'Hyaluronic Acid', 'hydration and anti-aging properties'),
                                                                    (9, 'Lactic Acid', 'balances the pH level of a formula, moisturizes and exfoliates'),
                                                                    (10, 'Shea Butter', 'moisturizing, soothing irritation, reducing inflammation'),
                                                                    (11, 'Mandelic Acid', 'surface exfoliation to target uneven tone texture and dullness'),
                                                                    (12, 'Niacinamide', 'form of vitamin B3 that helps reinforce the skin barrier'),
                                                                    (13, 'Panthenol', 'benefits for dry and sensitive skin'),
                                                                    (14, 'Retinol',' Increases cell turn over, effective on wrinkles and acne' ),
                                                                    (15, 'Salicylic acid', 'exfoliates the skin surface used to target congestion and improve texture'),
                                                                    (16, 'Squalene', 'oil-like molecule that reduces water loss'),
                                                                    (17, 'Alcohol', 'alcohol has drying, refreshing and antimicrobial properties. helps active ingredients penetrate more easily into the superficial layers of the skin'),
                                                                    ----------Possible irritants------
                                                                    (18, 'EDTA', 'ethylene diamine tetra-acetic acid, is used to improve stability and to enhance the appearance of cosmetic product'),
                                                                    (19, 'Ethylparaben', 'preservatives used in some cosmetic products to prevent growth of microbes'),
                                                                    (20, 'Fragrances','smells made of combinations of different ingredients' ),
                                                                    (21, 'PEG/PPG','polyethylene glycols (PEG) and polyproplene glycols (PPG) are humectants that preserve moisture or emulsifiers'),
                                                                    (22, 'Phenoxyethanol', 'perservative, used to prevent the growth of microbes'),
                                                                    (23, 'Silicones', 'adds long lasting shiny textures'),
                                                                    (24, 'Talc', 'absorbing and transparency properties, helps fragrances last longer, and makes the skin soft/silky'),
                                                                    (25, 'BHT', 'Butylated Hydroxytoluene is stablizing agent that prevents the changes in colour, odor and texture'),
                                                                    ----------Sun Filter-------
                                                                    (26, 'Chemical sun filters', 'Avobenzone, Homosalate, Octinoxate,Octocrylene, Oxybenzone, Drometrizol Trisiloxane: sun filter, absorbs UVA & UVB rays'),
                                                                    (27, 'Physical sun filters ', 'Zinc Oxide, Titanium Dioxide: filters UV radiations, soothing against irritation');




----------------Product Ingredients-----------------

 INSERT INTO ProductIngredients (productId, ingredientId) VALUES (1, 26), (1, 27), (1, 6), -- banana boat
                                                                  (2, 26), (2,27 ), (2, 6), -- Anthelios Melt-in Milk Sunscreen SPF 60
                                                                  (3, 26), (3, 27), (3, 10), -- Unseen Sunscreen SPF 40
                                                                  (4, 26), (4, 17), (4, 6), -- Urban Environment Vita-Clear Sunscreen SPF 42
                                                                  (5, 26), (5, 17), (5, 6), -- Hydro UV Defense Sunscreen - SPF50+
                                                                  (6, 27), (6, 12), (6, 13); -- Mini Cicapair Color Correcting Treatment SPF 30

INSERT INTO ProductIngredients (productId, ingredientId) VALUES (7, 6), (7, 16), (7, 25),(7, 15), -- Ultra Facial Moisturizer
                                                                 (8, 6), (8, 9), (8, 8), -- Natural Moisturizing Factors + Beta Glucan
                                                                 (9, 8), (9,6 ), (9, 20), -- Hydro Boost Water Gel
                                                                 (10,8 ), (10, 12), (10, 27), -- AM Facial Moisturizing Lotion SPF 30
                                                                 (11, 6), (11, 10), (11,16 ), -- Barrier+ Strengthening and Moisturizing Triple Lipid-Peptide Refillable Cream
                                                                 (12, 16), (12, 6), (12, 8); -- Omega Repair Deep Hydration Moisturizer

INSERT INTO ProductIngredients (productId, ingredientId) VALUES (13, 12), (13, 6), (13, 8), -- Hydrating Facial Cleanser
                                                                 (14, 6), (14, 17), (14, 20), -- Gentle Cleansing Foam
                                                                 (15, 20), (15, 21), (15, 7), -- Effaclar Purifying Foaming Gel
                                                                 (16, 10), (16, 21), (16, 17), -- Glucoside Foaming Cleanser
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

----------------Product Concerns-----------------
INSERT INTO ProductConcerns (productId, concernId) VALUES (7,1),(11,1), (12,1), (19,1), (20,1);
INSERT INTO ProductConcerns (productId, concernId) VALUES (15,2),(16,2), (17,2), (22,2), (25,2);
INSERT INTO ProductConcerns (productId, concernId) VALUES (15,3),(17,3), (22,3), (26,3);
INSERT INTO ProductConcerns (productId, concernId) VALUES (4, 4), (5, 4), (6, 4), (22, 4), (25, 4);
INSERT INTO ProductConcerns (productId, concernId) VALUES(11, 5), (12, 5), (19, 5), (23, 5), (6, 5);
INSERT INTO ProductConcerns (productId, concernId) VALUES (22, 6), (24, 6), (26, 6), (4, 6);

----------------Product Type-----------------
INSERT INTO userSkintype (productId, skintypeId) VALUES (1,1), (3,1), (4,1), (9,1), (10,1);
INSERT INTO userSkintype (productId, skintypeId) VALUES (2,2), (7,2), (8,2), (13,2), (14,2), (19,2), (20,2);
INSERT INTO userSkintype (productId, skintypeId) VALUES (5,3), (6,3), (11,3), (12,3), (17,3), (18,3), (23,3), (24,3), (25,3), (26,3);
INSERT INTO userSkintype (productId, skintypeId) VALUES (15,1), (16,1), (21,1), (22,1);

----------------Reviews-----------------
INSERT INTO reviews (userId, productId, reviewBody, score, date) VALUES
    ('00001', 1, 'This did wonders for me, I will be buying again!', 5, '2025-01-01'),
    ('00002', 1, 'Amazing product, will buy again!', 5, '2025-01-02'),
    ('00003', 1, 'Absolutely love it, highly recommended!!', 5, '2025-01-01'),
    ('00004', 2, 'My skin feels amazing after using this!!', 5, '2025-01-02'),
    ('00005', 2, 'Finally found something that works for me.!', 5, '2025-01-01'),
    ('00006', 2, '“Great texture, absorbs quickly, no irritation!', 4, '2025-01-02'),
    ('00007', 3, 'I have already bought a second one!',4,'2025-01-02'),
    ('00001', 3, 'This did wonders for me, I will be buying again!', 4, '2025-01-01'),
    ('00002', 4, 'worth every penny!', 4, '2025-01-02'),
    ('00003', 4, 'My go to product!', 4, '2025-01-01'),
    ('00004', 4, 'Its okay not the best ive tried!', 3, '2025-01-02'),
    ('00005', 5, 'Decent product might buy again', 3,'2025-01-01'),
    ('00006', 5, 'still deciding if its working ', 3, '2025-01-02'),
    ('00007', 5, 'average results, but good price', 3, '2025-01-01'),
    ('00001', 6, 'nothing special, but not bad!', 3, '2025-01-02'),
    ('00002', 6, 'works fine, not sure if id repurchase', 2, '2025-01-01'),
    ('00003', 6, 'Made my skin feel dry and tight', 2, '2025-01-02'),
    ('00004', 7, 'made me breakout after a few uses', 2, '2025-01-01'),
    ('00005', 7, 'smells funny, not worth it imo', 1, '2025-01-02'),
    ('00006', 7, 'disappointed expected better results', 1, '2025-01-01'),
    ('00006', 8, 'too greasy for my skin type, will be returning', 1, '2025-01-02'),
    ('00007', 8, 'This did wonders for me, I will be buying again!', 5, '2025-01-01'),
    ('00001', 8, 'Amazing product, will buy again!', 5, '2025-01-02'),
    ('00002', 9, 'Absolutely love it, highly recommended!!', 5, '2025-01-01'),
    ('00003', 9, 'My skin feels amazing after using this!!', 5, '2025-01-02'),
    ('00004', 9, 'Finally found something that works for me.!', 5, '2025-01-01'),
    ('00005', 10, '“Great texture, absorbs quickly, no irritation!', 4, '2025-01-02'),
    ('00006', 10, 'I have already bought a second one!',4,'2025-01-02'),
    ('00007', 10, 'This did wonders for me, I will be buying again!', 4, '2025-01-01'),
    ('00001', 11, 'worth every penny!', 4, '2025-01-02'),
    ('00002', 11, 'My go to product!', 4, '2025-01-01'),
    ('00003', 11, 'Its okay not the best ive tried!', 3, '2025-01-02'),
    ('00004', 12, 'Decent product might buy again', 3,'2025-01-01'),
    ('00005', 12, 'still deciding if its working ', 3, '2025-01-02'),
    ('00006', 12, 'average results, but good price', 3, '2025-01-01'),
    ('00007', 13, 'nothing special, but not bad!', 3, '2025-01-02'),
    ('00001', 13, 'works fine, not sure if id repurchase', 2, '2025-01-01'),
    ('00002', 13, 'Made my skin feel dry and tight', 2, '2025-01-02'),
    ('00003', 14, 'made me breakout after a few uses', 2, '2025-01-01'),
    ('00004', 14, 'smells funny, not worth it imo', 1, '2025-01-02'),
    ('00005', 14, 'disappointed expected better results', 1, '2025-01-01'),
    ('00006', 15, 'too greasy for my skin type, will be returning', 1, '2025-01-02'),
    ('00007', 15, 'This did wonders for me, I will be buying again!', 5, '2025-01-01'),
    ('00001', 15, 'Amazing product, will buy again!', 5, '2025-01-02'),
    ('00002', 16, 'Absolutely love it, highly recommended!!', 5, '2025-01-01'),
    ('00003', 16, 'My skin feels amazing after using this!!', 5, '2025-01-02'),
    ('00004', 16, 'Finally found something that works for me.!', 5, '2025-01-01'),
    ('00005', 17, '“Great texture, absorbs quickly, no irritation!', 4, '2025-01-02'),
    ('00006', 17, 'I have already bought a second one!',4,'2025-01-02'),
    ('00007', 17, 'This did wonders for me, I will be buying again!', 4, '2025-01-01'),
    ('00001', 18, 'worth every penny!', 4, '2025-01-02'),
    ('00002', 18, 'My go to product!', 4, '2025-01-01'),
    ('00003', 18,'Its okay not the best ive tried!', 3, '2025-01-02'),
    ('00004', 19, 'Decent product might buy again', 3,'2025-01-01'),
    ('00005', 19, 'still deciding if its working ', 3, '2025-01-02'),
    ('00006', 19, 'average results, but good price', 3, '2025-01-01'),
    ('00007', 20, 'nothing special, but not bad!', 3, '2025-01-02'),
    ('00001', 20, 'works fine, not sure if id repurchase', 2, '2025-01-01'),
    ('00002', 20, 'Made my skin feel dry and tight', 2, '2025-01-02'),
    ('00003', 21, 'made me breakout after a few uses', 2, '2025-01-01'),
    ('00004', 21, 'smells funny, not worth it imo', 1, '2025-01-02'),
    ('00005', 21, 'disappointed expected better results', 1, '2025-01-01'),
    ('00006', 22, 'too greasy for my skin type, will be returning', 1, '2025-01-02'),
    ('00007', 22, 'This did wonders for me, I will be buying again!', 5, '2025-01-01'),
    ('00001', 22, 'Amazing product, will buy again!', 5, '2025-01-02'),
    ('00002', 23, 'Absolutely love it, highly recommended!!', 5, '2025-01-01'),
    ('00003', 23, 'My skin feels amazing after using this!!', 5, '2025-01-02'),
    ('00004', 23, 'Finally found something that works for me.!', 5, '2025-01-01'),
    ('00005', 24, '“Great texture, absorbs quickly, no irritation!', 4, '2025-01-02'),
    ('00006', 24, 'I have already bought a second one!',4,'2025-01-02'),
    ('00007', 24, 'This did wonders for me, I will be buying again!', 4, '2025-01-01'),
    ('00001', 25, 'worth every penny!', 4, '2025-01-02'),
    ('00002', 25, 'My go to product!', 4, '2025-01-01'),
    ('00003', 25,'Its okay not the best ive tried!', 3, '2025-01-02'),
    ('00004', 26, 'Decent product might buy again', 3,'2025-01-01'),
    ('00006', 26, 'still deciding if its working ', 3, '2025-01-02'),
    ('00007', 26, 'average results, but good price', 3, '2025-01-01');




