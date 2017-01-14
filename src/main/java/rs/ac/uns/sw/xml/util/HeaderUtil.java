package rs.ac.uns.sw.xml.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class HeaderUtil {
    // ========================== Header names ========================== //
    public static final String SCT_HEADER_ALERT = "X-SCT-Alert";
    public static final String SCT_HEADER_PARAMS = "X-SCT-Params";
    public static final String SCT_HEADER_ERROR_KEY = "X-SCT-Error-Key";

    // ===========================================  Common error messages =========================================== //
    public static final String ERROR_MSG_ID_ALREADY_EXIST = "Entity with this id already exists.";
    public static final String ERROR_MSG_WRONG_STATE = "State of parliament forbid action";
    public static final String ERROR_MSG_NO_ACTIVE_PARLIAMENT = "There is no active parliament.";

    // ====================== Error codes =============================
    public static final Integer ERROR_CODE_ID_ALREADY_EXIST = 1001;
    public static final Integer ERROR_CODE_WRONG_STATE = 1002;
    public static final Integer ERROR_CODE_WRONG_STATE_TRANSITION = 1003;
    public static final Integer ERROR_CODE_NO_ACTIVE_PARLIAMENT = 1004;


    public static HttpHeaders failure(String entityName, Integer errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SCT_HEADER_ALERT, defaultMessage);
        headers.add(SCT_HEADER_PARAMS, entityName);
        headers.add(SCT_HEADER_ERROR_KEY, errorKey.toString());
        return headers;
    }

    public static ResponseEntity<?> wrongState(String state) {
        return ResponseEntity
                .badRequest()
                .headers(HeaderUtil.failure(
                        state,
                        HeaderUtil.ERROR_CODE_WRONG_STATE,
                        HeaderUtil.ERROR_MSG_WRONG_STATE))
                .body(null);
    }
}
