package cn.code.zeus.practice.request

import java.util.concurrent.Executors

class RequestQueue constructor(){

    companion object{
        private val pool = Executors.newFixedThreadPool(16)

         fun push(request:IRequest){

        }


    }
}