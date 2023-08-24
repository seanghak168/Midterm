package com.example.iteonlineshop.api.service;

        import com.example.iteonlineshop.api.model.Culture;
        import com.example.iteonlineshop.api.model.Museum;
        import com.example.iteonlineshop.api.model.PhnomPenh;
        import com.example.iteonlineshop.api.model.Popular_visit;
        import com.example.iteonlineshop.api.model.Profiles;
        import com.example.iteonlineshop.api.model.Temple;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface ApiService {
    @GET("/seanghak168/JsonApi/main/Popular_visit.json")
    Call<List<Popular_visit>> LoadPopularvisitList();
    @GET("/seanghak168/JsonApi/main/Culture.json")
    Call<List<Culture>> LoadCulturevisitList();
    @GET("/seanghak168/JsonApi/main/Temple.json")
    Call<List<Temple>> LoadTempleList();
    @GET("/seanghak168/JsonApi/main/museum.json")
    Call<List<Museum>> LoadMuseumList();
    @GET("seanghak168/JsonApi/main/Visit_Phnompenh.json")
    Call<List<PhnomPenh>> LoadPhnompenhvisitList();
    @GET("/kimsongsao/ferupp/main/profile.json")
    Call <Profiles> loadProfile();
}
