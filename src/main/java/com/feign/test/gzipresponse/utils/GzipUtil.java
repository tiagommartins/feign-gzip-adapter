package com.feign.test.gzipresponse.utils;

import com.google.common.io.CharStreams;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import static org.apache.commons.lang.ArrayUtils.isEmpty;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class GzipUtil {

    public static String unzip(final byte[] compressed) {
        notNull(compressed, "Não é possível descompactar objeto nulo.");
        isTrue(!isEmpty(compressed), "Não é possível descompactar objeto em branco.");

        return isZipped(compressed) ? reader(compressed) : new String(compressed);
    }

    private static String reader(byte[] compressed) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed)) {
            return reader(byteArrayInputStream);
        } catch(IOException e) {
            throw new RuntimeException("Falha ao descompactar objeto.", e);
        }
    }

    private static String reader(ByteArrayInputStream byteArrayInputStream) throws IOException {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
            return reader(gzipInputStream);
        }
    }

    private static String reader(GZIPInputStream gzipInputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
            return CharStreams.toString(inputStreamReader);
        }
    }

    private static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }

}
