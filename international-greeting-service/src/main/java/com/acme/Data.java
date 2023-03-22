package com.acme;

final class Data {

    private final String language;
    private final String name;

    public Data(String language, String name) {
        this.language = language;
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Data{" +
                       "language='" + language + '\'' +
                       ", name='" + name + '\'' +
                       '}';
    }
}
