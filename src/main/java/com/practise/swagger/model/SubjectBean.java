package com.practise.swagger.model;

import org.springframework.stereotype.Component;

/**
 * Created by Pratik Ambani on 8/10/2017.
 */
@Component
public class SubjectBean implements Subject {

    private String subject;

    @Override
    public String getSubject(){
        return  subject;
    }
}
