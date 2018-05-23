ANALYSIS
# **UC 4.1.3.2** - edit nutricional profile **allergens**


##Specifications
    
##Business Rule
*

##Unit Tests
* There's no allergens in the general repository;
* The user inserts a non-existing allergen;
* The user try to add an allergen which is already included in their profile;
* The user try to remove an allergen which is not included in their profile;

### Pre-condition
+ The cafeteria user must be registered;

### Post-conditions
* The cafeteria user has an updated nutritional profile;

### Main Actor
* Cafeteria User

### Stakeholders and their interests
+ Cafeteria User: has the possibility of having a record about their nutritional limits;
+ Chef: makes meals based on nutritional profiles;
