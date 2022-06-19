package com.axis.minagri.security.boundary;

import com.axis.minagri.security.control.OnRegistrationCompleteEvent;
import com.axis.minagri.security.control.UserService;
import com.axis.minagri.security.entity.User;
import com.axis.minagri.security.entity.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

 /*   @Autowired
    private JavaMailSender mailSender;*/

/*    @Autowired
    private ApplicationEventPublisher eventPublisher;*/

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public RegistrationController() {
        super();
    }

    // Registration

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView registerUserAccount(@Valid final UserDTO userDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", userDto);

        final User registered = userService.registerNewUserAccount(userDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));

        //request.login("coucou", "coucoupasss");
        authWithoutPassword(registered);
        return new ModelAndView("successRegister", "user", userDto);
        //return "redirect:/login";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }



    // Reset password
    /*@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            //mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }*/

 /*   @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final String result = securityUserService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }
*/
/*    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
    }*/

    // change user password
  /*  @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
    }*/

  /*  @RequestMapping(value = "/user/update/2fa", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse modifyUser2FA(@RequestParam("use2FA") final boolean use2FA) throws UnsupportedEncodingException {
        final User user = userService.updateUser2FA(use2FA);
        if (use2FA) {
            return new GenericResponse(userService.generateQRUrl(user));
        }
        return null;
    }
*/
    // ============== NON-API ============

    public void authWithoutPassword(User user) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin");
        user.getAuthorities().add(simpleGrantedAuthority);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,  user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
