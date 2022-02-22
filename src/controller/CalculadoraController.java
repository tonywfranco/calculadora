package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class CalculadoraController implements Initializable {

    @FXML
    public Button btUm;

    @FXML
    public Button btDois;

    @FXML
    public Button btTres;

    @FXML
    public Button btQuatro;

    @FXML
    public Button btCinco;

    @FXML
    public Button btSeis;

    @FXML
    public Button btSete;

    @FXML
    public Button btOito;

    @FXML
    public Button btNove;

    @FXML
    public Button btZero;

    @FXML
    public Button btZero2;

    @FXML
    public TextField txVisor;

    @FXML
    public Button btSoma;

    @FXML
    public Button btResult;

    @FXML
    public Button btClear;

    @FXML
    public Button btSubtracao;

    @FXML
    public Button btMult;

    @FXML
    public Button btDivisao;

    @FXML
    public Button btApagar;

    @FXML
    public Button btInverte;

    @FXML
    public Button btPonto;

    @FXML
    public Button btPercentual;

    @FXML
    public Button btRaizQuad;

    @FXML
    public Button btPotencia;

    @FXML
    private Text txAviso;

    @FXML
    public Button btMemoMais;

    @FXML
    public Text txMemoTotal;

    @FXML
    public Button btMemoTotal;

    @FXML
    public Button btMemoMenos;

    @FXML
    private Text txM;

    @FXML
    private Text txHelp;

    private static String visor;
    private boolean sub, div, soma, subtracao, multiplicacao, divisao, apagar, potencia, resultado;
    BigDecimal den1, memomais, memomenos;
    BigDecimal total = new BigDecimal(0.0);
    BigDecimal memototal = new BigDecimal(0.0);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txVisor.setEditable(false);
        txVisor.setText("0");
        txVisor.setFont(Font.font("digital-7", 50));
        txMemoTotal.setFont(Font.font("digital-7", 30));
        txM.setFont(Font.font("verdana", 24));

        adicionarNumVisorMouse(btUm);
        adicionarNumVisorMouse(btDois);
        adicionarNumVisorMouse(btTres);
        adicionarNumVisorMouse(btQuatro);
        adicionarNumVisorMouse(btCinco);
        adicionarNumVisorMouse(btSeis);
        adicionarNumVisorMouse(btSete);
        adicionarNumVisorMouse(btOito);
        adicionarNumVisorMouse(btNove);
        adicionarNumVisorMouse(btZero);
        adicionarNumVisorMouse(btZero2);

        semFoco(btClear);
        semFoco(btMemoTotal);
        semFoco(btMemoMais);
        semFoco(btMemoMenos);
        semFoco(btMult);
        semFoco(btSoma);
        semFoco(btSubtracao);
        semFoco(btDivisao);
        semFoco(btRaizQuad);
        semFoco(btResult);
        semFoco(btPotencia);
        semFoco(btPonto);
        semFoco(btPercentual);
        semFoco(btApagar);
        semFoco(btInverte);

        btSoma.setOnAction((event) -> {
            prepararSoma();
        });

        btSubtracao.setOnAction((event) -> {
            prepararSubtracao();
        });

        btMult.setOnAction((event) -> {
            prepararMultiplicacao();
        });

        btDivisao.setOnAction((event) -> {
            prepararDivisao();
        });

        btClear.setOnAction((event) -> {
            clearVisor();
        });

        btResult.setOnAction((event) -> {
            mostrarResultado();
        });

        btPotencia.setOnAction((event) -> {
            prepararPotencia();
        });

        btMemoMais.setOnAction((event) -> {
            memoriaMais();
        });

        btMemoMenos.setOnAction((event) -> {
            memoriaMenos();
        });

        btMemoTotal.setOnAction((event) -> {
            memototal = new BigDecimal(0.0);
            txMemoTotal.setText("");
            txM.setText("");
        });

        btPercentual.setOnAction((event) -> {
            percentualNumVisor();
        });

        btRaizQuad.setOnAction((event) -> {
            raizQuadNumVisor();
        });

        btInverte.setOnAction((event) -> {
            inverteSinalNumVisor();
        });

        btPonto.setOnAction((event) -> {
            adicionarPontoVisor();
        });

        btApagar.setOnAction((event) -> {
            apagarNumVisor();
        });

        txHelp.setOnMouseClicked((event) -> {
            mensagemAutoria();
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                } catch (Exception e) {
                }
            }
        });
    }

    public void clearVisor() {
        den1 = new BigDecimal(0.0);
        total = new BigDecimal(0.0);
        visor = null;
        txVisor.setText("0");
        txAviso.setText("");
        resultado = false;
    }

    private void semFoco(Button button) {
        button.setFocusTraversable(false);
    }

    private void adicionarNumVisorMouse(Button button) {
        button.setOnMouseClicked((event) -> {

            if (visor == null) {
                visor = button.getText();
                txVisor.setText(formatarNumVisor(visor));
                txAviso.setText("");
            } else {
                int a = visor.indexOf(".");
                int b = visor.indexOf("0");
                if (a == -1) {
                    if (visor.length() == 15) {
                        //
                    } else {
                        if (b == 0) {
                            visor = visor.substring(1, 1);
                        }
                        visor = visor + button.getText();
                        txVisor.setText(formatarNumVisor(visor));
                        txAviso.setText("");
                    }
                } else {
                    if (visor.length() == 19) {
                        //
                    } else {
                        if (visor.length() == a + 6) {
                            //
                        } else {
                            visor = visor + button.getText();
                            txVisor.setText(formatarNumVisor(visor));
                            txAviso.setText("");
                        }
                    }
                }

            }
        });
        button.setFocusTraversable(false);
    }

    public void adicionarNumVisorKeyboard(KeyEvent ke) {

        if (visor == null) {
            visor = ke.getText();
            txVisor.setText(formatarNumVisor(visor));
            txAviso.setText("");
        } else {
            int a = visor.indexOf(".");
            int b = visor.indexOf("0");
            if (a == -1) {
                if (visor.length() == 15) {
                    //
                } else {
                    if (b == 0) {
                        visor = visor.substring(1, 1);
                    }
                    visor = visor + ke.getText();
                    txVisor.setText(formatarNumVisor(visor));
                    txAviso.setText("");
                }
            } else {
                if (visor.length() == 19) {
                    //
                } else {
                    if (visor.length() == a + 6) {
                        //
                    } else {
                        visor = visor + ke.getText();
                        txVisor.setText(formatarNumVisor(visor));
                        txAviso.setText("");
                    }
                }
            }

        }
    }

    public void adicionarPontoVisor() {
        if (potencia) {
            //
        } else {
            if (visor == null) {
                visor = "0.";
                txVisor.setText("0.");
            } else {
                int a = visor.indexOf(".");
                if (a != -1) {
                    //
                } else {
                    if (visor.length() == 15) {
                        //
                    } else {
                        visor = visor + ".";
                        txVisor.setText(formatarNumVisor(visor));
                    }
                }
            }
        }
    }

    public void apagarNumVisor() {
        if (apagar) {
            visor = total.toString();
            total = new BigDecimal(0.0);
            apagar = false;
        }
        if (resultado) {
            visor = txVisor.getText();
        }
        try {
            int a = visor.length();
            double valor = Double.valueOf(visor);

            if (valor < 0 && a == 2) {
                visor = visor.substring(0, a - 2);
            } else {
                visor = visor.substring(0, a - 1);
            }

            if (visor.isEmpty()) {
                txVisor.setText("0");
                visor = null;
            } else {
                int b = visor.indexOf(".");
                if (b == -1) {
                    txVisor.setText(formatarNumVisor(visor));
                } else if (visor.length() == b + 1) {
                    txVisor.setText(formatarNumVisor(visor));
                } else {
                    txVisor.setText(formatarNumVisor(visor));
                }
            }
        } catch (Exception f) {
        }
    }

    public void percentualNumVisor() {
        try {
            if (total.compareTo(new BigDecimal(0.0)) == 0) {
                //
            } else {
                den1 = new BigDecimal(visor);
                BigDecimal perc = new BigDecimal(100);

                if (soma) {
                    try {
                        BigDecimal den2 = total.multiply(den1.divide(perc));
                        total = total.add(den2);
                    } catch (Exception e) {
                    }
                } else if (subtracao) {
                    try {
                        BigDecimal den2 = total.multiply(den1.divide(perc));
                        total = total.subtract(den2);
                    } catch (Exception e) {
                    }
                } else if (multiplicacao) {
                    try {
                        BigDecimal den2 = den1.divide(perc);
                        total = total.multiply(den2);
                    } catch (Exception e) {
                    }
                } else if (divisao) {
                    try {
                        BigDecimal den2 = den1.divide(perc);
                        total = total.divide(den2, 2, RoundingMode.HALF_EVEN);
                    } catch (Exception e) {
                    }
                }

                den1 = new BigDecimal(0.0);
                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                soma = false;
                subtracao = false;
                multiplicacao = false;
                divisao = false;
                resultado = true; //NECESSÁRIO PARA INVERTER O SINAL
            }
        } catch (Exception e) {
        }
    }

    private void raizQuadNumVisor() { // NÃO EXISTE RAIZ QUADRADA NEGATIVA
        try {
            if (potencia) {
                visor = total.toString();
                potencia = false;
            }
            if (txVisor.getText().isEmpty()) {
                //
            } else {
                if (soma) {
                    prepararSoma();
                } else if (subtracao) {
                    prepararSubtracao();
                } else if (multiplicacao) {
                    prepararMultiplicacao();
                } else if (divisao) {
                    prepararDivisao();
                }

                visor = txVisor.getText();
                total = new BigDecimal(visor);

                if (total.compareTo(new BigDecimal(0.0)) == 1) {
                    double a = Math.sqrt(total.doubleValue());
                    visor = Double.toString(a);
                    if (visor.length() > 7) {
                        visor = visor.substring(0, 7);
                    }
                    txVisor.setText(formatarNumVisor(visor));
                    total = new BigDecimal(visor);
                    visor = null;
                    resultado = true;
                } else if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    //
                } else {
                    if (resultado) {
                        visor = txVisor.getText();
                        total = new BigDecimal(visor);
                    } else {
                        txVisor.setText(formatarNumVisor(visor));
                        total = new BigDecimal(visor);
                    }

                    visor = null;
                    resultado = true;
                    txAviso.setFont(Font.font("digital-7", 20));
                    txAviso.setText("Raiz ² de número (-) não existe!");
                }
            }
        } catch (Exception e) {
        }

        soma = false;
        subtracao = false;
        multiplicacao = false;
        divisao = false;
    }

    private void inverteSinalNumVisor() {

        try {
            if (resultado) {
                visor = txVisor.getText();
            }
            if (visor == null) {
                //
            } else {
                try {
                    visor = (txVisor.getText()).replace("'", "");

                    if (visor.contains(".")) { // CONTÉM VÍRGULA
                        BigDecimal inv = new BigDecimal(-1.0);
                        den1 = new BigDecimal(visor).multiply(inv);
                        visor = den1.toString();
                    } else { // NÃO CONTÉM VÍRGULA
                        BigDecimal inv = new BigDecimal(-1.0);
                        den1 = new BigDecimal(visor).multiply(inv);
                        visor = den1.toString();
                    }
                    txVisor.setText(formatarNumVisor(visor));
                    txAviso.setText("");
                } catch (Exception f) {
                }
            }
        } catch (Exception e) {
        }
    }

    public void prepararSoma() {
        if (subtracao || multiplicacao || divisao) {
            subtracao = false;
            multiplicacao = false;
            divisao = false;
        } else {

            try {
                if (resultado) {
                    den1 = new BigDecimal(visor);
                    total = den1;
                } else {
                    den1 = new BigDecimal(visor);
                    total = total.add(den1);
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
                resultado = false;
            } catch (Exception e) {
            }

        }

        soma = true;
    }

    public void prepararSubtracao() {
        if (soma || multiplicacao || divisao) {
            soma = false;
            multiplicacao = false;
            divisao = false;
        } else {

            try {

                if (resultado) {
                    den1 = new BigDecimal(visor);
                    total = den1;
                } else {
                    den1 = new BigDecimal(visor);
                    if (total.compareTo(new BigDecimal(0.0)) == 0) {
                        if (sub) {
                            total = total.subtract(den1);
                            sub = false;
                        } else {
                            total = den1;
                        }
                    } else if (den1.compareTo(total) == 0) {
                        total = total.subtract(den1);
                    } else if (total.compareTo(den1) == 1) {
                        total = total.subtract(den1);
                    } else if (total.compareTo(den1) == -1) {
                        total = total.subtract(den1);
                    }
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    sub = true;
                }
                resultado = false;
            } catch (Exception e) {
            }

        }

        subtracao = true;
    }

    public void prepararMultiplicacao() {
        if (soma || subtracao || divisao) {
            soma = false;
            subtracao = false;
            divisao = false;
        } else {

            try {
                if (resultado) {
                    den1 = new BigDecimal(visor);
                    total = den1;
                } else {
                    den1 = new BigDecimal(visor);
                    if (total.compareTo(new BigDecimal(0.0)) == 0) {
                        total = den1;
                    } else {
                        total = total.multiply(den1);
                    }
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
                resultado = false;
            } catch (Exception e) {
            }

        }

        multiplicacao = true;
    }

    public void prepararDivisao() {
        if (soma || subtracao || multiplicacao) {
            soma = false;
            subtracao = false;
            multiplicacao = false;
        } else {

            try {
                if (resultado) {
                    den1 = new BigDecimal(visor);
                    total = den1;
                } else {
                    den1 = new BigDecimal(visor);
                    if (total.compareTo(new BigDecimal(0.0)) == 0) {

                        if (div) {
                            if (total.compareTo(den1) == 0) {
                                total = new BigDecimal(0.0);
                            } else if (den1.compareTo(new BigDecimal(0.0)) == 0) {
                                total = new BigDecimal(0.0);

                            } else {
                                total = new BigDecimal(0.0);
                            }

                            div = false;
                        } else {
                            total = den1; // primeiro vez é 0...
                            div = false;
                        }
                    } else if (den1.compareTo(new BigDecimal(0.0)) == 0) {
                        total = new BigDecimal(0.0);
                    } else {
                        total = total.divide(den1, 2, BigDecimal.ROUND_HALF_UP);
                    }
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    div = true;
                }
                resultado = false;
            } catch (Exception e) {
            }

        }

        divisao = true;
    }

    public void prepararPotencia() {
        try {
            den1 = new BigDecimal(visor);
            total = total.add(den1);
            visor = total.toString();
            txVisor.setText(formatarNumVisor(visor));
            visor = null;
            den1 = new BigDecimal(0.0);
        } catch (Exception e) {
        }

        potencia = true;
        soma = false;
        subtracao = false;
        multiplicacao = false;
        divisao = false;
    }

    public void mostrarResultado() {
        if (soma) {
            try {
                den1 = new BigDecimal(visor);
                total = total.add(den1);
                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
            } catch (Exception e) {
            }
        } else if (subtracao) {
            try {
                den1 = new BigDecimal(visor);
                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    if (sub) {
                        total = total.subtract(den1);
                        sub = false;
                    } else {
                        total = den1;
                    }
                } else if (den1.compareTo(total) == 0) {
                    total = total.subtract(den1);
                } else if (total.compareTo(den1) == 1) {
                    total = total.subtract(den1);
                } else if (total.compareTo(den1) == -1) {
                    total = total.subtract(den1);
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);

                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    sub = true;
                }
            } catch (Exception e) {
            }
        } else if (multiplicacao) {
            try {
                den1 = new BigDecimal(visor);

                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    total = den1;
                } else {
                    total = total.multiply(den1);
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
            } catch (Exception e) {
            }
        } else if (divisao) {
            try {
                den1 = new BigDecimal(visor);

                if (total.compareTo(new BigDecimal(0.0)) == 0) {

                    if (div) {
                        if (total.compareTo(den1) == 0) {
                            total = new BigDecimal(0.0);
                        } else if (den1.compareTo(new BigDecimal(0.0)) == 0) {
                            total = new BigDecimal(0.0);
                        } else {
                            total = new BigDecimal(0.0);
                        }

                        div = false;
                    } else {
                        total = den1; // primeiro vez é 0...
                        div = false;
                    }
                } else if (den1.compareTo(new BigDecimal(0.0)) == 0) {
                    total = new BigDecimal(0.0);
                } else {
                    total = total.divide(den1, 2, BigDecimal.ROUND_HALF_UP);
                }

                visor = total.toString();
                txVisor.setText(formatarNumVisor(visor));
                visor = null;
                den1 = new BigDecimal(0.0);
                if (total.compareTo(new BigDecimal(0.0)) == 0) {
                    div = true;
                }
            } catch (Exception e) {
            }
        } else if (potencia) {
            try {
                den1 = new BigDecimal(visor);
                int a = den1.intValue();

                if (total.compareTo(new BigDecimal(59.0)) == 1 || den1.compareTo(new BigDecimal(9.0)) == 1) {
                    txAviso.setFont(Font.font("digital-7", 20));
                    txAviso.setText("Erro! Maximo: 59^9");
                    txAviso.setTextAlignment(TextAlignment.RIGHT);
                    total = new BigDecimal(0.0);
                    den1 = new BigDecimal(0.0);
                    txVisor.setText(den1.toString());
                } else {
                    total = total.pow(a);
                    visor = total.toString();
                    txVisor.setText(formatarNumVisor(visor));
                }

                visor = null;
                den1 = new BigDecimal(0.0);
            } catch (Exception e) {
            }
        }

        if (!soma || !subtracao || !multiplicacao || !divisao || !potencia) {
            //
        } else {
            visor = total.toString();
            txVisor.setText(formatarNumVisor(visor));
            den1 = new BigDecimal(0.0);
            visor = null;
        }

        soma = false;
        subtracao = false;
        multiplicacao = false;
        divisao = false;
        div = false;
        apagar = true;  // ESTÁ CONECTADO AO MÉTODO DE APAGAR NUMEROS DO VISOR...
        resultado = true;
        potencia = false;
    }

    private void memoriaMais() {
        visor = (txVisor.getText()).replace("'", "");
        memomais = new BigDecimal(visor);
        memototal = memototal.add(memomais);
        memomais = new BigDecimal(0.0);
        visor = memototal.toString();
        txMemoTotal.setText(formatarNumVisor(visor));
        txM.setText("M");
        visor = null;
    }

    private void memoriaMenos() {
        visor = (txVisor.getText()).replace("'", "");
        memomenos = new BigDecimal(visor);
        memototal = memototal.subtract(memomenos);
        memomenos = new BigDecimal(0.0);
        visor = memototal.toString();
        txMemoTotal.setText(formatarNumVisor(visor));
        txM.setText("M");
        visor = null;
    }

    private String formatarNumVisor(String auxiliar) {
        BigDecimal bd = new BigDecimal(auxiliar);
        double b = Double.parseDouble(auxiliar);
        int a = auxiliar.indexOf(".");
        StringBuilder stringbuilder = new StringBuilder(auxiliar);

        if (b >= 0.0 && a == -1 && visor.length() == 4) { //// A VÍRGULA NÃO ESTÁ PRESENTE AQUI....
            stringbuilder.insert(1, "'");                 //// VALORES POSITIVOS ....  
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 5) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 6) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 7) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 8) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 9) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 10) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(9, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 11) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 12) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 13) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(9, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(13, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 14) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(14, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 15) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(15, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == -1 && visor.length() == 16) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(9, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(13, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(17, "'");
            auxiliar = stringbuilder.toString();
        }

        if (b < 0.0 && a == -1 && visor.length() == 5) { //// A VÍRGULA NÃO ESTÁ PRESENTE AQUI....
            stringbuilder.insert(2, "'");                //// VALORES NEGATIVOS ....
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 6) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 7) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 8) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 9) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 10) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(8, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 11) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 12) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 13) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(8, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(12, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 14) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(14, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 15) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(15, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == -1 && visor.length() == 16) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(8, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(12, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(16, "'");
            auxiliar = stringbuilder.toString();
        }

        if (b >= 0.0 && a == 4 && visor.length() >= 5) { //// A VÍRGULA ESTÁ PRESENTE AQUI....
            stringbuilder.insert(1, "'");                //// VALORES POSITIVOS ....
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 5 && visor.length() >= 6) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 6 && visor.length() >= 7) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 7 && visor.length() >= 8) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 8 && visor.length() >= 9) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 9 && visor.length() >= 10) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 10 && visor.length() >= 11) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(9, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 11 && visor.length() >= 12) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 12 && visor.length() >= 13) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 13 && visor.length() >= 14) {
            stringbuilder.insert(1, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(5, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(9, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(13, "'");
            auxiliar = stringbuilder.toString();
        } else if (b >= 0.0 && a == 14 && visor.length() >= 15) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(14, "'");
            auxiliar = stringbuilder.toString();
        }

        if (b < 0.0 && a == 5 && visor.length() >= 7) { //// A VÍRGULA ESTÁ PRESENTE AQUI....
            stringbuilder.insert(2, "'");                //// VALORES NEGATIVOS ....
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 6 && visor.length() >= 8) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 7 && visor.length() >= 9) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 8 && visor.length() >= 10) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 9 && visor.length() >= 11) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 10 && visor.length() >= 12) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(8, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 11 && visor.length() >= 13) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 12 && visor.length() >= 14) {
            stringbuilder.insert(3, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(7, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(11, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 13 && visor.length() >= 15) {
            stringbuilder.insert(4, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(8, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(12, "'");
            auxiliar = stringbuilder.toString();
        } else if (b < 0.0 && a == 14 && visor.length() >= 16) {
            stringbuilder.insert(2, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(6, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(10, "'");
            auxiliar = stringbuilder.toString();
            stringbuilder.insert(14, "'");
            auxiliar = stringbuilder.toString();
        }

        return auxiliar;
    }

    public void mensagemAutoria() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Elena Calculadora 1.0");
        alert.setContentText("Trata-se de uma calculadora simples, com 15 dígitos,"
                + "\n" + "para a sobrinha querida tirar a prova real de seus cálculos.\n"
                + "\n" + "Enviar dúvidas/erros/sugestões para o e-mail: 'tonywillyfranco@hotmail.com'.\n"
                + "\n" + "Programado por Tony Franco em Abril/2020.\n");
        alert.getDialogPane().setPrefSize(400, 340);
        alert.showAndWait();
    }
}
