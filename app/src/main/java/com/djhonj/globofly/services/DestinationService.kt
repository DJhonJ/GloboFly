package com.djhonj.globofly.services

import models.Comment
import retrofit2.Call
import retrofit2.http.GET

interface DestinationService {

    //Call: metodo para enviar una solicitud a un servidor web y recibir una respuesta
    //la llamada se puede ejecutar de forma sincrona con execute o de manetra asincrona con enqueue(retrofit2.Callback<T>)
    // la solicitud se puede cancelar con "cancel()"
    // Una llamada que está ocupada escribiendo su solicitud o leyendo su respuesta puede recibir un IOException;

    @GET("posts/1/comments")
    fun getDestinationList() : Call<List<Comment>>
}