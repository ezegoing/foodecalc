USE foodecalc;

INSERT INTO food_unit (name, user_id, protein, carbs, fat)
VALUES ('carrot', 1, 1, 10, 0);

INSERT INTO food_portion (food_unit_id, user_id, name, weight)
VALUES (1, 1, 'portion of carrots', 200);

INSERT INTO user (name)
VALUES ("anthony mc cann");

INSERT INTO meal (name, user_id)
VALUES ("Bowl of Vegetables", 1);

INSERT INTO meal_part (meal_id, food_portion_id)
VALUES (1, 1);

INSERT INTO meal_sitting (user_id, meal_id)
VALUES (1, 1);

SELECT food_portion.name, (food_unit.protein * (food_portion / 100))
FROM food_portion
INNER JOIN food_unit
ON food_portion.food_unit_id=food_unit.id;