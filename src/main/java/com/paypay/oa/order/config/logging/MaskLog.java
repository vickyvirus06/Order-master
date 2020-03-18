package com.paypay.oa.order.config.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name = "logmask", category = "Converter")
@ConverterKeys({"masked"})
public class MaskLog extends LogEventPatternConverter {
    // https://gomtiprabha.wordpress.com/2014/04/04/masking-sensitive-info-or-data-in-logs/
    public MaskLog(String[] options) {
        super("m", "m");
    }

    public static MaskLog newInstance(final String[] options) {
        return new MaskLog(options);
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder outputMsg) {
        String message = logEvent.getMessage().getFormat();

        // for Authorization or authorization...
        String regexMask1 = "uthorization=\\[.*?\\]";
        String log1 = message.replaceAll(regexMask1, "uthorization=[***]");
//
//        String regexMask2 = "\"password\":\".*?\"";
//        String log2 = log1.replaceAll(regexMask2, "\"password\":\"***\"");
//
//        String regexMask3 = "\"access_token\":\".*?\"";
//        String log3 = log2.replaceAll(regexMask3, "\"access_token\":\"***\"");
//
//        String regexMask4 = "\"accountNumber\":\".*?\"";
//        String log4 = log3.replaceAll(regexMask4, "\"accountNumber\":\"***\"");
//
//        String regexMask5 = "\"fileStream\":\".*?\"";
//        String log5 = log4.replaceAll(regexMask5, "\"fileStream\":\"... snipped ...\"");
//
//        String regexMask6 = "password=.*";
//        String log6 = log5.replaceAll(regexMask6, "password=***");

        String logFinal = log1;
        outputMsg.append(logFinal);
    }
}
