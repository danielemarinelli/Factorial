package core.API;

import org.openqa.selenium.remote.http.HttpMethod;

public class APIMethods {

    public static APIMethod GET_API = new APIMethod(Environments.getEndPoint(), "/factorial", HttpMethod.GET);
   }
