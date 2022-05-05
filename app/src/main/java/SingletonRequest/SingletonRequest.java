package SingletonRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequest {
    private static SingletonRequest mySingleTon;//objeto dentro de un objeto del mismo tipo
    private RequestQueue requestQueue;
    private static Context mctx;

    //el private del constructor no permite crear una instancia
    private SingletonRequest(Context context){
        this.mctx=context;
        this.requestQueue=getRequestQueue();
    }
    //con la existencia de la instancia, desencadena el mismo funcionamiento que el metodo getInstance
    //cartero
    private RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
    //static=metodo de clase. no repite el metodo en la creacion de multihilos, solo corre una vez
    //caja donde va el cartero
    public static synchronized SingletonRequest getInstance(Context context){//para controlar la existencia del objeto
        if(mySingleTon==null){
            mySingleTon=new SingletonRequest((context));
        }
        return mySingleTon;
    }
    //agregar tipo de carta al cartero
    //<T> se refiere a tipo de request, para que se puedan enviar varias cartas
    public <T> void addToRequesQue(Request<T> request){
        requestQueue.add(request);
    }
    //crear carta y pasarsela al cartero desde el main

}
