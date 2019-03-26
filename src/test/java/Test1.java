import DTO.CategoryDTO;
import DTO.PetDTO;
import DTO.TagsDTO;

import com.google.gson.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Util;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Test1 {
    private static PetDTO pet;
    private static CategoryDTO category;
    private static TagsDTO tagDto;

    private static Gson gson;

    private static String baseUrl = "http://petstore.swagger.io/v2";

    @Test
    public void GETCode() {
        given()
                .when().get("http://api.vidible.tv/company").then().statusCode(404);

    }

    @Test
    public void GetPetById() {
        JsonObject object = new JsonParser().parse(GetMethod(1).getBody().asString()).getAsJsonObject();
        String category_name = object.getAsJsonObject("category").get("name").getAsString();

        JsonArray tags = object.getAsJsonArray("tags");

        Assertions.assertEquals(200, GetMethod(1).getStatusCode());
        Assertions.assertNotNull(GetMethod(1).getBody());

        Assertions.assertEquals("available", object.getAsJsonObject().get("status").getAsString());
        Assertions.assertEquals("frog", category_name);

        for(int i = 0; i < tags.size(); i++) {
            String tag_name1 = tags.get(i).getAsJsonObject().get("name").getAsString();
            Assertions.assertEquals("pepin", tag_name1);
        }

        //ResponseBody body = response.body();
        //String pet = body.asString();

        //JsonPath jpath = new JsonPath(pet);

        //Assert.assertEquals("pepe", response.getBody());
        //Assert.assertEquals(true, pet.contains("idk"));

    }


    @Test
    public void addPet() {
        gson = new GsonBuilder().create();

        ArrayList<String> photos = new ArrayList<String>();
        photos.add("peperip.png");

        Util util = new Util();

        pet = util.
        fillPet(1, util.fillCategory(1, "frog"), "pepe", photos, util.fillTaglist(1, "pepin"), "available");


        System.out.println(gson.toJson(pet));

        Response response =
        given()
                .contentType(ContentType.JSON).
                body(pet).
        when()
                .post(baseUrl + "/pet").
        then()
                .statusCode(200)
                .extract().response();

    }

    @Test
    public void GetPetResponse() {
        gson = new GsonBuilder().create();

        PetDTO petDto = gson.fromJson(GetMethod(3).body().asString(), PetDTO.class);

        System.out.println(gson.toJson(petDto));

        Assertions.assertEquals("pepe", petDto.getName());
        Assertions.assertEquals("frog", petDto.getCategory().getName());


        //JsonObject object = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();

        /*int id = object.getAsJsonObject().get("id").getAsInt();
        String nombre = object.getAsJsonObject().get("name").getAsString();
        String status = object.getAsJsonObject().get("status").getAsString();
        
        JsonArray photoUrls = object.getAsJsonArray("photoUrls");
        ArrayList<String> photos = new ArrayList<String>();
        for(int i = 0; i < photoUrls.size(); i++) {
            String photo = photoUrls.get(0).getAsString();
            //System.out.println(photo);

            photos.add(photo);
        }*/
            
        //JsonArray tags = object.getAsJsonArray("tags");

        //ArrayList<TagsDTO> tag = new ArrayList<TagsDTO>();;

        /*for(int i = 0; i < tags.size(); i++) {
            int tag_id = tags.get(i).getAsJsonObject().get("id").getAsInt();
            String tag_name = tags.get(i).getAsJsonObject().get("name").getAsString();

            tagDto = new TagsDTO(tag_id, tag_name);
            tag.add(tagDto);
        }*/
        
        //int category_id = object.getAsJsonObject("category").get("id").getAsInt();
        //String category_name = object.getAsJsonObject("category").get("name").getAsString();

        //category = new CategoryDTO(category_id, category_name);


        //System.out.println(category_id + category_name);
        //pet = new PetDTO(id, category, nombre, photos, tag, status);
        //System.out.println(response.body().asString());

    }

    @Test
    public void EditPet() {
        JsonObject object = new JsonParser().parse(GetMethod(1).getBody().asString()).getAsJsonObject();

        gson = new GsonBuilder().create();

        ArrayList<String> photos = new ArrayList<String>();
        photos.add("peperip.png");

        Util util = new Util();

        pet = util.
                fillPet(1, util.fillCategory(1, "frog"), "popedopamine", photos, util.fillTaglist(1, "pepin"), "available");

        Response response =
                given()
                        .contentType(ContentType.JSON).
                        body(pet)
                .when()
                        .put(baseUrl + "/pet")
                .then().
                        statusCode(200).
                        extract().response();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("popedopamine", object.getAsJsonObject().get("name").getAsString());

    }

    @Test
    public void DeletePet() {
        Response response =
                given().
                        contentType(ContentType.JSON).
                        pathParam("petId", 3).
                when()
                        .delete(baseUrl + "/pet/{petId}")
                .then()
                        .statusCode(200)
                        .extract().response();

        Assertions.assertEquals("", response.body().asString());
    }

    public Response GetMethod(int id) {
        Response response =

                given().
                        contentType(ContentType.JSON).
                        pathParam("petId", id).
                when().
                        get(baseUrl + "/pet/{petId}").
                then().
                        statusCode(200).
                        extract().response();

        return response;
    }

    @Test
    public void Mountebank() {
        Response response =
                given().
                        queryParam("response", "token").
                        when()
                        .get("http://localhost:4545/4-6-x-snap/oauth2/oauth/authorize")
                        .then()
                        .statusCode(302)
                        .extract().response();

    }

}
