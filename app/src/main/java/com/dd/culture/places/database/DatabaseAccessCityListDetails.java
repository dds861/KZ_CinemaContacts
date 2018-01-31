package com.dd.culture.places.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import com.dd.culture.places.models.ModelCityListDetails;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCityListDetails extends AppCompatActivity {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccessCityListDetails instance;

    private DatabaseAccessCityListDetails(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccessCityListDetails getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccessCityListDetails(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<ModelCityListDetails> getList(int rowSelectedPosition) {

        //создаем список чтобы возвратить его
        List<ModelCityListDetails> list = new ArrayList<>();

        //формируем запрос
        String sqlQuery_CitySelected = "SELECT city_list FROM cinema_list";

        //Производим запрос в базу данных и получаем список городов из колонки city_list из таблицы "cinema_list"
        Cursor cursor_cityList = database.rawQuery(sqlQuery_CitySelected, null);

        //Переводим курсор на ту позицию на которую нажали
        cursor_cityList.moveToPosition(rowSelectedPosition-1);

        //Из колонки "city" в котором храняться список городов,
        //получаем Выбранный город
        String selectedCity = cursor_cityList.getString(0);

        //Получаем из таблицы каждую колонку по выбранному городу
        String sqlQuery_city = "SELECT * FROM cinema_list WHERE city= '" + selectedCity + "'";

        //Получаем колонку из таблицы т.е. делаем запрос в SQLite, и записываем все в курсор
        Cursor cursor_city = database.rawQuery(sqlQuery_city, null);

        //перемещаем курсор на первую строку в таблице чтобы перебирать
        cursor_city.moveToFirst();

        //перебираем все строки колонки в таблице до последнего
        while (!cursor_city.isAfterLast()) {
            //получаем очередной item из колонки
            String text_city = cursor_city.getString(2);
            String text_cinemaName = cursor_city.getString(3);
            String text_address = cursor_city.getString(4);
            String text_contacts = cursor_city.getString(5);
            //проверяем не пустая ли ячейка в колонке
            if (!text_city.equals("")) {
                //добавляем в список чтоб потом отправить этот список
                list.add(new ModelCityListDetails(text_city, text_cinemaName, text_address, text_contacts));
            }
            //перемещаемся на следующую строку в таблице
            cursor_city.moveToNext();
        }
        //закрываем курсор чтоб не было утечки памяти
        cursor_cityList.close();
        cursor_city.close();
        //возврашаем список
        return list;
    }
}