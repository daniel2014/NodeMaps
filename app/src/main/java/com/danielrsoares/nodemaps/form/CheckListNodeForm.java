package com.danielrsoares.nodemaps.form;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.danielrsoares.nodemaps.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CheckListNodeForm extends DialogFragment{

    private TextInputEditText txt_Data_Inicio_Node, txt_Hora_Inicio_Node, txt_Data_Termino_Node, txt_Hora_Termino_Node;
    private ImageButton btn_Data_Inicio, btn_Hora_Inicio, btn_Data_Termino, btn_Hora_Termino;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checklist_node_form, container, false);

        findViewById(view);
        setDataHoraInicio();
        setDataHoraTermino();

        return view;
    }

    private void findViewById(View view) {
        txt_Data_Inicio_Node = view.findViewById(R.id.txt_Data_Inicio_Node);
        txt_Hora_Inicio_Node = view.findViewById(R.id.txt_Hora_Inicio_Node);
        txt_Data_Inicio_Node.setEnabled(false); //Seleção fica desativado
        txt_Hora_Inicio_Node.setEnabled(false); //Seleção fica desativado
        btn_Data_Inicio = view.findViewById(R.id.imgBtn_Data_Inicio);
        btn_Hora_Inicio = view.findViewById(R.id.imgBtn_Hora_Inicio);

        txt_Data_Termino_Node = view.findViewById(R.id.txt_Data_Termino_Node);
        txt_Hora_Termino_Node = view.findViewById(R.id.txt_Hora_Termino_Node);
        txt_Data_Termino_Node.setEnabled(false);
        txt_Hora_Termino_Node.setEnabled(false);
        btn_Data_Termino = view.findViewById(R.id.imgBtn_Data_Termino);
        btn_Hora_Termino = view.findViewById(R.id.imgBtn_Hora_Termino);

    }


    public void setDataHoraInicio(){
        btn_Data_Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GregorianCalendar gc1 = new GregorianCalendar();
                int corrente_ano, corrente_mes, corrente_dia;
                corrente_mes = gc1.get(Calendar.MONTH);
                corrente_ano = gc1.get(Calendar.YEAR);
                corrente_dia = gc1.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        txt_Data_Inicio_Node.setText("" + dia + "/" + (mes+1)+ "/" + ano);
                    }
                }, corrente_ano, corrente_mes, corrente_dia);
                dpd.show();
            }
        });

        btn_Hora_Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar gc1 = new GregorianCalendar();
                int corrente_hora, corrente_minuto;
                corrente_hora = gc1.get(Calendar.HOUR_OF_DAY);
                corrente_minuto = gc1.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        txt_Hora_Inicio_Node.setText("" + hora +":" + minuto);
                    }
                }, corrente_hora, corrente_minuto, true);
                tpd.show();
            }
        });

    }


    public void setDataHoraTermino(){
        btn_Data_Termino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GregorianCalendar gc1 = new GregorianCalendar();
                int corrente_ano, corrente_mes, corrente_dia;
                corrente_mes = gc1.get(Calendar.MONTH);
                corrente_ano = gc1.get(Calendar.YEAR);
                corrente_dia = gc1.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        txt_Data_Termino_Node.setText("" + dia + "/" + (mes+1)+ "/" + ano);
                    }
                }, corrente_ano, corrente_mes, corrente_dia);
                dpd.show();
            }
        });

        btn_Hora_Termino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar gc1 = new GregorianCalendar();
                int corrente_hora, corrente_minuto;
                corrente_hora = gc1.get(Calendar.HOUR_OF_DAY);
                corrente_minuto = gc1.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        txt_Hora_Termino_Node.setText("" + hora +":" + minuto);
                    }
                }, corrente_hora, corrente_minuto, true);
                tpd.show();
            }
        });

    }

/*
    //Abre o Dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
*/
}
