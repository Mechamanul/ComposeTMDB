package com.mechamanul.composetmdb.data.utils
import com.google.gson.annotations.SerializedName
class ResponseWrapper {



    data class WrappedListResponse<T> (
        //common
        var code: Int,
        //401,404
        @SerializedName("status_code")var statusCode:Int?=null,
        @SerializedName("status_message")var statusMessage:String?=null,
        @SerializedName("success")var success:Boolean?=null,
        //200
        @SerializedName("page") var int : Int?=null,
        @SerializedName("results") var results : List<T>? = null,
        @SerializedName("total_results") var total_results : Int?=null,
        @SerializedName("total_pages") var total_pages : Int? = null,
    )


//    data class WrappedResponse<T> (
//        var code: Int,
//        @SerializedName("message") var message : String,
//        @SerializedName("status") var status : Boolean,
//        @SerializedName("errors") var errors : List<String>? = null,
//        @SerializedName("data") var data : T? = null
//    )
}