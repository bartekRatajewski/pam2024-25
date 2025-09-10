package edu.zsk.ratajewski;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDb();

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> {
            EditText emailInput = findViewById(R.id.emailInput);
            EditText passwordInput = findViewById(R.id.passwordInput);

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_LONG).show();
            } else if (!checkCredentials(email, password)) {
                Toast.makeText(this, "Niepoprawne dane logowania!", Toast.LENGTH_LONG).show();
                passwordInput.setText("");
            } else {
                Toast.makeText(this, "Zalogowano!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoggedInActivity.class));
            }
        });
    }

    private void initDb() {
        DBHelper helper = new DBHelper(this);
        db = helper.getWritableDatabase();
    }

    private boolean checkCredentials(String email, String password) {
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            boolean result = password.equals(cursor.getString(0));
            cursor.close();
            return result;
        }
        cursor.close();
        return false;
    }

    private static class DBHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "users.db";
        private static final int DB_VERSION = 1;

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
            db.execSQL("INSERT INTO users (email, password) VALUES " +
                    "('admin@example.com', 'admin'), " +
                    "('user1@example.com', 'user1'), " +
                    "('user2@example.com', 'user2'), " +
                    "('user3@example.com', 'user3');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }
}
