import javax.swing.JOptionPane;

public class MealPlanner {
    // 1. Constants for Meals (Price and Calories)
    public static final double BREAKFAST_PRICE = 5.50;
    public static final int BREAKFAST_CALORIES = 400;

    public static final double LUNCH_PRICE = 9.75;
    public static final int LUNCH_CALORIES = 750;

    public static final double DINNER_PRICE = 12.25;
    public static final int DINNER_CALORIES = 950;

    public static void main(String[] args) {
        // Welcome Message
        JOptionPane.showMessageDialog(null, "Welcome to the Campus Budget & Calorie Meal Planner!");

        // 2. Input: Daily Budget
        String budgetInput = JOptionPane.showInputDialog("Enter your daily meal budget ($):");
        double remainingBudget = Double.parseDouble(budgetInput);

        // 3. Input: Gender Selection for Calorie Target
        String[] genders = {"Female (2,000 cal target)", "Male (2,500 cal target)"};
        int genderChoice = JOptionPane.showOptionDialog(null, "Select your gender to determine calorie target:",
                "Gender Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, genders, genders[0]);

        int calorieTarget = (genderChoice == 0) ? 2000 : 2500;
        int totalCalories = 0;

        // 4. Meal Selection Loop
        boolean ordering = true;
        while (ordering) {
            String[] options = {"Breakfast ($5.50 / 400 cal)", "Lunch ($9.75 / 750 cal)", "Dinner ($12.25 / 950 cal)", "Finish & View Summary"};
            int selection = JOptionPane.showOptionDialog(null, 
                    "Remaining Budget: $" + String.format("%.2f", remainingBudget) + 
                    "\nTotal Calories Consumed: " + totalCalories + " / " + calorieTarget + " cal" +
                    "\n\nSelect a meal option:",
                    "Meal Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (selection == 0) { // Breakfast
                remainingBudget -= BREAKFAST_PRICE;
                totalCalories += BREAKFAST_CALORIES;
            } else if (selection == 1) { // Lunch
                remainingBudget -= LUNCH_PRICE;
                totalCalories += LUNCH_CALORIES;
            } else if (selection == 2) { // Dinner
                remainingBudget -= DINNER_PRICE;
                totalCalories += DINNER_CALORIES;
            } else { // Exit loop
                ordering = false;
            }
        }

        // 5. Build Final Summary & Workout Suggestions
        String summary = "=== Daily Summary ===\n" +
                         "Remaining Budget: $" + String.format("%.2f", remainingBudget) + "\n" +
                         "Total Calories Consumed: " + totalCalories + " cal\n" +
                         "Target Goal: " + calorieTarget + " cal\n\n";

        if (totalCalories <= calorieTarget) {
            summary += "Great job! You stayed within your healthy recommended calorie balance.";
        } else {
            int excessCalories = totalCalories - calorieTarget;
            int milesToJog = (int) Math.ceil((double) excessCalories / 100);
            summary += "You exceeded your daily calorie target by " + excessCalories + " cal.\n" +
                       "Suggested Workout: Consider jogging " + milesToJog + " mile(s) (at ~100 calories burned per mile).";
        }

        // Display Final Output Window
        JOptionPane.showMessageDialog(null, summary, "Final Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
