SET DB_CLOSE_DELAY -1;        
;             
CREATE USER IF NOT EXISTS SA SALT '1b6b481491d9485e' HASH '979db283a43475dbb5bf317f5e22afc657105488a86cce56b4ac78e1fd9d93d5' ADMIN;           
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6924E05E_E6EA_424E_A5A5_D0C421855945 START WITH 3 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_3E9A2522_F288_4359_8A4B_0DEBB385ED1E START WITH 3 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_83FFCD22_E40E_4816_8AF4_B43EB1085434 START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_43928211_11ED_4C29_A5E5_B3CC0010166A START WITH 5 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_218684D0_05F0_4A61_8817_3D4FE70D8E03 START WITH 8 BELONGS_TO_TABLE;    
CREATE MEMORY TABLE PUBLIC.CATEGORY(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_43928211_11ED_4C29_A5E5_B3CC0010166A) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_43928211_11ED_4C29_A5E5_B3CC0010166A,
    DESCRIPTION VARCHAR(255)
);     
ALTER TABLE PUBLIC.CATEGORY ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(ID);               
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.CATEGORY;
INSERT INTO PUBLIC.CATEGORY(ID, DESCRIPTION) VALUES(1, 'American');           
INSERT INTO PUBLIC.CATEGORY(ID, DESCRIPTION) VALUES(2, 'Italian');            
INSERT INTO PUBLIC.CATEGORY(ID, DESCRIPTION) VALUES(3, 'Mexican');            
INSERT INTO PUBLIC.CATEGORY(ID, DESCRIPTION) VALUES(4, 'Fast Food');          
CREATE MEMORY TABLE PUBLIC.INGREDIENT(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_83FFCD22_E40E_4816_8AF4_B43EB1085434) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_83FFCD22_E40E_4816_8AF4_B43EB1085434,
    AMOUNT DECIMAL(19, 2),
    DESCRIPTION VARCHAR(255),
    RECIPE_ID BIGINT,
    UOM_ID BIGINT
);            
ALTER TABLE PUBLIC.INGREDIENT ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.INGREDIENT;              
CREATE MEMORY TABLE PUBLIC.NOTE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6924E05E_E6EA_424E_A5A5_D0C421855945) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_6924E05E_E6EA_424E_A5A5_D0C421855945,
    RECIPE_NOTES CLOB,
    RECIPE_ID BIGINT
);         
ALTER TABLE PUBLIC.NOTE ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);   
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.NOTE;    
INSERT INTO PUBLIC.NOTE(ID, RECIPE_NOTES, RECIPE_ID) VALUES(1, STRINGDECODE('For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\nFeel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\nThe simplest version of guacamole is just mashed avocados with salt. Don''t let the lack of availability of other ingredients stop you from making guacamole.\nTo extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n\n\nRead more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws'), 1);   
INSERT INTO PUBLIC.NOTE(ID, RECIPE_NOTES, RECIPE_ID) VALUES(2, STRINGDECODE('We have a family motto and it is this: Everything goes better in a tortilla.\nAny and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\nToday\u2019s tacos are more purposeful \u2013 a deliberate meal instead of a secretive midnight snack!\nFirst, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\nGrill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n\n\nRead more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ'), 2);     
CREATE MEMORY TABLE PUBLIC.RECIPE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_3E9A2522_F288_4359_8A4B_0DEBB385ED1E) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_3E9A2522_F288_4359_8A4B_0DEBB385ED1E,
    COOK_TIME INTEGER,
    DESCRIPTION VARCHAR(255),
    DIFFICULTY VARCHAR(255),
    DIRECTIONS CLOB,
    IMAGE BLOB,
    PREP_TIME INTEGER,
    SERVINGS INTEGER,
    SOURCE VARCHAR(255),
    URL VARCHAR(255),
    NOTE_ID BIGINT
);     
ALTER TABLE PUBLIC.RECIPE ADD CONSTRAINT PUBLIC.CONSTRAINT_8 PRIMARY KEY(ID); 
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.RECIPE;  
INSERT INTO PUBLIC.RECIPE(ID, COOK_TIME, DESCRIPTION, DIFFICULTY, DIRECTIONS, IMAGE, PREP_TIME, SERVINGS, SOURCE, URL, NOTE_ID) VALUES(1, 0, 'Perfect Guacamole', 'EASY', STRINGDECODE('1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon\n2 Mash with a fork: Using a fork, roughly mash the avocado. (Don''t overdo it! The guacamole should be a little chunky.)\n3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\nAdd the chopped onion, cilantro, black pepper, and chilies. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\nRemember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\nChilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n\n\nRead more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd'), NULL, 10, 4, 'Simply Recipes', 'http://www.simplyrecipes.com/recipes/perfect_guacamole/', 1);   
INSERT INTO PUBLIC.RECIPE(ID, COOK_TIME, DESCRIPTION, DIFFICULTY, DIRECTIONS, IMAGE, PREP_TIME, SERVINGS, SOURCE, URL, NOTE_ID) VALUES(2, 9, 'Spicy Grilled Chicken Taco', 'MODERATE', STRINGDECODE('1 Prepare a gas or charcoal grill for medium-high, direct heat.\n2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\nSet aside to marinate while the grill heats and you prepare the rest of the toppings.\n\n\n3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\nWrap warmed tortillas in a tea towel to keep them warm until serving.\n5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n\n\nRead more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm'), NULL, 20, 4, 'Simply Recipes', 'http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/', 2);            
CREATE MEMORY TABLE PUBLIC.RECIPE_CATEGORY(
    RECIPE_ID BIGINT NOT NULL,
    CATEGORY_ID BIGINT NOT NULL
);              
ALTER TABLE PUBLIC.RECIPE_CATEGORY ADD CONSTRAINT PUBLIC.CONSTRAINT_D PRIMARY KEY(RECIPE_ID, CATEGORY_ID);    
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.RECIPE_CATEGORY;         
INSERT INTO PUBLIC.RECIPE_CATEGORY(RECIPE_ID, CATEGORY_ID) VALUES(1, 1);      
INSERT INTO PUBLIC.RECIPE_CATEGORY(RECIPE_ID, CATEGORY_ID) VALUES(1, 3);      
INSERT INTO PUBLIC.RECIPE_CATEGORY(RECIPE_ID, CATEGORY_ID) VALUES(2, 1);      
INSERT INTO PUBLIC.RECIPE_CATEGORY(RECIPE_ID, CATEGORY_ID) VALUES(2, 3);      
CREATE MEMORY TABLE PUBLIC.UNIT_OF_MEASURE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_218684D0_05F0_4A61_8817_3D4FE70D8E03) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_218684D0_05F0_4A61_8817_3D4FE70D8E03,
    DESCRIPTION VARCHAR(255),
    INGREDIENT_ID BIGINT
);   
ALTER TABLE PUBLIC.UNIT_OF_MEASURE ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);        
-- 7 +/- SELECT COUNT(*) FROM PUBLIC.UNIT_OF_MEASURE;         
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(1, 'Each', NULL);   
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(2, 'Teaspoon', NULL);               
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(3, 'Tablespoon', NULL);             
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(4, 'Dash', NULL);   
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(5, 'Cup', NULL);    
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(6, 'Pint', NULL);   
INSERT INTO PUBLIC.UNIT_OF_MEASURE(ID, DESCRIPTION, INGREDIENT_ID) VALUES(7, 'Ounce', NULL);  
ALTER TABLE PUBLIC.RECIPE_CATEGORY ADD CONSTRAINT PUBLIC.FKQSI87I8D4QQDEHLV2EIWVPWB FOREIGN KEY(CATEGORY_ID) REFERENCES PUBLIC.CATEGORY(ID) NOCHECK;          
ALTER TABLE PUBLIC.RECIPE ADD CONSTRAINT PUBLIC.FKLF335HPXLG22J27PG6MK4K9XT FOREIGN KEY(NOTE_ID) REFERENCES PUBLIC.NOTE(ID) NOCHECK;          
ALTER TABLE PUBLIC.UNIT_OF_MEASURE ADD CONSTRAINT PUBLIC.FKSUVP4JW48BI3H82HKXRFBGFQU FOREIGN KEY(INGREDIENT_ID) REFERENCES PUBLIC.INGREDIENT(ID) NOCHECK;     
ALTER TABLE PUBLIC.INGREDIENT ADD CONSTRAINT PUBLIC.FKJ0S4YWMQQQW4H5IOMMIGH5YJA FOREIGN KEY(RECIPE_ID) REFERENCES PUBLIC.RECIPE(ID) NOCHECK;  
ALTER TABLE PUBLIC.INGREDIENT ADD CONSTRAINT PUBLIC.FK6IV5L89QMITEDN5M2A71KTA2T FOREIGN KEY(UOM_ID) REFERENCES PUBLIC.UNIT_OF_MEASURE(ID) NOCHECK;            
ALTER TABLE PUBLIC.RECIPE_CATEGORY ADD CONSTRAINT PUBLIC.FKCQLQNVFYARHIEEWFEAYK3V25V FOREIGN KEY(RECIPE_ID) REFERENCES PUBLIC.RECIPE(ID) NOCHECK;             
ALTER TABLE PUBLIC.NOTE ADD CONSTRAINT PUBLIC.FKAELN1FTJ8676MM6E78IU7IBWQ FOREIGN KEY(RECIPE_ID) REFERENCES PUBLIC.RECIPE(ID) NOCHECK;        
