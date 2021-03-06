CREATE TABLE covidData (
  id INT AUTO_INCREMENT,
  continent varchar(30),
  country VARCHAR(20) NOT NULL,
  dateA varchar(10),
  total_Cases INT(11) ,
  new_Cases INT(11) ,
  total_Deaths INT(11) ,
  new_Deaths INT(11) ,
  reproduction_Rate FLOAT ,
  new_Tests INT(11),
  total_Tests INT(11) ,
  total_Vaccinations INT(11) ,
  people_Vaccinated INT(11) ,
  people_Fully_Vaccinated INT(11) ,
  new_Vaccinations INT(11) ,
  stringency_Index FLOAT ,
  population FLOAT ,
  population_Density FLOAT ,
  median_Age FLOAT ,
  female_Smokers FLOAT ,
  male_Smokers FLOAT ,
  constraint covidData_id_pk PRIMARY KEY(id)
)