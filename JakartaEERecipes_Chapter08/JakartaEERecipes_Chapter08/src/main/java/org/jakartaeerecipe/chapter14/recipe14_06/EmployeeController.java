package org.jakartaeerecipe.chapter14.recipe14_06;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import org.jakartaeerecipe.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Named("employeeJsonController")
@RequestScoped
public class EmployeeController {

    private String lastSearchText;

    private String searchResult;

    List<Employee> employees;

    public EmployeeController() {
    }

    public String fetchJson(){
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/JakartaEERecipes_Chapter08-1.0-SNAPSHOT/rest/org.jakartaeerecipe.entity.employee");

        employees = (target.request(jakarta.ws.rs.core.MediaType.APPLICATION_XML)
                .get(new GenericType<List<Employee>>() { }));
        System.out.println("Items: " + employees);
        Jsonb jsonb = JsonbBuilder.create();
        String result = null;

        result = jsonb.toJson(employees);
        System.out.println("JSON String: " + result);
        return result;
    }

    public List<Employee> fetchJavaFromJson(){
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/JakartaEERecipes_Chapter08-1.0-SNAPSHOT/rest/org.jakartaeerecipe.entity.employee");

        String employeesJson = (target.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                .get( new GenericType<String>() { }));
        System.out.println("Items: " + employeesJson);
        Jsonb jsonb = JsonbBuilder.create();
        employees = jsonb.fromJson(employeesJson, ArrayList.class);
        // Specific Generic type conversion:
        // employees = jsonb.fromJson(employeesJson, new ArrayList<Employee>(){}.getClass().getGenericSuperclass());
        return employees;
    }

    public void findEmployeeByLast() {
        setSearchResult(null);
        String text = "/" + this.lastSearchText;
        JsonObject json = Json.createObjectBuilder().build();
        JsonValue object = json.getJsonObject(fetchJson());
        if (lastSearchText != null && object != null) {
            JsonPointer pointer = Json.createPointer(text);
            System.out.println("text: " + text + pointer);
            System.out.println("json: " + object);
            JsonValue result = pointer.getValue(object.asJsonArray());

            // Replace a value
            JsonArray array = (JsonArray) pointer.replace(object.asJsonArray(), Json.createValue("JsonMaster"));
            setSearchResult(array.toString());
        }
    }

    /**
     * @return the lastSearchText
     */
    public String getLastSearchText() {
        return lastSearchText;
    }

    /**
     * @param lastSearchText the lastSearchText to set
     */
    public void setLastSearchText(String lastSearchText) {
        this.lastSearchText = lastSearchText;
    }

    /**
     * @return the searchResult
     */
    public String getSearchResult() {
        return searchResult;
    }

    /**
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }
}