package com.mycompany.app;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public interface ReaderInterface {

    String DELIMITER = ";";
    Charset CHARSET = StandardCharsets.UTF_8;

    List<List<String>> read(Path path);
}
