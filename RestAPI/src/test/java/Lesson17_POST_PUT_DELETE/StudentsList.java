package Lesson17_POST_PUT_DELETE;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class StudentsList {

    String url = "http://localhost:9000";

    public RequestSpecification httpRequest;
    public Response response;
    JsonPath jp;

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
    }

    @Test
    public void test01() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Dakota");
        params.put("lastName", "Reed");
        params.put("email", "dakota.reed.28@gmail.com");
        params.put("programme", "QA Automation");

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        if (response.getBody().asString().contains("error")) {
            response = httpRequest.get("http://localhost:9000/student/list");
            jp = response.jsonPath();
            List<String> allStudentsEmail = jp.getList("email");
            List<String> allStudentsId = jp.getList("id");
            for (int i=0; i < allStudentsEmail.size(); i++) {
                if (allStudentsEmail.get(i).equals("dakota.reed.28@gmail.com")) {
                    String idOfStudent = String.valueOf(allStudentsId.get(i));
                    response = httpRequest.delete("/student/"+idOfStudent);
                    System.out.println(response.getBody().asString()+"DELETED");
                }
            }
        }

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        assertEquals(response.getStatusCode(), 201);
    }
    @Test
    public void test02() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Kuku");
        params.put("lastName", "Lolo");
        params.put("email", "kuku_lolo@gmail.com");
        params.put("programme", "QA Automation");
        params.put("courses", Arrays.asList("Java Course", "CSharp Course", "Python Course"));

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        if (response.getBody().asString().contains("error")) {
            response = httpRequest.get("http://localhost:9000/student/list");
            jp = response.jsonPath();
            List<String> allStudentsEmail = jp.getList("email");
            List<String> allStudentsId = jp.getList("id");
            for (int i=0; i < allStudentsEmail.size(); i++) {
                if (allStudentsEmail.get(i).equals("kuku_lolo@gmail.com")) {
                    String idOfStudent = String.valueOf(allStudentsId.get(i));
                    response = httpRequest.delete("/student/"+idOfStudent);
                    System.out.println(response.getBody().asString()+"DELETED");
                }
            }
        }

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        assertEquals(response.getStatusCode(), 201);
    }
    @Test
    public void test03() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Kuku");
        params.put("lastName", "Lolo");
        params.put("email", "kuku_lolo@gmail.com");
        params.put("programme", "QA Automation");
        params.put("courses", Arrays.asList("Java Course", "CSharp Course", "Python Course"));
        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        response = httpRequest.get("http://localhost:9000/student/list");
        jp = response.jsonPath();
        List<String> allStudents = jp.getList("email");
        List<String> allStudentsId = jp.getList("id");
        for (int i=0; i < allStudents.size(); i++) {
            if (allStudents.get(i).equals("kuku_lolo@gmail.com")) {
                String idOfStudent = String.valueOf(allStudentsId.get(i));
                JSONObject params1 = new JSONObject();
                params1.put("firstName", "Kuku");
                params1.put("lastName", "Lolo");
                params1.put("email", "kuku_lolo@gmail.com");
                params1.put("programme", "Full Stack");
                params1.put("courses", Arrays.asList("Java Course", "CSharp Course", "Python Course"));
                httpRequest.body(params1.toJSONString());
                response = httpRequest.put("/student/"+idOfStudent);
                System.out.println(response.getBody().asString());
            }
        }

        assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void test04() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Kuku");
        params.put("lastName", "Lolo");
        params.put("email", "kuku_lolo@gmail.com");
        params.put("programme", "QA Automation");
        params.put("courses", Arrays.asList("Java Course", "CSharp Course", "Python Course"));
        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");
        System.out.println(response.getBody().asString());

        response = httpRequest.get("http://localhost:9000/student/list");
        jp = response.jsonPath();
        List<String> allStudentsEmail = jp.getList("email");
        List<String> allStudentsId = jp.getList("id");
        for (int i=0; i < allStudentsEmail.size(); i++) {
            if (allStudentsEmail.get(i).equals("kuku_lolo@gmail.com")) {
                String idOfStudent = String.valueOf(allStudentsId.get(i));
                response = httpRequest.delete("/student/"+idOfStudent);
                System.out.println(response.getBody().asString()+"DELETED");
                }
        }
//        response = httpRequest.delete("/student/102");

        assertEquals(response.getStatusCode(), 204);

//        public void writeCSV(String url, String value) throws IOException {
//            FileWriter pw = new FileWriter("./results.csv",true);
//            StringBuilder sb = new StringBuilder();
//            sb.append(url);
//            sb.append(',');
//            sb.append(value.replace(",", ""));
//            sb.append('\n');
//            pw.write(sb.toString());
//            pw.close();
//        }


//        try
//        {
//            JsonNode jsonTree = new ObjectMapper().readTree(response.getBody().asString()).get("url");
//            CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//            JsonNode firstObject = jsonTree.elements().next();
//            firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
////                jsonTree.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
//            CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//            CsvMapper csvMapper = new CsvMapper();
//            csvMapper.writerFor(JsonNode.class)
//                    .with(csvSchema)
//                    .writeValue(new File("E:\\Automation\\Project__RestAPI_ChuckNorris\\CSVFiles\\jokes.csv"), jsonTree);
//
//        }   catch(Exception ex)
//        { throw ex; }
//
//        <dependency>
//            <groupId>com.fasterxml.jackson.dataformat</groupId>
//            <artifactId>jackson-dataformat-csv</artifactId>
//            <version>2.13.3</version>
//        </dependency>
    }

}
