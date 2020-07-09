package com.hdapp.test2.data.api.networkintercepter;

public interface CryptoStrategy {
    String encrypt(String body) throws Exception;

    String decrypt(String data) throws Exception;

}