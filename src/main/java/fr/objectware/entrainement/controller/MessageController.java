package fr.objectware.entrainement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.objectware.entrainement.Consummer;
import fr.objectware.entrainement.Producer;
import fr.objectware.entrainement.model.Messages;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")

public class MessageController {


    private final Producer producer;
    private final Consummer consummer;
    private int increment = 0;

    @Autowired
    MessageController(Producer producer, Consummer consummer) {
        this.producer = producer;
        this.consummer = consummer;
    }


    @PostMapping(value = "/publish", consumes = {"application/json"}, produces = {"application/json"})
    public String postJSonMsg(@RequestBody Messages message) throws JsonProcessingException, JSONException {
        message.setOrdre(increment);


        if (message.getUserPseudo().equals("@bertouune")) {
            this.producer.sendMsg(message.messageJson());
            //sur un topic
        } else this.producer.sendMsg2(message.messageJson());
        //sur un autre topic

        increment++;
        return "message envoyé! :)";
    }


    @GetMapping(value = "/listMessageBertouune", produces = {"application/json"})
    public List<Messages> listMsg() {
        return this.consummer.ListMsg();
    }

    @GetMapping(value = "/listMessageAutre")
    public List<Messages> listMsg2() {
        return this.consummer.ListMsg2();
    }


    @GetMapping(value = "/conversation")
    public List<Messages> allMsg() {
        return this.consummer.getAllMsg();
    }


    @GetMapping(value = "/conversation/{user1}/{user2}")
    public List<Messages> allMsg(@PathVariable String user1, @PathVariable String user2) {
        return this.consummer.getAllMsgBetween2(user1,user2);
    }
}


