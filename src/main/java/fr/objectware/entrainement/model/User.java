package fr.objectware.entrainement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String arobase;
    String pseudo;
    int nbFollowers;
    int nbFollowing;
    List<Messages> msgList;


}
