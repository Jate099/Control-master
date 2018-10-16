package com.example.estudiante.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Comunicacion.Cliente;
import Comunicacion.Mensaje;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    ImageButton btn_atack;
    ImageButton btn_right;
    ImageButton btn_left;
    ImageButton btn_accel;

    Cliente c;
    Mensaje m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText e;

        //String ip = e.getText();


        c = new Cliente("172.30.168.159", 5000);
        c.start();
        m = new Mensaje();

        btn_atack = findViewById(R.id.btn_atack);
        btn_right = findViewById(R.id.btn_right);
        btn_left = findViewById(R.id.btn_left);
        btn_accel = findViewById(R.id.btn_accel);

        btn_atack.setOnTouchListener(this);
        btn_accel.setOnTouchListener(this);
        btn_right.setOnTouchListener(this);
        btn_left.setOnTouchListener(this);
    }

    public void keyPressed(View btn){
        switch (btn.getId()){

            case R.id.btn_accel:
                m.ACEL = true;

                break;

            case R.id.btn_atack:
                m.ATTACK = true;
                break;

            case R.id.btn_right:
                m.RIGHT = true;

                break;

            case R.id.btn_left:
                m.LEFT = true;

                break;
        }
        c.enviar(m);
    }

    public void keyReleased(View btn){
        switch (btn.getId()){
            case R.id.btn_accel:
                m.ACEL = false;

                break;

            case R.id.btn_atack:
                m.ATTACK = false;

                break;

            case R.id.btn_right:
                m.RIGHT = false;

                break;

            case R.id.btn_left:
                m.LEFT = false;

                break;
        }
        c.enviar(m);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            keyPressed(view);
        }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            keyReleased(view);
        }
        return false;
    }
}
