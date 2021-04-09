package com.vna.change.service.time;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import java.util.Calendar;

public class DateTime {

    public  static Calendar calendar;
    public static DateFormat fmtDateAndTime;
    public static TimePickerDialog.OnTimeSetListener time;
//    public static DatePickerDialog.OnDateSetListener day;

    public static  void ngayGio(){
        //Lấy đối tượng Calendar ra, mặc định ngày hiện tại
        Calendar now = Calendar.getInstance();
        //Muốn xuất Giờ:Phút:Giây AM (PM)
        String strDateFormat12 = "hh:mm:ss a";
        String strDateFormat24 = "HH:mm:ss";
        SimpleDateFormat sdf =null;
        //Tạo đối tượng SimpleDateFormat với định dạng 12
        sdf= new SimpleDateFormat(strDateFormat12);
        //1. gọi hàm format để lấy giờ:phút:giây loại 12
        System.out.println("Giờ định dạng 12 : " +
                sdf.format(now.getTime()));
        //Tạo đối tượng SimpleDateFormat với định dạng 24
        sdf = new SimpleDateFormat(strDateFormat24);
        //2. gọi hàm format để lấy giờ:phút:giây loại 24
        System.out.println("Giờ định dạng 24 : " +
                sdf.format(now.getTime()));
    }
    public static void ngayGioDialog(Button btGio, final TextView tvGio, final Context context) {
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvGio.setText(hourOfDay+":"+minute);
            }
        };

        btGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // new TimePickerDialog()
            }
        });



    }
}
