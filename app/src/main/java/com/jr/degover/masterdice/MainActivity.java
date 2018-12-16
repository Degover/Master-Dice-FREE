package com.jr.degover.masterdice;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Variaveis Globais
    //Declara o SharedPreferences
    SharedPreferences prefs;
    //Declara os Botões
    Button d2Bt;
    Button d3Bt;
    Button d4Bt;
    Button d6Bt;
    Button d8Bt;
    Button d20Bt;
    Button d30Bt;
    Button d100Bt;
    Button dBPBt;
    Button dFBt;
    ImageButton plusDiceBt;
    ImageButton minusDiceBt;
    ImageButton plusAcrBt;
    ImageButton minusAcrBt;
    //Declara os layouts e containers e similares
    ScrollView registerScroll;
    LinearLayout register;
    TextView diceNumText;
    TextView acrNumText;
    TextView resultText;
    //Declara outras variaveis
    int diceNum;
    int acrNum;
    String diceNumStr;
    String acrNumStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicia as variaveis globais
        //Inicia o SharedPreferences
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Inicia os Botões
        d2Bt = findViewById(R.id.d2Button);
        d3Bt = findViewById(R.id.d3Button);
        d4Bt = findViewById(R.id.d4Button);
        d6Bt = findViewById(R.id.d6Button);
        d8Bt = findViewById(R.id.d8Button);
        d20Bt = findViewById(R.id.d20Button);
        d30Bt = findViewById(R.id.d30Button);
        d100Bt = findViewById(R.id.d100Button);
        dBPBt = findViewById(R.id.dBPButton);
        dFBt = findViewById(R.id.dFButton);
        plusDiceBt = findViewById(R.id.plusDiceButton);
        minusDiceBt = findViewById(R.id.minusDiceButton);
        plusAcrBt = findViewById(R.id.plusAcrescButton);
        minusAcrBt = findViewById(R.id.minusAcrescButton);
        //Inicia os layouts e containers e similares
        registerScroll = findViewById(R.id.registerScroll);
        register = findViewById(R.id.register);
        diceNumText = findViewById(R.id.diceNumber);
        acrNumText = findViewById(R.id.acrescNumber);
        resultText = findViewById(R.id.resultView);
        //Inicia outras variaveis
        diceNum = 1;
        diceNumStr = diceNum + " dado";
        diceNumText.setText(diceNumStr);
        acrNum = 0;
        acrNumStr = "± " + acrNum;
        acrNumText.setText(acrNumStr);
        resultText.setText("Let the game begin!");

        //Coloca os clickListeners
        d2Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 2, 2);
            }
        });
        d3Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 3, 1);
            }
        });
        d4Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 4, 1);
            }
        });
        d6Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 6, 1);
            }
        });
        d8Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 8, 1);
            }
        });
        d20Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 20, 1);
            }
        });
        d30Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 30, 1);
            }
        });
        d100Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 100, 1);
            }
        });
        dBPBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDiceBP(MainActivity.this);
            }
        });
        dFBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(MainActivity.this, 3, 3);
            }
        });
        plusDiceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++diceNum;
                diceNumStr = diceNum + " dados";
                diceNumText.setText(diceNumStr);
            }
        });
        minusDiceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(diceNum > 1){
                    --diceNum;
                    if(diceNum == 1){
                        diceNumStr = diceNum + " dado";
                    } else{
                        diceNumStr = diceNum + " dados";
                    }
                    diceNumText.setText(diceNumStr);
                } //end if
            }
        });
        plusAcrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++acrNum;
                acrNumStr = "± " + acrNum;
                acrNumText.setText(acrNumStr);
            }
        });
        minusAcrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --acrNum;
                acrNumStr = "± " + acrNum;
                acrNumText.setText(acrNumStr);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Abre o menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    //Função que simula a jogada dos dados
    public void rollDice(Context context, int diceFaces, int diceType){
        //Declara as variaveis
        String text; //String que sera mostrada na tela
        TextView textView;
        int total = 0;
        int result;

        //Inicia as variaveis
        if(diceType == 3){ //Se o dado for um dF
            text = "  dF: ";
        } else{ //Se for qualquer outro dado
            text = "  d" + diceFaces + ": ";
        } //end else
        textView = new TextView(context);

        //Joga cada dado, e adiciona na String text
        for(int i = 0; i < diceNum; i++){
            //Pega o numero aleatório e soma no total
            result = (int)Math.floor(Math.random() * diceFaces) + 1;
            if(diceType == 3){ //Se for um dado dF
                total += result - 2;
            } else{ //Se for qualquer outro dado
                total += result;
            } //end if(diceType)

            //Coloca o resultado na string
            switch(diceType){
                case 1: //Case seja dado comum
                    text += result;
                    break;
                case 2: //Caso seja um dado de duas faces
                    text += getDice2Result(result);
                    break;
                case 3: //Caso seja um dF
                    if(result == 1){ //Resultado negativo
                        text += "-";
                    } else if(result == 2){ //Resultado nulo
                        text += "0";
                    } else{ //Resultado positivo
                        text += "+";
                    } //end else(result)
                    break;
                default:
                    text += result;
                    break;
            } //end switch(diceType)

            if(i < (diceNum - 1)){ //Se for mais de um dado ou não for o ultimo
                if(diceType == 3){ //Se for um dF
                    text += " ";
                } else{ //Se for qualquer outro dado
                    text += " + ";
                } //end else(diceType)
            } else if(acrNum > 0){ //Coloca o acréscimo
                text += " (+ " + acrNum + ") = " + (acrNum + total);
            } else if(acrNum < 0){ //Coloca o decréscimo
                text += " (- " + (acrNum * -1) + ") = " + (acrNum + total);
            } else if(diceNum > 1){ //Se for mais de um dado
                text += " = " + total;
            } //end else(diceNum)
        } //end for

        //Adiciona a view do divisor
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout divider = (RelativeLayout) inflater.inflate(R.layout.divider, null, false);
        register.addView(divider);

        //Coloca string de texto na tela
        register.addView(textView);
        textView.setText(text);
        resultText.setText(text);

        //Coloca a aparencia do texto, de acordo com a versão do android
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            textView.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Large);
        } else{
            textView.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
        } //end else(Build.Version)

        //Muda a cor, se a opção tiver sido selecionada
        if(prefs.getBoolean("colorMode", false)){
            //Variaveis usadas para armazenar as cores do RGB
            int colorR, colorB;
            int colorFinal;
            //Calcula as três cores
            if(diceType == 3){ //Se for um dF
                if((total + diceNum) == 0){ //Se for o menor resultado possivel, taca FullRed
                    colorB = 0;
                    colorR = 255;
                } else{ //Senão, calcula a cor
                    colorB = (int) Math.floor(((total + diceNum) / (float) (diceNum * 2 + 1)) * 255);
                    colorR = (int) Math.floor((((total + diceNum) / (float) (diceNum * 2 + 1)) - 1) * -255);
                } //end else(total)
            } else{ //Se for qualquer outro
                if(total == diceNum){ //Se for o menor resultado possivel, taca FullRed
                    colorB = 0;
                    colorR = 255;
                } else{ //Senão, calcula a cor
                    colorB = (int) Math.floor(total / (float) (diceFaces * diceNum) * 255);
                    colorR = (int) Math.floor(((total / (float) (diceFaces * diceNum)) - 1) * -255);
                }
            } //end else(diceType)

            //Muda o texto com a cor correspondente
            colorFinal = Color.argb(255, colorR, 0, colorB);
            textView.setTextColor(colorFinal);
            resultText.setTextColor(colorFinal);
        }

        //Foca no fim do Scroll
        registerScroll.fullScroll(ScrollView.FOCUS_DOWN);
    } //end rollDice

    //Função que retorna String do dado de duas faces
    public String getDice2Result(int result){
        String retorno = ""; //String que guardara o retorno

        if(diceNum == 1 && acrNum == 0) { //Se for apenas um dado
            //Muda de acordo com a configuração selecionada
            switch (prefs.getInt("d2Mode", 1)) {
                case 1: //Case as config do d2 sejam do modo 'Sim ou Não'
                    if (result == 1) {
                        retorno = "Sim";
                    } else {
                        retorno = "Não";
                    } //end else
                    break;
                case 2: //Case as config do d2 sejam do modo 'Cara ou Coroa'
                    if (result == 1) {
                        retorno = "Cara";
                    } else {
                        retorno = "Coroa";
                    } //end else
                    break;
                case 3: //Case as config do d2 sejam do modo Binário
                    retorno = (result - 1) + "";
                    break;
                default:
                    retorno = (result - 1) + "";
            } //end switch(prefs)
        } else{
            retorno = result + "";
        } //end else(diceNum)

        return retorno;
    } //end getDice2Result

    //Função que simula a jogada do dado das partes do corpo
    public void rollDiceBP(Context context){
        //Adiciona o texto
        TextView tx = new TextView(context);
        register.addView(tx);

        //Adiciona a view do divisor
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout divider = (RelativeLayout) inflater.inflate(R.layout.divider, null, false);
        register.addView(divider);

        //Pega o numero aleatório
        int result = (int)Math.floor(Math.random() * 17);

        //Coloca string de texto
        String[] partes = {"Mão Esquerda", "Mão Direita", "Antebraço Esquerdo", "Antebraço Direito", "Braço Esquerdo", "Braço direito",
                            "Pé Esquerdo", "Pé Direito", "Perna Esquerda", "Perna Direita", "Coxa Esquerda", "Coxa Direita",
                            "Estomago", "Peito", "Ombro Esquerdo", "Ombro Direito", "Cabeça"};
        String text;
        text = "  dBP: " + partes[result];

        //Coloca a string no TextView
        tx.setText(text);
        resultText.setText(text);

        //Coloca a aparencia do texto, de acordo com a versão do android
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tx.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Large);
        } else{
            tx.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
        }

        //Foca no fim do Scroll
        registerScroll.fullScroll(ScrollView.FOCUS_DOWN);
    } //end rollDiceBP

} //end MainActivity
