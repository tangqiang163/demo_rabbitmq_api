package com.example.demo_rabbitmq_api.Demo.dxl;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;
import java.util.List;

public class DemoTest {

    public static void main(String[] args) throws Exception {

        System.out.println(test02());
    }

    public static void test(int n,int m){

        List list =new ArrayList();

        for (int i = 0; i < n; i++) {
            list.add(0);
        }

        int j = 1;
        int count = 0;
        for (int i = 0; i < n * n ; i++) {

            if(count == n-1){
                break;
            }

            int index = i%n;

            if(j == m){



                if((int)list.get(index) == 0){

                    j = 1;
                    count++;
                    list.set(index,1);

                }


                continue;
            }

            if((int)list.get(index) != 1){
                j++;
            }




        }


        for (int i = 0; i <n ; i++) {
            if ((int)list.get(i) == 0){
                System.out.println(i+1);
            }
        }

    }

    public static int  test02(){
        try {

            return 1;
        }catch (Exception EX){
            return 0;
        }finally {
            System.out.println("11");
        }
    }
}
