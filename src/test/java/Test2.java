import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Test2 {
    private static String baseUrl = "http://localhost:4545";

    @Test
    public void getOAuthToken() {
      Response response =
              given().
                    contentType(ContentType.TEXT).
                    queryParam("mjx_server", "toad-mb-08").
                    header("Authorization", "Bearer 7584c302-9c16-44af-956a-b25fd42f0bf0").
                    body("grant_type=authorization_code&client_secret=cc8bbcd2b1cc2bba4f003dec25aadc4469322cff12aa3ac8e0346cdd405f9742&client_id=e19f67dc-af89-4610-8d67-faa1fecb89ed").
              when()
                    .post(baseUrl + "/4-6-x-snap/oauth2/oauth/token").
              then()
                   .assertThat().statusCode(200).extract().response();

        Assertions.assertEquals("19a05887-e396-4274-89c3-6cf10ebd64fc", response.getHeader("X-XSRF-TOKEN"));
    }

    @Test
    public void getImposters() {
        Response response =
                given()
                        .contentType(ContentType.JSON).
                when()
                        .get("http://localhost:2525/imposters/4545").
                then()
                        .assertThat().statusCode(200).extract().response();

        Assertions.assertEquals(true, response.getBody().asString().contains("http"));
    }

    @Test
    public void getOAuth() {
        String location = "urn:ietf:wg:oauth:2.0:oob#access_token=7371ea17-b3ee-4ea6-9a7d-82e86d38c01e&token_type=bearer&expires_in=3599&scope=all";

        Response response =
                given().
                        redirects().follow(false).
                        queryParam("response_type", "token").
                        queryParam("redirect_uri", "urn:ietf3Awg3Aoauth3A2.03Aoob").
                        queryParam("mjx_server", "toad-mb-08").
                        queryParam("client_id", "fisv1_implicit_testapi_4.3.0").
                        queryParam("orgcode", "ccalocator")
                .when()
                        .get(baseUrl + "/4-6-x-snap/oauth2/oauth/authorize").
                then()
                        .assertThat().statusCode(302).extract().response();

        Assertions.assertNotNull(response.getHeader("Location"));

        Assertions.assertEquals("MBanking", response.getHeader("Server"));
        Assertions.assertEquals(location, response.getHeader("Location"));
    }

    @Test
    public void getClientDetails() {
        Response response =
                given().
                        queryParam("mjx_server", "toad-mb-08")
                        .header("Authorization", "Bearer 7371ea17-b3ee-4ea6-9a7d-82e86d38c01e")
                        .header("Accept", "application/json; charset=UTF-8")
                        .body("redirectUri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&grantTypes=client_credentials&serviceIds=mbank-locatorsearch&scopes=dsf&authorities=ROLE_CLIENT")
                .when()
                        .post(baseUrl + "/4-6-x-snap/oauth2/authenticate/clientdetails")
                .then()
                        .assertThat().statusCode(200).extract().response();

        Assertions.assertNotNull(response.getHeader("client_secret"));
        Assertions.assertNotNull(response.getHeader("client_id"));

        Assertions.assertEquals("cc8bbcd2b1cc2bba4f003dec25aadc4469322cff12aa3ac8e0346cdd405f9742", response.getHeader("client_secret"));
        Assertions.assertEquals("e19f67dc-af89-4610-8d67-faa1fecb89ed", response.getHeader("client_id"));
    }
}
