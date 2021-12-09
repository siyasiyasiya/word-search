package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

public class HelloController {

    public HelloController(){

    }

    @FXML
    Label lblTurn, lblResult, lblTaken;
    @FXML
    Button btnStart;
    @FXML
    GridPane gdpPlayGrid;
    //Creates a 2D array of the GridSpot Class that represent one spot on a Tic Tac Toe Board
    private GridSpot [][] board = new GridSpot[10][10];
    //Creates a 2D array of Buttons
    private Button [][] boardSpotsBTN = new Button[10][10];
    //turn = which player's turn
    int turn, rowIndex, colIndex;
    //tie is used to check if there is a tie or a win
    private boolean tie = false;

    String[] names = {
            "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "William", "Sophia",
            "Elijah", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason",
            "Harper", "Ethan", "Evelyn"
    };

    String[] animals = {
            "Aardvark", "Albatross", "Alligator", "Alpaca", "Ant",
            "Anteater", "Antelope", "Ape", "Armadillo", "Donkey", "Baboon", "Badger", "Barracuda", "Bat", "Bear",
            "Beaver", "Bee", "Bison", "Boar", "Buffalo", "Butterfly", "Camel", "Capybara", "Caribou", "Cassowary",
            "Cat", "Caterpillar", "Cattle", "Chamois", "Cheetah", "Chicken", "Chimpanzee", "Chinchilla", "Clam",
            "Cobra", "Cockroach", "Cod", "Cormorant", "Coyote", "Crab", "Crane", "Crocodile", "Crow", "Curlew",
            "Deer", "Dinosaur", "Dog", "Dogfish", "Dolphin", "Dotterel", "Dove", "Dragonfly", "Duck", "Dugong",
            "Dunlin", "Eagle", "Echidna", "Eel", "Eland", "Elephant", "Elk", "Emu", "Falcon", "Ferret", "Finch",
            "Fish", "Flamingo", "Fly", "Fox", "Frog", "Gaur", "Gazelle", "Gerbil", "Giraffe", "Gnat", "Gnu", "Goat",
            "Goldfinch", "Goldfish", "Goose", "Gorilla", "Goshawk", "Grasshopper", "Grouse", "Guanaco", "Gull", "Hamster",
            "Hare", "Hawk", "Hedgehog", "Heron", "Herring", "Hippopotamus", "Hornet", "Horse", "Human", "Hummingbird",
            "Ibex", "Ibis", "Jackal", "Jaguar", "Jay", "Jellyfish", "Kangaroo", "Kingfisher", "Koala", "Kookabura", "Kouprey",
            "Kudu", "Lapwing", "Lark", "Lemur", "Leopard", "Lion", "Llama", "Lobster", "Locust", "Loris", "Louse",
            "Lyrebird", "Magpie", "Mallard", "Manatee", "Mandrill", "Mantis", "Marten", "Meerkat",
            "Mink", "Mole", "Mongoose", "Monkey", "Moose", "Mosquito", "Mouse", "Mule", "Narwhal", "Newt", "Nightingale",
            "Octopus", "Okapi", "Opossum", "Oryx", "Ostrich", "Otter", "Owl", "Oyster", "Panther", "Parrot", "Partridge", "Peafowl",
            "Pelican", "Penguin", "Pheasant", "Pig", "Pigeon", "Pony", "Porcupine", "Porpoise", "Quail", "Quelea", "Quetzal",
            "Rabbit", "Raccoon", "Rail", "Ram", "Rat", "Raven", "Red deer", "Red panda", "Reindeer", "Rhinoceros", "Rook",
            "Salamander", "Salmon", "Sand Dollar", "Sandpiper", "Sardine", "Scorpion", "Seahorse", "Seal", "Shark", "Sheep",
            "Shrew", "Skunk", "Snail", "Snake", "Sparrow", "Spider", "Spoonbill", "Squid", "Squirrel", "Starling", "Stingray",
            "Stinkbug", "Stork", "Swallow", "Swan", "Tapir", "Tarsier", "Termite", "Tiger", "Toad", "Trout", "Turkey", "Turtle",
            "Viper", "Vulture", "Wallaby", "Walrus", "Wasp", "Weasel", "Whale", "Wildcat", "Wolf", "Wolverine", "Wombat", "Woodcock",
            "Woodpecker", "Worm", "Wren", "Yak", "Zebra"};

    @FXML
    private void start(){
        //after adding the grdipane in scenebuilder, modify the fxml manually to eliminate rows and columns
        //these are some sample properties to change how the gridpane looks
        gdpPlayGrid.setHgap(3);
        gdpPlayGrid.setVgap(3);
        gdpPlayGrid.setPadding(new Insets(10));
        gdpPlayGrid.setGridLinesVisible(true);
        gdpPlayGrid.setAlignment(Pos.CENTER);

//        turn is a random number: either 0 or 1 which represents the first or second player
        turn = (int) Math.round(Math.random()*1);
        lblTurn.setText("It is Player " + (turn%2) + "'s Turn!" );
//        btnStart.setDisable(true);
        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                //initializes each of the indexes in the Button array
                boardSpotsBTN[i][j] = new Button();
                //sets each Buttons text
                boardSpotsBTN[i][j].setText("-");
                //sets size of buttons
                boardSpotsBTN[i][j].setPrefHeight(30);
                boardSpotsBTN[i][j].setPrefWidth(30);
                //similar to initializing a new class but for each spot in the array
                board[i][j] = new GridSpot();//calls constructor
                //Parameters:  object, columns, rows
                //adds each of the ImageViews to the GridPane in javafx at a specific spot
                //Parameters:  object, columns, rows
                gdpPlayGrid.add(boardSpotsBTN[i][j], j, i);

            }
        }

        for (String name: animals) {
            enterWord(name);
        }

        fillArray();


        //this is the mouse event: same as if you were adding it in scenebuilder but this lets you do it dynamically
        EventHandler z = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //gets the row index of which button you clicked on
                rowIndex = GridPane.getRowIndex(((Button) t.getSource()));
                //gets the column index of which button you clicked on
                colIndex = GridPane.getColumnIndex(((Button) t.getSource()));
                System.out.println(rowIndex);
                System.out.println(colIndex);
                lblTaken.setText("-");
                display(rowIndex, colIndex, t);
//                if(!checkDone()) {
//                    System.out.println(turn % 2);
//                    lblResult.setText("Result:");
//                    switchTurn();
//                }else{
//                    reset();
//                }

            }
        };

        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                //setting the onMouseClicked property for each of the buttons to call z (the event handler)
                boardSpotsBTN[i][j].setOnAction(z);
            }
        }
    }
    public boolean checkDone(){

        return false;

    }
    public void switchTurn(){
        //switches between player 1 and 2
        turn++;
        lblTurn.setText("It is Player " + (turn%2) + "'s Turn!" );

    }

    public void enterWord(String s){
//        String[] direc = {"U", "D", "L", "R"};
        boolean works = false;
        int count = 0;

        while (!works){
            int y = randomNumber(0, 9);
            int x = randomNumber(0, 9);
            int which = randomNumber(0, 3);
            works = true;
            count ++;

            //if direction is up
            if(which == 0){
                //checking if the word will be in bounds
                if(y-s.length()+1 >= 0){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y-j][x].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y-j][x].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y-j][x].setText(board[y-j][x].getBtnText());
                        }
                    }
                }
            }

            //if direction is down
            if(which == 1){
                //checking if the word will be in bounds
                if(y+s.length()-1 <= 9){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y+j][x].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y+j][x].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y+j][x].setText(board[y+j][x].getBtnText());
                        }
                    }
                }
            }

            //if direction is left
            if(which == 2){
                //checking if the word will be in bounds
                if(x-s.length()+1 >= 0){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y][x-j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y][x-j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y][x-j].setText(board[y][x-j].getBtnText());
                        }
                    }
                }
            }

            //if direction is right
            if(which == 3){
                //checking if the word will be in bounds
                if(x+s.length()-1 <= 9){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y][x+j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y][x+j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y][x+j].setText(board[y][x+j].getBtnText());
                        }
                    }
                }
            }

            //if direction is up and left
            if(which == 4){
                //checking if the word will be in bounds
                if(y-s.length()+1 >= 0 && x-s.length()+1 >= 0){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y-j][x-j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y-j][x-j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y-j][x-j].setText(board[y-j][x-j].getBtnText());
                        }
                    }
                }
            }

            //if direction is up and right
            if(which == 5){
                //checking if the word will be in bounds
                if(y-s.length()+1 >= 0 && x+s.length()-1 <= 9){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y-j][x+j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y-j][x+j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y-j][x+j].setText(board[y-j][x+j].getBtnText());
                        }
                    }
                }
            }

            //if direction is down and left
            if(which == 6){
                //checking if the word will be in bounds
                if(y+s.length()+1 <= 9 && x-s.length()+1 >= 0){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y+j][x-j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y+j][x-j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y+j][x-j].setText(board[y+j][x-j].getBtnText());
                        }
                    }
                }
            }

            //if direction is down and right
            if(which == 7){
                //checking if the word will be in bounds
                if(y+s.length()+1 <= 9 && x+s.length()+1 <= 9){

                    //checking if spot is occupied or not
                    for (int j = 0; j < s.length(); j++) {
                        if (!board[y+j][x+j].getBtnText().equals("-")){
                            works = false;
                        }
                    }
                    if(works){
                        //pushing letters into grid
                        for (int j = 0; j < s.length(); j++) {
                            board[y+j][x+j].clickedSpot(s.substring(j, j+1));
                            boardSpotsBTN[y+j][x+j].setText(board[y+j][x+j].getBtnText());
                        }
                    }
                }
            }


            if(count > 500){
               works = true;
            }
        }

//        board[y][x].clickedSpot("c");
//        board[y][x+1].clickedSpot("a");
//        board[y][x+2].clickedSpot("t");
//
//        boardSpotsBTN[y][x].setText(board[y][x].getBtnText());
//        boardSpotsBTN[y][x+1].setText(board[y][x+1].getBtnText());;
//        boardSpotsBTN[y][x+2].setText(board[y][x+2].getBtnText());;
    }

    //row = row index; col = column index; t = the source at where the button was clicked
    public void display(int row, int col, ActionEvent t){
        //checking if there is already a value at the position where the user clicked
        if(board[row][col].getBtnText()!="-"){
            lblTaken.setText("Result: Pick a new spot");
            System.out.println("Taken");
            //turn is subtracted because the person gets another try
            turn--;
        }else{
            //changes board to the turn of the player (1 or 2)
//            board[row][col].clickedSpot(turn%2);
            //gets the resource link of the image based on the turn and sets the image in ImageView 2D array and teh javafx GridPane
            boardSpotsBTN[row][col].setText(board[row][col].getBtnText());
//            ((ImageView) t.getSource()).setImage(new Image(board[k][j].getResourceLink()));

        }
        //using board which is initialized with 0's, sout board to make sure it all works
        for(int k = 0;k<board.length;k++){
            for(int j = 0; j<board.length;j++){
                System.out.print(board[k][j].getBtnText() + " ");
            }
            System.out.println("");
        }
    }

    public void fillArray(){
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        for(int k = 0;k<board.length;k++){
            for(int j = 0; j<board.length;j++){
                if(board[k][j].getBtnText().equals("-")){
                    board[k][j].clickedSpot(alphabet[randomNumber(0, 25)]);
                    boardSpotsBTN[k][j].setText(board[k][j].getBtnText());
                }
                boardSpotsBTN[k][j].setText(board[k][j].getBtnText().toUpperCase(Locale.ROOT));
            }
        }
    }

    public void reset(){
//
    }
    public int randomNumber(int a, int b) {
        double x = Math.floor(Math.random() * (b - a + 1) + a);
        return (int) x;
    }
}