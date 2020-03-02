package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class EmployeeHandler extends DatabaseHandler {

    public EmployeeHandler (Context context) {
        super(context);
    }
    public boolean insert(Employee emp)
    {
        ContentValues val = new ContentValues();
        val.put("name",emp.name);
        val.put("email",emp.email);
        val.put("phone",emp.phone);
        val.put("address",emp.address);
        SQLiteDatabase db = this.getWritableDatabase();
        int success  = (int) db.insert("employee",null,val);
        db.close();
        if(success>0) return true;
        else return false;
    }

    public boolean update(Employee emp)
    {
        ContentValues val = new ContentValues();
        val.put("name",emp.name);
        val.put("email",emp.email);
        val.put("phone",emp.phone);
        val.put("address",emp.address);
        SQLiteDatabase db = this.getWritableDatabase();
        String [] ids = new String[1];
        ids[0] = String.valueOf(emp.id);
        int success  = db.update("employee",val,"id = "+String.valueOf(emp.id),null);
        db.close();
        if(success>0) return true;
        else return false;
    }

    public ArrayList<Employee> retrieve()
    {
        ArrayList<Employee> emps= new ArrayList<Employee>();
        String sql = "SELECT * FROM Employee ORDER BY id";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));

                Employee emp = new Employee();
//                objectStudent.id = id;
                emp.name = name;
                emp.id=id;
                emp.phone = phone;
                emp.address = address;
                emp.email = email;
//                objectStudent.email = studentEmail;

                emps.add(emp);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return emps;
    }

    public Employee find(String eid)
    {
        Employee emp= new Employee();
        String sql = "SELECT * FROM Employee where id="+eid;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));


//                objectStudent.id = id;
                emp.name = name;
                emp.id=id;
                emp.phone = phone;
                emp.address = address;
                emp.email = email;
//                objectStudent.email = studentEmail;
            cursor.close();



        }
        else{
            emp.id=-1;
            return emp;
        }


        db.close();

        return emp;
    }


    public Employee delete(int id){
        boolean deleteSuccessful = false;
        Employee emp = this.find(String.valueOf(id));
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("employee", "id ='" + id + "'", null) > 0;
        db.close();
        return  emp;
    }
    public int getCount() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM employee";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public boolean deleteLast() {
        String sql = "SELECT max(id) as mx FROM Employee";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        boolean deleteSuccessful = false;
        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mx")));
            Employee emp = this.find(String.valueOf(id));
            db = this.getWritableDatabase();
            deleteSuccessful = db.delete("employee", "id ='" + id + "'", null) > 0;

        }
        db.close();
        return  deleteSuccessful;
    }
}
