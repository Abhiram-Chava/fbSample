package pack2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

public class BugTest {
	public static void main(String[] args) {		// TODO Auto-generated method stub
		RestAssured.baseURI="https://chavaabhiram777.atlassian.net/";
		String createIssueResponse 	= given().header("Content-Type","application/json")
		.header("Authorization","Basic Y2hhdmFhYmhpcmFtNzc3QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBpckJvLTBxQ291NjU1Si1WMV91MHllRUM5UUc1b09qUTZNUVZwamhlQmsxQldBM3dGTkhZWVRZQ0E0eGdYME1YZ2JDanhfM1VyWDR6RlVhd3Z2X2Y1VTQtRGw4RDBSY19lcEtpM2RJdjE3a1VPM0V5X1ZmWTZjYmF4Q085ZEJNMU9zcFdRS290Qy1PSHhaT05tQ1g5VXFqQmY5bDJoQnBSYmJ3Q09PaE0tcms9NzgwRjg3N0Q=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Links are not working-Automation.\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "").log().all()
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).contentType("application/json")
		.extract().response().asString(); 
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);
		given().pathParam("key", issueId).header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic Y2hhdmFhYmhpcmFtNzc3QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBpckJvLTBxQ291NjU1Si1WMV91MHllRUM5UUc1b09qUTZNUVZwamhlQmsxQldBM3dGTkhZWVRZQ0E0eGdYME1YZ2JDanhfM1VyWDR6RlVhd3Z2X2Y1VTQtRGw4RDBSY19lcEtpM2RJdjE3a1VPM0V5X1ZmWTZjYmF4Q085ZEJNMU9zcFdRS290Qy1PSHhaT05tQ1g5VXFqQmY5bDJoQnBSYmJ3Q09PaE0tcms9NzgwRjg3N0Q=")
		.multiPart("file",new File("C:\\Users\\abhiramchava\\Pictures\\Screenshots\\Screenshot (34).png"))
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);			
	}
}