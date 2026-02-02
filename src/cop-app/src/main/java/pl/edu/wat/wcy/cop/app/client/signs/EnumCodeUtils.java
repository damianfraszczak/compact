package pl.edu.wat.wcy.cop.app.client.signs;

import pl.edu.wat.wcy.cop.app.shared.domain.App6AMilitaryUnitDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
// Provides enum code utilities.

public class EnumCodeUtils {

    public static final int SIDE_INDEX = 1;

    public static String getCode(App6AMilitaryUnitDto object, String code) {
        return getApp6aUnitCode(object, code).toLowerCase();
    }

    public static String getFunctionIdCode(App6AMilitaryUnitDto object) {
        if (object.getCode() == null || object.getCode().isEmpty() || object.getCode().length() < 15)
            return null;
        else
            return object.getCode().substring(4, 10);
    }

    public static String getCodeFromSide(AffiliationEnumDto affEnum) {
        if (affEnum == null)
            return "-";
        if (affEnum.equals(AffiliationEnumDto.FRIEND))
            return "f";
        else if (affEnum.equals(AffiliationEnumDto.HOSTILE))
            return "h";
        else if (affEnum.equals(AffiliationEnumDto.NEUTRAL))
            return "n";
        else if (affEnum.equals(AffiliationEnumDto.UNKNOWN))
            return "u";
        return "-";
    }

    public static AffiliationEnumDto getSideForObjectCode(String code) {
        char affCode = code.charAt(SIDE_INDEX);
        if (affCode == 'f')
            return AffiliationEnumDto.FRIEND;
        if (affCode == 'h')
            return AffiliationEnumDto.HOSTILE;
        if (affCode == 'u')
            return AffiliationEnumDto.UNKNOWN;
        if (affCode == 'n')
            return AffiliationEnumDto.NEUTRAL;
        return AffiliationEnumDto.FRIEND;
    }

    public static String getCodeFromUnitSize(UnitSizeEnumDto unitEnum) {
        if (unitEnum == null)
            return "--";
        switch (unitEnum) {
            case ARMY:
                // return "fk";
                return "ek";
            case ARMYGROUP:
                // return "fl";
                return "el";
            case BATTALION:
                return "ef";
            case BRIGADE:
                // return "fh";
                return "eh";
            case COMPANY:
                return "ee";
            case CORPS:
                // return "fj";
                return "ej";
            case DIVISION:
                return "ei";
            case PLATOON:
                return "ed";
            case REGIMENTORGROUP:
                return "eg";
            case REGION:
                // return "fm";
                return "em";
            case SECTION:
                return "ec";
            case SQUAD:
                return "eb";
            case TASKFORCEORBATTLEGROUP:
                return "e-";
            // return "g-";
            case TEAMORCREW:
                return "ea";
            // return "ga";
            default:
                return "--";
        }

    }


    public static String getApp6aUnitCode(App6AMilitaryUnitDto unit, String functionCode) {
        StringBuffer app6aCode = new StringBuffer();
        try {
            app6aCode.append("s");
            app6aCode.append(getCodeFromSide(unit.getAffiliation()));
            app6aCode.append("gp");
            // 6zn
            app6aCode.append(functionCode);

            if (unit.isHeadquarters() == false)
                app6aCode.append(getCodeFromUnitSize(unit.getMobilitySize()));
            else
                app6aCode.append("a" + getCodeFromUnitSize(unit.getMobilitySize()).substring(1));
            app6aCode.append("---");
            if (app6aCode.length() < 15)
                app6aCode.append("-");
            return app6aCode.toString();
        } catch (NullPointerException ex) {
            return getApp6aUnitCode(unit, functionCode);
        }
    }
}
