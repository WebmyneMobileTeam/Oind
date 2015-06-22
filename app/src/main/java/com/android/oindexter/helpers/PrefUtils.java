package com.android.oindexter.helpers;

import android.content.Context;
import android.graphics.Typeface;

import com.android.oindexter.model.User;


/**
 * Created by xitij on 17-03-2015.
 */
public class PrefUtils {


    public static boolean checkLogin(Context ctx){
        boolean isLoggedIn = com.android.oindexter.helpers.Prefs.with(ctx).getBoolean("isLogged",false);
        return isLoggedIn;
    }

    public static void setLogin(Context ctx, boolean value){
        Prefs.with(ctx).save("isLogged",value);
    }

    public static void setUser(Context ctx,User user){
        ComplexPreferences pres = ComplexPreferences.getComplexPreferences(ctx,"user",0);
        pres.putObject("current_user",user);
        pres.commit();
    }

    public static User getUser(Context ctx){

        ComplexPreferences pres = ComplexPreferences.getComplexPreferences(ctx,"user",0);
        User currentUser = pres.getObject("current_user",User.class);
        return currentUser;
    }



}
