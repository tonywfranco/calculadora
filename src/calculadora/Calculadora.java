package calculadora;

import controller.CalculadoraController;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Tony
 */
public class Calculadora extends Application {

    private Scene scene;
    CalculadoraController controller;

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Font.loadFont(getClass().getResourceAsStream("/fontes/digital-7.ttf"), 80);

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Calculadora.fxml"));

            Parent painel = loader.load();
            controller = loader.getController();

            scene = new Scene(painel);
            stage.setScene(scene);
            stage.setMaximized(false);//janela inteira
            stage.setTitle("Elena Calculadora 1.0");
            stage.setResizable(false);
            stage.show();
            setStage(stage);
            painel.requestFocus();
            controller.txVisor.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), -fx-text-box-border), "
                    + "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);");

            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                if (event.getCode() == KeyCode.DIGIT0) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btZero);
                }
                if (event.getCode() == KeyCode.DIGIT1) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btUm);
                }
                if (event.getCode() == KeyCode.DIGIT2) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btDois);
                }
                if (event.getCode() == KeyCode.DIGIT3) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btTres);
                }
                if (event.getCode() == KeyCode.DIGIT4) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btQuatro);
                }
                if (event.getCode() == KeyCode.DIGIT5 && !event.isShiftDown()) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btCinco);
                }
                if (event.getCode() == KeyCode.DIGIT6) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btSeis);
                }
                if (event.getCode() == KeyCode.DIGIT7) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btSete);
                }
                if (event.getCode() == KeyCode.DIGIT8 && !event.isShiftDown()) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btOito);
                }
                if (event.getCode() == KeyCode.DIGIT9) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btNove);
                }
                if (event.getCode() == KeyCode.NUMPAD0) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btZero);
                }
                if (event.getCode() == KeyCode.NUMPAD1) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btUm);
                }
                if (event.getCode() == KeyCode.NUMPAD2) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btDois);
                }
                if (event.getCode() == KeyCode.NUMPAD3) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btTres);
                }
                if (event.getCode() == KeyCode.NUMPAD4) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btQuatro);
                }
                if (event.getCode() == KeyCode.NUMPAD5 && !event.isShiftDown()) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btCinco);
                }
                if (event.getCode() == KeyCode.NUMPAD6) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btSeis);
                }
                if (event.getCode() == KeyCode.NUMPAD7) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btSete);
                }
                if (event.getCode() == KeyCode.NUMPAD8 && !event.isShiftDown()) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btOito);
                }
                if (event.getCode() == KeyCode.NUMPAD9) {
                    controller.adicionarNumVisorKeyboard(event);
                    efeitos(controller.btNove);
                }
                if (event.getCode() == KeyCode.EQUALS && event.isShiftDown()) {
                    controller.prepararSoma();
                    efeitos(controller.btSoma);
                }
                if (event.getCode() == KeyCode.MINUS) {
                    controller.prepararSubtracao();
                    efeitos(controller.btSubtracao);
                }
                if (event.getCode() == KeyCode.Q && event.isAltDown()) {
                    controller.prepararDivisao();
                    efeitos(controller.btDivisao);
                }
                if (event.getCode() == KeyCode.DIGIT8 && event.isShiftDown()) {
                    controller.prepararMultiplicacao();
                    efeitos(controller.btMult);
                }
                if ((event.getCode() == KeyCode.ENTER) || (event.getCode() == KeyCode.EQUALS && !event.isShiftDown())) {
                    controller.mostrarResultado();
                    efeitos(controller.btResult);
                }
                if (event.getCode() == KeyCode.DEAD_TILDE && event.isShiftDown()) {
                    controller.prepararPotencia();
                    efeitos(controller.btPotencia);
                }
                if (event.getCode() == KeyCode.COMMA) {
                    controller.adicionarPontoVisor();
                    efeitos(controller.btPonto);
                }
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    controller.apagarNumVisor();
                    efeitos(controller.btApagar);
                }
                if (event.getCode() == KeyCode.DIGIT5 && event.isShiftDown()) {
                    controller.percentualNumVisor();
                    efeitos(controller.btPercentual);
                }
                if (event.getCode() == KeyCode.ADD) {
                    controller.prepararSoma();
                    efeitos(controller.btSoma);
                }
                if (event.getCode() == KeyCode.SUBTRACT) {
                    controller.prepararSubtracao();
                    efeitos(controller.btSubtracao);
                }
                if (event.getCode() == KeyCode.MULTIPLY) {
                    controller.prepararMultiplicacao();
                    efeitos(controller.btMult);
                }
                if (event.getCode() == KeyCode.DIVIDE) {
                    controller.prepararDivisao();
                    efeitos(controller.btDivisao);
                }
                if (event.getCode() == KeyCode.DECIMAL) {
                    controller.adicionarPontoVisor();
                    efeitos(controller.btPonto);
                }
                if (event.getCode() == KeyCode.C) {
                    controller.clearVisor();
                    efeitos(controller.btClear);
                }
                if (event.getCode() == KeyCode.H) {
                    controller.mensagemAutoria();
                }
            });
        } catch (IOException e) {
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Calculadora.stage = stage;
    }

    private void efeitos(Button button) {
        button.arm();
        PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
        pause.setOnFinished(evt -> {
            button.disarm();
        });
        pause.play();
    }
}
