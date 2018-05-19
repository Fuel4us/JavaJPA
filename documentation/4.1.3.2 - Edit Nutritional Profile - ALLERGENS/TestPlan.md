TEST PLAN
# **UC 4.1.3.2** - edit nutricional profile **allergens**


##TEST SUCESSFULL REMOVE ALLERGEN
1. Login (i.e: username: 900330 password:Password1);
2. Chooses the option < NutritionalProfile >;
3. Chooses the option < Allergens >;
4. Chooses the option < Remove allergen >
5. (The system shows the list of user's allergens) The User chooses an allergen of that list;
6. The system shows a message informing that the allergen was sucessfully removed;

##TEST SUCESSFULL ADD ALLERGEN
1. Login (i.e: username: 900330 password:Password1);
2. Chooses the option < NutritionalProfile >;
3. Chooses the option < Allergens >;
4. Chooses the option < Add allergen >
5. (The system shows the list of all available allergens) The User chooses an allergen that the it doesn't have in their list;
6. The system shows a message informing that the allergen was sucessfully added;


##TEST UNSUCESSFULL ADD ALLERGEN
1. Login (i.e: username: 900330 password:Password1);
2. Chooses the option < NutritionalProfile >;
3. Chooses the option < Allergens >;
4. Chooses the option < Add allergen >
5. (The system shows the list of all available allergens) The User chooses an allergen that the it has in their list;
6. The system shows a message informing that was not possible to add the selected allergen;