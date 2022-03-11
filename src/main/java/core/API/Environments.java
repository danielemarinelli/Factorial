package core.API;

import static core.TestConfig.ENV;

public class Environments {

    private static String endPoint;

    public static void load() {
        if (ENV.equalsIgnoreCase("DEV")) {
            endPoint = "https://qainterview.pythonanywhere.com";
        }

        if (ENV.equalsIgnoreCase("INT")) {
            endPoint = "https://qainterview.pythonanywhere.com";
        }

        if (ENV.equalsIgnoreCase("PREPROD")) {
            endPoint = "https://qainterview.pythonanywhere.com";
        }

        if (ENV.equalsIgnoreCase("PROD")) {
            endPoint = "https://qainterview.pythonanywhere.com";
        }
    }

    public static String getEndPoint() {
        return endPoint;
    }


}
