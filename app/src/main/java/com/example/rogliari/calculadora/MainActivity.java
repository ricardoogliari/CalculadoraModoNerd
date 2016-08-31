package com.example.rogliari.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado, txtNumeros, txtModoNerd;

    private StringBuffer expressao = new StringBuffer();
    private StringBuffer resultado = new StringBuffer();

    private boolean modoNerd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtNumeros = (TextView) findViewById(R.id.txtNumeros);
        txtModoNerd = (TextView) findViewById(R.id.txtModoNerd);
    }

    public void trataNumero(String nr){
        expressao.append(nr);
        fazConta();
    }

    public void trataOperador(String op){
        if (expressao.charAt(expressao.length() - 1) != ' '){
            expressao.append(" " + op + " ");
            txtNumeros.setText(txtNumeros.getText() + op);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.defaault_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        modoNerd = !modoNerd;

        txtModoNerd.setVisibility(modoNerd?View.VISIBLE:View.GONE);

        if (txtResultado.getText().length() > 0) {
            if (modoNerd) {
                int nr = Integer.parseInt(resultado.toString());
                txtResultado.setText(Integer.toBinaryString(nr));
            } else {
                txtResultado.setText(resultado);
            }
        }

        return true;
    }

    public void fazConta(){
        String[] tokens = expressao.toString().split(" ");
        String op1 = null;
        String operador = null;
        String op2 = null;

        if (tokens.length >= 3) {
            for (String token : tokens) {
                switch (token) {
                    case "+":
                    case "-":
                    case "x":
                    case "/":
                        operador = token;
                        break;
                    default:
                        if (op1 == null) {
                            op1 = token;
                        } else {
                            op2 = token;
                            switch (operador) {
                                case "+":
                                    resultado.replace(0, resultado.length(), "" + (Integer.parseInt(op1) + Integer.parseInt(op2)));
                                    break;
                                case "-":
                                    resultado.replace(0, resultado.length(), "" + (Integer.parseInt(op1) - Integer.parseInt(op2)));
                                    break;
                                case "x":
                                    resultado.replace(0, resultado.length(), "" + (Integer.parseInt(op1) * Integer.parseInt(op2)));
                                    break;
                                case "/":
                                    resultado.replace(0, resultado.length(), "" + (Integer.parseInt(op1) / Integer.parseInt(op2)));
                            }
                            op1 = resultado.toString();
                        }
                }
            }

            if (modoNerd){
                int nr = Integer.parseInt(resultado.toString());
                txtResultado.setText(Integer.toBinaryString(nr));
            } else {
                txtResultado.setText(resultado);
            }
        } else {
            txtResultado.setText("");
        }
    }

    public void trataExclusao(){
        if (expressao.toString().endsWith(" ")){
            expressao.delete(expressao.length() - 3, expressao.length());
        } else {
            expressao.deleteCharAt(expressao.length() - 1);
        }

        fazConta();
        txtNumeros.setText(txtNumeros.getText().subSequence(0, txtNumeros.getText().length() - 1));
    }

    public void clicouBotao(View view){
        switch (view.getId()){
            case R.id.btnUm:
                trataNumero("1");
                txtNumeros.setText(txtNumeros.getText() + "1");
                break;
            case R.id.btnDois:
                trataNumero("2");
                txtNumeros.setText(txtNumeros.getText() + "2");
                break;
            case R.id.btnAdicao:
                trataOperador("+");
                break;
            case R.id.btnTres:
                trataNumero("3");
                txtNumeros.setText(txtNumeros.getText() + "3");
                break;
            case R.id.btnQuatro:
                trataNumero("4");
                txtNumeros.setText(txtNumeros.getText() + "4");
                break;
            case R.id.btnCinco:
                trataNumero("5");
                txtNumeros.setText(txtNumeros.getText() + "5");
                break;
            case R.id.btnSeis:
                trataNumero("6");
                txtNumeros.setText(txtNumeros.getText() + "6");
                break;
            case R.id.btnSete:
                trataNumero("7");
                txtNumeros.setText(txtNumeros.getText() + "7");
                break;
            case R.id.btnOito:
                trataNumero("8");
                txtNumeros.setText(txtNumeros.getText() + "8");
                break;
            case R.id.btnNove:
                trataNumero("9");
                txtNumeros.setText(txtNumeros.getText() + "9");
                break;
            case R.id.btnZero:
                trataNumero("0");
                txtNumeros.setText(txtNumeros.getText() + "0");
                break;
            case R.id.btnIgual:
                break;
            case R.id.btnPonto:
                break;
            case R.id.btnC:
                trataExclusao();
                break;
            case R.id.btnMutiplicacao:
                trataOperador("x");
                break;
            case R.id.btnDivisao:
                trataOperador("/");
                break;
            case R.id.btnSubtracao:
                trataOperador("-");
                break;

        }
    }
}
