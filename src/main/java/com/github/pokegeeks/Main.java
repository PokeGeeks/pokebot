package com.github.pokegeeks;

import com.pokegoapi.auth.GoogleUserCredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;
import okhttp3.OkHttpClient;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Pokebot's entry point.
 */
public class Main {
    public static void main(String[] args) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        try {
            // instanciate a provider, it will give an url
            GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(httpClient);

            // in this url, you will get a code for the google account that is logged
            System.out.println("Please go to " + provider.LOGIN_URL);
            System.out.println("Enter authorisation code:");

            // Ask the user to enter it in the standart input
            Scanner sc = new Scanner(System.in);
            String access = sc.nextLine();

            // we should be able to login with this token
            provider.login(access);
            System.out.println("Refresh token:" + provider.getRefreshToken());

        } catch (RemoteServerException | LoginFailedException e) {
            e.printStackTrace();
        }
    }
}
