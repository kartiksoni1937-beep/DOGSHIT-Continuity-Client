/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.utils.EncryptedString;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.glfw.GLFW;

public final class KeyUtils {
    public static CharSequence getKey(int key) {
        switch (key) {
            case 2: {
                return EncryptedString.of("M$~`M~`B");
            }
            case -1: {
                return EncryptedString.of("U-_.n~$$k\u00b7^_n^\u00b7|o`*w..n");
            }
            case 256: {
                return EncryptedString.of("E``*s_$^\u00b7c");
            }
            case 96: {
                return EncryptedString.of("G\u00ad$^r`.~a~.v^~e\u00ad~\u00ad^ |`_*A\u00b7^`c|~~c_.e*\u00adn|*~\u00b7t");
            }
            case 161: {
                return EncryptedString.of("W^-o`-.~r\u00ad$l\u00ad^d^|\u00b7 *~|~1");
            }
            case 162: {
                return EncryptedString.of("W\u00b7$--o`\u00ad~r~-..l~\u00b7-`d_\u00ad $~`^2");
            }
            case 283: {
                return EncryptedString.of("P_-`.r_\u00adi\u00ad\u00ad*n|-^t^\u00b7. \u00ad-S|*$\u00adc_*\u00b7`r~\u00b7e^$~e~|$n");
            }
            case 284: {
                return EncryptedString.of("P\u00ad_$|a.`~|u\u00b7|`s|`_e");
            }
            case 260: {
                return EncryptedString.of("I`*n^*$*s*\u00ad`|e$-_`r|_$t");
            }
            case 261: {
                return EncryptedString.of("D$\u00ade`|l||e``^t\u00b7_**e");
            }
            case 268: {
                return EncryptedString.of("H._^o-\u00b7m\u00ad\u00ad^*e");
            }
            case 266: {
                return EncryptedString.of("P_\u00ad\u00b7\u00b7a`~g~`e\u00ad\u00ad- `--U*_p");
            }
            case 267: {
                return EncryptedString.of("P-\u00ad`a_$g\u00b7_e\u00b7_^\u00ad ~$*D\u00ad|o-$`^w*^n");
            }
            case 269: {
                return EncryptedString.of("E`*\u00ad_n$*\u00ad^d");
            }
            case 258: {
                return EncryptedString.of("T..\u00b7a~.\u00ad*b");
            }
            case 341: {
                return EncryptedString.of("L_-$e-^f^\u00adt$~*` ~~*|C`.o$~\u00b7$n~.t~-\u00adr\u00b7.o^\u00ad\u00b7\u00adl");
            }
            case 345: {
                return EncryptedString.of("R||$-i-$\u00b7g\u00b7|\u00ad|h~\u00adt$$$~ **``C\u00ad\u00ado~^^n$\u00b7t.|r`$^\u00b7o^\u00b7|-l");
            }
            case 342: {
                return EncryptedString.of("L|\u00b7e.\u00ad.|f-*.t~^_$ *$A$|-l~`$~t");
            }
            case 346: {
                return EncryptedString.of("R_*i\u00ad^\u00b7g\u00b7`h|`|t-.$\u00ad |\u00b7\u00b7`A|`l`~`*t");
            }
            case 340: {
                return EncryptedString.of("L-~e\u00ad-*f_.t\u00b7-~$ \u00b7\u00b7S`*$.h\u00b7\u00b7~i`_._f-`$`t");
            }
            case 344: {
                return EncryptedString.of("R\u00ad~.|i$*^g_.h^$.^t_|$* _`$S***$h\u00b7\u00b7i$^-\u00b7f\u00ad^t");
            }
            case 265: {
                return EncryptedString.of("A`_~_r^\u00b7r.\u00ad`o-\u00b7`w.`_ ~~|U$*$p");
            }
            case 264: {
                return EncryptedString.of("A`~\u00b7*r\u00ad`_$r|*o.\u00ad\u00ad`w$\u00ad\u00ad |^^D``o\u00b7\u00b7^`w-$-n");
            }
            case 263: {
                return EncryptedString.of("A|$r^`_|r*|`\u00ado.-\u00b7$w$|\u00b7` |$`\u00b7L\u00ad*\u00ad.e~-f^\u00ad.t");
            }
            case 262: {
                return EncryptedString.of("A.$^\u00adr\u00b7`*\u00b7r-\u00b7o$.-^w$^ ||_R**|i.$g$`|h$\u00ad*t");
            }
            case 39: {
                return EncryptedString.of("A$.\u00b7p_$o`._s`**_t\u00b7-r-|^o|_p*$^h^\u00b7$e");
            }
            case 259: {
                return EncryptedString.of("B_~|~a*$~`c_|k|-_`s$*p-~$a$**^c|\u00b7*e");
            }
            case 280: {
                return EncryptedString.of("C~|^\u00ada`|_p-\u00b7\u00b7\u00b7s|..^ $.\u00b7_L*_o~.c-|_k");
            }
            case 348: {
                return EncryptedString.of("M|||\u00ade|*|~n\u00ad|u");
            }
            case 343: {
                return EncryptedString.of("L*\u00ade*~*f``\u00adt`_ _-S$-_u|-|~p$^^e^$r");
            }
            case 347: {
                return EncryptedString.of("R-\u00b7i^..g`_h-|t__.\u00ad ~\u00b7|S\u00ad.\u00adu`^_`p.~|e$^*r");
            }
            case 257: {
                return EncryptedString.of("E-_\u00b7n\u00ad`|\u00adt_~$-e$-\u00adr");
            }
            case 335: {
                return EncryptedString.of("N|\u00adu.^m*$-p-\u00b7\u00b7*a^`d^\u00ad |`\u00b7\u00b7E$*\u00b7~n^\u00ad~|t\u00b7\u00b7.e*.^r");
            }
            case 282: {
                return EncryptedString.of("N..*u^`.m^**_ \u00ad^-`L.\u00b7`o\u00ad\u00adc$.k");
            }
            case 32: {
                return EncryptedString.of("S\u00b7-*\u00adp|*_a^.~c$*$\u00b7e");
            }
            case 290: {
                return EncryptedString.of("F~~1");
            }
            case 291: {
                return EncryptedString.of("F-|^2");
            }
            case 292: {
                return EncryptedString.of("F^\u00ad_3");
            }
            case 293: {
                return EncryptedString.of("F$..^4");
            }
            case 294: {
                return EncryptedString.of("F-\u00ad.5");
            }
            case 295: {
                return EncryptedString.of("F~$\u00b7.6");
            }
            case 296: {
                return EncryptedString.of("F^_-_7");
            }
            case 297: {
                return EncryptedString.of("F\u00ad$$8");
            }
            case 298: {
                return EncryptedString.of("F\u00ad*\u00b79");
            }
            case 299: {
                return EncryptedString.of("F_~|1^_0");
            }
            case 300: {
                return EncryptedString.of("F$~.*1.$\u00ad\u00b71");
            }
            case 301: {
                return EncryptedString.of("F._1_-2");
            }
            case 302: {
                return EncryptedString.of("F_`_^1`$_$3");
            }
            case 303: {
                return EncryptedString.of("F.*1\u00b7\u00ad.|4");
            }
            case 304: {
                return EncryptedString.of("F`$\u00b7\u00ad1$_~5");
            }
            case 305: {
                return EncryptedString.of("F.~-1`^.6");
            }
            case 306: {
                return EncryptedString.of("F$-1._`7");
            }
            case 307: {
                return EncryptedString.of("F*|1``8");
            }
            case 308: {
                return EncryptedString.of("F*\u00b71~$9");
            }
            case 309: {
                return EncryptedString.of("F-_2-.|*0");
            }
            case 310: {
                return EncryptedString.of("F\u00ad-2*-|~1");
            }
            case 311: {
                return EncryptedString.of("F$\u00ad\u00ad2`_~2");
            }
            case 312: {
                return EncryptedString.of("F$~^2~*3");
            }
            case 313: {
                return EncryptedString.of("F$.2\u00b7*\u00ad$4");
            }
            case 314: {
                return EncryptedString.of("F.\u00ad-2^.\u00b75");
            }
            case 281: {
                return EncryptedString.of("S$_$c$_^r$\u00b7o|*~l_-l~^\u00ad ._~L|$\u00b7o`^_c`\u00b7~|k");
            }
            case 91: {
                return EncryptedString.of("L^-\u00ade\u00ad\u00b7~f..t\u00b7`^` $\u00ad_~B*^`r_\u00ad^~a^~~*c\u00b7.^k\u00ad~e.|.t");
            }
            case 93: {
                return EncryptedString.of("R$$i\u00ad_g*`*~h\u00b7~$t_~$$ *^B^**|r`|a_*~c_**^k.\u00b7\u00ade$\u00ad_t");
            }
            case 59: {
                return EncryptedString.of("S|\u00ade..`~m|^*.i``~\u00adc_\u00ad`o-\u00ad\u00adl_\u00ad|o|.~n");
            }
            case 61: {
                return EncryptedString.of("E..*|q|\u00ad|\u00b7u`|a\u00b7`l|.s");
            }
            case 92: {
                return EncryptedString.of("B$-.$a\u00ad\u00ad*c.**k^~^-s_$^l\u00b7|a_^^s^$h");
            }
            case 44: {
                return EncryptedString.of("C$^_|o\u00b7\u00b7|m`*-*m~.\u00b7*a");
            }
            case 0: {
                return EncryptedString.of("L_|_-M`.*_B");
            }
            case 1: {
                return EncryptedString.of("R~~_M_\u00ad\u00b7B");
            }
        }
        String keyName = GLFW.glfwGetKeyName((int)key, (int)0);
        if (keyName == null) {
            return EncryptedString.of("N`_.-o--n\u00b7*|e");
        }
        return StringUtils.capitalize((String)keyName);
    }

    public static boolean isKeyPressed(int keyCode) {
        if (keyCode <= 8) {
            return GLFW.glfwGetMouseButton((long)Sweto.mc.method_22683().method_4490(), (int)keyCode) == 1;
        }
        return GLFW.glfwGetKey((long)Sweto.mc.method_22683().method_4490(), (int)keyCode) == 1;
    }
}

