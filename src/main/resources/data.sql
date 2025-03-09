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
INSERT INTO products (productId, name, brand, price, category, type, imageURL) VALUES
                                                                                   (1, 'ultra fluid', 'Laneige', 40.50, 'sunscreen',
                                                                                    'Broad-Spectrum SPF 50+: Protects against UVA and UVB rays. Centella asiatica: Soothes skin. Glycerin and Hydro-Ionized Mineral Water: Hydrate skin.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (2, 'ultra fluid', 'Shiseido', 42.00, 'sunscreen',
                                                                                    'WetForce and HeatForce Technologies: Create an invisible, lightweight protective veil. SynchroShield™: Supports the protective layer upon exposure to heat, water, and sun.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (3, 'ultra fluid', 'Clinique', 59.50, 'sunscreen',
                                                                                    'Water, Ethylhexyl Methoxycinnamate, C12-15 Alkyl Benzoate, Titanium Dioxide, Zinc Oxide, Caprylyl Methicone, Butylene Glycol, Aloe Barbadensis Leaf Water, Caffeine, Tocopheryl Acetate.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (4, 'ultra fluid', 'Evereden', 39.00, 'sunscreen',
                                                                                    'Sheertech Zinc™: Lightweight, sheer texture with no white cast. Safflower Oleotech™: Hydrates skin throughout the day. Sunflower Seed Oil: Antioxidant-rich oil that conditions skin.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (5, 'ultra fluid', 'Dr.Jart+', 60.00, 'sunscreen',
                                                                                    'Water, Homosalate, Ethylhexyl Methoxycinnamate, Propanediol, Ethylhexyl Salicylate, Alcohol, Octocrylene, Lavender Oil, Silica, Tocopherol.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (6, 'ultra fluid', 'Supergoop!', 33.00, 'sunscreen',
                                                                                    'Sunflower Extract: Protects skin from environmental elements. Rosemary Leaf Extract: Calms skin and is rich in antioxidants.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (7, 'ultra fluid', 'CAY SKIN', 51.50, 'sunscreen',
                                                                                    'Sea Moss: Hydrates and protects skin. Cocoa Seed Butter: Replenishes moisture. Hydrating Nectar: Improves skin texture.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (8, 'ultra fluid', 'Clarins', 44.00, 'sunscreen',
                                                                                    'Sunflower: Soothes sunburned skin. Safflower: Enhances and intensifies a tan. Aloe Vera: Hydrates and calms sun-stressed skin.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (9, 'ultra fluid', 'Paula’s Choice', 54.00, 'sunscreen',
                                                                                    'Broad-Spectrum SPF 50: Defends against UV rays. Oat and Green Tea Extracts: Relieve visible redness. Black Elderberry, Goji, and Pomegranate Extracts: Protect skin’s moisture barrier.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),

                                                                                   (10, 'ultra fluid', 'ILIA', 65.00, 'sunscreen',
                                                                                    'Plant-based Squalane: Hydrates and improves elasticity. Niacinamide: Smooths texture and minimizes pores. Hyaluronic Acids: Plump and boost moisture levels.',
                                                                                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s');

INSERT INTO products (productId, name, brand, price, category, type, imageURL) VALUES
                                                                                   (11, 'Superfood Cleanser', 'Youth to the People', 53.00, 'cleanser',
                                                                                    'Water/Aqua/Eau, Cocamidopropyl Hydroxysultaine, Sodium Cocoyl Glutamate, Sorbeth-230 Tetraoleate, Polysorbate 20, Sodium Chloride, Aloe Barbadensis Leaf Juice Powder, Kale Leaf Extract, Spinach Leaf Extract, Green Tea Leaf Extract, Vitamin C, Glycerin, Vitamin B5, Vitamin E.',
                                                                                    'https://example.com/youth-to-the-people-cleanser.jpg'),
                                                                                   (12, 'Squalane Cleanser', 'The Ordinary', 14.80, 'cleanser',
                                                                                    'Decyl-Glucoside and Coco-Glucoside: Help remove dirt and other impurities.',
                                                                                    'https://example.com/the-ordinary-cleanser.jpg'),
                                                                                   (13, 'Avocado Cleanser', 'Glow Recipe', 38.00, 'cleanser',
                                                                                    'Ceramides, Prebiotics, Postbiotics, Colloidal Oatmeal, Avocado Oil.',
                                                                                    'https://example.com/glow-recipe-cleanser.jpg'),
                                                                                   (14, 'Cream Skin Milk Cleanser', 'Laneige', 39.50, 'cleanser',
                                                                                    'Blue Hyaluronic Acid, Allantoin, Amino Acid Complex.',
                                                                                    'https://example.com/laneige-cleanser.jpg'),
                                                                                   (15, 'Pure Skin Cleanser', 'First Aid Beauty', 32.50, 'cleanser',
                                                                                    'Aloe, Allantoin, Glycerin.',
                                                                                    'https://example.com/first-aid-beauty-cleanser.jpg'),
                                                                                   (16, 'Foaming Oil Cleanser', 'Skinfix', 40.50, 'cleanser',
                                                                                    'Triple Lipid Complex, Seaweed Hyaluronate Blend, Aloe Vera.',
                                                                                    'https://example.com/skinfix-cleanser.jpg'),
                                                                                   (17, 'Green Tea Foam Cleanser', 'Innisfree', 19.00, 'cleanser',
                                                                                    'Green Tea Water, Amino Acid Complex.',
                                                                                    'https://example.com/innisfree-cleanser.jpg'),
                                                                                   (18, 'Soy Face Cleanser', 'Fresh', 53.00, 'cleanser',
                                                                                    'Soy Proteins, Cucumber Extract, Aloe Vera.',
                                                                                    'https://example.com/fresh-cleanser.jpg'),
                                                                                   (19, 'Vinopure Cleanser', 'Caudalie', 57.00, 'cleanser',
                                                                                    'Grape Water, Salicylic Acid, Peppermint Oil.',
                                                                                    'https://example.com/caudalie-cleanser.jpg'),
                                                                                   (20, 'Whipped Greens Cleanser', 'Farmacy', 44.00, 'cleanser',
                                                                                    'Moringa, Apple and Oats, Vitamin F.',
                                                                                    'https://example.com/farmacy-cleanser.jpg');

INSERT INTO products (productId, name, brand, price, category, type, imageURL) VALUES
                                                                                   (21, 'Natural Moisturizing Factors + HA', 'The Ordinary', 18.10, 'Moisturizer', 'Normal, Oily, Combination, Dry', 'image_url_here'),
                                                                                   (22, 'Plum Plump Hyaluronic Acid Moisturizer', 'Glow Recipe', 54.00, 'Moisturizer', 'Normal, Dry, Combination, Oily', 'image_url_here'),
                                                                                   (23, 'The Dewy Skin Cream', 'Tatcha', 97.00, 'Moisturizer', 'Normal, Dry', 'image_url_here'),
                                                                                   (24, 'Ultra Repair Face Moisturizer', 'First Aid Beauty', 51.00, 'Moisturizer', 'Normal, Dry, Combination, Oily', 'image_url_here'),
                                                                                   (25, 'Water Bank Blue Hyaluronic Cream Moisturizer', 'LANEIGE', 52.00, 'Moisturizer', 'Normal, Dry, Combination, Oily', 'image_url_here'),
                                                                                   (26, 'Vitamin Enriched Face Base', 'Bobbi Brown', 34.00, 'Moisturizer', 'Normal, Oily, Combination', 'image_url_here'),
                                                                                   (27, 'Lala Retro Whipped Cream', 'Drunk Elephant', 94.00, 'Moisturizer', 'Normal, Dry, Combination, Oily', 'image_url_here'),
                                                                                   (28, 'Premier Cru The Cream', 'Caudalie', 155.00, 'Moisturizer', 'Normal, Dry, Combination', 'image_url_here'),
                                                                                   (29, 'Squalane + Omega Repair Cream', 'Biossance', 81.00, 'Moisturizer', 'Normal, Dry, Combination', 'image_url_here'),
                                                                                   (30, 'Moisturizing Cream', 'Cetaphil', 15.29, 'Moisturizer', 'Dry', 'image_url_here');

INSERT INTO products (productId, name, brand, price, category, type, imageURL)
VALUES
    (31, 'Toner', 'Lise Watier', 32.00, 'Toner', 'Normal, Dry', 'N/A'),
    (32, 'Toner', 'Clarins', 48.00, 'Toner', 'Normal, Dry', 'N/A'),
    (33, 'Toner', 'Dermalogica', 72.00, 'Toner', 'Normal, Combination, Dry', 'N/A'),
    (34, 'Toner', 'La Mer', 162.00, 'Toner', 'Normal, Combination, Oily', 'N/A'),
    (35, 'Toner', 'Fenty Beauty by Rihanna', 25.00, 'Toner', 'Normal, Combination, Oily', 'N/A'),
    (36, 'Toner', 'Farmacy', 23.00, 'Toner', 'Normal, Combination, Oily', 'N/A'),
    (37, 'Toner', 'Mario Badescu', 24.50, 'Toner', 'Dry and Combination', 'N/A'),
    (38, 'Toner', 'Belif', 40.50, 'Toner', 'Normal, Combination, Oily', 'N/A'),
    (39, 'Toner', 'Laneige', 42.00, 'Toner', 'Normal, Dry', 'N/A'),
    (40, 'Toner', 'Lancome', 84.00, 'Toner', 'Normal, Combination, Oily', 'N/A');

INSERT INTO products (productId, name, brand, price, category, type, imageURL)
VALUES
    (41, 'Youth To The People', 'Youth To The People', 53.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image1.jpg'),
    (42, 'Tatcha', 'Tatcha', 92.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image2.jpg'),
    (43, 'Origins', 'Origins', 39.00, 'exfoliator', 'Normal, Combination, Oily', 'https://www.example.com/image3.jpg'),
    (44, 'Caudalie', 'Caudalie', 51.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image4.jpg'),
    (45, 'The INKEY List', 'The INKEY List', 24.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image5.jpg'),
    (46, 'Clinique', 'Clinique', 40.50, 'exfoliator', 'Oily, Combination', 'https://www.example.com/image6.jpg'),
    (47, 'Glow Recipe', 'Glow Recipe', 57.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image7.jpg'),
    (48, 'Glossier', 'Glossier', 41.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image8.jpg'),
    (49, 'Peace Out', 'Peace Out', 36.00, 'exfoliator', 'Normal, Combination, Oily', 'https://www.example.com/image9.jpg'),
    (50, 'Fresh', 'Fresh', 53.00, 'exfoliator', 'Normal, Dry, Combination, Oily', 'https://www.example.com/image10.jpg');


----------------Ingredients-----------------
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (1, 'Water');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (2, 'Aloe Barbadensis Leaf Juice');


----------------testResults-----------------
--INSERT INTO testResults(testResultId, user, recommendedProducts, concerns, avoidIngredients, budget, skinType)
--VALUES (1);

