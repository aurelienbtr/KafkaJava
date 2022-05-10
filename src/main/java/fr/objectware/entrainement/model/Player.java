package fr.objectware.entrainement.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    public String firstname;
    public String lastname;
    public String team;
    public int number;
    public int age;

    public String playerJson() throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }



}
