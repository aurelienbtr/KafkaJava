package fr.objectware.entrainement.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.objectware.entrainement.Producer;
import fr.objectware.entrainement.config.AppConfig;
import fr.objectware.entrainement.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class TestController {

    private final Producer producer;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name") String name) {
        return name;
    }


    @GetMapping("/records") // num me sert a choisir le nombre de ligne que j'affiche
    public String records(@RequestParam(value = "num", defaultValue = "5") Long num) {
        ArrayList<String> resp = new ArrayList<String>();
        String thisLine = null;
        try {
            FileReader fr = new FileReader("src/main/resources/temp.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((thisLine = br.readLine()) != null) {
                resp.add(thisLine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp.stream().limit(num).collect(Collectors.toList()).toString();
    }


    @Autowired
    TestController(Producer producer)
    {
        this.producer =producer;
    }


    // ca me permet d'envoyer un objet Json depuis Postman, et le @KafkaListener ecoute
    @PostMapping(value="/publishJson", consumes = {"application/json"}, produces = {"application/json"})
    public String postJSonMsg(@RequestBody Player p) throws JSONException, JsonProcessingException {
       this.producer.sendMsg(p.playerJson());
        return "cest ok";
    }
}
