package com.melihcan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"Şifreler uyuşmuyor.",BAD_REQUEST),
    TICKET_NAME_ERROR(1302,"ticket bulunamadı..",BAD_REQUEST),

    NO_MORE_TICKETS(1304,"BILETLER TÜKENMİŞTİR...",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301,"Aradığınız id ye ait kullanıcı bulunamamıştır",INTERNAL_SERVER_ERROR),

    INVALID_TOKEN(4001,"GEÇERSİZ TOKEN BİLGİSİ",BAD_REQUEST);


    private int code;

    private String message;

    private HttpStatus httpStatus;
}
