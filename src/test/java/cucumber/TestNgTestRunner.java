package cucumber;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",
				  glue="VinitCom.Stepdefinition",
				  monochrome=true,
				  tags="@Purchaseorder",
				  plugin= {"html:target/cucmber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests {

/*Temp public List<HashMap<String,String>> getJsonDataToMap(String FilePath) throws IOException{
		
		//Read json to string
		
		String jsonContent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8 );
		
		//String to HashMap (need Jackson databind dependancy)
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
		{});
		return data;*/
	
}
