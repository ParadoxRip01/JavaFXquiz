package quiz.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuizApp extends Application {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        Label questionLabel = new Label();
        Button[] buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            Button button = new Button();
            int finalI = i;
            button.setOnAction(e -> handleAnswer(buttons, finalI));
            buttons[i] = button;
            layout.getChildren().add(button);
        }
        layout.getChildren().add(0, questionLabel);

        questions = initializeQuestions();

        showNextQuestion(questionLabel, buttons);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Quiz App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Question> initializeQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is Object-Oriented Programming (OOP)?",
                "A programming model organized around objects rather than actions, focusing on data rather than logic.",
                Arrays.asList("A procedural programming model", "A database management system", "A type of software testing")
        ));
        questions.add(new Question(
                "What is encapsulation?",
                "The concept of hiding internal state and requiring all interaction to be performed through an object's methods.",
                Arrays.asList("A way to inherit methods from another class", "A method to increase performance", "A pattern for interfacing with databases")
        ));
        questions.add(new Question(
                "What is abstraction in OOP?",
                "A process of hiding the implementation details and showing only essential features of the object.",
                Arrays.asList("The extraction of data from one format to another", "Direct access to hardware", "Writing detailed documentation")
        ));
        questions.add(new Question(
                "What distinguishes abstraction from encapsulation?",
                "Abstraction hides complexity with simpler interfaces, whereas encapsulation hides the internal state.",
                Arrays.asList("Abstraction deals with physical data storage", "Encapsulation is only used in network security", "There is no difference")
        ));
        questions.add(new Question(
                "How does an abstract class differ from an interface?",
                "An abstract class can have implemented methods, whereas an interface cannot have implementations before Java 8.",
                Arrays.asList("An interface can contain instance fields", "Abstract classes cannot have any methods", "Interfaces are used to create objects")
        ));
        questions.add(new Question(
                "What is polymorphism?",
                "The ability of an object to take on many forms, typically through method overriding and overloading.",
                Arrays.asList("The ability of a function to execute asynchronously", "A database optimization technique", "A method of looping through data")
        ));
        questions.add(new Question(
                "What is the role of inheritance in OOP?",
                "Allows one class to inherit the properties and methods of another, facilitating code reuse and polymorphism.",
                Arrays.asList("Prevents classes from sharing methods", "Encrypts data for secure transmission", "Optimizes application for faster performance")
        ));
        questions.add(new Question(
                "Explain the difference between polymorphism and inheritance.",
                "Polymorphism allows objects to interact through shared interfaces, while inheritance shares properties and behaviors between classes.",
                Arrays.asList("They are the same concept", "Inheritance is a design pattern", "Polymorphism prevents classes from inheriting features")
        ));
        questions.add(new Question(
                "Describe method overloading and overriding.",
                "Overloading allows multiple methods with the same name but different parameters; overriding changes the implementation of an inherited method.",
                Arrays.asList("Overloading can only be done in child classes", "Overriding allows changing method parameters", "They do not involve inheritance")
        ));
        questions.add(new Question(
                "What is the difference between a class and an object?",
                "A class is a blueprint for creating objects; an object is an instance of a class.",
                Arrays.asList("A class is an instance of an object", "Objects can exist without classes", "Classes are specific to databases")
        ));
        questions.add(new Question(
                "How do you utilize an abstract class in a project?",
                "Define a common base class with shared methods and properties, which subclasses can extend and modify.",
                Arrays.asList("By creating instances of the abstract class", "To store data temporarily", "As the main method of execution in applications")
        ));
        return questions;
    }

    private void handleAnswer(Button[] buttons, int chosenAnswer) {
        if (questions.get(currentQuestionIndex).checkAnswer(chosenAnswer)) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            showNextQuestion((Label) buttons[0].getParent().getChildrenUnmodifiable().get(0), buttons);
        } else {
            System.out.println("Quiz Finished!");
            System.out.println("Your score: " + score + " out of " + questions.size());
        }
    }

    private void showNextQuestion(Label questionLabel, Button[] buttons) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.question);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(currentQuestion.options.get(i));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Question {
        String question;
        List<String> options;
        int correctAnswerIndex;

        public Question(String question, String correctAnswer, List<String> wrongAnswers) {
            this.question = question;
            options = new ArrayList<>(wrongAnswers);
            correctAnswerIndex = new Random().nextInt(options.size());
            options.add(correctAnswerIndex, correctAnswer);
        }

        public boolean checkAnswer(int userChoice) {
            return userChoice == correctAnswerIndex + 1;
        }
    }
}
