import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.security.SecureRandom;

public class WesternMoonFX extends Application {
    private Stage primaryStage;
    private String key;
    private String user;
    private String password;
    private boolean loginSuccess = false;
    private ObservableList<Entidad> listaSoldadosMineros = FXCollections.observableArrayList();
    private ObservableList<AeroCars> listaAeroCars = FXCollections.observableArrayList();
    private Stack<Scene> sceneHistory = new Stack<>();
    private Spinner<Integer> spnAliens;
    private TextField txtDistancia;
    private Spinner<Integer> spnAerocars;
    private TextArea txtResults;
    private ResponsableVehiculo responsable1 = new ResponsableVehiculo(null, null, null, null, null, null);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.key = generateSecureKey();
        // Crear aerocars de reserva
        listaAeroCars.add(new AeroCars("Aerocar de reserva 1", 4, "Gasolina", 200, 10000, responsable1, 4, 200, true));
        listaAeroCars.add(new AeroCars("Aerocar de reserva 2", 4, "Gasolina", 200, 1000, responsable1, 4, 200, true));

        primaryStage.setTitle("WesternMoon by PHPTeam");
        mostrarAnimacion();
    }

    private String generateSecureKey() {
        SecureRandom random = new SecureRandom();
        return String.format("%016d", Math.abs(random.nextLong() % 10_000_000_000_000_000L)) +
                String.format("%016d", Math.abs(random.nextLong() % 10_000_000_000_000_000L));
    }

    private void mostrarAnimacion() {
        VBox splashLayout = new VBox(20);
        splashLayout.setAlignment(Pos.CENTER);
        splashLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #0a0a2a, #1a1a4a);");

        // Imagen de luna (puedes reemplazar con tu propio recurso)
        ImageView moonImage = new ImageView(new Image("file:moon.png")); // Asegúrate de tener este archivo
        moonImage.setFitHeight(150);
        moonImage.setFitWidth(150);

        Text title = new Text("WesternMoon");
        title.setFont(Font.font("Impact", FontWeight.BOLD, 48));
        title.setFill(Color.WHITE);
        title.setEffect(new javafx.scene.effect.DropShadow(10, Color.SILVER));

        Text subtitle = new Text("by PHPTeam");
        subtitle.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
        subtitle.setFill(Color.LIGHTGRAY);

        ProgressIndicator progress = new ProgressIndicator();
        progress.setStyle("-fx-progress-color: #a0a0ff;");

        splashLayout.getChildren().addAll(moonImage, title, subtitle, progress);
        Scene splashScene = new Scene(splashLayout, 600, 400);
        primaryStage.setScene(splashScene);
        primaryStage.show();

        // Animación de fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), splashLayout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Pasar al menú principal después de la animación
        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(e -> showMainMenu());
        pause.play();
    }

    private void showMainMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1a1a3a, #2a2a5a);");

        // Menú superior
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #3a3a7a;");

        Menu fileMenu = new Menu("Archivo");
        MenuItem exitItem = new MenuItem("Salir");
        exitItem.setGraphic(new ImageView(new Image("file:exit.png", 16, 16, true, true)));
        exitItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);

        // Contenido central
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        // Estilo para los botones
        String buttonStyle = "-fx-background-color: #4a4a8a, linear-gradient(#5a5a9a, #3a3a7a); " +
                "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; " +
                "-fx-padding: 10 20 10 20; -fx-background-radius: 5;";

        Button btnLogin = createStyledButton(loginSuccess ? "Cerrar Sesión" : "Iniciar Sesión", buttonStyle);
        btnLogin.setOnAction(e -> {
            if (loginSuccess) {
                loginSuccess = false;
                user = null;
                password = null;
                listaSoldadosMineros.clear();
                showMainMenu();
            } else {
                showLoginScreen();
            }
        });

        Button btnOperacion = createStyledButton("Comenzar Operación", buttonStyle);
        btnOperacion.setDisable(!loginSuccess);
        btnOperacion.setOnAction(e -> showOperationScreen());

        Button btnMaquinaria = createStyledButton("Gestión de Maquinaria", buttonStyle);
        btnMaquinaria.setDisable(!loginSuccess);
        btnMaquinaria.setOnAction(e -> showMaquinariaScreen());

        Button btnListado = createStyledButton("Listado de Operaciones", buttonStyle);
        btnListado.setDisable(!loginSuccess);
        btnListado.setOnAction(e -> showListadoOperaciones());

        buttonBox.getChildren().addAll(btnLogin, btnOperacion, btnMaquinaria, btnListado);

        root.setTop(menuBar);
        root.setCenter(buttonBox);

        Scene scene = new Scene(root, 800, 600);
        navigateTo(scene);
    }

    private Button createStyledButton(String text, String style) {
        Button button = new Button(text);
        button.setStyle(style);
        button.setOnMouseEntered(
                e -> button.setStyle(style + "-fx-effect: dropshadow(three-pass-box, #a0a0ff, 10, 0, 0, 0);"));
        button.setOnMouseExited(e -> button.setStyle(style));
        return button;
    }

    private void showLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25));
        grid.setStyle("-fx-background-color: linear-gradient(to bottom, #2a2a5a, #3a3a7a);");

        Text scenetitle = new Text("Inicio de Sesión");
        scenetitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scenetitle.setFill(Color.WHITE);
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Usuario:");
        userName.setTextFill(Color.WHITE);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setPromptText("Nombre de usuario");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Contraseña:");
        pw.setTextFill(Color.WHITE);
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Contraseña");
        grid.add(pwBox, 1, 2);

        CheckBox registerCheck = new CheckBox("Registrar nuevo usuario");
        registerCheck.setTextFill(Color.LIGHTGRAY);
        grid.add(registerCheck, 1, 3);

        Button btnAction = new Button("Continuar");
        btnAction.setStyle("-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnAction.setOnAction(e -> {
            user = userTextField.getText();
            password = pwBox.getText();
            password=EncryptOperation.encrypt(password, key);

            if (registerCheck.isSelected()) {
                handleRegistration(user, password);
            } else {
                handleLogin(user, password);
            }
        });

        Button btnBack = new Button("Volver");
        btnBack.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");
        btnBack.setOnAction(e -> goBack());

        HBox buttonBox = new HBox(10, btnBack, btnAction);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 1, 4);

        Scene scene = new Scene(grid, 600, 400);
        navigateTo(scene);
    }

    private void handleLogin(String username, String password) {
        try {
            // Verificar campos vacíos
            if (username == null || username.trim().isEmpty() ||
                    password == null || password.trim().isEmpty()) {
                showAlert("Error", "Usuario y contraseña son requeridos", "error");
                return;
            }
            System.out.println(password);
            // Intentar login con UserSystem
            boolean loginResult = UserSystem.login(username, DecryptOperation.decryptText(password, key));

            if (loginResult) {
                loginSuccess = true;
                this.user = username;
                this.password = password;
                showAlert("Éxito", "Inicio de sesión exitoso para " + username, "success");
                showMainMenu();
            } else {
                showAlert("Error", "Credenciales incorrectas o usuario no existe", "error");
            }
        } catch (Exception e) {
            showAlert("Error", "Error durante el login: " + e.getMessage(), "error");
            e.printStackTrace();
        }
    }

    private void handleRegistration(String username, String password) {
        try {
            // Verificar campos vacíos
            if (username == null || username.trim().isEmpty() ||
                    password == null || password.trim().isEmpty()) {
                showAlert("Error", "Usuario y contraseña son requeridos", "error");
                return;
            }

            // Intentar registro con UserSystem
            boolean registerResult = UserSystem.register(username, DecryptOperation.decryptText(password, key), key);

            if (registerResult) {
                loginSuccess = true;
                this.user = username;
                this.password = password;
                showAlert("Éxito", "Usuario " + username + " registrado correctamente", "success");
                showMainMenu();
            } else {
                showAlert("Error", "No se pudo registrar el usuario (¿ya existe?)", "error");
            }
        } catch (Exception e) {
            showAlert("Error", "Error durante el registro: " + e.getMessage(), "error");
            e.printStackTrace();
        }
    }

    private void showOperationScreen() {
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: #2a2a5a;");

        // Configuración
        Tab configTab = new Tab("Configuración");
        configTab.setContent(createConfigTab());
        configTab.setClosable(false);

        // Personal
        Tab personnelTab = new Tab("Personal");
        personnelTab.setContent(createPersonnelTab());
        personnelTab.setClosable(false);

        // Resultados
        Tab resultsTab = new Tab("Resultados");
        resultsTab.setContent(createResultsTab());
        resultsTab.setClosable(false);

        tabPane.getTabs().addAll(configTab, personnelTab, resultsTab);

        Button btnBack = new Button("Volver al Menú Principal");
        btnBack.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");
        btnBack.setOnAction(e -> showMainMenu());

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(tabPane, btnBack);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #2a2a5a, #3a3a7a);");

        Scene scene = new Scene(mainLayout, 900, 650);
        navigateTo(scene);
    }

    private GridPane createConfigTab() {
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: transparent;");

        // Controles para configuración de operación
        Label lblAliens = new Label("Número esperado de alienígenas:");
        lblAliens.setTextFill(Color.WHITE);
        spnAliens = new Spinner<>(0, 100, 0);
        spnAliens.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        Label lblDistancia = new Label("Distancia en años luz:");
        lblDistancia.setTextFill(Color.WHITE);
        txtDistancia = new TextField();
        txtDistancia.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        Label lblAerocars = new Label("Aerocars adicionales:");
        lblAerocars.setTextFill(Color.WHITE);
        spnAerocars = new Spinner<>(0, 10, 0);
        spnAerocars.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        grid.add(lblAliens, 0, 0);
        grid.add(spnAliens, 1, 0);
        grid.add(lblDistancia, 0, 1);
        grid.add(txtDistancia, 1, 1);
        grid.add(lblAerocars, 0, 2);
        grid.add(spnAerocars, 1, 2);

        return grid;
    }

    private VBox createPersonnelTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: transparent;");

        // Tabla para mostrar personal
        TableView<Entidad> table = new TableView<>();
        table.setItems(listaSoldadosMineros);
        table.setStyle("-fx-background-color: #3a3a7a;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Entidad, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nameCol.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        TableColumn<Entidad, String> typeCol = new TableColumn<>("Tipo");
        typeCol.setCellValueFactory(cellData -> {
            Entidad entidad = cellData.getValue();
            if (entidad instanceof Soldado) {
                return new javafx.beans.property.SimpleStringProperty("Soldado");
            } else {
                return new javafx.beans.property.SimpleStringProperty("Minero");
            }
        });
        typeCol.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        table.getColumns().addAll(nameCol, typeCol);
        table.setPrefHeight(400);

        // Botones para añadir personal
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button btnAddSoldier = createStyledButton("Añadir Soldado",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnAddSoldier.setOnAction(e -> showAddSoldierDialog());

        Button btnAddMiner = createStyledButton("Añadir Minero",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnAddMiner.setOnAction(e -> showAddMinerDialog());

        Button btnAutoFill = createStyledButton("Rellenar Automático",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnAutoFill.setOnAction(e -> autoFillPersonnel());

        buttonBox.getChildren().addAll(btnAddSoldier, btnAddMiner, btnAutoFill);

        vbox.getChildren().addAll(table, buttonBox);
        return vbox;
    }

    private void autoFillPersonnel() {
        listaSoldadosMineros.clear();
        int numAliens = spnAliens.getValue();

        if (numAliens <= 0) {
            showAlert("Error", "Debe haber al menos 1 alienígena", "error");
            return;
        }

        // Calcular cantidad necesaria (2 soldados y 2 mineros por alien)
        int numSoldados = numAliens * 2;
        int numMineros = numAliens * 2;

        // Nombres predefinidos (del ejemplo CLI)
        String[] nombresSoldados = { "Eduvigis Cambil", "Julia García", "Pascual Soler", "Adrián Ramírez" };
        String[] nombresMineros = { "Marcial Rodríguez", "Primo Gil", "Ángeles Núñez", "Consuelo Morales" };
        String[] rangos = { "Comandante", "Veterano", "Miembro" };
        String[] generos = { "Masculino", "Femenino", "Otro" };

        Random random = new Random();

        // Generar soldados (2 por alien)
        for (int i = 0; i < numSoldados; i++) {
            String nombre = nombresSoldados[i % nombresSoldados.length];
            String rango = rangos[random.nextInt(rangos.length)];
            listaSoldadosMineros.add(new Soldado(nombre, rango));
        }

        // Generar mineros (2 por alien)
        for (int i = 0; i < numMineros; i++) {
            String nombre = nombresMineros[i % nombresMineros.length];
            int edad = 20 + random.nextInt(40); // Edad entre 20 y 59
            String genero = generos[random.nextInt(generos.length)];
            listaSoldadosMineros.add(new Minero(nombre, edad, genero));
        }

        showAlert("Éxito",
                "Se han generado:\n" +
                        "- " + numSoldados + " soldados\n" +
                        "- " + numMineros + " mineros\n" +
                        "(2 de cada por cada alienígena)",
                "success");
    }

    private VBox createResultsTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: transparent;");

        txtResults = new TextArea();
        txtResults.setEditable(false);
        txtResults.setStyle("-fx-control-inner-background: #3a3a7a; -fx-text-fill: white; -fx-font-family: monospace;");
        txtResults.setPrefHeight(400);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button btnCalculate = createStyledButton("Calcular Costes",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnCalculate.setOnAction(e -> {
            String results = calculateCosts();
            txtResults.setText(results);
        });

        Button btnSave = createStyledButton("Guardar Operación",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        btnSave.setOnAction(e -> {
            saveOperation(txtResults.getText());
            showAlert("Éxito", "Operación guardada correctamente", "success");
        });

        buttonBox.getChildren().addAll(btnCalculate, btnSave);
        vbox.getChildren().addAll(txtResults, buttonBox);
        return vbox;
    }

    private void showAddSoldierDialog() {
        Dialog<Soldado> dialog = new Dialog<>();
        dialog.setTitle("Añadir Soldado");
        dialog.setHeaderText("Introduce los datos del nuevo soldado");
        dialog.getDialogPane().setStyle("-fx-background-color: #3a3a7a;");

        ButtonType addButton = new ButtonType("Añadir", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: transparent;");

        Label lblName = new Label("Nombre:");
        lblName.setTextFill(Color.WHITE);
        TextField txtName = new TextField();
        txtName.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        Label lblRank = new Label("Rango:");
        lblRank.setTextFill(Color.WHITE);
        TextField txtRank = new TextField();
        txtRank.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        grid.add(lblName, 0, 0);
        grid.add(txtName, 1, 0);
        grid.add(lblRank, 0, 1);
        grid.add(txtRank, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new Soldado(txtName.getText(), txtRank.getText());
            }
            return null;
        });

        Optional<Soldado> result = dialog.showAndWait();
        result.ifPresent(soldado -> {
            listaSoldadosMineros.add(soldado);
            showAlert("Éxito", "Soldado añadido correctamente", "success");
        });
    }

    private void showAddMinerDialog() {
        Dialog<Minero> dialog = new Dialog<>();
        dialog.setTitle("Añadir Minero");
        dialog.setHeaderText("Introduce los datos del nuevo minero");
        dialog.getDialogPane().setStyle("-fx-background-color: #3a3a7a;");

        ButtonType addButton = new ButtonType("Añadir", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: transparent;");

        Label lblName = new Label("Nombre:");
        lblName.setTextFill(Color.WHITE);
        TextField txtName = new TextField();
        txtName.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        Label lblAge = new Label("Edad:");
        lblAge.setTextFill(Color.WHITE);
        Spinner<Integer> spnAge = new Spinner<>(18, 100, 25);
        spnAge.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        Label lblGender = new Label("Género:");
        lblGender.setTextFill(Color.WHITE);
        TextField txtGender = new TextField();
        txtGender.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");

        grid.add(lblName, 0, 0);
        grid.add(txtName, 1, 0);
        grid.add(lblAge, 0, 1);
        grid.add(spnAge, 1, 1);
        grid.add(lblGender, 0, 2);
        grid.add(txtGender, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new Minero(txtName.getText(), spnAge.getValue(), txtGender.getText());
            }
            return null;
        });

        Optional<Minero> result = dialog.showAndWait();
        result.ifPresent(minero -> {
            listaSoldadosMineros.add(minero);
            showAlert("Éxito", "Minero añadido correctamente", "success");
        });
    }

    private String calculateCosts() {
        int numAliens = spnAliens.getValue();
        double distancia = 0;
        try {
            distancia = Double.parseDouble(txtDistancia.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Distancia inválida", "error");
            return "";
        }

        int numAerocars = spnAerocars.getValue();

        // Añadir aerocars adicionales
        for (int i = 0; i < numAerocars; i++) {
            listaAeroCars.add(new AeroCars());
        }

        int numSoldados = (int) listaSoldadosMineros.stream().filter(s -> s instanceof Soldado).count();
        int numMineros = (int) listaSoldadosMineros.stream().filter(s -> s instanceof Minero).count();

        double yursSoldados = numSoldados * 22;
        double yursMineros = numMineros * 20;
        double yursAerocars = listaAeroCars.size() * 150;
        double raizCuadradaDistancia = Math.sqrt(distancia);
        double yursDistancia = raizCuadradaDistancia * 12 * listaAeroCars.size();

        StringBuilder resultado = new StringBuilder();
        resultado.append("=== RESULTADOS DE OPERACIÓN ===\n\n");
        resultado.append("Distancia de la nave DKW-RR.3: ").append(distancia).append(" años luz\n");
        resultado.append("Número de alienígenas: ").append(numAliens).append(" + ").append(numAliens * 4)
                .append(" yurs\n");
        resultado.append("Número de soldados: ").append(numSoldados).append(" + ").append(yursSoldados)
                .append(" yurs\n");
        resultado.append("Número de mineros: ").append(numMineros).append(" + ").append(yursMineros).append(" yurs\n");
        resultado.append("Número de aerocars: ").append(listaAeroCars.size()).append(" + ").append(yursAerocars)
                .append(" yurs\n");
        resultado.append("Coste por distancia: ").append(yursDistancia).append(" yurs\n\n");
        resultado.append("TOTAL: ").append(yursSoldados + yursMineros + yursAerocars + yursDistancia + (numAliens * 4))
                .append(" yurs");

        return resultado.toString();
    }

    private void saveOperation(String operationData) {
        try {
            Path userDir = Paths.get("UserFiles", user);
            if (!Files.exists(userDir)) {
                Files.createDirectories(userDir);
            }

            // Guardar contenido encriptado
            String encryptedResult = EncryptOperation.encrypt(operationData,
                    DecryptOperation.decryptText(password, key));
            Files.write(userDir.resolve("costesOperacion.txt"), encryptedResult.getBytes());

        } catch (Exception e) {
            showAlert("Error", "No se pudo guardar la operación: " + e.getMessage(), "error");
        }
    }

 
    private void showMaquinariaScreen() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #2a2a5a, #3a3a7a);");
    
        // Título
        Label title = new Label("Gestión de Maquinaria");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);
        
        // Opción para elegir entre SQL y BIN
        Label lblOption = new Label("Seleccione el tipo de almacenamiento:");
        lblOption.setTextFill(Color.WHITE);
        
        ToggleGroup storageGroup = new ToggleGroup();
        RadioButton rbSQL = new RadioButton("SQL");
        RadioButton rbBIN = new RadioButton("BIN");
        rbSQL.setToggleGroup(storageGroup);
        rbBIN.setToggleGroup(storageGroup);
        rbSQL.setTextFill(Color.WHITE);
        rbBIN.setTextFill(Color.WHITE);
        
        HBox optionBox = new HBox(15, rbSQL, rbBIN);
        optionBox.setAlignment(Pos.CENTER);
    
        // Panel para opciones de maquinaria (inicialmente oculto)
        VBox machineOptions = new VBox(15);
        machineOptions.setAlignment(Pos.CENTER);
        machineOptions.setVisible(false);
        
        Button btnShowMachines = createStyledButton("Mostrar Maquinaria",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
        
        Button btnModifyMachines = createStyledButton("Modificar Maquinaria",
                "-fx-background-color: #5a5a9a; -fx-text-fill: white;");
    
        Button btnBack = createStyledButton("Volver", "-fx-background-color: #4a4a8a; -fx-text-fill: white;");
        btnBack.setOnAction(e -> goBack());
    
        // Manejar selección de tipo de almacenamiento
        storageGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            machineOptions.setVisible(true);
            
            if (newVal == rbSQL) {
                btnShowMachines.setOnAction(e -> {
                    SQLUtil.mostrarMaquinaria();
                    showAlert("Información", "Mostrando maquinaria desde SQL", "info");
                });
                
                btnModifyMachines.setOnAction(e -> showModifyMachineDialog(true));
            } else if (newVal == rbBIN) {
                btnShowMachines.setOnAction(e -> {
                    String maquinaria = IABob.mostrarMaquinaria();
                    TextArea textArea = new TextArea(maquinaria);
                    textArea.setEditable(false);
                    textArea.setStyle("-fx-control-inner-background: #3a3a7a; -fx-text-fill: white; -fx-font-family: monospace;");
                    
                    Stage stage = new Stage();
                    VBox root = new VBox(textArea);
                    root.setStyle("-fx-background-color: #2a2a5a;");
                    stage.setScene(new Scene(root, 600, 400));
                    stage.setTitle("Maquinaria BIN");
                    stage.show();
                });
                
                btnModifyMachines.setOnAction(e -> showModifyMachineDialog(false));
            }
        });
    
        machineOptions.getChildren().addAll(btnShowMachines, btnModifyMachines);
        layout.getChildren().addAll(title, lblOption, optionBox, machineOptions, btnBack);
    
        Scene scene = new Scene(layout, 500, 400);
        navigateTo(scene);
    }
    
    private void showModifyMachineDialog(boolean isSQL) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Modificar Maquinaria");
        dialog.setHeaderText("Seleccione el tipo de maquinaria a modificar");
        dialog.getDialogPane().setStyle("-fx-background-color: #3a3a7a;");
    
        ButtonType continueButton = new ButtonType("Continuar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(continueButton, ButtonType.CANCEL);
    
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: transparent;");
    
        Label lblType = new Label("Tipo de maquinaria:");
        lblType.setTextFill(Color.WHITE);
        
        ComboBox<String> cbType = new ComboBox<>();
        if (isSQL) {
            cbType.getItems().addAll("1. Ciberexcavadora", "2. Martillo", "3. Cibercompresor");
        } else {
            cbType.getItems().addAll("1. Pala", "2. Ciberexcavadora", "3. Martillo", "4. Cibercompresor");
        }
        cbType.setStyle("-fx-background-color: #4a4a8a; -fx-text-fill: white;");
    
        grid.add(lblType, 0, 0);
        grid.add(cbType, 1, 0);
    
        dialog.getDialogPane().setContent(grid);
    
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == continueButton) {
                String selectedType = cbType.getValue();
                if (selectedType != null) {
                    String typeNumber = selectedType.substring(0, 1);
                    if (isSQL) {
                        SQLUtil.modificarObjeto();
                    } else {
                        IABob.modificarObjeto();
                    }
                }
            }
            return null;
        });
    
        dialog.showAndWait();
    }
    
    

    private void showListadoOperaciones() {
        try {
            String filePath = ".\\UserFiles\\"+user+"\\costesOperacion.txt";
            if (filePath!=null) {
                String content = DecryptOperation.decryptParaGUI(DecryptOperation.decryptText(password, key), filePath);

                TextArea textArea = new TextArea(content);
                textArea.setEditable(false);
                textArea.setStyle(
                        "-fx-control-inner-background: #3a3a7a; -fx-text-fill: white; -fx-font-family: monospace;");

                Button btnBack = createStyledButton("Volver", "-fx-background-color: #4a4a8a; -fx-text-fill: white;");
                btnBack.setOnAction(e -> goBack());

                VBox layout = new VBox(15, textArea, btnBack);
                layout.setPadding(new Insets(20));
                layout.setStyle("-fx-background-color: linear-gradient(to bottom, #2a2a5a, #3a3a7a);");

                Scene scene = new Scene(layout, 700, 500);
                navigateTo(scene);
            } else {
                showAlert("Error", "No hay operaciones guardadas", "error");
            }
        } catch (Exception e) {
            showAlert("Error", "No se pudo leer el archivo: " + e.getMessage(), "error");
        }
    }

    private void showAlert(String title, String message, String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Estilo basado en el tipo
        switch (type.toLowerCase()) {
            case "error":
                alert.getDialogPane().setStyle("-fx-background-color: #5a2a2a;");
                break;
            case "success":
                alert.getDialogPane().setStyle("-fx-background-color: #2a5a2a;");
                break;
            default:
                alert.getDialogPane().setStyle("-fx-background-color: #3a3a7a;");
        }

        alert.showAndWait();
    }

    private void navigateTo(Scene newScene) {
        if (primaryStage.getScene() != null) {
            sceneHistory.push(primaryStage.getScene());
        }
        primaryStage.setScene(newScene);
    }

    private void goBack() {
        if (!sceneHistory.isEmpty()) {
            primaryStage.setScene(sceneHistory.pop());
        }
    }
}