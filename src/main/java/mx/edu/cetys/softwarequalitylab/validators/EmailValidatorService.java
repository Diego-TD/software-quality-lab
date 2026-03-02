package mx.edu.cetys.softwarequalitylab.validators;

import java.util.HashMap;
import java.util.Map;

public class EmailValidatorService {
    int MAX_EMAIL_LENGTH = 47;
    int MIN_DOMAIN_LENGTH = 1;
    int MAX_DOMAIN_LENGTH = 5;

    private final Map<Character, Boolean> userAllowed = new HashMap<>();
    private final Map<Character, Boolean> providerAllowed = new HashMap<>();
    private final Map<Character, Boolean> domainAllowed = new HashMap<>();

    public EmailValidatorService() {
        initializeBaseChars(userAllowed);
        initializeBaseChars(providerAllowed);
        initializeBaseChars(domainAllowed);

        // valid special user characters: .-_+
        userAllowed.put('.', true);
        userAllowed.put('-', true);
        userAllowed.put('_', true);
        userAllowed.put('+', true);

        // valid special provider/domain characters: .
        providerAllowed.put('.', true);
    }

    public boolean isValid(String email) {
        // 1) null / empty
        if (email == null || email.isEmpty()) {
            return false;
        }

        // 2) max length
        if (email.length() > MAX_EMAIL_LENGTH) {
            return false;
        }

        // 3) separator must be exactly one '#'
        int separatorIndex = email.indexOf('#');
        if (separatorIndex <= 0) {
            return false; // no # or starts with #
        }
        if (separatorIndex != email.lastIndexOf('#')) {
            return false; // more than one #
        }

        String userPart = email.substring(0, separatorIndex);
        String providerDomainPart = email.substring(separatorIndex + 1);

        if (providerDomainPart.isEmpty()) {
            return false;
        }

        // 4) split provider and domain using last '.'
        int lastDot = providerDomainPart.lastIndexOf('.');
        if (lastDot <= 0 || lastDot == providerDomainPart.length() - 1) {
            return false;
        }

        String providerPart = providerDomainPart.substring(0, lastDot);
        String domainPart = providerDomainPart.substring(lastDot + 1);

        // 5) domain length min/max
        if (domainPart.length() < MIN_DOMAIN_LENGTH || domainPart.length() > MAX_DOMAIN_LENGTH) {
            return false;
        }

        // 6) at least one '4' in domain
        if (!domainPart.contains("4")) {
            return false;
        }

        // 7) no diphthong (implemented as repeated consecutive vowels in user part: aa, ee, ii, oo, uu)
        if (hasDiphthong(userPart)) {
            return false;
        }

        // 8) valid characters by section
        if (!containsOnlyAllowed(userPart, userAllowed)) {
            return false;
        }
        if (!containsOnlyAllowed(providerPart, providerAllowed)) {
            return false;
        }
        if (!containsOnlyAllowed(domainPart, domainAllowed)) {
            return false;
        }

        return true;
    }

    private void initializeBaseChars(Map<Character, Boolean> allowed) {
        for (char c = 'a'; c <= 'z'; c++) {
            allowed.put(c, true);
        }
        for (char c = '0'; c <= '9'; c++) {
            allowed.put(c, true);
        }
    }

    private boolean containsOnlyAllowed(String value, Map<Character, Boolean> allowed) {
        for (int i = 0; i < value.length(); i++) {
            if (!allowed.containsKey(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDiphthong(String userPart) {
        for (int i = 0; i < userPart.length() - 1; i++) {
            char current = userPart.charAt(i);
            char next = userPart.charAt(i + 1);

            if (isVowel(current) && isVowel(next) && current == next) {
                return true;
            }
        }
        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
