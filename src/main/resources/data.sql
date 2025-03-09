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


----------------Ingredients-----------------
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (1, 'Water');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (2, 'Aloe Barbadensis Leaf Juice');


----------------testResults-----------------
--INSERT INTO testResults(testResultId, user, recommendedProducts, concerns, avoidIngredients, budget, skinType)
--VALUES (1);

