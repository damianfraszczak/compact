/**
 *
 */
package pl.edu.wat.wcy.cop.services;

import org.springframework.data.domain.AuditorAware;
// Represents username auditor aware.

public class UsernameAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // if (authentication == null || !authentication.isAuthenticated()) {
        // return null;
        // }
        // return ((UserContext) authentication.getPrincipal()).getUsername();
        return "damian.fraszczak@wat.edu.pl";
    }
}
