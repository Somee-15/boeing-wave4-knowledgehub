package com.stackroute.service;

import com.stackroute.domain.IntentWord;
import com.stackroute.domain.TermNode;
import com.stackroute.repository.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IntentServiceImpl implements IntentService {
    private IntentRepository intentRepository;
    private ArrayList<TermNode> allTermNodes;

    @Autowired
    public IntentServiceImpl(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
        this.allTermNodes = new ArrayList<>(intentRepository.getAllTermNodes());
    }

    public ArrayList<IntentWord> getAllIntentWords() {
        ArrayList<IntentWord> allIntentWords = new ArrayList<>();
        for (int i = 0; i < allTermNodes.size(); i++) {
            allIntentWords.add(new IntentWord(allTermNodes.get(i).getName(),
                    0,
                    allTermNodes.get(i).getParent_node_type(),
                    allTermNodes.get(i).getRelationship(),
                    Double.parseDouble(allTermNodes.get(i).getWeight())));
        }
        return allIntentWords;
    }

    public void setAllTermNodes(ArrayList<TermNode> allTermNodes) {
        this.allTermNodes = allTermNodes;
    }
}




