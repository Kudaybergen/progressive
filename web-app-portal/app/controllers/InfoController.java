package controllers;

import models.Info;
import play.mvc.Controller;

/**
 * Created by tima on 10/21/15.
 */
public class InfoController extends Controller {
    public static void about() {
        Info info = Info.find("info_type  like ?","about").first();
        render(info);
    }

    public static void faq() {
        Info info = Info.find("info_type  like ?","faq").first();
        render(info);
    }

    public static void contacts() {
        Info info = Info.find("info_type  like ?","contacts").first();
        render(info);
    }
}
