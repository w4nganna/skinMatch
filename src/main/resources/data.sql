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
    (1, 'ultra fluid', 'la roche posay', 30.50, 'sunscreen', 'dry', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (2, 'Hydrating Facial Cleanser', 'CeraVe', 14.99, 'Cleanser', 'dry', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (3, 'Effaclar Purifying Foaming Gel', 'La Roche-Posay', 17.50, 'Cleanser', 'oily', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet92735/y/14.jpg'),
    (4, 'Glycolic Acid 7% Toning Solution', 'The Ordinary', 10.80, 'Toner', 'combo', 'https://www.sephora.com/productimages/sku/s2768083-main-zoom.jpg?imwidth=315'),
    (5, 'Ultra Facial Moisturizer', 'Kiehl', 22.00, 'Moisturizer', 'dry', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (6, 'Hydro Boost Water Gel', 'Neutrogena', 19.99, 'Moisturizer', 'oily', 'https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/cet/cet11300/y/37.jpg'),
    (7, 'Salicylic Acid 2% Exfoliating Solution', 'Paula’s Choice', 25.00, 'Exfoliator', 'combo', 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1'),
    (8, 'Anthelios Melt-in Milk Sunscreen SPF 60', 'La Roche-Posay', 36.99, 'Sunscreen', 'dry', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (9, 'Unseen Sunscreen SPF 40', 'Supergoop!', 34.00, 'Sunscreen', 'oily', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF_fMJvHqqHsq-E9ehEZCZtF82DAsztreSLw&s'),
    (10, 'Daily Microfoliant Exfoliator', 'Dermalogica', 59.00, 'Exfoliator', 'combo', 'https://cdn-tp3.mozu.com/30113-50629/cms/50629/files/9b6a0227-a973-4a31-9164-6881f4b5eae1');

----------------Ingredients-----------------
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (1, 'Water');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (2, 'Aloe Barbadensis Leaf Juice');
INSERT INTO ingredients (ingredientId, ingredientName) VALUES (3, 'Test Ingredient');

----------------Reviews-----------------
INSERT INTO reviews (userId, productId, reviewBody, score, date)
VALUES
    ('00001', 1, 'This product was perfect for my skin', 5, '2025-Jan-05'),
    ('00002', 1, 'This product did not work for me ', 2, '2025-Jan-03'),
    ('00001', 2, 'This product was okay', 3, '2024-Feb-19'),
    ('00002', 2, 'no thanks', 1, '2023-April-01');
