DESIGN
# **UC 4.1.3.2** - edit nutricional profile **allergens**
##	SHORT FORMAT
The user initialize the process of edit user nutritional profile. The system shows the options for changing nutritional profile. The Cafeteria user chooses the option to change their allergens. The system shows the list of allergens and request one of the two options:
* Add allergen:
    * The system requests the allergen code;
    * The cafeteria user inserts the allergen code;
 * Remove allergen:
    * The system requests the allergen code;
    * The cafeteria user inserts the allergen code;
    
The system shows a success message;

###	SSD Short Format
![UC2.jpg](./ssd.jpg)

##	LONG FORMAT

### Main Actor
* Cafeteria User

### Stakeholders and their interests
+ Cafeteria User: has the possibility of having a record about their nutritional limits;
+ Chef: makes meals based on nutritional profiles;

### Pre-condition
+ The cafeteria user must be registered;

### Post-conditions
* The cafeteria user has an updated nutritional profile;

### Success Scenario (Basic Flow)
1. The user initialize the process of edit user nutritional profile. 
2. The system shows the options for changing nutritional profile. 
3. The Cafeteria user chooses the option to change their allergens. 
4. The system shows the list of allergens and request one of the two options:
    
    4.1 Add allergen:

        4.1.1 The system requests the allergen code;.
        4.1.2 The cafeteria user inserts the allergen code;

    4.2 Remove allergen:

        4.2.1 The system requests the allergen code;
        4.2.2 The cafeteria user inserts the allergen code;

### Extensions
    a. The user requests the cancelation of the UC.
    + The UC quits.


### Ocurrence Frequence
* Any time a Cafeteria User wants to change their nutritional profile.


### Opened questions
+ none

### Envolved Agregates
+ User
+ Allergens

### Envolved Repositories
+ 

###Controller
+ ChangeUserAllergensController

###UI
+ ChangeUserAllergensUI