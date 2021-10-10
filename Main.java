package Threads;

// 1 - CREAM 2 ECHIPE A CATE 11 JUCATORI ,  SI O ECHIPA DE REZERVA CU 5 JUCATORI                 [ X ]
// 2 - JUCATORII JOACA , IAR CAND APAS TASTA "S" JOACA SE OPRESTE                                [ X ]
// 3 - CAND JOACA E OPRITA , ALEGEM ECHIPA DIN CARE VREM SA SCHIMBAM UN JUCATOR                  [ X ]
// 4 - AFISAM LISTA CU JUCATORII DIN ECHIPA ALEASA , SI ALEGEM UN JUCATOR PE CARE SA-L SCHIMBAM  [ X ]
// 5 - AFISAM LISTA CU JUCATORII DE REZERVA , SI ALEGEM JUCATORUL                                [ X ]
// 6 - JUCATORUL EXPULZAT,NU MAI ARE DREPTUL DE A JUCA,IL ADAUGAM IN "JUCATORI DESCALIFICATI"    [ X ]
// 7 - CAND TASTAM "C" JOCUL VA CONTINUA                                                         [   ]



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    // CREAM LISTA CU ECHIPE
    public static List<Thread> echipa1 = new ArrayList<>();
    public static List<Thread> echipa2 = new ArrayList<>();
    public static List<Thread> echipaRez = new ArrayList<>();
    public static List<Thread> jucatoriDescalificati = new ArrayList<>();

    public static boolean reload=false;


    public static boolean isActive = true;

    public static void main(String[] args) {

//      POPULAM ECHIPA 1 CU 11 MEMBRI
        for (int i = 1; i <= 11; i++) {
            Thread x = new Thread(new Jucator("Echipa 1", i)," Jucator nr:"+i+" Echipa 1" );
            echipa1.add(x);
        }


//        new Thread(() -> {
//            echipa1.forEach(Thread::start);
//        }).start();

//      CREAM UN THREAD CARE NE VA STARTA FIECARE THREAD/JUCATOR DIN LISTA
        Runnable x1 = () -> {
            echipa1.forEach(Thread::start);
        };
        Thread techipa1 = new Thread(x1);
        techipa1.start();

//      POPULAM ECHIPA 2 CU 11 MEMBRI
        for (int i = 1; i <= 11; i++) {
            Thread y = new Thread(new Jucator("Echipa 2", i), " Jucator nr:"+i+" Echipa 2");
            echipa2.add(y);
        }
//        new Thread(() -> {
//            echipa2.forEach(Thread::start);
//        }).start();

//      CREAM UN THREAD CARE NE VA STARTA FIECARE THREAD/JUCATOR DIN LISTA

        Runnable x2 = () -> {
            echipa2.forEach(Thread::start);
        };
        Thread techipa2 = new Thread(x2);
        techipa2.start();

//      POPULAM ECHIPA DE REZERVA CU 5 MEMBRI
        for (int i = 1; i <= 5; i++) {
            Thread y = new Thread(new Jucator("Echipa Rez", i), "Jucator nr:"+i+" Echipa Rez");
            echipaRez.add(y);
        }


//      CREAM UN THREAD NOU CARE VA ASTEPTA SA STOPAM SAU SA RELUAM JOCUL
        Runnable x = () -> {
            while (true) {
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();

                // DACA S-A INTRODUS LITERA "S" , STOPAM JOACA
                if(s.equalsIgnoreCase("s")){
                    System.out.println("STOP GAME!");
                    isActive=false;

                // CEREM ECHIPA DIN CARE VREM SA SCHIMBAM JUCATORUL
                    System.out.println("STOP PLAY\nALEGE ECHIPA: 1 SAU 2");
                    int a=sc.nextInt();
                    if(a==1){
                        echipa1.forEach(System.out::println);

                // ALEGEM JUCATORUL PE CARE VREM SA-L SCHIMBAM
                        System.out.println("Alege jucatorul pe care il schimbam [SCRIE NR.]:");
                    int id = sc.nextInt();

                    if(id!=0){
               // JUCATORUL ALES VA FI DESCALIFICAT,ASTFEL EL NU VA MAI PUTEA JUCA
                        jucatoriDescalificati.add(echipa1.get(id-1));
                        echipa1.remove(id-1);
                    }

                // AFISAM JUCATORII DE REZERVA , SI ALEGEM UNUL
                        echipaRez.forEach(System.out::println);
                        System.out.println("Alege jucatorul nou din echipa de rezerva [SCRIE NR.]:");
                        int idRez = sc.nextInt();


                        if(idRez!=0){
                            echipa1.add(echipaRez.get(idRez-1));
                            echipaRez.remove(idRez-1);
                // AFISAM NOUA LISTA DE JUCATORI
                            System.out.println("Lista noua cu jucatori din Echipa 1");
                            echipa1.forEach(System.out::println);
                            System.out.println("PRESS C TO CONTINUE!");
                            String c=sc.nextLine();
                            if(c.equalsIgnoreCase("c")) isActive=true;
                            }
                        }
                // ACEIASI PROCEDURA , DOAR CA PENTRU ECHIPA 2
                    else if (a==2){
                        echipa2.forEach(System.out::println);
                        System.out.println("Alege jucatorul pe care il schimbam [SCRIE NR.]:");
                        int id = sc.nextInt();

                        if(id!=0){
                            jucatoriDescalificati.add(echipa2.get(id-1));
                            echipa2.remove(id-1);
                        }
                        echipaRez.forEach(System.out::println);
                        System.out.println("Alege jucatorul nou din echipa de rezerva [SCRIE NR.]:");
                        int idRez = sc.nextInt();

                        if(idRez!=0){
                            echipa2.add(echipaRez.get(idRez-1));
                            echipaRez.remove(idRez-1);

                            System.out.println("Lista noua cu jucatori din Echipa 2");
                            echipa2.forEach(System.out::println);
                            System.out.println("PRESS C TO CONTINUE!");
                            Scanner scc=new Scanner(System.in);
                            String c=scc.nextLine();System.out.println("c="+c);

                            if(c.equalsIgnoreCase("c")) {
//                                techipa1.isAlive();
                                System.out.println("Reloading");
                                reload=true;
//                                isActive=true;
                              }
                           }
                        }
                    }
                }

//
            };   Thread stop = new Thread(x);
        stop.start();
        };

    }


//

//    }
//}





