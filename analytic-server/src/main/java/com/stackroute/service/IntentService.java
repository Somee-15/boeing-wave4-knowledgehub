package com.stackroute.service;

import com.stackroute.domain.IntentWord;
import com.stackroute.domain.TermNode;

import java.util.ArrayList;
import java.util.Collection;

public interface IntentService {
    public Collection<IntentWord> getAllIntentWords();

    public void setAllTermNodes(ArrayList<TermNode> allTermNodes);
}
