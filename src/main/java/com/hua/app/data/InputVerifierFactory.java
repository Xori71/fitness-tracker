package com.hua.app.data;

public class InputVerifierFactory {
    public static CustomInputVerifier createInputVerifier(String regex) {
        return new CustomInputVerifier(regex);
    }
}