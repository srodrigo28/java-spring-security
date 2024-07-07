package br.com.treinaweb.twprojects.core.utils;

public class StringUtils {
    
    public static String formatPhone(String phone) {
        return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }

    public static String cleanPhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }

    public static String cleanCpf(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    public static String formatCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String cleanZipCode(String zipCode) {
        return zipCode.replaceAll("[^0-9]", "");
    }

    public static String formatZipCode(String zipCode) {
        return zipCode.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

}
