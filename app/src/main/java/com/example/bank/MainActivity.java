package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    double sum = 2000;
    TextView Balanse ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Balanse =findViewById(R.id.textView);
        MyThread minus = new MyThread("-");
        minus.start();
        MyThread plus=  new MyThread("+");
        plus.start();

    }

    class MyThread extends Thread{
        private String notification;

        public MyThread(String notification) {
            this.notification = notification;
        }
        @Override
        public void run(){
            for (int i = 0; i < 1000; i++) {
                if (Objects.equals(notification, "+")) {
                    sum+= 1000*(Math.random());
                }
                if (Objects.equals(notification, "-")) {
                    sum-= 1000*(Math.random());
                }
                if (sum < 0 || sum==0) {
                    sum=0;
                    bankrupt();
                }
                else {
                    richman();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        public void bankrupt() {
            Balanse.setText("У вас недостаточно средств");
        }
        public void richman() {
            Balanse .setText("баланс = " + sum + "рублей");
        }

    }
}

