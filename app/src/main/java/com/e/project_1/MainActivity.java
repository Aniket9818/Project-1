package com.e.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText etCheckingdate, etCheckoutdate, etRoom, etAdult, etChild;
    Button btnCalculate;
    TextView tvTotalDays, tvTotalRoom,tvRoomPrice, tvTotal, tvVat, tvGrandTotal;
    Spinner spRoom;
    int year1, year2;
    int month1, month2;
    int day1, day2;
    int room_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCheckingdate = findViewById(R.id.etCheckingdate);
        etCheckoutdate = findViewById(R.id.etCheckoutdate);
        spRoom = findViewById(R.id.spRoom);
        etRoom = findViewById(R.id.etRoom);
        etAdult = findViewById(R.id.etAdult);
        etChild = findViewById(R.id.etChild);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvTotalDays = findViewById(R.id.tvTotalDays);
        tvTotalRoom = findViewById(R.id.tvTotalRoom);
        tvRoomPrice = findViewById(R.id.tvRoomPrice);
        tvTotal = findViewById(R.id.tvTotal);
        tvVat = findViewById(R.id.tvVat);
        tvGrandTotal = findViewById(R.id.tvGrandTotal);





        String Room[] ={"--Select Room--", "Deluxe: Rs 2000","Presidential: Rs 5000","Premium: Rs 4000"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Room);
        spRoom.setAdapter(adapter);



        etCheckingdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarLoad();
            }
        });


        etCheckoutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarLoad1();
            }
        });
    }


    private  void CalendarLoad(){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;
                String date = "Day/Month/Year: " + dayOfMonth + "/" + month + "/" + year;
                day1 = dayOfMonth;
                month1 = month;
                year1 = year;
                etCheckingdate.setText(date);
            }
        },year,month,dayOfMonth);
        datePickerDialog.show();
    }




    private  void CalendarLoad1(){

        final Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH);
        int day = c1.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;
                String date = "Date/Month/Year: " + dayOfMonth + "/" + month + "/" + year;
                day2 = dayOfMonth;
                month2 = month;
                year2 = year;
                etCheckoutdate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etCheckingdate.getText())){
                    etCheckingdate.setText("Please enter checking date");
                    return;
                } else if(TextUtils.isEmpty(etCheckoutdate.getText())){
                    etCheckoutdate.setText("Please enter checkout date");
                    return;
                } else if(TextUtils.isEmpty(etAdult.getText())){
                    etAdult.setText("Please enter number of adult!!");
                    return;
                } else if(TextUtils.isEmpty(etChild.getText())){
                    etChild.setText("Please enter number of child!!");
                    return;
                }else if(TextUtils.isEmpty(etRoom.getText())){
                    etRoom.setText("Please enter number of room");
                    return;
                }

                Calendar c = Calendar.getInstance();
                c.set(year1, month1, day1);
                Calendar c1 = Calendar.getInstance();
                c1.set(year2, month2, day2);


                long diffMillis = c1.getTimeInMillis() - c.getTimeInMillis();
                long daysDiff = (diffMillis / (24 * 60 * 60 * 1000));


                double price, totalPrice, vat, grandTotal;


                room_no = Integer.parseInt(etRoom.getText().toString());


                String room = spRoom.getSelectedItem().toString();

                if(room == "Deluxe: Rs 2000"){
                    price = 2000;
                    totalPrice = price * room_no * daysDiff;
                    vat = (0.13) * totalPrice;
                    grandTotal =  totalPrice + vat;

                    tvTotalDays.setText("Total Days: " + daysDiff);
                    tvTotalRoom.setText("Total Room: " + room_no);
                    tvRoomPrice.setText("Price Per Room: " + "Rs 2000");
                    tvTotal.setText("Total Price: " + "Rs" + totalPrice);
                    tvVat.setText("VAT: " + "Rs" + vat);
                    tvGrandTotal.setText("GrandTotal Price: " + "Rs" + grandTotal);
                    Toast.makeText(MainActivity.this, "Grand Total Price is " + "Rs" + grandTotal, Toast.LENGTH_SHORT).show();
                } else if(room == "Presidential: Rs 5000"){
                    price = 5000;
                    totalPrice = price * room_no * daysDiff;
                    vat = (0.13) * totalPrice;
                    grandTotal =  totalPrice + vat;

                    tvTotalDays.setText("Total Days: " + daysDiff);
                    tvTotalRoom.setText("Total Room: " + room_no);
                    tvRoomPrice.setText("Price Per Room: " + price);
                    tvTotal.setText("Total Price: " + "Rs" + totalPrice);
                    tvVat.setText("VAT: " + "Rs " + vat);
                    tvGrandTotal.setText("GrandTotal Price: " + "Rs " + grandTotal);
                    Toast.makeText(MainActivity.this, "Grand Total Price is " + "Rs " + grandTotal, Toast.LENGTH_SHORT).show();
                } else if(room == "Premium: Rs 4000"){
                    price = 4000;
                    totalPrice = price * room_no * daysDiff;
                    vat = (0.13) * totalPrice;
                    grandTotal =  totalPrice + vat;

                    tvTotalDays.setText("Total Days: " + daysDiff);
                    tvTotalRoom.setText("Total Room: " + room_no);
                    tvRoomPrice.setText("Price Per Room: " + price);
                    tvTotal.setText("Total Price: " + "Rs" + totalPrice);
                    tvVat.setText("VAT: " + "Rs " + vat);
                    tvGrandTotal.setText("GrandTotal Price: " + "Rs " + grandTotal);

                }

            }
        });


    }
}
