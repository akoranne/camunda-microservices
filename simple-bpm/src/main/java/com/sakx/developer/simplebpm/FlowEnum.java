package com.sakx.developer.simplebpm;

import lombok.Getter;

@Getter
public enum FlowEnum
{
    HELLO_FLOW ("Hello", "hello.bpmn"),
    ;
 
    @SuppressWarnings("unused")
	private String name;
    @SuppressWarnings("unused")
	private String fname;
 
    FlowEnum(String name, String fname) {
        this.name = name;
        this.fname = fname;
    }
 
}