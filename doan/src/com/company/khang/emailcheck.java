package com.company.khang;

public class emailcheck {
    public static void check(String str) throws EmailAddressException{
        int place=str.indexOf('@');
        if(place==-1){
            throw new EmailAddressException(" email khong hop le do khong chua '@' ");
        }
        if(place==0){
            throw new EmailAddressException("email khong hop le do chua ' @' o vi tri dau tien");
        }
        place=str.indexOf('@',place+1);
        if(place!=-1){
            throw new EmailAddressException("email khong hop le do co hon 1 @");
        }
    }
}
