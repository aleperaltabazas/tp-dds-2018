package DDS.SGE.Web.Controllers;

public class Routes {
    public static final String HOME = "/";

    public static final String LOGIN = "/login";

    public static final String HOGAR = "/hogar";

    public static final String OPTIMIZADOR = "/optimizador";

    public static final String ADMINISTRADOR = "/administrador";
    public static final String ADMINISTRADOR_LOGIN = ADMINISTRADOR + "/login";

    public static final String HOGARES = "/hogares";
    public static final String HOGARES_ID = HOGARES + "/:id";

    public static final String SOLICITUDES = "/solicitudes";
    public static final String SOLICITUDES_ID = SOLICITUDES + "/:id";
    public static final String SOLICITUDES_ID_INFO = SOLICITUDES_ID + "/info";
    public static final String SOLICITUDES_ID_ACCEPT = SOLICITUDES_ID + "/accept";
    public static final String SOLICITUDES_ID_REJECT = SOLICITUDES_ID + "/reject";

    public static final String DISPOSITIVOS = "/dispositivos";
    public static final String DISPOSITIVOS_PAGE = "/dispositivos/:page";

    public static final String DISPOSITIVOS_ID_ACQUIRE = DISPOSITIVOS + "/:id/acquire";
    public static final String DISPOSITIVOS_ID_INFO = DISPOSITIVOS + "/:id/info";

    private static final String DISPOSITIVOS_NEW = DISPOSITIVOS + "/new";
    public static final String DISPOSITIVOS_NEW_INTELIGENTE = DISPOSITIVOS_NEW + "/inteligente";
    public static final String DISPOSITIVOS_NEW_ESTANDAR = DISPOSITIVOS_NEW + "/estandar";

    public static final String TRANSFORMADOR = "/transformador";

    public static final String USER = "/me";
    public static final String USER_EDIT = USER + "/edit";
    public static final String USER_DISPOSITIVOS = USER + "/dispositivos";
    public static final String USER_DISPOSITIVOS_ID_ON = USER_DISPOSITIVOS + "/:id/on";
    public static final String USER_DISPOSITIVOS_ID_OFF = USER_DISPOSITIVOS + "/:id/off";
    public static final String USER_DISPOSITIVOS_ID_SAVE = USER_DISPOSITIVOS + "/:id/save";

    public static final String SIGNUP = "/signup";

    public static final String CONSUMO = "/consumo";
    public static final String CONSUMO_OBTENER = CONSUMO + "/obtener";

    public static final String LOGOUT = "/logout";

    public static final String LIFE = "/42";

    public static final String GLITCH = "*";
}
