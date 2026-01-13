package com.hua.app.utilities.userinterface.data;

public class InputVerifierFactory {
    public static CustomInputVerifier createInputVerifier(String regex) {
        return new CustomInputVerifier(regex);
    }
}