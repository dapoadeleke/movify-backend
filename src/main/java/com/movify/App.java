package com.movify;

import com.movify.controller.AuthController;
import com.movify.controller.MovieController;
import com.movify.dto.ServiceResponse;
import com.movify.model.User;
import com.movify.security.SecurityConstants;
import com.movify.service.CacheService;
import com.movify.utils.CustomException;
import com.movify.utils.Message;
import org.apache.commons.lang3.StringUtils;
import org.jooby.Jooby;
import org.jooby.RequestLogger;
import org.jooby.Status;
import org.jooby.apitool.ApiTool;
import org.jooby.ebean.Ebeanby;
import org.jooby.guava.GuavaCache;
import org.jooby.guava.GuavaSessionStore;
import org.jooby.handlers.CorsHandler;
import org.jooby.hbv.Hbv;
import org.jooby.jdbc.Jdbc;
import org.jooby.json.Jackson;
import org.jooby.mail.CommonsEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    {
        use("*", new CorsHandler());
        use("*", new RequestLogger().extended());
        use(new AppModule());
        use(new Jackson());
        use(new Jdbc());
        use(new Ebeanby());
        use(new Hbv());
        use(GuavaCache.newCache());
        session(GuavaSessionStore.class);
        use(new CommonsEmail());
        use(new ApiTool()
            .swagger(new ApiTool.Options("/swagger")
                .redoc()
            )
        );


        before("*", (req, res) -> {
          System.out.println(String.format("Path: %s", req));
        });

        before("api/v1/internal/**", (req, res) -> {
            ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);
            CacheService cache = require(CacheService.class);
            String token = "";
            try {
                token = req.header(SecurityConstants.HEADER_STRING).value();
            }catch (Exception e) {}

            User applicationUser = cache.getUser(token);
            if (StringUtils.isBlank(token) || applicationUser == null) {
                response.setCode(Message.UNAUTORIZED).setMessage(Message.UNAUTORIZED_MESSAGE);
                res.status(Status.UNAUTHORIZED).send(response);
            }

        });

        get("/", () -> "Movify web service");
        use(AuthController.class);
        use(MovieController.class);

        err((req, rsp, err) -> {
            Throwable cause = err.getCause();
            if (cause instanceof ConstraintViolationException) {
                Set<ConstraintViolation<?>> constraints = ((ConstraintViolationException) cause)
                        .getConstraintViolations();

                ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_INCOMPLETE_PARAM_MSG);
                rsp.header("Access-Control-Allow-Origin", "*");
                rsp.status(org.jooby.Status.OK);
                rsp.send(response.setMessage(constraints.stream().map(c -> c.getMessage()).collect(Collectors.joining(" \n "))));
            } else if (cause instanceof CustomException) {
                ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_INCOMPLETE_PARAM_MSG);
                rsp.header("Access-Control-Allow-Origin", "*");
                rsp.status(org.jooby.Status.OK);
                rsp.send(response.setMessage(cause.getMessage()));
            }else {
                ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);
                LOGGER.error("Error processing request", err);
                rsp.status(Status.OK);
                rsp.send(response);
            }
        });

    }

    public static void main(final String[] args) {
      run(App::new, args);
    }

}
