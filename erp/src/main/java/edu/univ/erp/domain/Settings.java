package edu.univ.erp.domain;

public class Settings
{
    private String key; // eg. maintenance_mode
    private String value; // eg. ON/OFF

    //constructor
    public Settings(String key, String value)
    {
    this.key = key;
    this.value = value;
    }

    // Getters
    public String getKey() { return key; }
    public String getValue() { return value; }

    // Setters
    public void setKey(String key) { this.key = key; }
    public void setValue(String value) { this.value = value; }
}
