/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentia.tutoriales.bolt;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import twitter4j.Status;

@Aspect
public class Asp {

        private ArrayList<Tuple> colaA = new ArrayList<>();
        private ArrayList<Tuple> colaB = new ArrayList<>();
        private long inicial = System.currentTimeMillis();

    
	@Around("execution(* com.autentia.tutoriales.bolt.TweetSplitterBolt.execute(..))")
	public Object metodo (ProceedingJoinPoint joinPoint) throws Throwable {
            synchronized(colaA){
                Object result=new Object();
                Object[] nuevo= joinPoint.getArgs();
                Tuple tupla=(Tuple) nuevo[0];
                String id = tupla.getSourceComponent();
                //mientras no pase el tiempo se encola
                if (System.currentTimeMillis()-inicial<15000){
                   if (id.equals("line-reader-spout")){
                   colaA.add(tupla);
                    }
                    //Si es B
                    else{
                        colaB.add(tupla);
                    }
                                    }
                //si ya paso el tiempo se procede a enviar streams en el orden correcto
                else{
                //Si es A
                    if (id.equals("line-reader-spout")){
                       colaA.add(tupla);
                       if (colaB.isEmpty()){
                           nuevo[0]= colaA.get(0);
                           colaA.remove(0);
                           result= joinPoint.proceed(nuevo);

                       }
                       else{
                           nuevo[0]= colaB.get(0);
                           colaB.remove(0);
                           result= joinPoint.proceed(nuevo);

                       }
                    }
                    //Si es B
                    else{
                        colaB.add(tupla);
                        nuevo[0]= colaB.get(0);
                        colaB.remove(0);
                        result= joinPoint.proceed(nuevo);
                    }
                }
                return result;
	}
        }

}