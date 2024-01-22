import javafx.application.Application;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;

    /**
     *
     * @author Abhinav Vinod
     * @version 1.0
     * The Jordle gameplay mechanics.
     */
    public class Wordle extends Application {
        private int row = 0;
        private int col = 0;
        private String[] input = new String[5];
        private ArrayList<String> words = Words.create();
        private String word = new String(words.get((int) (Math.random() * Words.list.size())));
        private boolean complete = false;
        private int count = 0;

        /**
         * Start. with a main window as stage.
         *
         * @param mainWindow the main window
         */
        @Override
        public void start(Stage mainWindow) {
            mainWindow.setTitle("Jordle");
            BorderPane mainLayout = new BorderPane();
            mainLayout.setFocusTraversable(false);
            Scene root = new Scene(mainLayout, 700, 800);
            //Title HBox
            HBox titleBox = new HBox();
            titleBox.setFocusTraversable(false);
            Label title = new Label("Jordle");
            title.setFocusTraversable(false);
            title.setFont(Font.font("Times New Roman", 50));
            title.setTextFill(Color.WHITESMOKE);
            title.setPadding(new Insets(0));
            titleBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY,
                    new CornerRadii(0.0), new Insets(0.0))));
            titleBox.setBorder(new Border(new BorderStroke(Color.LIGHTSTEELBLUE, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, new BorderWidths(5))));
            titleBox.getChildren().add(title);
            titleBox.setAlignment(Pos.CENTER);
            //Bottom hbox
            HBox bottom = new HBox();
            bottom.setFocusTraversable(false);
            bottom.setSpacing(10);
            bottom.setPrefHeight(100);
            Label l1 = new Label("Try guessing a word! ^_^");
            l1.setFocusTraversable(false);
            l1.setFont(Font.font("Times New Roman", 20));
            l1.setTextFill(Color.WHITESMOKE);
            Button b1 = new Button();
            Button b2 = new Button();
            b1.setFocusTraversable(false);
            b1.setText("Instructions");
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage help = new Stage();
                    help.setTitle("Instructions");
                    HBox exitHB = new HBox();
                    Button exit = new Button();
                    exit.setText("Close");
                    exit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            help.close();
                        }
                    });
                    exitHB.getChildren().add(exit);
                    exitHB.setAlignment(Pos.CENTER);
                    HBox topHelp = new HBox();
                    Label top = new Label("Instructions");
                    top.setFont(Font.font("Times New Roman", 20));
                    top.setTextFill(Color.WHITESMOKE);
                    top.setPadding(new Insets(0));
                    topHelp.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY,
                            new CornerRadii(0.0), new Insets(0.0))));
                    topHelp.setBorder(new Border(new BorderStroke(Color.LIGHTSTEELBLUE, BorderStrokeStyle.SOLID,
                            CornerRadii.EMPTY, new BorderWidths(5))));
                    topHelp.getChildren().add(top);
                    topHelp.setAlignment(Pos.CENTER);
                    Label center = new Label("\n\n\nHow to Play:\n\n1. Try to guess a five letter word.\n"
                            + "2. Green letter boxes signify correct letter at the correct place.\n"
                            + "3. Yellow letter boxes signify correct letter, but wrong place.\n"
                            + "4. Grey letter boxes signify that this letter is not in the word.\n"
                            + "5. You have 6 tries to get the word.\n"
                            + "6. Happy guessing :D");
                    center.setFont(Font.font("Times New Roman", 18));
                    center.setTextFill(Color.BLACK);
                    BorderPane info = new BorderPane();
                    info.setTop(topHelp);
                    info.setCenter(center);
                    info.setBottom(exitHB);
                    info.setAlignment(topHelp, Pos.CENTER);
                    info.setAlignment(center, Pos.TOP_CENTER);
                    info.setAlignment(exitHB, Pos.BASELINE_CENTER);
                    Scene helpScene = new Scene(info, 500, 400);
                    help.setScene(helpScene);
                    help.show();
                }
            });
            bottom.getChildren().addAll(l1, b2, b1);
            bottom.setAlignment(Pos.TOP_CENTER);
            bottom.setBackground(new Background(new BackgroundFill(Color.BLACK,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            bottom.setFocusTraversable(false);

            //Central Section of BorderPane
            StackPane center = new StackPane();

            //GridPane for boxes
            GridPane boxGrid = new GridPane();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    Label quiet = new Label("");
                    quiet.setFont(Font.font("Times New Roman", 50));
                    boxGrid.add(new StackPane(new Rectangle(75, 75, Color.LIGHTSLATEGREY), quiet), i, j);
                }
            }
            boxGrid.setBackground(new Background(new BackgroundFill(Color.BLACK,
                    new CornerRadii(0.0), new Insets(-10.0))));
            boxGrid.setAlignment(Pos.CENTER);
            boxGrid.setVgap(10.0);
            boxGrid.setHgap(10.0);
            //Place the boxGrid into StackPane
            center.setAlignment(boxGrid, Pos.CENTER);
            //Place all the parts into the mainLayout
            mainLayout.setCenter(center);
            mainLayout.setTop(titleBox);
            mainLayout.setBottom(bottom);
            mainLayout.setAlignment(title, Pos.CENTER);
            mainLayout.setAlignment(bottom, Pos.TOP_CENTER);
            mainWindow.setScene(root);
            mainWindow.show();
            center.getChildren().add(boxGrid);
            center.setAlignment(boxGrid, Pos.CENTER);
            center.setFocusTraversable(true);
            b2.setFocusTraversable(false);
            b2.setText("Restart");
            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    for (Node n: boxGrid.getChildren()) {
                        StackPane curr = (StackPane) n;
                        ((Label) curr.getChildren().get(1)).setText("");
                        ((Rectangle) curr.getChildren().get(0)).setFill(Color.LIGHTSLATEGREY);
                    }
                    row = 0;
                    col = 0;
                    count = 0;
                    complete = false;
                    word = new String(Words.list.get((int) (Math.random() * Words.list.size())));
                    l1.setText("Try guessing a word! ^_^");
                }
            });
            //Word to be guessed
            Alert a = new Alert(AlertType.ERROR);
            center.setOnKeyPressed((KeyEvent k) -> {
                String[] letters = word.split("", 5);
                count = 0;
                if (Arrays.deepEquals(input, letters)) {
                    complete = true;
                }
                if (col < 5 && k.getCode().isLetterKey() && !(complete)) {
                    for (Node n: boxGrid.getChildren()) {
                        StackPane curr = (StackPane) n;
                        if (boxGrid.getRowIndex(n) == row && boxGrid.getColumnIndex(n) == col) {
                            ((Label) curr.getChildren().get(1)).setText(k.getCode().getName());
                        }
                    }
                    col++;
                } else if (k.getCode() == KeyCode.ENTER && row < 6 && col < 5 && !(complete)) {
                    a.show();
                    a.setContentText("Please type 5 letter words.");
                } else if (k.getCode() == KeyCode.ENTER && row < 6 && !(complete)) {
                    for (int i = 0; i < col; i++) {
                        for (Node n: boxGrid.getChildren()) {
                            StackPane curr = (StackPane) n;
                            if (boxGrid.getRowIndex(n) == row && boxGrid.getColumnIndex(n) == i) {
                                input[i] = ((Label) curr.getChildren().get(1)).getText();
                            }
                        }
                    }
                    for (int v = 0; v < letters.length; v++) {
                        for (int p = 0; p < input.length; p++) {
                            if ((input[p].toLowerCase()).equals(letters[v])) {
                                for (Node n: boxGrid.getChildren()) {
                                    StackPane curr = (StackPane) n;
                                    if (boxGrid.getRowIndex(n) == row && boxGrid.getColumnIndex(n) == p) {
                                        ((Rectangle) curr.getChildren().get(0)).setFill(Color.KHAKI);
                                    }
                                }
                            }
                        }
                    }
                    for (int j = 0; j < letters.length; j++) {
                        if ((input[j].toLowerCase()).equals(letters[j])) {
                            for (Node n: boxGrid.getChildren()) {
                                StackPane curr = (StackPane) n;
                                if (boxGrid.getRowIndex(n) == row && boxGrid.getColumnIndex(n) == j) {
                                    ((Rectangle) curr.getChildren().get(0)).setFill(Color.YELLOWGREEN);
                                    count++;
                                    if (count == 5) {
                                        complete = true;
                                        l1.setText("Congrats! You guessed the word right! :D");
                                    }
                                }
                            }
                        }
                    }
                    if (row == 5 && count != 5) {
                        String out = new String("Game Over! The word was " + word + ". :,(");
                        l1.setText(out);
                    }
                    col = 0;
                    row++;
                } else if (k.getCode() == KeyCode.BACK_SPACE && col > 0 && !(complete)) {
                    col--;
                    for (Node n: boxGrid.getChildren()) {
                        StackPane curr = (StackPane) n;
                        if (boxGrid.getRowIndex(n) == row && boxGrid.getColumnIndex(n) == col) {
                            ((Label) curr.getChildren().get(1)).setText("");
                        }
                    }
                }
            });
        }
    }
