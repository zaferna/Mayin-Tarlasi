import java.awt.image.ImageProducer;
import java.util.Random;
import java.util.Scanner;

public class MinesSweeper {
    int kolonSayisi;
    int satirSayisi;
    int selectedRow;
    int selectedCol;


    MinesSweeper(int kolonSayisi, int satirSayisi) {
        this.kolonSayisi = kolonSayisi;
        this.satirSayisi = satirSayisi;
    }

    String[][] map2(int col, int row) {
        String[][] map2 = new String[row][col];

        for (int j = 0; j < row; j++) {                //cizgi  doseme map2
            for (int k = 0; k < col; k++) {
                map2[j][k] = "-";
            }
        }

        return map2;
    }

    String[][] map(int col, int row) {                //arrray tanimlama ve haritayi olusturma
        String[][] map1 = new String[row][col];


        double mineQuan = row * col / 4;
        int startnum = 0;
        int i = 0;


        for (int j = 0; j < row; j++) {    //sifir doseme map1
            for (int k = 0; k < col; k++) {
                map1[j][k] = "0";
            }
        }


        while (startnum < mineQuan) {   //mayin doseme
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    int chance = (int) (Math.random() * 100);
                    if (chance < col * row && startnum <= mineQuan) {
                        if (map1[j][k].equals("*")) {
                            startnum = startnum;
                        } else {
                            map1[j][k] = "*";
                            startnum++;
                        }
                    } else {
                        map1[j][k] = map1[j][k];
                    }
                }
            }
        }


        for (int j = 0; j < row; j++) {        //Rakamlari yerlestirme
            for (int k = 0; k < col; k++) {
                int count = 0;
                if (!map1[j][k].equals("*")) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        for (int m = k - 1; m <= k + 1; m++) {
                            if ((n != -1 && m != -1 && n != row && m != col)) {
                                if (map1[n][m] == "*") {
                                    count++;
                                }

                            }
                        }

                    }

                } else {
                    map1[j][k] = "*";
                    continue;
                }
                String s = String.valueOf(count);
                map1[j][k] = s;

            }
        }

        return map1;

    }


    void run() {                                  // Program Dongusu

        String[][] map1 = map(this.kolonSayisi, this.satirSayisi); //multiple array cagirma
        String[][] map2 = map2(this.kolonSayisi, this.satirSayisi); //multiple array cagirma

        for (int j = 0; j < this.satirSayisi; j++) {               // yazdirmak icin
            for (int k = 0; k < this.kolonSayisi; k++) {
                System.out.print(map1[j][k]);
            }
            System.out.println();
        }
        for (int j = 0; j < this.satirSayisi; j++) {                        // yazdirmak icin
            for (int k = 0; k < this.kolonSayisi; k++) {
                System.out.print(map2[j][k]);
            }
            System.out.println();
        }


        int n = 0;


        while (n < ((this.satirSayisi * this.kolonSayisi) * 3) / 4) {   //max hak dongusu baslangic


            Scanner inp = new Scanner(System.in);


            boolean success = true;                  //Parametre Check satir
            do {
                System.out.print("Satir Gir : ");
                int selectedRow = inp.nextInt();
                this.selectedRow = selectedRow;
                if (this.selectedRow < 0 || this.selectedRow > this.satirSayisi) {
                    System.out.println("Yanlis Parametre Girdiniz..Tekrar giriniz");
                    success = false;
                }else{
                    success=true;
                }

            }
            while (!success );

            do {                                                       //Parametre Check sutun
                System.out.print("Sutun Gir : ");
                int selectedCol = inp.nextInt();
                this.selectedCol = selectedCol;

                if (this.selectedCol < 0 || this.selectedCol > this.kolonSayisi) {
                    System.out.println("Yanlis Parametre Girdiniz..Tekrar giriniz");
                    success = false;
                }else{
                    success=true;
                }
            } while (!success);                       //Parametre Check bitis


            if (!map1[this.selectedRow][this.selectedCol].equals("*")) {                      //mayin check
                map2[this.selectedRow][this.selectedCol] = map1[this.selectedRow][this.selectedCol];
                n++;

            } else {


                System.out.println("Patladin..");                                     //mayin check
                map2[this.selectedRow][this.selectedCol] = map1[this.selectedRow][this.selectedCol];
                for (int j = 0; j < this.satirSayisi; j++) {                               // yazdirmak icin
                    for (int k = 0; k < this.kolonSayisi; k++) {
                        System.out.print(map2[j][k]);
                    }
                    System.out.println();
                }

                break;
            }


            for (int j = 0; j < this.satirSayisi; j++) {                     // yazdirmak icin
                for (int k = 0; k < this.kolonSayisi; k++) {
                    System.out.print(map2[j][k]);
                }
                System.out.println();
            }
            System.out.println("==================");
        }                                       //max hak dongusu bitis

        System.out.println("=====TEBRIKLER KAZANDIN====");

        }
      }



