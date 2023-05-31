package com.marvin.util.web.bridge;

import java.util.HashSet;
import java.util.Set;

public class CacheBridge {

    private static final Set<String> canBeNotifiedMethods = new HashSet<>();

    private static boolean IS_NOT_INITIALED = false;

    public static void addAll(Set<String> set) {
        canBeNotifiedMethods.addAll(set);
    }

    public static Set<String> getNotifiedMethods() {
        IS_NOT_INITIALED = true;
        return canBeNotifiedMethods;
    }

    public static boolean init() {
        return IS_NOT_INITIALED;
    }
}
