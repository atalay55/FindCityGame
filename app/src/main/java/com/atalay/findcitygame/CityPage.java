package com.atalay.findcitygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class CityPage extends AppCompatActivity {


        private TextView txtViewBilgiGoster, txtViewHarfGoster, txtSonucGoster, txtToplamPuan,txtTimer;
        private EditText edittahmindeBulun;
        private Button btnTahminEt, btnHarfAl;
        private String comeCity, cityCharDimen = "";
        private Random rdnomForcity;
        private int rndCityNumber, rndForNumberLetter, givenLetterNumber;
        private float maxPoint = 100.0f,minusPoint,point=0, totalPoint;
        private CountDownTimer counter;
        private Controller controller;
        private ArrayList<Character> cityLatter;

        String[] citys = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
                "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
                "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum ", "Eskişehir",
                "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
                "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya ", "Malatya",
                "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
                "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon  ", "Tunceli", "Şanlıurfa", "Uşak",
                "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "Kırıkkale", "Batman", "Şırnak",
                "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük ", "Kilis", "Osmaniye ", "Düzce"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_city_page);
            txtViewBilgiGoster = (TextView) findViewById(R.id.txtViewBilgiGöster);
            txtViewHarfGoster = (TextView) findViewById(R.id.txtViewHarfGöster);
            edittahmindeBulun = (EditText) findViewById(R.id.editTxtTahmindeBulun);
            txtSonucGoster=(TextView)findViewById(R.id.textShowFinal) ;
            txtTimer = (TextView) findViewById(R.id.txtTime);
            txtToplamPuan=(TextView) findViewById(R.id.txtToplamPuan);


            controller= new Controller();
            rdnomForcity = new Random();
            randomGetir();



        }



        public void btnHarfAl(View v) {

            if (cityLatter.size() > 0) {
                harfAl();
                System.out.println("puan"+ point);
            }else
                txtSonucGoster.setText("harf kalmadı");


        }
        public  void  timer(){
           counter= new CountDownTimer(30100,1000) {
                @Override
                public void onTick(long l) {
                    txtTimer.setText(String.valueOf(l/1000));
                }

                @Override
                public void onFinish() {
                    Toast.makeText(getApplicationContext(),"süre bitti",Toast.LENGTH_SHORT).show();
                    totalPoint=0;
                    txtToplamPuan.setText("Toplam puaniniz :" + totalPoint);
                    randomGetir();


                }

            }.start();


        }


        public void btnTahmindeBulun(View v) {
            String tempTahmin =edittahmindeBulun.getText().toString().trim();

            if ( controller.fixName(tempTahmin).equals(comeCity)) {
                totalPoint += point;
                txtSonucGoster.setText("tebrikler bildiniz");
                Toast.makeText(getApplicationContext(),"kazandığınız puan"+ point,Toast.LENGTH_SHORT).show();
                txtToplamPuan.setText("Toplam puaniniz :" + totalPoint);
                edittahmindeBulun.setText(" ");
                counter.cancel();
                randomGetir();



            }else if (controller.fixName(tempTahmin).isEmpty()) {
                Toast.makeText(getApplicationContext(),"tahmin alanı bos olamaz",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"yanlıs tahmin",Toast.LENGTH_SHORT).show();
            }




        }

        private void randomGetir() {
            timer();
            cityCharDimen = "";
            rndCityNumber = rdnomForcity.nextInt(citys.length);
            comeCity = citys[rndCityNumber];
            System.out.println("gelen il :" + comeCity);
            txtViewBilgiGoster.setText(comeCity.length() + " harfli ilimiz");

            int tempCheckGivenLetter=controller.checkGivenLetterNumber(comeCity);

            for (int i = 0; i < comeCity.length(); i++) {
                if (i < comeCity.length() - 1) {
                    cityCharDimen += "_ ";
                } else
                    cityCharDimen += "_";
            }
            txtViewHarfGoster.setText(cityCharDimen);
            cityLatter = new ArrayList<>();
            for (char come : comeCity.toCharArray()) {
                cityLatter.add(come);
            }

            for(int g =0 ; g<tempCheckGivenLetter;g++){ harfAl(); }

            minusPoint = maxPoint/cityLatter.size();
            point = maxPoint ;



        }

        private void harfAl(){
            rndForNumberLetter = rdnomForcity.nextInt(cityLatter.size());
            String[] dimen = txtViewHarfGoster.getText().toString().split(" ");
            char[] txtLatter = comeCity.toCharArray();

            for (int i = 0; i < comeCity.length(); i++) {
                if (dimen[i].equals("_") && txtLatter[i] == cityLatter.get(rndForNumberLetter)) {
                    dimen[i] = String.valueOf(cityLatter.get(rndForNumberLetter));
                    cityCharDimen = "";

                    for (int j = 0; j < comeCity.length(); j++) {
                        if (j == i) { cityCharDimen += dimen[j] + " "; }
                        else if (j < comeCity.length() - 1) { cityCharDimen += dimen[j] + " "; }
                        else { cityCharDimen += dimen[j]; }
                    }
                    break;
                }
            }
            txtViewHarfGoster.setText(cityCharDimen);
            cityLatter.remove(rndForNumberLetter);
            point = point -minusPoint;

        }


    }

