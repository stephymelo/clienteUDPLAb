package com.example.clienteudplab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private UDPConexion udp;
    private ImageView hamburgerButton;
    private ImageView sodaButton;
    private ImageView friesButton;
    private Date fecha;
    private int pedido;
    private String[]fechaOrdenSplit;
    private String fechaOrden;
    String json;
    ArrayList<Orden> orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hamburgerButton=findViewById(R.id.hamburgerButton);
        sodaButton=findViewById(R.id.sodaButton);
        friesButton=findViewById(R.id.friesButton);

        orden = new ArrayList<>();
        udp=new UDPConexion();
        udp.start();
        hamburgerButton.setOnClickListener(this);
        sodaButton.setOnClickListener(this);
        friesButton.setOnClickListener(this);
        pedido=0;



    }

    @Override
    public void onClick(View v) {
        fecha=new Date();
        fechaOrdenSplit=fecha.toString().split(" ");
        fechaOrden="Hora:"+fechaOrdenSplit[3];
       Gson gson=new Gson();
       json = gson.toJson(orden);

        switch (v.getId()) {
            case R.id.hamburgerButton:
                pedido+=1;
                orden.add(new Orden("Hamburger",pedido,fechaOrden));
                udp.sendMessage(json);
                break;
            case R.id.sodaButton:
                pedido+=1;
                orden.add(new Orden("Soda",pedido,fechaOrden));
                udp.sendMessage(json);
                break;

            case R.id.friesButton:
                pedido+=1;
                orden.add(new Orden("Fries",pedido,fechaOrden));
                udp.sendMessage(json);
                break;



        }


    }


}