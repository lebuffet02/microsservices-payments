package api.pagamentos.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskUtils {

    private static final String REGEX_CPF = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
    private static final String REGEX_CEP = "(\\d{5})(\\d{3})";
    private static final String PATTERN_CPF = "$1.$2.$3-$4";
    private static final String PATTERN_CEP = "$1-$2";

    public static String maskCpf(String tipoCpf) {
        String cpf = clean(tipoCpf);
        return (cpf.length() == 11) ? pattern(cpf, REGEX_CPF, PATTERN_CPF) : null;
    }

    public static String maskCep(String tipoCep) {
        String cep = clean(tipoCep);
        return (cep.length() == 8) ? pattern(cep, REGEX_CEP, PATTERN_CEP) : null;
    }

    private static String pattern(String tipo, String regex, String tipoPattern) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tipo);
        return (matcher.find()) ? matcher.replaceAll(tipoPattern) : null;
    }

    private static String clean(String tipo) {
        return !tipo.isEmpty() ? tipo.replaceAll("\\s", "") : null;
    }
}
