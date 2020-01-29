CREATE TABLE coffees (
  id    INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name  VARCHAR(70),
  coffeeDate  DATE,
  type VARCHAR(30),
  weight INT,
  roasting VARCHAR(50)
);