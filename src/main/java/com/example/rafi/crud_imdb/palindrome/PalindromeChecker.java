package com.example.rafi.crud_imdb.palindrome;

public class PalindromeChecker {
    public static void main(String[] args) {
        String text = "Katak";

        String reversedText = ""; // Inisialisasi variabel reversedText untuk menyimpan string hasil pembalikan text

        for (int i = text.length() - 1; i >= 0; i--) { // Loop untuk membalik string text
            char lastChar = text.charAt(i); // Mengambil karakter dari text mulai dari belakang
            reversedText += String.valueOf(lastChar); // Menambahkan karakter ke string reversedText
            // Setelah loop, reversedText akan menjadi "kataK"
        }

        // Membandingkan lowecase dari string text dan reversedText
        if (text.toLowerCase().equals(reversedText.toLowerCase())) {
            System.out.println("Text adalah palindrome");
        } else {
            System.out.println("Text bukan palindrome");
        }

    }
}
