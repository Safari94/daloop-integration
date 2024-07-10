package com.klc.daloopintegration.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemsDescription {

    public static final String MANY_OFFLINES = "Eeste posto teve offline mais de 4 vezes nas últimas 24 horas. Confirma o estado deste posto em https://app.klcmobility.pt/ e  em OPC  https://opc.klcmobility.pt/. Recomendamos um reset remoto ao posto. Caso o problema persista pode ser necessário ir ao local";
    public static final String TWO_MIN_SESSION = "ste posto teve sessões de carga que terminaram em menos de 2 minutos. Confirma o estado deste posto em https://app.klcmobility.pt/ e  em OPC  https://opc.klcmobility.pt/. Recomendamos um reset remoto e verificar se há cargas em curso. Verificar também os valores e dados da ultima preventiva pode ser importante. Caso o problema persista deve haver uma visita ao local.";

}
