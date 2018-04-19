USE CASE - CREATE MEAL PLAN

	SHORT FORMAT
	{
		The user starts the creation of the meal plan. The system asks the user to select the menu. The user selects the menu.
		For each meal, the system asks what is the number of dishes. For each meal, the user inserts the number. The system asks for confirmation.
		The user confirms the operation.
	}

	PRE-CONDITIONS
	{
		There must be at least one meal saved in the system.
	}
	
	POST-CONDITIONS
	{
		The meal plan for the selected meal is saved in the system.
	}
	
	PRIMARY FLUX CENARIO
	{
		1. The user begins creation of meal plan.
		2. The system asks what is the menu for that plan.
		3. The user selects the menu.
		4. The system asks for the number of dishes.
		5. The user inserts the number of dishes.
		6. The system asks for confirmation.
		7. The user confirms.
		8. The use case ends.
	}
	
	ALTERNATIVE FLUX
	{
		\*a. The users cancels the operation. The use case ends.
		
		2a. There are no menus saved in the system.
			1. The system informs the user about the problem.
			2. The use case ends.
	}